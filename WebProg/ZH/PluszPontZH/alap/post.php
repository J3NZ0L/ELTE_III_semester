<?php
require_once "auth.php";
require_once "jsonstorage.php";

session_start();

$auth = new Auth();

if (!$auth->is_authenticated()) {
  header('Location: login.php');
  exit();
}

$errors = [];

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
  $title = $_POST['title'] ?? '';
  $content = $_POST['content'] ?? '';

  if (empty($title) || empty($content)) {
    $errors[] = 'All fields are required.';
  } else {
    $post = [
      'title'   => $title,
      'content' => $content,
      'author'  => $_SESSION['user']['username'],
      'date'    => date('Y.m.d H:i')
    ];

    $postsStorage = new JsonStorage('data/posts.json');
    $postsStorage->insert((object) $post);

    header('Location: index.php');
    exit();
  }
}
?>

<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Create a Post</title>
</head>

<body>
  <h1>Create a Post</h1>

  <?php if (!empty($errors)): ?>
    <ul>
      <?php foreach ($errors as $error): ?>
        <li><?= htmlspecialchars($error) ?></li>
      <?php endforeach; ?>
    </ul>
  <?php endif; ?>

  <form action="" method="POST">
    <label for="title">Title:</label><br>
    <input type="text" name="title" id="title"><br>
    <label for="content">Content:</label><br>
    <textarea name="content" id="content"></textarea><br>
    <button type="submit">Submit</button>
  </form>
</body>

</html>