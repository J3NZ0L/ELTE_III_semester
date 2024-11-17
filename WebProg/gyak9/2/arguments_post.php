<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
<?php
    if(isset($_POST["name"]))
        $name = $_POST["name"];
    else
        $name = "";
?>

    <form action="" method="post">
        <label for="name">Name: </label>
        <input type="text" name="name" value="<?=$name?>" id="">
        <button type="submit"></button>
    </form>

<?php
    if(!empty($name))
        echo "Hello $name!" . " Hossz: " . strlen($_POST["name"]);
?>
</body>
</html>