<?php
require_once "auth.php";

$errors = [];
$auth = new Auth();

if ($_SERVER['REQUEST_METHOD'] == 'POST') {
  $name = $_POST['name'] ?? '';
  $password = $_POST['password'] ?? '';

  if (empty($name) || empty($password)) {
    $errors[] = 'All fields are required.';
  } elseif ($auth->user_exists($name)) {
    $errors[] = 'Username is already taken.';
  } else {
    $auth->register(['username' => $name, 'password' => $password]);
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
  <title>Register</title>
</head>

<body>
  <h1>Register a new account</h1>
  <?php if (!empty($errors)): ?>
    <ul>
      <?php foreach ($errors as $error): ?>
        <li><?php echo htmlspecialchars($error); ?></li>
      <?php endforeach; ?>
    </ul>
  <?php endif; ?>
  <form action="" method="POST">
    <label for="name">Name:</label><br>
    <input type="text" name="name" id="name"><br>
    <label for="password">Password:</label><br>
    <input type="password" name="password" id="password"><br>
    <button type="submit" name="register">Register</button>
  </form>
  <a href="login.php">Login</a>
</body>

</html>