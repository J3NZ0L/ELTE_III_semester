<?php
include_once "product.php";
include_once "order.php";
$product_repository = new ProductRepository();
$order_repository = new OrderRepository();

if (count($_GET) != 0){
    $products = $product_repository->getProductsByCategory($_GET("category"));

} else {
    $products = $product_repository->all();
}

if (count($_POST) != 0 && !empty($_POST["name"]) && !empty($_POST["address"]) && !empty($_POST["products"])){
    $order_repository->add(new Order($_POST["name"], $_POST["address"], $_POST["products"]));
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
    <h2><?=$_GET["category"] ?? "Osszes"?></h2>
    <form action="" method="post">
    <?php
    $i = 0;
    foreach ( $products as $p){
        echo '<input type="checkbox" id="'.$i.'"name="products[]" value ="'.$p->name.'">';
        echo '<label for="'.$li.'">'.$p->name.'</label><br>';
        $li++;
    }
    ?>
        <br><br>
        <label for="name">Név:</label>
        <input type="text" name="name" id="name">
        <label for="address">Cím:</label>
        <input type="text" name="address" id="address">
        <button type="submit"></button>
    </form>
    <br><br>
    <a href="orders.php">Megrendelesek</a>
</body>
</html>