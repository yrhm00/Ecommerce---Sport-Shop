<%@ include file="include/importTags.jsp" %>
<div class="container mt-4">
    <nav aria-label="breadcrumb">
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="<spring:url value='/'/>">Accueil</a></li>
            <li class="breadcrumb-item"><a href="<spring:url value='/produits'/>">Catalogue</a></li>
            <li class="breadcrumb-item"><a href="<spring:url value='/produits/categorie/${product.categoryId}'/>">${product.categoryNom}</a></li>
            <li class="breadcrumb-item active">${product.nom}</li>
        </ol>
    </nav>
    
    <div class="row">
        <div class="col-md-6">
            <div class="card">
                <div class="card-body text-center">
                    <p class="text-muted">Image du produit</p>
                    <div style="height: 300px; background-color: #f0f0f0; display: flex; align-items: center; justify-content: center;">
                        <span>📦 ${product.nom}</span>
                    </div>
                </div>
            </div>
        </div>
        
        <div class="col-md-6">
            <h1>${product.nom}</h1>
            <p class="h2 text-primary mb-3"><fmt:formatNumber value="${product.prix}" type="currency" currencySymbol="€" minFractionDigits="2" /></p>
            
            <div class="mb-4">
                <h5>Description</h5>
                <p>${product.description}</p>
            </div>
            
            <div class="mb-4">
                <p><strong>Stock disponible :</strong> ${product.stock} unités</p>
                <p><strong>Catégorie :</strong> ${product.categoryNom}</p>
                <p><strong>Référence :</strong> ${product.code}</p>
            </div>
            
            <c:choose>
                <c:when test="${product.stock > 0}">
                    <form method="get" action="<spring:url value='/panier/ajouter/${product.id}'/>">
                        <div class="input-group mb-3" style="max-width: 200px;">
                            <label for="quantite" class="form-label me-2">Quantité :</label>
                            <input type="number" class="form-control" id="quantite" name="quantite" value="1" min="1" max="${product.stock}" required>
                        </div>
                        <button type="submit" class="btn btn-success btn-lg">Ajouter au panier</button>
                    </form>
                </c:when>
                <c:otherwise>
                    <div class="alert alert-warning">Produit actuellement en rupture de stock</div>
                </c:otherwise>
            </c:choose>
            
            <a href="<spring:url value='/produits/categorie/${product.categoryId}'/>" class="btn btn-outline-secondary mt-3">← Retour à la catégorie</a>
        </div>
    </div>
</div>

