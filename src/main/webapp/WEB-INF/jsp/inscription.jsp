<%@ include file="include/importTags.jsp" %>
<div class="container mt-4">
  <div class="row justify-content-center">
    <div class="col-md-6">
      <h1>Inscription</h1>

      <!-- Message d'erreur global -->
      <c:if test="${not empty errorMessage}">
        <div class="alert alert-danger">${errorMessage}</div>
      </c:if>

      <!-- Formulaire avec Spring Form tags -->
      <form:form
        method="post"
        action="${pageContext.request.contextPath}/inscription"
        modelAttribute="inscriptionForm"
      >
        <div class="mb-3">
          <form:label path="username" class="form-label"
            >Nom d'utilisateur (Login)</form:label
          >
          <form:input path="username" type="text" class="form-control" />
          <form:errors path="username" cssClass="text-danger" />
        </div>

        <div class="mb-3">
          <form:label path="password" class="form-label"
            >Mot de passe</form:label
          >
          <form:input path="password" type="password" class="form-control" />
          <form:errors path="password" cssClass="text-danger" />
        </div>

        <div class="mb-3">
          <form:label path="confirmPassword" class="form-label"
            >Confirmer le mot de passe</form:label
          >
          <form:input
            path="confirmPassword"
            type="password"
            class="form-control"
          />
          <form:errors path="confirmPassword" cssClass="text-danger" />
        </div>

        <div class="mb-3">
          <form:label path="nom" class="form-label">Nom</form:label>
          <form:input path="nom" type="text" class="form-control" />
          <form:errors path="nom" cssClass="text-danger" />
        </div>

        <div class="mb-3">
          <form:label path="prenom" class="form-label">Prénom</form:label>
          <form:input path="prenom" type="text" class="form-control" />
          <form:errors path="prenom" cssClass="text-danger" />
        </div>

        <div class="mb-3">
          <form:label path="email" class="form-label">Email</form:label>
          <form:input path="email" type="email" class="form-control" />
          <form:errors path="email" cssClass="text-danger" />
        </div>

        <div class="mb-3">
          <form:label path="telephone" class="form-label"
            >Téléphone (facultatif)</form:label
          >
          <form:input path="telephone" type="tel" class="form-control" />
          <form:errors path="telephone" cssClass="text-danger" />
        </div>

        <div class="mb-3">
          <form:label path="adresse" class="form-label"
            >Adresse de livraison</form:label
          >
          <form:textarea path="adresse" class="form-control" rows="3" />
          <form:errors path="adresse" cssClass="text-danger" />
        </div>

        <button type="submit" class="btn btn-primary">S'inscrire</button>
        <a href="<spring:url value='/'/>" class="btn btn-secondary">Annuler</a>
      </form:form>
    </div>
  </div>
</div>
