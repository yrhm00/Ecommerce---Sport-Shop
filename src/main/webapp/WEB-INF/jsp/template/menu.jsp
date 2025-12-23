<%@ include file="../include/importTags.jsp" %>
<nav class="navbar navbar-expand-lg navbar-light bg-light rounded mb-3">
    <div class="container-fluid">
        <div class="collapse navbar-collapse">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link" href="<spring:url value='/'/>"><spring:message code="menu.home"/></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<spring:url value='/produits'/>"><spring:message code="menu.catalog"/></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<spring:url value='/panier'/>">
                        <spring:message code="menu.cart"/>
                        <c:if test="${sessionScope.cart != null && sessionScope.cart.totalItems > 0}">
                            <span class="badge bg-primary">${sessionScope.cart.totalItems}</span>
                        </c:if>
                    </a>
                </li>
            </ul>
            
            <div class="d-flex me-3">
                <span class="navbar-text me-2">
                    <a href="<spring:url value=''><spring:param name='locale' value='fr'/></spring:url>" class="text-decoration-none text-secondary">FR</a> |
                    <a href="<spring:url value=''><spring:param name='locale' value='en'/></spring:url>" class="text-decoration-none text-secondary">EN</a>
                </span>
            </div>

            <div class="d-flex">
                <!-- Si l'utilisateur est connecté -->
                <sec:authorize access="isAuthenticated()">
                    <span class="navbar-text me-3">
                        <spring:message code="menu.welcome"/>, <sec:authentication property="principal.prenom" />
                    </span>
                    <a href="<spring:url value='/deconnexion'/>" class="btn btn-outline-danger"><spring:message code="menu.logout"/></a>
                </sec:authorize>
                
                <!-- Si l'utilisateur n'est PAS connecté -->
                <sec:authorize access="!isAuthenticated()">
                    <a href="<spring:url value='/connexion'/>" class="btn btn-outline-success me-2"><spring:message code="menu.login"/></a>
                    <a href="<spring:url value='/inscription'/>" class="btn btn-primary"><spring:message code="menu.register"/></a>
                </sec:authorize>
            </div>
        </div>
    </div>
</nav>
