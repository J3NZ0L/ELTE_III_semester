<?php
require_once ("jsonstorage.php");
require_once ("car.php");
$errorList = [];
$fieldNames = ['licensePlate', 'color', 'brand' ,'dateOfManufacturement'];
$name = $_POST['name'] ?? '';

$licensePlate = $_POST['licensePlate'] ?? '';
$color = $_POST['color'] ?? '';
$brand = $_POST['brand'] ?? '';
$dateOfManufacturement = $_POST['dateOfManufacturement'] ?? '';


//checking if every field is filled out
if (empty($licensePlate) || empty($color) || empty($brand) || empty($dateOfManufacturement)){
    $errorList["MissingFieldError"] = "All of the fields have to be filled out.";
}

$dateValue = $_POST["dateOfManufacturement"];

if (!empty($dateValue) && (!is_numeric($dateValue) || strlen($dateValue)!=4)){
    $errorList["InvalidYearError"] = "Invalid value as manufacturing year.";
}

if (sizeof($errorList) == 0) {
    $carObj = new Car($licensePlate, $color, $brand, $dateOfManufacturement);
    $jsonStorage = new JsonStorage("autok.json");
    $jsonStorage->insert($carObj);
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
    <h1>Add car</h1>
    <?php
        if (sizeof($errorList)>0){
            echo "<h2> Errors in the form:</h2>";
        }
    ?>
    <ul>
        <?php
        foreach($errorList as $error){
            echo "<li> $error </li>";
        }
        ?>
    </ul>
    <!-- fields for the attributes of the cars -->
    <!-- in case of an error, the form is stateful - stateless otherwise -->
    <form action="" method="post">
        <label for="licensePlate">License plate:</label>
        <input type="text" name="licensePlate" id="licensePlate" value="<?=!empty($errorList) ? $licensePlate : '' ?>">
        <br>
        <label for="color">Color:</label>
        <input type="text" name="color" id="color" value="<?=!empty($errorList) ?  $color : '' ?>">
        <br>

        <label for="brand">Brand:</label>
        <input type="text" name="brand" id="brand" value="<?=!empty($errorList) ?  $brand : '' ?>">
        <br>

        <label for="dateOfManufacturement">Year of manufacturement</label>
        <input type="text" name="dateOfManufacturement" id="dateOfManufacturement" value="<?=!empty($errorList)?  $dateOfManufacturement : '' ?>">
        <br>
        <input type="submit" value="Submit car">
    </form>
    <br>
    <a href="cars.php">View cars</a>
</body>
</html>