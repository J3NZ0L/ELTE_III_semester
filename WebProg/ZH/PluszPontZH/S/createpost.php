<?php
require_once "auth.php";
require_once "jsonstorage.php";
require_once "validation.php";

session_start();
$auth = new Auth();
if (!$auth->is_authenticated()) {
  header('Location: login.php');
  die();
}

$errors = [];
$input = $_POST;
$posts = new JsonStorage("posts.json");

if (!empty($_POST)) {
    if (is_empty($input, "title") || is_empty($input, "content")) {
        $errors[] = "Cim es tartalom megadasa is kotelezo";
    } else {
        $post = new stdClass();
        $post->title = $input['title'];
        $post->content = $input['content'];
        $post->creator = $_SESSION['username'];
        $post->date = date('Y.m.d H:i');

        $posts->insert($post);

        header('Location: index.php');
        die();
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
  <h1>Poszt letrehozasa</h1>

  <?php
    if (count($errors) > 0) {
        echo "<h2> Hiba a poszt letrehozasa soran: </h2><ul>";
        foreach ($errors as $error) {
            echo "<li> $error </li>";
        }
        echo "</ul>";
    }
  ?>

  <form action="" method="POST">
    <label for="title">Cim:</label>
    <br>
    <input type="text" name="title" id="title"><br>
    <label for="content">Tartalom:</label>
    <br>
    <textarea name="content" id="content"></textarea>
    <br>
    <button type="submit">Bekuldes</button>
  </form>
</body>

</html>
