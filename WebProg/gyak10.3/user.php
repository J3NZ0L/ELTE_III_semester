<?php
require_once "jsonstorage.php";
class UserRepository extends jsonStorage
{
    public function __construct(){
        parent::_construct('./data/users.json');
    }
}