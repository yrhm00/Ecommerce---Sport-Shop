<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="include/importTags.jsp" %>
<div class="container py-5">
  <div class="row justify-content-center">
    <div class="col-lg-8">
      
      <!-- Titre et Intro -->
      <div class="text-center mb-5">
        <h1 class="display-5 fw-bold"><spring:message code="profile.title"/></h1>
        <p class="lead text-muted"><spring:message code="profile.success"/></p>
      </div>

      <!-- Messages -->
      <c:if test="${not empty errorMessage}">
        <div class="alert alert-danger alert-dismissible fade show" role="alert">
          <i class="bi bi-exclamation-triangle-fill me-2"></i> ${errorMessage}
          <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
        </div>
      </c:if>
      <c:if test="${not empty successMessage}">
        <div class="alert alert-success alert-dismissible fade show" role="alert">
          <i class="bi bi-check-circle-fill me-2"></i> ${successMessage}
          <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
        </div>
      </c:if>

      <!-- Carte du formulaire -->
      <div class="card shadow-sm border-0 rounded-3">
        <div class="card-header bg-primary text-white p-4 rounded-top-3">
           <h4 class="mb-0"><i class="bi bi-person-circle me-2"></i> <spring:message code="menu.profile"/></h4>
        </div>
        <div class="card-body p-4 p-md-5 bg-light">
          <form:form
            method="post"
            action="${pageContext.request.contextPath}/profil"
            modelAttribute="user"
            class="needs-validation"
          >
            <!-- Nom d'utilisateur (Lecture seule) -->
            <div class="mb-4">
              <label class="form-label text-muted small text-uppercase fw-bold"><spring:message code="profile.username.fixed"/></label>
              <div class="input-group">
                <span class="input-group-text bg-white"><i class="bi bi-person-lock"></i></span>
                <form:input path="username" type="text" class="form-control bg-white" readonly="true" />
              </div>
            </div>

            <hr class="my-4 text-muted" />

            <!-- Nom et Prénom -->
            <div class="row g-3 mb-3">
                <div class="col-md-6">
                  <form:label path="nom" class="form-label fw-bold"><spring:message code="user.lastname"/></form:label>
                  <form:input path="nom" type="text" class="form-control" placeholder="Votre nom" />
                  <form:errors path="nom" cssClass="text-danger small mt-1" />
                </div>
        
                <div class="col-md-6">
                  <form:label path="prenom" class="form-label fw-bold"><spring:message code="user.firstname"/></form:label>
                  <form:input path="prenom" type="text" class="form-control" placeholder="Votre prénom" />
                  <form:errors path="prenom" cssClass="text-danger small mt-1" />
                </div>
            </div>

            <!-- Email et Téléphone -->
            <div class="row g-3 mb-3">
                <div class="col-md-6">
                  <form:label path="email" class="form-label fw-bold"><spring:message code="user.email"/></form:label>
                  <div class="input-group">
                        <span class="input-group-text"><i class="bi bi-envelope"></i></span>
                        <form:input path="email" type="email" class="form-control" placeholder="name@example.com" />
                  </div>
                  <form:errors path="email" cssClass="text-danger small mt-1" />
                </div>
                <div class="col-md-6">
                  <form:label path="telephone" class="form-label fw-bold"><spring:message code="user.phone"/></form:label>
                  <div class="input-group">
                        <span class="input-group-text"><i class="bi bi-telephone"></i></span>
                        <form:input path="telephone" type="tel" class="form-control" placeholder="+32 ..." />
                  </div>
                  <form:errors path="telephone" cssClass="text-danger small mt-1" />
                </div>
            </div>

            <!-- Adresse -->
            <div class="mb-4">
              <form:label path="adresse" class="form-label fw-bold"><spring:message code="user.address"/></form:label>
              <form:textarea path="adresse" class="form-control" rows="3" placeholder="Rue, numéro, code postal, ville..." />
              <form:errors path="adresse" cssClass="text-danger small mt-1" />
            </div>

            <!-- Boutons -->
            <div class="d-flex justify-content-end gap-2 mt-5">
                <a href="<spring:url value='/'/>" class="btn btn-outline-secondary px-4"><spring:message code="btn.cancel"/></a>
                <button type="submit" class="btn btn-primary px-5 shadow-sm">
                    <i class="bi bi-save me-2"></i> <spring:message code="profile.update"/>
                </button>
            </div>
          </form:form>
        </div>
      </div>
    </div>
  </div>
</div>
