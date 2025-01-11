<?php
require_once "auth.php";

function is_empty($input, $key){
    return !(isset($input[$key]) && trim($input[$key]) !== "");
}

function validate_login($input, &$errors, $auth){
        // TODO:

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
        // TODO:

    if (is_empty($input, "email")){
        $errors[] = "Email address is mandatoriy";
    }
    if (is_empty($input, "password")){
        $errors[] = "Password is mandatory";
    }
    if (count($errors) ==0){
        if ($auth->user_exists($input['email'])){
            $errors[] = "This email has been already registered";
        }
    }
    return !(bool) $errors;
}

function validate_car_details($input, &$errors){
    // TODO:
    $fields = ["brand", "model", "year", "transmission", "fuel_type", "passengers", "daily_price_huf", "image"];
    foreach ($fields as $field) {
        if (is_empty($input, $field)) {
            $errors[] = ucfirst($field) . " is mandatory";
        }
    }
    return !(bool) $errors;
}
?>