<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Créer une Todolist</title>
    <!-- Lien vers Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card shadow-sm">
                <div class="card-header bg-primary text-white text-center">
                    <h1>Creer une Todolist</h1>
                </div>
                <div class="card-body">
                    <form action="tdl" method="POST">
                        <div class="mb-3">
                            <label for="title" class="form-label">Titre de la Todolist :</label>
                            <input type="text" id="title" name="title" class="form-control" placeholder="Entrez le titre" required>
                        </div>
                        <button type="submit" class="btn btn-success w-100">Creer</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
