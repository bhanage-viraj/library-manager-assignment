<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><c:choose><c:when test="${editMode}">Update Book</c:when><c:otherwise>Add Book</c:otherwise></c:choose> | Library Manager</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>
<%@ include file="../fragments/navigation.jspf" %>

<main class="page-shell form-shell">
    <section class="page-heading">
        <div>
            <p class="eyebrow"><c:choose><c:when test="${editMode}">Update Operation</c:when><c:otherwise>Create Operation</c:otherwise></c:choose></p>
            <h1><c:choose><c:when test="${editMode}">Update Book</c:when><c:otherwise>Add Book</c:otherwise></c:choose></h1>
        </div>
        <a class="button" href="${pageContext.request.contextPath}/books">Back to Books</a>
    </section>

    <section class="content-section">
        <form:form cssClass="entity-form" method="post" modelAttribute="book" action="${pageContext.request.contextPath}${formAction}">
            <form:errors path="*" cssClass="form-summary" element="div"/>

            <div class="field-grid">
                <label>
                    <span>Title</span>
                    <form:input path="title" maxlength="160"/>
                    <form:errors path="title" cssClass="field-error"/>
                </label>

                <label>
                    <span>ISBN</span>
                    <form:input path="isbn" maxlength="24"/>
                    <form:errors path="isbn" cssClass="field-error"/>
                </label>

                <label>
                    <span>Genre</span>
                    <form:input path="genre" maxlength="80"/>
                    <form:errors path="genre" cssClass="field-error"/>
                </label>

                <label>
                    <span>Publication Year</span>
                    <form:input path="publicationYear" type="number" min="1450" max="2100"/>
                    <form:errors path="publicationYear" cssClass="field-error"/>
                </label>

                <label>
                    <span>Price</span>
                    <form:input path="price" type="number" min="0" step="0.01"/>
                    <form:errors path="price" cssClass="field-error"/>
                </label>

                <label>
                    <span>Author</span>
                    <form:select path="author.id">
                        <form:option value="" label="Select an author"/>
                        <form:options items="${authors}" itemValue="id" itemLabel="name"/>
                    </form:select>
                    <form:errors path="author.id" cssClass="field-error"/>
                </label>
            </div>

            <div class="form-actions">
                <button class="button primary" type="submit">
                    <c:choose><c:when test="${editMode}">Save Changes</c:when><c:otherwise>Create Book</c:otherwise></c:choose>
                </button>
                <a class="button" href="${pageContext.request.contextPath}/books">Cancel</a>
            </div>
        </form:form>
    </section>
</main>
</body>
</html>

