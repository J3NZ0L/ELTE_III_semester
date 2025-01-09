<?php
include_once "car.php";
$carRepository = new CarRepository();

$filters = [
  'start_date' => $_GET['start_date'] ?? null,
  'end_date' => $_GET['end_date'] ?? null,
  'transmission' => $_GET['transmission'] ?? null,
  'passenger_number' => $_GET['passenger_number'] ?? null,
  'min_price' => $_GET['min_price'] ?? null,
  'max_price' => $_GET['max_price'] ?? null,
];

$cars = $carRepository->all();
?>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Car Rental Service</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
</head>
<body>

  <!-- Navbar -->
  <nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
      <a class="navbar-brand" href="#">Car Rental</a>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav">
          <li class="nav-item">
            <a class="nav-link" href="index.php">Home</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="profile.php">Profile</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="login.php">Login</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="register.php">Register</a>
          </li>
        </ul>
      </div>
    </div>
  </nav>
  <!-- Filter Form -->
  <div class="container my-4">
    <h2>Filter results</h2>
    <form method="GET" action="index.php" class="row g-3">
      <div class="col-md-3">
        <label for="start_date" class="form-label">Start Date</label>
        <input type="date" class="form-control" id="start_date" name="start_date" value="<?= htmlspecialchars($_GET['start_date'] ?? '') ?>">
      </div>
      <div class="col-md-3">
        <label for="end_date" class="form-label">End Date</label>
        <input type="date" class="form-control" id="end_date" name="end_date" value="<?= htmlspecialchars($_GET['end_date'] ?? '') ?>">
      </div>
      <div class="col-md-3">
        <label for="transmission" class="form-label">Transmission</label>
        <select class="form-control" id="transmission" name="transmission">
          <option value="">Any</option>
          <option value="automatic" <?= (isset($_GET['transmission']) && $_GET['transmission'] == 'automatic') ? 'selected' : '' ?>>Automatic</option>
          <option value="manual" <?= (isset($_GET['transmission']) && $_GET['transmission'] == 'manual') ? 'selected' : '' ?>>Manual</option>
        </select>
      </div>
      <div class="col-md-3">
        <label for="passenger_number" class="form-label">Passenger Number</label>
        <input type="number" class="form-control" id="passenger_number" name="passenger_number" value="<?= htmlspecialchars($_GET['passenger_number'] ?? '') ?>">
      </div>
      <div class="col-md-3">
        <label for="min_price" class="form-label">Min Price (HUF/day)</label>
        <input type="number" class="form-control" id="min_price" name="min_price" value="<?= htmlspecialchars($_GET['min_price'] ?? '') ?>">
      </div>
      <div class="col-md-3">
        <label for="max_price" class="form-label">Max Price (HUF/day)</label>
        <input type="number" class="form-control" id="max_price" name="max_price" value="<?= htmlspecialchars($_GET['max_price'] ?? '') ?>">
      </div>
      <div class="col-md-3">
        <button type="submit" class="btn btn-primary mt-4">Filter</button>
      </div>
    </form>
  </div>
  <!-- Main Content: Car List -->
  <div class="container my-5">
    <h1 class="mb-4">Available Cars</h1>
    <div class="row"> 
        <?php foreach ($cars as $car): ?>
            <div class="col-md-4 mb-4">
                <a href="car_details.php?id=<?= htmlspecialchars($car->id) ?>" class="text-decoration-none">
                    <div class="card h-100">
                        <img src="<?= htmlspecialchars($car->image) ?>" class="card-img-top" alt="<?= htmlspecialchars($car->brand . ' ' . $car->model) ?>">
                        <div class="card-body">
                            <h5 class="card-title"><?= htmlspecialchars($car->brand . ' ' . $car->model) ?></h5>
                            <p class="card-text">Year: <?= htmlspecialchars($car->year) ?></p>
                            <p class="card-text">Price: <?= htmlspecialchars($car->daily_price_huf) ?> HUF/day</p>
                        </div>
                    </div>
                </a>
            </div>
        <?php endforeach; ?>
    </div>
</div>

</div>

</body>
</html>


