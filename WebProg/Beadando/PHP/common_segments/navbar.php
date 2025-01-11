<?php
require_once "helper/auth.php";
$auth = new Auth();
?>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid">
      <a class="navbar-brand" href="index.php">iKarRental</a>
      <?php if ($auth->is_authenticated()): ?>
        <span class="navbar-text d-lg-none ms-auto me-2">Logged in as: <?= htmlspecialchars($_SESSION['user']->name) ?></span>
      <?php endif; ?>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav me-auto">
        <?php if ($auth->is_authenticated()): ?>
          <li class="nav-item">
            <a class="nav-link" href="profile.php">Profile</a>
          </li>
        <?php endif; ?>
        </ul>
        <ul class="navbar-nav ms-auto">
          <?php if ($auth->is_authenticated()): ?>
                <span class="navbar-text d-none d-lg-inline ms-auto me-2">Logged in as: <?= htmlspecialchars($_SESSION['user']->name) ?></span>
              <li class="nav-item">
                <a class="nav-link" href="logout.php">Logout</a>
              </li>
              
          <?php else: ?>
            <li class="nav-item">
            <a class="nav-link" href="register.php">Register</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="login.php">Login</a>
            </li>
          <?php endif; ?>
        </ul>
      </div>
    </div>
  </nav>