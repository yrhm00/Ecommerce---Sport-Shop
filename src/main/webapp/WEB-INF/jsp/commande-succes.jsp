<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="include/importTags.jsp" %>
<div class="container py-5 text-center">
    <div class="alert alert-success p-5">
        <h1 class="display-4"><spring:message code="success.title"/></h1>
        <p class="lead mt-3"><spring:message code="success.message"/></p>
        <p class="mb-4"><spring:message code="success.paypal.valid"/></p>
        <hr>
        <a href="<spring:url value='/produits'/>" class="btn btn-primary mt-3"><spring:message code="success.back"/></a>
    </div>
</div>
