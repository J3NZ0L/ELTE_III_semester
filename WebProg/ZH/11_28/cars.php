<?php
require_once("jsonstorage.php");

$storage = new JsonStorage("autok.json");
$cars = $storage->all();
$brand = "";

if ($_SERVER["REQUEST_METHOD"] === "POST") {
  $brand = $_POST["brand"];
  if (!empty($brand)) {
    $cars = $storage->filter(function ($car) use ($brand) {
      return $car["brand"] === $brand;
    });
  }
}
?>

<!DOCTYPE html>
<html lang="hu">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>PHP Csoport ZH</title>
</head>

<body>
  <h1>Autók kilistázása</h1>
  <form action="" method="post">
    <label for="brand">Márka: </label>
    <input type="text" name="brand" id="brand"
      value="<?= htmlspecialchars($brand) ?>">
    <button type="submit">Szűrés</button>
  </form>
  <table>
    <thead>
      <tr>
        <th>Rendszám</th>
        <th>Szín</th>
        <th>Márka</th>
        <th>Gyártási év</th>
      </tr>
    </thead>
    <tbody>
      <?php foreach ($cars as $car): ?>
        <tr>
          <td><?php echo htmlspecialchars($car["licensePlate"]); ?></td>
          <td><?php echo htmlspecialchars($car["color"]); ?></td>
          <td><?php echo htmlspecialchars($car["brand"]); ?></td>
          <td><?php echo htmlspecialchars($car["year"]); ?></td>
        </tr>
      <?php endforeach; ?>
    </tbody>
  </table>
  <br>
  <a href="index.php">Vissza</a>
</body>

</html>