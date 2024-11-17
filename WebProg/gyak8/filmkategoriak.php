<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <select>
<?php
$arr = array(
    1=> "Horror",
    2=> "Kaland",
    3=> "Dráma",
    4=> "Krimi",
    5=> "Dokumentumfilm"
);
foreach($arr as $key => $value){
    echo "<option value=\"".$key."\">".$value."</option>\n";
}
?>
</body>
</html>