<%@ include file="include/importTags.jsp" %>
<div class="container mt-4">
  <div class="row justify-content-center">
    <div class="col-md-4">
      <h1>Connexion</h1>

      <!-- Message de succès après inscription -->
      <c:if test="${param.inscriptionSuccess != null}">
        <div class="alert alert-success">
          Inscription réussie ! Vous pouvez maintenant vous connecter.
        </div>
      </c:if>

      <!-- Message après déconnexion -->
      <c:if test="${param.logout != null}">
        <div class="alert alert-info">
          Vous avez été déconnecté avec succès.
        </div>
      </c:if>

      <!-- Message d'erreur de connexion -->
      <c:if test="${param.error != null}">
        <div class="alert alert-danger">
          Nom d'utilisateur ou mot de passe incorrect.
        </div>
      </c:if>

      <!-- Formulaire de connexion pour Spring Security -->
      <form method="post" action="<spring:url value='/connexion'/>">
        <div class="mb-3">
          <label for="username" class="form-label">Nom d'utilisateur</label>
          <input
            type="text"
            class="form-control"
            id="username"
            name="username"
            required
          />
        </div>
        <div class="mb-3">
          <label for="password" class="form-label">Mot de passe</label>
          <input
            type="password"
            class="form-control"
            id="password"
            name="password"
            required
          />
        </div>
        <button type="submit" class="btn btn-success">Se connecter</button>
        <a href="<spring:url value='/inscription'/>" class="btn btn-link"
          >Créer un compte</a
        >
      </form>
    </div>
  </div>
</div>
