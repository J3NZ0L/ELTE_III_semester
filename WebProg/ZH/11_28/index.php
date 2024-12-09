<?php
include_once "jsonstorage.php";

$errors = [];
$licensePlate = $color = $brand = $year = "";

if ($_SERVER["REQUEST_METHOD"] == "POST") {
  $licensePlate = $_POST["licensePlate"];
  $color = $_POST["color"];
  $brand = $_POST["brand"];
  $year = $_POST["year"];

  $isValid = true;

  if (empty($_POST["licensePlate"]) || empty($_POST["color"]) || empty($_POST["brand"]) || empty($_POST["year"])) {
    $errors[] = "Nem adtál meg minden adatot!";
    $isValid = false;
  }

  if (!is_numeric($_POST["year"]) || strlen($_POST["year"]) != 4) {
    $errors[] = "A gyártási évnek négyjegyű számnak kell lennie!";
    $isValid = false;
  }

  if ($isValid) {
    $storage = new JsonStorage("autok.json");
    $storage->insert((object) [
      "licensePlate" => $licensePlate,
      "color" => $color,
      "brand" => $brand,
      "year" => $year
    ]);

    $licensePlate = $color = $brand = $year = "";
  }
}
?>

<!DOCTYPE html>
<html lang="hu">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Document</title>
</head>
<body>
  <h1>Add car</h1>
  <ul>
    <?php
      if (len($errors)){
        
      }
      foreach ($errors as $error){
       echo "<li>$error</li>";
      }
    ?>
  </ul>
  <form action="" method="post">
    <label for="licensePlate">Rendszám: </label>
    <input type="text" name="licensePlate" id="licensePlate"
      value="<?= htmlspecialchars($licensePlate) ?>"><br>
    <label for="color">Szín: </label>
    <input type="text" name="color" id="color"
      value="<?= htmlspecialchars($color) ?>"><br>
    <label for="brand">Gyártó: </label>
    <input type="text" name="brand" id="brand"
      value="<?= htmlspecialchars($brand) ?>"><br>
    <label for="year">Gyártási év: </label>
    <input type="number" name="year" id="year"
      value="<?= htmlspecialchars($year) ?>"><br>
    <button type="submit">Küldés</button>
  </form>
  <br>
  <a href="cars.php">Autók</a>
</body>

</html>