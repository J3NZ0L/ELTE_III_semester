<?php
    if (isset($_GET["inc"])){
        include $_GET["inc"] . ".php";
    }
?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <h1>Includer</h1>
    <h4>Type the file to be included, into the url.</h4>
</body>
</html>