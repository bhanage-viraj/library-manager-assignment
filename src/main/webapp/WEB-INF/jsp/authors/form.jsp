<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><c:choose><c:when test="${editMode}">Update Author</c:when><c:otherwise>Add Author</c:otherwise></c:choose> | Library Manager</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>
<%@ include file="../fragments/navigation.jspf" %>

<main class="page-shell form-shell">
    <section class="page-heading">
        <div>
            <p class="eyebrow"><c:choose><c:when test="${editMode}">Update Operation</c:when><c:otherwise>Create Operation</c:otherwise></c:choose></p>
            <h1><c:choose><c:when test="${editMode}">Update Author</c:when><c:otherwise>Add Author</c:otherwise></c:choose></h1>
        </div>
        <a class="button" href="${pageContext.request.contextPath}/authors">Back to Authors</a>
    </section>

    <section class="content-section">
        <form:form cssClass="entity-form" method="post" modelAttribute="author" action="${pageContext.request.contextPath}${formAction}">
            <form:errors path="*" cssClass="form-summary" element="div"/>

            <div class="field-grid">
                <label>
                    <span>Name</span>
                    <form:input path="name" maxlength="120"/>
                    <form:errors path="name" cssClass="field-error"/>
                </label>

                <label>
                    <span>Email</span>
                    <form:input path="email" type="email" maxlength="160"/>
                    <form:errors path="email" cssClass="field-error"/>
                </label>

                <label>
                    <span>Country</span>
                    <form:input path="country" maxlength="80"/>
                    <form:errors path="country" cssClass="field-error"/>
                </label>

                <label class="field-wide">
                    <span>Biography</span>
                    <form:textarea path="biography" rows="5" maxlength="500"/>
                    <form:errors path="biography" cssClass="field-error"/>
                </label>
            </div>

            <div class="form-actions">
                <button class="button primary" type="submit">
                    <c:choose><c:when test="${editMode}">Save Changes</c:when><c:otherwise>Create Author</c:otherwise></c:choose>
                </button>
                <a class="button" href="${pageContext.request.contextPath}/authors">Cancel</a>
            </div>
        </form:form>
    </section>
</main>
</body>
</html>

