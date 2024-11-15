import React, { useState, useEffect, useRef } from 'react';
import ClayForm, { ClayInput } from '@clayui/form';
import ClayButton from '@clayui/button';
import ClayLayout from '@clayui/layout';
import ClayAlert from '@clayui/alert';
import ClayIcon from '@clayui/icon';
import ClayCard from '@clayui/card';
import ReCAPTCHA from 'react-google-recaptcha';
import axios from 'axios';

const RegistrationForm = () => {
  const [formData, setFormData] = useState({
    name: '',
    surname: '',
    dateOfBirth: '',
    email: '',
  });

  const [touched, setTouched] = useState({
    name: false,
    surname: false,
    dateOfBirth: false,
    email: false,
  });

  const [captchaVerified, setCaptchaVerified] = useState(false);
  const [cpatchaValue, setCaptchaValue] = useState("")
  const [errors, setErrors] = useState({});
  const [submitted, setSubmitted] = useState(false);
  const [error, setError] = useState(null);
  const [isFormValid, setIsFormValid] = useState(false);
  const recaptchaRef = useRef(null);

  useEffect(() => {
    const validate = () => {
      const errors = {};
      if (formData.name.length < 3) errors.name = 'Name must be at least 3 characters.';
      if (formData.surname.length < 3) errors.surname = 'Surname must be at least 3 characters.';
      if (!formData.dateOfBirth) {
        errors.dateOfBirth = 'Date of Birth is required.';
      } else if (new Date(formData.dateOfBirth) > new Date()) {
        errors.dateOfBirth = 'Date of Birth cannot be in the future.';
      }
      const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
      if (!emailPattern.test(formData.email)) errors.email = 'Invalid email address.';
      setErrors(errors);
      return Object.keys(errors).length === 0;
    };

    setIsFormValid(validate() && captchaVerified);
  }, [formData, captchaVerified]);

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleBlur = (e) => {
    const { name } = e.target;
    setTouched({ ...touched, [name]: true });
  };

  const handleCaptchaChange = (value) => {
    setCaptchaValue(value);
    setCaptchaVerified(!!value);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    recaptchaRef.current.reset();
    if (!isFormValid) return;

    try {
      const response = await axios.post(
        '/o/registrations-rest/registrations',
        {
          ...formData,
          recaptcha: cpatchaValue
        },
        { headers: { 'Content-Type': 'application/json' } }
      );
      if (response.status === 201) {
        setSubmitted(true);
        setError(null);
      }
    } catch (err) {
      console.error('Error submitting registration:', err);
      setSubmitted(true);
      setError(`Failed to submit registration: ${err?.response?.data || err.message}`);
    }
  };

  return (
    <ClayLayout.ContainerFluid>
      {error && <ClayAlert displayType="danger">{error}</ClayAlert>}
      {submitted && !error ? (
        <>
          <ClayAlert displayType="success">Registration submitted successfully!</ClayAlert>
          <ClayCard>
            <ClayCard.Body>
              <ClayCard.Description displayType="title">Registration Recap</ClayCard.Description>
              <ClayCard.Description displayType="text">
                <strong>Name:</strong> {formData.name}
              </ClayCard.Description>
              <ClayCard.Description displayType="text">
                <strong>Surname:</strong> {formData.surname}
              </ClayCard.Description>
              <ClayCard.Description displayType="text">
                <strong>Date of Birth:</strong> {formData.dateOfBirth}
              </ClayCard.Description>
              <ClayCard.Description displayType="text">
                <strong>Email:</strong> {formData.email}
              </ClayCard.Description>
            </ClayCard.Body>
          </ClayCard>
        </>
      ) : (
        <>
          <h2>Registration Form</h2>
          <ClayForm onSubmit={handleSubmit}>
            <ClayForm.Group className={touched.name && errors.name ? 'has-error' : touched.name ? 'has-success' : ''}>
              <label htmlFor="name">Name</label>
              <ClayInput
                type="text"
                id="name"
                name="name"
                value={formData.name}
                onChange={handleInputChange}
                onBlur={handleBlur}
                required
              />
              {touched.name && errors.name && (
                <ClayForm.FeedbackGroup>
                  <ClayForm.FeedbackItem>
                    <ClayIcon symbol="exclamation-full" /> {errors.name}
                  </ClayForm.FeedbackItem>
                </ClayForm.FeedbackGroup>
              )}
            </ClayForm.Group>

            <ClayForm.Group className={touched.surname && errors.surname ? 'has-error' : touched.surname ? 'has-success' : ''}>
              <label htmlFor="surname">Surname</label>
              <ClayInput
                type="text"
                id="surname"
                name="surname"
                value={formData.surname}
                onChange={handleInputChange}
                onBlur={handleBlur}
                required
              />
              {touched.surname && errors.surname && (
                <ClayForm.FeedbackGroup>
                  <ClayForm.FeedbackItem>
                    <ClayIcon symbol="exclamation-full" /> {errors.surname}
                  </ClayForm.FeedbackItem>
                </ClayForm.FeedbackGroup>
              )}
            </ClayForm.Group>

            <ClayForm.Group className={touched.dateOfBirth && errors.dateOfBirth ? 'has-error' : touched.dateOfBirth ? 'has-success' : ''}>
              <label htmlFor="dateOfBirth">Date of Birth</label>
              <ClayInput
                type="date"
                id="dateOfBirth"
                name="dateOfBirth"
                value={formData.dateOfBirth}
                onChange={handleInputChange}
                onBlur={handleBlur}
                required
              />
              {touched.dateOfBirth && errors.dateOfBirth && (
                <ClayForm.FeedbackGroup>
                  <ClayForm.FeedbackItem>
                    <ClayIcon symbol="exclamation-full" /> {errors.dateOfBirth}
                  </ClayForm.FeedbackItem>
                </ClayForm.FeedbackGroup>
              )}
            </ClayForm.Group>

            <ClayForm.Group className={touched.email && errors.email ? 'has-error' : touched.email ? 'has-success' : ''}>
              <label htmlFor="email">Email</label>
              <ClayInput
                type="email"
                id="email"
                name="email"
                value={formData.email}
                onChange={handleInputChange}
                onBlur={handleBlur}
                required
              />
              {touched.email && errors.email && (
                <ClayForm.FeedbackGroup>
                  <ClayForm.FeedbackItem>
                    <ClayIcon symbol="exclamation-full" /> {errors.email}
                  </ClayForm.FeedbackItem>
                </ClayForm.FeedbackGroup>
              )}
            </ClayForm.Group>

            <ClayForm.Group>
              <ReCAPTCHA ref={recaptchaRef} sitekey="YOUR_RECAPTCHA_SITE_KEY" onChange={handleCaptchaChange} />
            </ClayForm.Group>

            <ClayButton type="submit" displayType="primary" disabled={!isFormValid}>
              Register
            </ClayButton>
          </ClayForm>
        </>
      )}
    </ClayLayout.ContainerFluid>
  );
};

export default RegistrationForm;
