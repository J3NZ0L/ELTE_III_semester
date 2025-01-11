<?php
require_once "helper/auth.php";
require_once "helper/validation.php";
session_start();

$auth = new Auth();

$errors = [];
if (count($_POST) != 0) {
  if (validate_login($_POST, $errors, $auth)) {
    $auth->login($_POST);

    // try to redirect to the page the user was on before attempting to log out, not expected by the assignment but a nice touch, in my opinion
    $redirect_url = isset($_SESSION['redirect_url']) ? $_SESSION['redirect_url'] : 'index.php';
    header("Location: $redirect_url");
    exit();
  }
} else {
  // set the redirect_url session variable, for it to become accessible later, when we need to redirect the user back to the page they were on before attempting to log in
  if (isset($_SERVER['HTTP_REFERER']) && strpos($_SERVER['HTTP_REFERER'], $_SERVER['HTTP_HOST']) !== false) {
    $_SESSION['redirect_url'] = $_SERVER['HTTP_REFERER'];
  }
}
?>


<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Login</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
  
  <?php include "common_segments/navbar.php"; ?>

  <div class="container mt-4">
    <h1>Login</h1>
    <?php if ($errors) {?>
    <h2> Error(s) occurred while trying to log in: </h2>
    <ul>
        <?php foreach ($errors as $error) { ?>
        <li><?=$error?></li>
        <?php }?>
    </ul>
    <?php }?>
    <form action="" method="post" novalidate>
      <div class="mb-3">
        <label for="email" class="form-label">Email address</label>
        <input type="email" class="form-control" id="email" name="email" placeholder="Enter your email" value="<?php echo isset($_POST['email']) ? htmlspecialchars($_POST['email']) : ''; ?>">
      </div>
      <div class="mb-3">
        <label for="password" class="form-label">Password</label>
        <input type="password" class="form-control" id="password" name="password" placeholder="Enter your password">
      </div>
      <button type="submit" class="btn btn-primary">Login</button>
    </form>
  </div>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
