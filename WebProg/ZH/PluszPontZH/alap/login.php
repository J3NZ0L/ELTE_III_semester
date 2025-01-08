<?php
require_once "auth.php";

session_start();

$errors = [];
$auth = new Auth();

if ($_SERVER['REQUEST_METHOD'] == 'POST') {
  $name = $_POST['name'] ?? '';
  $password = $_POST['password'] ?? '';

  if (empty($name) || empty($password)) {
    $errors[] = 'All fields are required.';
  } else {
    if (!$auth->check_credentials($name, $password)) {
      $errors[] = 'Invalid credentials.';
    } else {
      $auth->login(['username' => $name, 'password' => $password]);
      header('Location: post.php');
      exit();
    }
  }
}
?>

<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Login</title>
</head>

<body>
  <h1>Login to an existing account</h1>
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
    <button type="submit">Login</button>
  </form>
  <a href="register.php">Register</a>
</body>

</html>