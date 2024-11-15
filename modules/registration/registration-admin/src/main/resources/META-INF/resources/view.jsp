<%@ include file="/init.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page import="com.hredwan.registration.model.Registration" %>
<%@ page import="java.util.List" %>

<html>
<head>
    <title>Registration Admin</title>
    <link rel="stylesheet" href="<%= themeDisplay.getPathThemeCss() %>/clay.css" />
</head>
<body>
    <h1>Registration Admin Panel</h1>

    <c:if test="${not empty registrations}">
        <p>${count}   Registrations<p>
        <!-- Table Start -->
        <table class="table">
            <thead>
                <tr class="clay-table-head">
                    <th class="clay-table-cell">Registration ID</th>
                    <th class="clay-table-cell">Name</th>
                    <th class="clay-table-cell">Surname</th>
                    <th class="clay-table-cell">Date of Birth</th>
                    <th class="clay-table-cell">Email</th>
                    <th class="clay-table-cell">Registration Date</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="registration" items="${registrations}">
                    <tr class="clay-table-row">
                        <td class="clay-table-cell">${registration.registrationId}</td>
                        <td class="clay-table-cell">${registration.name}</td>
                        <td class="clay-table-cell">${registration.surname}</td>
                        <td class="clay-table-cell">${registration.dateOfBirth}</td>
                        <td class="clay-table-cell">${registration.email}</td>
                        <td class="clay-table-cell">${registration.registrationDate}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <!-- Table End -->
    </c:if>

    <c:if test="${empty registrations}">
        <p>No registrations found.</p>
    </c:if>

</body>
</html>
