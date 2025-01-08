<?php
require_once "auth.php";
$errorList = [];
session_start();
$auth = new Auth();

function is_empty($input, $key){
  return !(isset($input[$key]) && trim($input[$key]) !== "");
}


function validate_signup($input, &$errors, $auth){
  if (is_empty($input, "username")){
      $errors[] = "Felhasznalonev kotelezo";
  }
  if (is_empty($input, "password")){
      $errors[] = "Jelszo megadasa kotelezo";
  }
  if (count($errors) ==0){
      if ($auth->user_exists($input['username'])){
          $errors[] = "Letezik mar ilyen felhasznalo";
      }
  }

  return !(bool) $errors;
}

$errors = [];
if (count($_POST) != 0){
    IF (validate_signup($_POST, $errors, $auth)){
        $auth->register($_POST);
        header('Location: login.php');
        exit();
    }
}
?>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Regisztracio</title>
</head>
<body>
  <h1> Regisztracio </h1>
  <?php
    if (count($errorList)>0){
      echo "<h2> Hiba a regisztracio soran: </h2><ul>";
      foreach($errorList as $error){
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
    <button type="submit">Regisztracio</button>
  </form>
  <br>
  <a href="login.php">Bejelentkezes</a>
</body>
</html>