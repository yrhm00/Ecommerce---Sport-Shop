<%@ include file="include/importTags.jsp" %>
<div class="container py-5">
    <h2><spring:message code="checkout.title"/></h2>

    <c:choose>
        <c:when test="${empty sessionScope.cart || empty sessionScope.cart.items}">
            <div class="alert alert-warning">
                <spring:message code="cart.empty"/>
            </div>
            <a href="<spring:url value='/produits'/>" class="btn btn-primary"><spring:message code="cart.continue"/></a>
        </c:when>
        <c:otherwise>
            <div class="table-responsive mb-4">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th><spring:message code="product.details"/></th>
                            <th><spring:message code="product.price"/></th>
                            <th>Qté</th>
                            <th>Total</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${sessionScope.cart.items}" var="item">
                            <tr>
                                <td>${item.product.nom}</td>
                                <td><fmt:formatNumber value="${item.product.prix}" type="currency" currencySymbol="€"/></td>
                                <td>${item.quantite}</td>
                                <td><fmt:formatNumber value="${item.sousTotal}" type="currency" currencySymbol="€"/></td>
                            </tr>
                        </c:forEach>
                        <c:if test="${sessionScope.cart.discountAmount > 0}">
                            <tr>
                                <td colspan="3" class="text-end text-success fw-bold">Promotion (-10%)</td>
                                <td class="text-success fw-bold">- <fmt:formatNumber value="${sessionScope.cart.discountAmount}" type="currency" currencySymbol="€"/></td>
                            </tr>
                            <tr>
                                <td colspan="3" class="text-end fw-bold">Total à payer</td>
                                <td class="fw-bold"><fmt:formatNumber value="${sessionScope.cart.totalWithDiscount}" type="currency" currencySymbol="€"/></td>
                            </tr>
                        </c:if>
                        <c:if test="${sessionScope.cart.discountAmount <= 0}">
                            <tr>
                                <td colspan="3" class="text-end fw-bold"><spring:message code="cart.total"/></td>
                                <td class="fw-bold"><fmt:formatNumber value="${sessionScope.cart.total}" type="currency" currencySymbol="€"/></td>
                            </tr>
                        </c:if>
                    </tbody>
                </table>
            </div>

            <sec:authorize access="!isAuthenticated()">
                <div class="alert alert-info">
                    <spring:message code="checkout.loginRequired"/>
                </div>
                <a href="<spring:url value='/connexion'/>" class="btn btn-primary"><spring:message code="menu.login"/></a>
            </sec:authorize>

            <sec:authorize access="isAuthenticated()">
                <form action="<spring:url value='/commandes/confirmer'/>" method="post" class="mt-4">
                    <!-- CSRF Token is usually handled automatically by Spring Security tags but good to keep in mind -->
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    
                    <c:if test="${not empty paymentError}">
                        <div class="alert alert-danger">
                            ${paymentError}
                        </div>
                    </c:if>
                    <c:if test="${not empty param.error}">
                        <div class="alert alert-danger">
                            <c:choose>
                                <c:when test="${param.error == 'payment_cancelled'}">
                                    Paiement annulé par l'utilisateur.
                                </c:when>
                                <c:otherwise>
                                    Échec du paiement PayPal. Veuillez réessayer.
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </c:if>

                    <div class="card mb-4">
                        <div class="card-header bg-primary text-white">
                            Paiement via PayPal
                        </div>
                        <div class="card-body text-center">
                            <p class="mb-3">Vous allez être redirigé vers PayPal pour sécuriser votre paiement.</p>
                            <button type="submit" class="btn btn-warning btn-lg">
                                <i class="fab fa-paypal"></i> Payer avec PayPal
                            </button>
                        </div>
                    </div>

                    <!-- Button removed from here as it is now inside card body -->
                </form>
            </sec:authorize>
        </c:otherwise>
    </c:choose>
</div>
