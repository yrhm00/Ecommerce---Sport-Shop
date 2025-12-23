<%@ include file="include/importTags.jsp" %>
<div class="container mt-4">
    <nav aria-label="breadcrumb">
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="<spring:url value='/'/>">Accueil</a></li>
            <li class="breadcrumb-item"><a href="<spring:url value='/produits'/>">Catalogue</a></li>
            <li class="breadcrumb-item active">${category.nom}</li>
        </ol>
    </nav>
    
    <h1>${category.nom}</h1>
    
    <div class="row">
        <c:forEach items="${products}" var="product">
            <div class="col-md-4 mb-4">
                <div class="card h-100">
                    <div class="card-body">
                        <h5 class="card-title">${product.nom}</h5>
                        <p class="card-text">${product.description}</p>
                        <p class="h4 text-primary"><fmt:formatNumber value="${product.prix}" type="currency" currencySymbol="€" minFractionDigits="2" /></p>
                        <p class="text-muted">Stock: ${product.stock}</p>
                    </div>
                    <div class="card-footer">
                        <a href="<spring:url value='/produits/${product.id}'/>" class="btn btn-sm btn-outline-primary">Voir détails</a>
                        <a href="<spring:url value='/panier/ajouter/${product.id}'/>" class="btn btn-sm btn-success">Ajouter au panier</a>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
    
    <c:if test="${empty products}">
        <div class="alert alert-info">Aucun produit dans cette catégorie pour le moment.</div>
    </c:if>
</div>

