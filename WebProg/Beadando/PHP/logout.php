<?php 
session_start();
session_destroy();

// try to redirect to the page the user was on before attempting to log out, not expected by the assignment but a nice touch, in my opinion

if (isset($_SERVER['HTTP_REFERER']) && strpos($_SERVER['HTTP_REFERER'], $_SERVER['HTTP_HOST']) !== false) {
    $_SESSION['redirect_url'] = $_SERVER['HTTP_REFERER'];
}

$redirect_url = isset($_SESSION['redirect_url']) ? $_SESSION['redirect_url'] : 'index.php';
header("Location: $redirect_url");
die();