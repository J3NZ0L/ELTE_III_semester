<?php 
session_start();
session_destroy();

header('Location: index.php');
die(); //nem lenne szukseges hiszen nincs tobb call, de ez a jo praktika