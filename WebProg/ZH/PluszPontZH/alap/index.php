<?php
require_once "auth.php";
require_once "jsonstorage.php";

session_start();

$auth = new Auth();
$postsStorage = new JsonStorage('data/posts.json');
$posts = array_reverse($postsStorage->all());

$username = '';
if ($auth->is_authenticated()) {
  $username = $_SESSION['user']['username'];
}
?>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>All Posts</title>
</head>

<body>
  <div>
    <a href="post.php">Create a New Post</a><br>
    <?php if ($auth->is_authenticated()): ?>
      <span>Logged in as <b><?= htmlspecialchars($username) ?></b></span>
      <a href="logout.php">Logout</a>
    <?php endif; ?>
  </div>

  <h1>All Posts</h1>

  <?php foreach ($posts as $post): ?>
    <div style="border: 1px solid #000; margin-bottom: 10px; padding: 10px;">
      <h2><?= htmlspecialchars($post->title) ?></h2>
      <p><?= nl2br(htmlspecialchars($post->content)) ?></p>
      <p>Author: <b><?= htmlspecialchars($post->author) ?></b></p>
      <p>Date: <code><?= htmlspecialchars($post->date) ?></code></p>
    </div>
  <?php endforeach; ?>
</body>

</html>