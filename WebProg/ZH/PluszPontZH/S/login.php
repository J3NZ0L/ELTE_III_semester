<?php
require_once "auth.php";
require_once "validation.php";
session_start();

$auth = new Auth();

$errors = [];

if (count($_POST)!= 0){
    if (validate_login($_POST, $errors, $auth)){
        $auth->login($_POST);
        header('Location: createpost.php');
        die();
    }
}
?>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Bejelentkezes</title>
</head>
<body>
  <h1> Bejelentkezes </h1>
  <?php
    if (count($errors)>0){
      echo "<h2> Hiba a bejelentkezes soran: </h2><ul>";
      foreach($errors as $error){
        echo "<li> $error </li>";
      }
      echo "</ul>";
    }
  ?>
  <form action="" method="post">
    <label for="username">Felhasznalonev:</label>
    <input type="text" name="username" id="username">
    <br>
    <label for="password">Jelszo:</label>
    <input type="password" name="password" id="password">
    <br>
    <button type="submit">Bejelentkezes</button>
  </form>
  <br>
  <a href="signup.php">Regisztracio</a>
</body>
</html>