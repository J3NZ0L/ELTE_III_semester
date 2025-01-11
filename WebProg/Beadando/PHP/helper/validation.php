<?php
require_once "auth.php";

function is_empty($input, $key){
    return !(isset($input[$key]) && trim($input[$key]) !== "");
}

function validate_login($input, &$errors, $auth){
    if (is_empty($input, "email")){
        $errors[] = "Email address is mandatoriy";
    }
    if (is_empty($input, "password")){
        $errors[] = "Password is mandatory";
    }
    if (count($errors) ==0){
        if (!$auth->check_credentials($input['email'], $input['password'])){
            $errors[] = "Email address or password is incorrect";
        }
    }

    return !(bool) $errors;
}

function validate_signup($input, &$errors, $auth){
    if (is_empty($input, "name")) {
        $errors[] = "Name is mandatory";
    }
    if (is_empty($input, "email")){
        $errors[] = "Email address is mandatoriy";
    }
    if (is_empty($input, "password")){
        $errors[] = "Password is mandatory";
    }
    if (count($errors)==0){
        if (!filter_var($input["email"], FILTER_VALIDATE_EMAIL)) {
            $errors[] =  "Invalid email address";
        }
        if (!preg_match("/^[\p{L} '-]+$/u", $input["name"])) {
            $errors[] = "Name contains invalid characters";
        }
        if (count($errors)==0){
            if ($auth->user_exists($input['email'])){
                $errors[] = "This email has been already registered";
            }
        }
    }
    return !(bool) $errors;
}

function validate_car_details($input, &$errors){
    $fields = ["brand", "model", "year", "transmission", "fuel_type", "passengers", "daily_price_huf", "image"];
    foreach ($fields as $field) {
        if (is_empty($input, $field)) {
            $errors[] = ucfirst($field) . " is mandatory";
        }
    }
    if (!count($errors) == 0){
        return false;
    }
        
    $numericFields = ["year", "passengers", "daily_price_huf"];

    foreach ($numericFields as $field) {
        if (!is_numeric($input[$field])){
            $errors[] = ucfirst($field) ." must be a number";
        }
    }

    if (!count($errors) == 0){
        return false;
    }
        
    if (($input["year"] < 1886 || $input["year"] > date("Y"))) {
        $errors[] = "Year must be between 1886 and " . date("Y");
    }

    if (($input["passengers"] < 1 || $input["passengers"] > 30)) {
        $errors[] = "Passengers must be between 1 and 30";
    }

    if (($input["daily_price_huf"] < 0)) {
        $errors[] = "Daily price must be a positive number";
    }

    if (!filter_var($input["image"], FILTER_VALIDATE_URL)) {
        $errors[] = "Invalid image URL";
    }

    return !(bool) $errors;
}
?>