<%@ include file="include/importTags.jsp" %>
<div class="p-5 mb-4 bg-light rounded-3">
    <div class="container-fluid py-5">
        <h1 class="display-5 fw-bold"><spring:message code="home.welcome.title"/></h1>
        <p class="col-md-8 fs-4"><spring:message code="home.welcome.text"/></p>
        <button class="btn btn-primary btn-lg" type="button" onclick="window.location.href='<spring:url value="/produits"/>'"><spring:message code="home.btn.catalog"/></button>
    </div>
</div>

<div class="row align-items-md-stretch">
    <div class="col-md-6">
        <div class="h-100 p-5 text-white bg-dark rounded-3">
            <h2><spring:message code="home.news.title"/></h2>
            <p><spring:message code="home.news.text"/></p>
            <button class="btn btn-outline-light" type="button"><spring:message code="home.news.btn"/></button>
        </div>
    </div>
    <div class="col-md-6">
        <div class="h-100 p-5 bg-light border rounded-3">
            <h2><spring:message code="home.promo.title"/></h2>
            <p><spring:message code="home.promo.text"/></p>
            <button class="btn btn-outline-secondary" type="button"><spring:message code="home.promo.btn"/></button>
        </div>
    </div>
</div>

