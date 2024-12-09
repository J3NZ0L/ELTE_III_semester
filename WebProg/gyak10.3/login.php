<?php
require_once "classes/auth.php";
session_start();
$auth = new Auth();
function is_empty($input, $key){
    return !(isset($input[$key]) && trim($input[$key]) !== "");
}
function validate($input, &$errors, $auth){
    if (is_empty($input, "username")){
        $errors[] = "Felhasznalonev kotelezo";
    }
    if (is_empty($input, "password")){
        $errors[] = "Jelszo megadasa kotelezo";
    }
    if (count($errors) ==0){
        if ($auth->check_credentials($input['username'], $input['password'])){
            $errors[] = "Hibas felhasznalonev vagy jelszo";
        }
    }

    return !(bool) $errors;
}

$errors = [];

if (count($_POST)!= 0){
    if (validate($_POST, $errors, $auth)){
        $auth->login($_POST);
        header('Location: makeorder.php');
        die();
    }
}

?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <h2>Bejelentkezés</h2>
    <?php if ($errors){ ?>
    <ul>
        <?php foreach ($errors as $error){?>
        <li><?=$error?></li>
        <?php }?>
    </ul>
    <?php }?>
    <form action="" method="post">
        <label for="username">Felhasznalo: </label>
        <input type="text" name="username" id="username"><br>
        <label for="password">Jelszo: </label>
        <input type="password" name="password" id="password">
        <input type="submit" value="Bejelentkezés">
    </form>
    <a href="register.php">Regisztráció</a>
</body>
</html>