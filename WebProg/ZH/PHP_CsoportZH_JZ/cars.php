<?php
require_once("jsonstorage.php");

$jsonStorage = new JsonStorage("autok.json");
$carArr = $jsonStorage->all();

$brand = $_POST["brand"];
if (!empty($brand)) {
  $cars = $jsonStorage->filter(function ($car) use ($brand) {
    return $car["brand"] === $brand;
  });
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
  <h1>List cars</h1>

  <form action="" method="post">
    <label for="brand">Brand:</label>
    <input type="text" name="brand" id="brand"
      value="<?=$brand?>">
    <button type="submit">Search for brand</button>
  </form>

  <table>

    <thead>
      <tr>
        <th>License plate</th>
        <th>Color</th>
        <th>Brand</th>
        <th>Year of manufacturement</th>
      </tr>
    </thead>

    <tbody>
      <?php foreach ($cars as $car){ ?>
        <tr>
          <td><?php echo $car["licensePlate"]; ?></td>
          <td><?php echo $car["color"]; ?></td>
          <td><?php echo $car["brand"]; ?></td>
          <td><?php echo $car["dateOfManufacturement"]; ?></td>
        </tr>
      <?php } ?>
    </tbody>

  </table>
  
  <br>
  <a href="index.php">Back to the home page</a>
</body>

</html>