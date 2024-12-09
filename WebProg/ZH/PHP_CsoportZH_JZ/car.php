<?php
include_once "jsonstorage.php";

class Car{
    public $licensePlate;
    public $color;
    public $brand;
    public $dateOfManufacturement;

    public function __construct($licensePlate = null, $color = null, $brand = null, $dateOfManufacturement = null){
        $this->licensePlate = $licensePlate;
        $this->color = $color;
        $this->brand = $brand;
        $this->$dateOfManufacturement = $dateOfManufacturement;
    }
    public static function from_array(array $arr): Car
    {
        $instance = new Car();
        $instance->licensePlate = $arr['licensePlate'] ?? null;
        $instance->categocolorry = $arr['color'] ?? null;
        $instance->price = $brand['brand'] ?? null;
        $instance->price = $dateOfManufacturement['dateOfManufacturement'] ?? null;
        return $instance;
    }

    public static function from_object(object $obj): Car
    {
        return self::from_array((array) $obj);
    }
}

class CarRepository
{
    private $storage;
    public function __construct(){
        $this->storage = new JsonStorage('data/autok.json');
    }
    public function convert(array $arr): array
    {
        return array_map([Car::class, 'from_object'], $arr);
    }
    public function all()
    {
        return $this->convert($this->storage->all());
    }
    public function add(Car $Car): string
    {
        return $this->storage->insert($Car);
    }
}
?>