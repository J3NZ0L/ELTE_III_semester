<?php
session_start();
?>

<!-- Profile page -->
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>User Profile</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
  <?php include "common_segments/navbar.php"; ?>
  <div class="container mt-4">
    <h1>User Profile</h1>
    <p>Name: <?= htmlspecialchars($_SESSION['user']->name) ?></p>
    <p>Email: <?= htmlspecialchars($_SESSION['user']->email) ?></p>
    <p>Rented Cars:</p>
    <ul>
<!-- To be implemented --> 
    </ul>
  </div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
