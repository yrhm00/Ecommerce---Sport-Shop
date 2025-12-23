<%@ include file="../include/importTags.jsp" %>
<nav class="navbar navbar-expand-lg navbar-light bg-light rounded mb-3">
    <div class="container-fluid">
        <div class="collapse navbar-collapse">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link" href="<spring:url value='/'/>">Accueil</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<spring:url value='/produits'/>">Catalogue</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<spring:url value='/panier'/>">
                        Mon Panier
                        <c:if test="${sessionScope.cart != null && sessionScope.cart.totalItems > 0}">
                            <span class="badge bg-primary">${sessionScope.cart.totalItems}</span>
                        </c:if>
                    </a>
                </li>
            </ul>
            <div class="d-flex">
                <!-- Si l'utilisateur est connecté -->
                <sec:authorize access="isAuthenticated()">
                    <span class="navbar-text me-3">
                        Bonjour, <sec:authentication property="principal.prenom" />
                    </span>
                    <a href="<spring:url value='/deconnexion'/>" class="btn btn-outline-danger">Déconnexion</a>
                </sec:authorize>
                
                <!-- Si l'utilisateur n'est PAS connecté -->
                <sec:authorize access="!isAuthenticated()">
                    <a href="<spring:url value='/connexion'/>" class="btn btn-outline-success me-2">Connexion</a>
                    <a href="<spring:url value='/inscription'/>" class="btn btn-primary">Inscription</a>
                </sec:authorize>
            </div>
        </div>
    </div>
</nav>
