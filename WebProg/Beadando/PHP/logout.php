<?php 
session_start();
session_destroy();

$redirect_url = isset($_SESSION['redirect_url']) ? $_SESSION['redirect_url'] : 'index.php';
header("Location: $redirect_url");
die();