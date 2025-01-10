<?php
require_once "auth.php";
require_once "validation.php";
session_start();

$auth = new Auth();

$errors = [];
if (count($_POST)!= 0){
    if (validate_login($_POST, $errors, $auth)){
        $auth->login($_POST);
        header('Location: index.php');
        die();
    }
}


?>


<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Login</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
  <div class="container mt-4">
    <h1>Login</h1>
    <?php
    if (count($errors)>0){
      echo "<h2> An error occurred while trying to log in: </h2><ul>";
      foreach($errors as $error){
        echo "<li> $error </li>";
      }
      echo "</ul>";
    }
  ?>
    <form action="" method="post" novalidate>
      <div class="mb-3">
        <label for="email" class="form-label">Email address</label>
        <input type="email" class="form-control" id="email" name="email" placeholder="Enter your email" >
      </div>
      <div class="mb-3">
        <label for="password" class="form-label">Password</label>
        <input type="password" class="form-control" id="password" name="password" placeholder="Enter your password">
      </div>
      <button type="submit" class="btn btn-primary">Login</button>
    </form>
  </div>
</body>
</html>
