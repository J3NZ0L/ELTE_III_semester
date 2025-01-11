<?php
include_once "classes/car.php";
session_start();
$carRepository = new CarRepository();

$cars = $carRepository->all();

$carId = $_GET['id'] ?? null;

$car = null;
foreach ($cars as $c) {
    if ($c->id == $carId) {
        $car = $c;
        break;
    }
}

if (!$car) {
    echo "Car not found";
    exit;
}

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    if (!isset($_SESSION['user'])) {
        header('Location: login.php');
        exit;
    } else {
        // reservation is yet to be implemented, from here
    }
}
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><?= htmlspecialchars($car->brand . ' ' . $car->model) ?> Details</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <?php include_once "common_segments/navbar.php"; ?>
    
    <!-- Heading and base car data -->
    <div class="container my-5">
    <h1><?= htmlspecialchars($car->brand . ' ' . $car->model) ?></h1>
    <img src="<?= htmlspecialchars($car->image) ?>" class="img-fluid mb-4" alt="<?= htmlspecialchars($car->brand . ' ' . $car->model) ?>">
    <ul class="list-group">
        <li class="list-group-item">Year: <?= htmlspecialchars($car->year) ?></li>
        <li class="list-group-item">Transmission: <?= htmlspecialchars($car->transmission) ?></li>
        <li class="list-group-item">Fuel Type: <?= htmlspecialchars($car->fuel_type) ?></li>
        <li class="list-group-item">Passengers: <?= htmlspecialchars($car->passengers) ?></li>
        <li class="list-group-item">Daily Price: <?= htmlspecialchars($car->daily_price_huf) ?> HUF/day</li>
    </ul>

    <!-- Reservation form -->
    <div class="col-md-4">
        <form action="" method="POST" novalidate>
            <input type="hidden" name="car_id" value="<?= htmlspecialchars($car->id) ?>">
            <h2 class="my-3">Reserve:</h2>
            <!-- Date range selector -->
            <div class="mb-3">
                <label for="start_date" class="form-label">Start Date:</label>
                <input type="date" id="start_date" name="start_date" class="form-control" required>
            </div>
            <div class="mb-3">
                <label for="end_date" class="form-label">End Date:</label>
                <input type="date" id="end_date" name="end_date" class="form-control" required>
            </div>
            <button type="submit" class="btn btn-primary w-100">Reserve Car</button>
        </form>
    </div>

    <div class="text-end mt-4">
        <a href="index.php" class="btn btn-secondary">Back to Main Page</a>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
