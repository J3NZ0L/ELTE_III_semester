

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

  <!-- Main Content: Car List -->
  <div class="container my-5">
    <h1 class="mb-4">Available Cars</h1>
    <div class="row"> 
    <?php
        // Get the car ID from the URL
        $carId = $_GET['id'] ?? null;
        $cars = json_decode(file_get_contents('data/cars.json'), true);

        foreach ($cars as $car) {
          echo '
          <h1 class="mb-4">' . htmlspecialchars($car['brand'] . ' ' . $car['model']) . '</h1>
          <div class="row">
              <div class="col-md-6">
                  <img src="' . htmlspecialchars($car['image']) . '" class="img-fluid" alt="' . htmlspecialchars($car['brand'] . ' ' . $car['model']) . '">
              </div>
              <div class="col-md-6">
                  <ul class="list-group">
                      <li class="list-group-item">Year: ' . htmlspecialchars($car['year']) . '</li>
                      <li class="list-group-item">Transmission: ' . htmlspecialchars($car['transmission']) . '</li>
                      <li class="list-group-item">Fuel: ' . htmlspecialchars($car['fuel_type']) . '</li>
                      <li class="list-group-item">Passengers: ' . htmlspecialchars($car['passengers']) . '</li>
                      <li class="list-group-item">Price: ' . htmlspecialchars($car['daily_price_huf']) . ' HUF/day</li>
                  </ul>
              </div>
          </div>
          ';
        }
        ?>
    </div>
</div>

</body>
</html>


