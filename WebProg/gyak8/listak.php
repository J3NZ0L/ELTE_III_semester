<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <?php
    $hibak = ["nincs valami", "szalami", 
    "hiba"];
    for ($i = 0; $i < count($hibak); $i++){
        echo "<li>".$hibak[$i]."</li>";
    }
    ?>
</body>
</html>