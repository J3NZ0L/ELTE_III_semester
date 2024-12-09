<?php
require_once "classes/auth.php";
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
        if ($auth->user_exists($input['username'])){
            $errors[] = "Letezik mar ilyen felhasznalo";
        }
    }

    return !(bool) $errors;
}

$errors = [];
if (count($_POST) != 0){
    IF (validate($_POST, $errors, $auth)){
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
    <title>Document</title>
</head>
<body>
    <h2>Regisztráció</h2>
    <?php if ($errors) {?>
    <ul>
        <?php foreach ($erros as $error) { ?>
        <li><?=$error?></li>
        <?php }?>
    </ul>
    <?php }?>
    <form action="" method="post">
        <label for="username">Felhasznalo: </label>
        <input type="text" name="username" id="username"><br>
        <label for="password">Jelszo: </label>
        <input type="password" name="password" id="password">
        <input type="submit" value="Regisztráció">
    </form>
    <a href="login.php">Bejelentkezés</a>
</body>
</html>
