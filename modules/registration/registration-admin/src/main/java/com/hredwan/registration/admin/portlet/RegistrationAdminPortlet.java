package com.hredwan.registration.admin.portlet;

import com.hredwan.registration.admin.constants.RegistrationAdminPortletKeys;

import com.hredwan.registration.model.Registration;
import com.hredwan.registration.service.RegistrationLocalService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.io.IOException;
import java.util.List;

/**
 * @author redwan
 */
@Component(
	property = {
			"com.liferay.portlet.display-category=category.hidden",
			"com.liferay.portlet.instanceable=false",
			"javax.portlet.display-name="+RegistrationAdminPortletKeys.REGISTRATIONADMIN,
			"javax.portlet.init-param.template-path=/",
			"javax.portlet.init-param.view-template=/view.jsp",
			"javax.portlet.name=" + RegistrationAdminPortletKeys.REGISTRATIONADMIN,
			"javax.portlet.resource-bundle=content.Language",
			"javax.portlet.security-role-ref=power-user,user,administrator",
	},
	service = Portlet.class
)
public class RegistrationAdminPortlet extends MVCPortlet {
	private static final Log _log = LogFactoryUtil.getLog(RegistrationAdminPortlet.class);

	@Reference
	private RegistrationLocalService _registrationLocalService;

	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {

		int registrationsCount = _registrationLocalService.getRegistrationsCount();

		List<Registration> registrations = _registrationLocalService.getRegistrations(0, 50);

		renderRequest.setAttribute("registrations", registrations);
		renderRequest.setAttribute("count", registrationsCount);
		super.doView(renderRequest, renderResponse);
	}
}