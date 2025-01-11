<?php 
session_start();
session_destroy();

// set the redirect_url if can be set
if (isset($_SERVER['HTTP_REFERER']) && strpos($_SERVER['HTTP_REFERER'], $_SERVER['HTTP_HOST']) !== false) {
    $_SESSION['redirect_url'] = $_SERVER['HTTP_REFERER'];
}

// check if the user is logging out from add_car.php and redirect to index.php if true
if (isset($_SERVER['HTTP_REFERER']) && strpos($_SERVER['HTTP_REFERER'], 'add_car.php') !== false) {
    $_SESSION['redirect_url'] = 'index.php';
}

// try to redirect to the page the user was on before attempting to log out, not expected by the assignment but a nice touch, in my opinion
$redirect_url = isset($_SESSION['redirect_url']) ? $_SESSION['redirect_url'] : 'index.php';

header("Location: $redirect_url");
die();