<?php
require_once "classes/product.php";
require_once "classes/auth.php";
require_once "classes/order.php";

session_start();
$auth = new Auth();
if (!$auth->is_authenticated()){
    header('Location: login.php');
    die();
}
$order_repositor = new OrderRepository();
function is_empty($input, $key){
    return !(isset($input[$key]) && trim($input[$key])!== "");
}
function validate($input, &$errors){
    if (is_empty($input, "name") || is_empty($input, "address")){
        $errors[] = "Az adatok megadasa kotelezo";
    }

    return !(bool) $errors;
}

$errors = [];
if (isset($_SESSION["products"]) && count($_SESSION["products"]) != 0){
    if (validate($_POST, $errors)){
        $order_repository->add(new Order($_POST["name"], $_POST["address"], $_SESSION["products"]));
        unset($_SESSION["products"]);
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
    <?php require_once "cart.phhp"?>
    <?php if ($errors) {?>
    <ul>
        <?php foreach ($erorrs as $error) {?>
        <li><?=$error?></li>
        <?php }?>

    </ul>
    <?php }?>
    <form action="" method="post">
        <label for="name">Nev: </label>
        <input type="text" name="name" id="name"><br>
        <label for="address">Cim: </label>
        <input type="text" name="address" id="address"><br>
        <button type="submit">Megrendeles</button>
    </form>
    <a href="products.php">Termekek</a>
</body>
</html>