<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Books | Library Manager</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>
<%@ include file="../fragments/navigation.jspf" %>

<main class="page-shell">
    <section class="page-heading">
        <div>
            <p class="eyebrow">Read Operation</p>
            <h1>Books and Authors</h1>
        </div>
        <a class="button primary" href="${pageContext.request.contextPath}/books/new">Add Book</a>
    </section>

    <c:if test="${not empty successMessage}">
        <div class="alert success">${successMessage}</div>
    </c:if>

    <section class="content-section">
        <div class="section-heading">
            <h2>Book Catalog</h2>
            <span>${bookAuthorViews.size()} joined rows</span>
        </div>
        <div class="table-wrap">
            <table>
                <thead>
                <tr>
                    <th>Title</th>
                    <th>ISBN</th>
                    <th>Genre</th>
                    <th>Year</th>
                    <th>Price</th>
                    <th>Author</th>
                    <th>Email</th>
                    <th>Action</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="row" items="${bookAuthorViews}">
                    <tr>
                        <td>${row.bookTitle}</td>
                        <td>${row.isbn}</td>
                        <td>${row.genre}</td>
                        <td>${row.publicationYear}</td>
                        <td><fmt:formatNumber value="${row.price}" type="currency"/></td>
                        <td>${row.authorName}</td>
                        <td>${row.authorEmail}</td>
                        <td>
                            <a class="button compact" href="${pageContext.request.contextPath}/books/${row.bookId}/edit">Edit</a>
                        </td>
                    </tr>
                </c:forEach>
                <c:if test="${empty bookAuthorViews}">
                    <tr>
                        <td colspan="8" class="empty-cell">No books found.</td>
                    </tr>
                </c:if>
                </tbody>
            </table>
        </div>
        <p class="caption">
            This table is populated by a repository JPQL inner join between books and authors.
        </p>
    </section>
</main>
</body>
</html>

