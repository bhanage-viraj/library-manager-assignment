<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Authors | Library Manager</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>
<%@ include file="../fragments/navigation.jspf" %>

<main class="page-shell">
    <section class="page-heading">
        <div>
            <p class="eyebrow">Read Operation</p>
            <h1>Authors</h1>
        </div>
        <a class="button primary" href="${pageContext.request.contextPath}/authors/new">Add Author</a>
    </section>

    <c:if test="${not empty successMessage}">
        <div class="alert success">${successMessage}</div>
    </c:if>

    <section class="content-section">
        <div class="section-heading">
            <h2>Author Directory</h2>
            <span>${authors.size()} rows</span>
        </div>
        <div class="table-wrap">
            <table>
                <thead>
                <tr>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Country</th>
                    <th>Biography</th>
                    <th>Action</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="author" items="${authors}">
                    <tr>
                        <td>${author.name}</td>
                        <td>${author.email}</td>
                        <td>${author.country}</td>
                        <td>${author.biography}</td>
                        <td>
                            <a class="button compact" href="${pageContext.request.contextPath}/authors/${author.id}/edit">Edit</a>
                        </td>
                    </tr>
                </c:forEach>
                <c:if test="${empty authors}">
                    <tr>
                        <td colspan="5" class="empty-cell">No authors found.</td>
                    </tr>
                </c:if>
                </tbody>
            </table>
        </div>
    </section>
</main>
</body>
</html>

