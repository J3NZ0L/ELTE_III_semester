<?php
include_once "storage.php";

class Car{
    public $id;
    public $brand;
    public $model;
    public $year;
    public $fuel_type;
    public $passangers;
    public $daily_price_huf;
    public $image;

    public function __construct($id = null, $brand = null, $model = null, $year = null, $fuel_type = null, $passangers = null, $daily_price_huf = null, $image = null){
        $this->id = $id;
        $this->brand = $brand;
        $this->model = $model;
        $this->year = $year;
        $this->fuel_type = $fuel_type;
        $this->passangers = $passangers;
        $this->daily_price_huf = $daily_price_huf;
        $this->image = $image;
    }

    public static function from_array(array $arr): Car
    {
        $instance = new Car();
        $instance->id = $arr['id'] ?? null;
        $instance->brand = $arr['brand'] ?? null;
        $instance->model = $arr['model'] ?? null;
        $instance->year = $arr['year'] ?? null;
        $instance->fuel_type = $arr['fuel_type'] ?? null;
        $instance->passangers = $arr['passangers'] ?? null;
        $instance->daily_price_huf = $arr['daily_price_huf'] ?? null;
        $instance->image = $arr['image'] ?? null;
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
        $this->storage = new JsonStorage('data/cars.json');
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