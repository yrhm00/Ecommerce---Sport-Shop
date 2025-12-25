<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
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
            <div class="card border-0 shadow-sm position-relative">
                <c:if test="${product.newArrival}">
                    <div class="position-absolute top-0 start-0 m-3">
                        <span class="badge bg-success fs-5">NOUVEAU</span>
                    </div>
                </c:if>
                <c:if test="${product.originalPrice != null}">
                    <div class="position-absolute top-0 end-0 m-3">
                        <span class="badge bg-danger fs-5">-10%</span>
                    </div>
                </c:if>
                <div class="card-body text-center p-5">
                    <img src="<spring:url value='${product.imageUrl}'/>" class="img-fluid rounded" alt="${product.nom}" style="max-height: 400px;">
                </div>
            </div>
        </div>
        
        <div class="col-md-6">
            <h1>${product.nom}</h1>
            <c:choose>
                <c:when test="${product.originalPrice != null}">
                    <p class="mb-1">
                        <span class="h2 text-danger"><fmt:formatNumber value="${product.prix}" type="currency" currencySymbol="€" minFractionDigits="2" /></span>
                        <span class="h4 text-muted text-decoration-line-through ms-3"><fmt:formatNumber value="${product.originalPrice}" type="currency" currencySymbol="€" minFractionDigits="2" /></span>
                    </p>
                </c:when>
                <c:otherwise>
                    <p class="h2 text-primary mb-3"><fmt:formatNumber value="${product.prix}" type="currency" currencySymbol="€" minFractionDigits="2" /></p>
                </c:otherwise>
            </c:choose>
            
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
                        <c:if test="${not empty product.sizesStock}">
                            <div class="mb-3" style="max-width: 200px;">
                                <label for="taille" class="form-label">Taille :</label>
                                <select id="taille" name="taille" class="form-select" onchange="updateStock(this)" required>
                                    <c:forEach items="${product.sizesStock}" var="entry">
                                        <option value="${entry.key}" data-stock="${entry.value}" ${entry.value == 0 ? 'disabled' : ''}>
                                            ${entry.key} <c:choose><c:when test="${entry.value > 0}">(${entry.value} dispo)</c:when><c:otherwise>(Rupture)</c:otherwise></c:choose>
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                        </c:if>

                        <div class="input-group mb-3" style="max-width: 200px;">
                            <label for="quantite" class="form-label me-2">Quantité :</label>
                            <input type="number" class="form-control" id="quantite" name="quantite" value="1" min="1" max="${product.stock}" required>
                        </div>
                        <button type="submit" id="btn-add" class="btn btn-success btn-lg">Ajouter au panier</button>
                    </form>

                    <c:if test="${not empty product.sizesStock}">
                        <script>
                            function updateStock(select) {
                                var selectedOption = select.options[select.selectedIndex];
                                var stock = parseInt(selectedOption.getAttribute('data-stock'));
                                var qtyInput = document.getElementById('quantite');
                                var btn = document.getElementById('btn-add');
                                
                                if (stock > 0) {
                                    qtyInput.max = stock;
                                    if(qtyInput.value > stock) qtyInput.value = stock;
                                    qtyInput.disabled = false;
                                    btn.disabled = false;
                                    btn.classList.remove('btn-secondary');
                                    btn.classList.add('btn-success');
                                    btn.innerText = "Ajouter au panier";
                                } else {
                                    qtyInput.disabled = true;
                                    btn.disabled = true;
                                    btn.classList.remove('btn-success');
                                    btn.classList.add('btn-secondary');
                                    btn.innerText = "Rupture de stock";
                                }
                            }
                            // Init au chargement
                            document.addEventListener('DOMContentLoaded', function() {
                                var select = document.getElementById('taille');
                                if(select) updateStock(select);
                            });
                        </script>
                    </c:if>
                </c:when>
                <c:otherwise>
                    <div class="alert alert-warning">Produit actuellement en rupture de stock</div>
                </c:otherwise>
            </c:choose>
            
            <a href="<spring:url value='/produits/categorie/${product.categoryId}'/>" class="btn btn-outline-secondary mt-3">← Retour à la catégorie</a>
        </div>
    </div>
</div>

