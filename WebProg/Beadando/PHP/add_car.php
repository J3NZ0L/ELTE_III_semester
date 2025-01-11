<?php
require_once "helper/validation.php";
require_once "classes/car.php";
session_start();

$carRepository = new CarRepository();

$errors = [];

if (count($_POST) != 0){
    IF (validate_car_details($_POST, $errors)){
        $car = new Car(
            $carRepository->getNextId(),
            $_POST['brand'],
            $_POST['model'],
            $_POST['year'],
            $_POST['transmission'],
            $_POST['fuel_type'],
            $_POST['passengers'],
            $_POST['daily_price_huf'],
            $_POST['image']
        );
        $carRepository->add($car);
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
  <title>Registration</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
  <?php include "common_segments/header.php"; ?>
  <div class="container mt-4">
    <h1>Add car</h1>
    <?php if ($errors) {?>
    <ul>
        <h2> Error(s) occurred while trying to add a car: </h2>
        <?php foreach ($errors as $error) { ?>
        <li><?=$error?></li>
        <?php }?>
    </ul>
    <?php }?>
    <form action="" method="post" novalidate>
    <div class="mb-3">
        <label for="brand" class="form-label">Brand</label>
        <input type="text" class="form-control" id="brand" name="brand" required>
    </div>
    <div class="mb-3">
        <label for="model" class="form-label">Model</label>
        <input type="text" class="form-control" id="model" name="model" required>
    </div>
    <div class="mb-3">
        <label for="year" class="form-label">Year</label>
        <input type="number" class="form-control" id="year" name="year" required>
    </div>
    <div class="col-md-3">
        <label for="transmission" class="form-label">Transmission</label>
        <select class="form-control" id="transmission" name="transmission">
          <option value="">Any</option>
          <option value="automatic" <?= (isset($_GET['transmission']) && $_GET['transmission'] == 'automatic') ? 'selected' : '' ?>>Automatic</option>
          <option value="manual" <?= (isset($_GET['transmission']) && $_GET['transmission'] == 'manual') ? 'selected' : '' ?>>Manual</option>
        </select>
      </div>
    <div class="mb-3">
        <label for="fuel_type" class="form-label">Fuel Type</label>
        <input type="text" class="form-control" id="fuel_type" name="fuel_type" required>
    </div>
    <div class="mb-3">
        <label for="passengers" class="form-label">Passengers</label>
        <input type="number" class="form-control" id="passengers" name="passengers" required>
    </div>
    <div class="mb-3">
        <label for="daily_price_huf" class="form-label">Daily Price (HUF)</label>
        <input type="number" class="form-control" id="daily_price_huf" name="daily_price_huf" required>
    </div>
    <div class="mb-3">
        <label for="image" class="form-label">Image URL</label>
        <input type="url" class="form-control" id="image" name="image" required>
    </div>
    <button type="submit" class="btn btn-primary mb-3">Add car</button>
    </form>
  </div>
</body>
</html>
