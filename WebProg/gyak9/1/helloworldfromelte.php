<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
<?php
    $addr = $_SERVER['REMOTE_ADDR'];
    $in_elte_domain = str_starts_with($addr, "::1");
    if ($in_elte_domain) {
        echo "Hello world!";
    } else{
        echo "No permission, ," . $addr;
    }
?>
</body>
</html>