<?php
function validate($input){
    if (!ctype_xdigit($input) || strlen($input) != 6){ //returns if input is hex color code or not
        return true;
    }
    return false;
}
$error = false;

//validate arg
if (isset($_GET["color"])){
    $error = validate($_GET["color"]);
    if (!$error){
        $color = $_GET["color"];
    }
} else {
    $color = "ffffff";
}
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body style="background-color: #<?=$color?>">
    <ul>
        <?php
        if($error)
            echo "<li> HIbás színkód!</li>"
        ?>
    </ul>
    <ul>
        <li><a href="?color=00bbff">kék</a></li>
        <li><a href="?color=ffff44">sárga</a></li>
        <li><a href="?color=ff4444">piros</a></li>
    </ul>
    <form action="" method="get">
        <label for="color">Color</label>
        <input type="text" name="color" value="ffffff" id="">
        <button type="submit">Send</button>
    </form>
</body>
</html>