<?php
require_once "auth.php";

function is_empty($input, $key){
    return !(isset($input[$key]) && trim($input[$key]) !== "");
}

function validate_login($input, &$errors, $auth){
    if (is_empty($input, "username")){
        $errors[] = "Felhasznalonev kotelezo";
    }
    if (is_empty($input, "password")){
        $errors[] = "Jelszo megadasa kotelezo";
    }
    if (count($errors) ==0){
        if (!$auth->check_credentials($input['username'], $input['password'])){
            $errors[] = "Hibas felhasznalonev vagy jelszo";
        }
    }

    return !(bool) $errors;
}

function validate_signup($input, &$errors, $auth){
    if (is_empty($input, "username")){
        $errors[] = "Felhasznalonev kotelezo";
    }
    if (is_empty($input, "password")){
        $errors[] = "Jelszo megadasa kotelezo";
    }
    if (count($errors) ==0){
        if (!$auth->user_exists($input['username'])){
            $errors[] = "Letezik mar ilyen felhasznalo";
        }
    }
  
    return !(bool) $errors;
}

?>