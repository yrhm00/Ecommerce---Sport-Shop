<%@ include file="include/importTags.jsp" %>
<div class="container mt-4">
    <h1>Mon Panier</h1>
    
    <c:choose>
        <c:when test="${not empty cart.items && cart.items.size() > 0}">
            <div class="table-responsive">
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th>Produit</th>
                            <th>Prix unitaire</th>
                            <th>Quantité</th>
                            <th>Sous-total</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${cart.items}" var="item">
                            <tr>
                                <td>
                                    <strong>${item.product.nom}</strong><br>
                                    <small class="text-muted">${item.product.description}</small>
                                </td>
                                <td><fmt:formatNumber value="${item.product.prix}" type="currency" currencySymbol="€" minFractionDigits="2" /></td>
                                <td>
                                    <form method="post" action="<spring:url value='/panier/modifier'/>" class="d-inline">
                                        <input type="hidden" name="productId" value="${item.product.id}" />
                                        <input type="number" name="quantite" value="${item.quantite}" min="1" max="${item.product.stock}" 
                                               class="form-control form-control-sm" style="width: 80px; display: inline-block;" />
                                        <button type="submit" class="btn btn-sm btn-outline-primary">Modifier</button>
                                    </form>
                                </td>
                                <td><strong><fmt:formatNumber value="${item.sousTotal}" type="currency" currencySymbol="€" minFractionDigits="2" /></strong></td>
                                <td>
                                    <a href="<spring:url value='/panier/supprimer/${item.product.id}'/>" 
                                       class="btn btn-sm btn-danger"
                                       onclick="return confirm('Êtes-vous sûr de vouloir supprimer ce produit ?')">
                                        Supprimer
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                    <tfoot>
                        <!-- Ligne Promotion -->
                        <c:if test="${cart.discountAmount > 0}">
                            <tr>
                                <td colspan="3" class="text-end text-success"><strong>Promotion (-10%) :</strong></td>
                                <td colspan="2"><strong class="text-success">- <fmt:formatNumber value="${cart.discountAmount}" type="currency" currencySymbol="€" minFractionDigits="2" /></strong></td>
                            </tr>
                            <tr>
                                <td colspan="3" class="text-end"><strong>Total à payer :</strong></td>
                                <td colspan="2"><strong class="h4 text-primary"><fmt:formatNumber value="${cart.totalWithDiscount}" type="currency" currencySymbol="€" minFractionDigits="2" /></strong></td>
                            </tr>
                        </c:if>
                        <c:if test="${cart.discountAmount <= 0}">
                            <tr>
                                <td colspan="3" class="text-end"><strong>Total :</strong></td>
                                <td colspan="2"><strong class="h4 text-primary"><fmt:formatNumber value="${cart.total}" type="currency" currencySymbol="€" minFractionDigits="2" /></strong></td>
                            </tr>
                        </c:if>
                    </tfoot>
                </table>
            </div>
            
            <div class="mt-4">
                <a href="<spring:url value='/produits'/>" class="btn btn-outline-secondary">Continuer les achats</a>
                <a href="<spring:url value='/panier/vider'/>" class="btn btn-outline-danger"
                   onclick="return confirm('Êtes-vous sûr de vouloir vider votre panier ?')">
                    Vider le panier
                </a>
                <a href="<spring:url value='/commandes/checkout'/>" class="btn btn-success btn-lg float-end">
                    Valider la commande
                </a>
            </div>
        </c:when>
        <c:otherwise>
            <div class="alert alert-info">
                <h4>Votre panier est vide</h4>
                <p>Découvrez nos produits et ajoutez-les à votre panier !</p>
            </div>
            <a href="<spring:url value='/produits'/>" class="btn btn-primary">Voir le catalogue</a>
        </c:otherwise>
    </c:choose>
</div>
