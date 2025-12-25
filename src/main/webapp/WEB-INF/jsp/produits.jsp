<%@ include file="include/importTags.jsp" %>
<div class="container mt-4">
  <h1><spring:message code="catalog.title"/></h1>
  <p class="lead"><spring:message code="catalog.subtitle"/></p>

  <div class="row">
    <c:forEach items="${categories}" var="category">
      <div class="col-md-4 mb-3">
        <div class="card h-100 shadow-sm border-0 transition-hover">
          <img src="<spring:url value='${category.imageUrl}'/>" class="card-img-top" alt="${category.nom}" style="height: 200px; object-fit: cover;">
          <div class="card-body text-center">
            <h5 class="card-title fw-bold my-3">${category.nom}</h5>
            <p class="card-text"><spring:message code="catalog.category.desc"/></p>
            <a
              href="<spring:url value='/produits/categorie/${category.id}'/>"
              class="btn btn-primary"
              ><spring:message code="catalog.btn.view"/></a
            >
          </div>
        </div>
      </div>
    </c:forEach>
  </div>
</div>
