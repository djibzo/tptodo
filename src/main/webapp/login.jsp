<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
<div class="container d-flex justify-content-center align-items-center" style="min-height: 100vh;">
    <div class="container mt-5 d-flex justify-content-center">
        <div class="card shadow-lg p-4" style="width: 100%; max-width: 500px; border-radius: 10px;">
            <div class="card-body">
                <h3 class="card-title text-center mb-4 text-primary">Connexion</h3>
                <form action="login" method="post">
                    <!-- Login -->
                    <div class="mb-3">
                        <label for="login" class="form-label"><i class="bi bi-person-circle"></i> Username</label>
                        <input type="text" name="username" class="form-control" id="login" placeholder="Choisissez un login" required>
                    </div>

                    <!-- Mot de passe -->
                    <div class="mb-3">
                        <label for="password" class="form-label"><i class="bi bi-lock-fill"></i> Mot de passe</label>
                        <input type="password" name="password" class="form-control" id="password" placeholder="Entrez un mot de passe" required>
                    </div>

                    <!-- Bouton Envoyer -->
                    <button type="submit" class="btn btn-primary w-100">
                        <i class="bi bi-send-fill"></i> Envoyer
                    </button>
                </form>
                <div style="color: red;">
                    <%= request.getAttribute("errorMessage") != null ? request.getAttribute("errorMessage") : "" %>
                </div>

                <!-- Lien vers la connexion -->
                <div class="text-center mt-3">
                    <a href="inscription" class="text-secondary text-decoration-none">Pas de compte ? S'inscrire'</a>
                </div>
            </div>
        </div>
    </div>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">

</div>
</body>
</html>