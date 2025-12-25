<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="include/importTags.jsp" %>
<div class="container mt-4">
    <nav aria-label="breadcrumb">
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="<spring:url value='/'/>"><spring:message code="breadcrumbs.home"/></a></li>
            <li class="breadcrumb-item"><a href="<spring:url value='/produits'/>"><spring:message code="breadcrumbs.catalog"/></a></li>
            <li class="breadcrumb-item active">${category.nom}</li>
        </ol>
    </nav>
    
    <h1>${category.nom}</h1>
    
    <div class="row">
        <c:forEach items="${products}" var="product">
            <div class="col-md-4 mb-4">
                <div class="card h-100 shadow-sm border-0">
                    <c:if test="${product.originalPrice != null}">
                        <div class="position-absolute top-0 end-0 m-2">
                            <span class="badge bg-danger fs-6">-10%</span>
                        </div>
                    </c:if>
                    <c:if test="${product.newArrival}">
                        <div class="position-absolute top-0 start-0 m-2">
                            <span class="badge bg-success fs-6">NOUVEAU</span>
                        </div>
                    </c:if>
                    <div class="d-flex align-items-center justify-content-center bg-white p-3 rounded-top" style="height: 250px;">
                        <img src="<spring:url value='${product.imageUrl}'/>" class="img-fluid" alt="${product.nom}" style="max-height: 100%; max-width: 100%; object-fit: contain;">
                    </div>
                    <div class="card-body">
                        <h5 class="card-title fw-bold">${product.nom}</h5>
                        <p class="card-text">${product.description}</p>
                        <c:choose>
                            <c:when test="${product.originalPrice != null}">
                                <p class="mb-1">
                                    <span class="h4 text-danger"><fmt:formatNumber value="${product.prix}" type="currency" currencySymbol="€" minFractionDigits="2" /></span>
                                    <span class="text-muted text-decoration-line-through ms-2"><fmt:formatNumber value="${product.originalPrice}" type="currency" currencySymbol="€" minFractionDigits="2" /></span>
                                </p>
                            </c:when>
                            <c:otherwise>
                                <p class="h4 text-primary"><fmt:formatNumber value="${product.prix}" type="currency" currencySymbol="€" minFractionDigits="2" /></p>
                            </c:otherwise>
                        </c:choose>
                        <p class="text-muted"><spring:message code="product.stock"/> ${product.stock}</p>
                    </div>
                    <div class="card-footer text-center">
                        <a href="<spring:url value='/produits/${product.id}'/>" class="btn btn-primary w-100"><spring:message code="product.btn.details"/></a>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
    
    <c:if test="${empty products}">
        <div class="alert alert-info"><spring:message code="category.empty"/></div>
    </c:if>
</div>

