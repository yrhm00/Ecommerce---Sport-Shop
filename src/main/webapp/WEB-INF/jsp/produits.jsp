<%@ include file="include/importTags.jsp" %>
<div class="container mt-4">
  <h1>Catalogue des Produits</h1>
  <p class="lead">Choisissez une catégorie pour voir nos produits</p>

  <div class="row">
    <c:forEach items="${categories}" var="category">
      <div class="col-md-4 mb-3">
        <div class="card h-100">
          <div class="card-body">
            <h5 class="card-title">${category.nom}</h5>
            <p class="card-text">Découvrez notre sélection</p>
            <a
              href="<spring:url value='/produits/categorie/${category.id}'/>"
              class="btn btn-primary"
              >Voir les produits</a
            >
          </div>
        </div>
      </div>
    </c:forEach>
  </div>
</div>
