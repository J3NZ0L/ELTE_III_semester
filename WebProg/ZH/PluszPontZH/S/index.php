<?php
require_once "auth.php";
require_once "jsonstorage.php";
session_start();
$auth = new Auth();

$posts = new JsonStorage("posts.json");
$allPosts = $posts->all();
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Fooldal</title>
</head>
<body>
<h1><a href="createpost.php">Create new post</a></h1>
<?php 
  if ($auth->is_authenticated()){
    echo "<p> Bejelentkezve: " . $_SESSION["username"] . "</p>";
    echo "<a href='logout.php'>Kijelentkezes</a>";
  }  
?>
<h2>Posts</h2>
<?php if (!empty($allPosts)): ?>
    <?php foreach ($allPosts as $post): ?>
        <div>
            <h2><?= $post->title ?></h2>
            <p><?= nl2br($post->content) ?></p>
            <p>
                <strong>Keszito:</strong> <?= $post->creator ?><br>
                <strong>Datum:</strong> <?= $post->date ?>
            </p>
            <hr>
        </div>
    <?php endforeach; ?>
<?php endif; ?>
</body>
</html>