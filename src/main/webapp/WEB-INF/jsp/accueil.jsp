<%@ include file="include/importTags.jsp" %>
<div class="p-5 mb-4 bg-light rounded-3">
    <div class="container-fluid py-5">
        <h1 class="display-5 fw-bold">Bienvenue sur JogginApp Store</h1>
        <p class="col-md-8 fs-4">Le meilleur équipement pour vos sessions de jogging.</p>
        <button class="btn btn-primary btn-lg" type="button" onclick="window.location.href='<spring:url value="/produits"/>'">Voir le catalogue</button>
    </div>
</div>

<div class="row align-items-md-stretch">
    <div class="col-md-6">
        <div class="h-100 p-5 text-white bg-dark rounded-3">
            <h2>Nouveautés</h2>
            <p>Découvrez nos derniers arrivages de chaussures et vêtements techniques.</p>
            <button class="btn btn-outline-light" type="button">Voir plus</button>
        </div>
    </div>
    <div class="col-md-6">
        <div class="h-100 p-5 bg-light border rounded-3">
            <h2>Promotions</h2>
            <p>Profitez de nos offres exceptionnelles pour les soldes d'hiver.</p>
            <button class="btn btn-outline-secondary" type="button">Voir les promos</button>
        </div>
    </div>
</div>

