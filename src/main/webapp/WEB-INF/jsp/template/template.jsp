<%@ include file="../include/importTags.jsp" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title><tiles:insertAttribute name="titre" /></title>
    <!-- Bootstrap CSS pour un style rapide et propre -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body { padding-top: 20px; }
        .footer { margin-top: 50px; padding: 20px; background-color: #f8f9fa; }
    </style>
</head>
<body>

    <div class="container">
        <header class="mb-4">
            <tiles:insertAttribute name="entete" />
            <tiles:insertAttribute name="menu" />
        </header>

        <main>
            <tiles:insertAttribute name="contenu" />
        </main>

        <footer class="footer text-center">
            <tiles:insertAttribute name="piedpage" />
        </footer>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

