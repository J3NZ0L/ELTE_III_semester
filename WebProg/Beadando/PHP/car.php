<?php
include_once "jsonstorage.php";

class Car{
    public $id;
    public $brand;
    public $model;
    public $year;
    public $transmission;
    public $fuel_type;
    public $passengers;
    public $daily_price_huf;
    public $image;

    public function __construct($id = null, $brand = null, $model = null, $year = null, $transmission = null, $fuel_type = null, $passengers = null, $daily_price_huf = null, $image = null){
        $this->id = $id;
        $this->brand = $brand;
        $this->model = $model;
        $this->year = $year;
        $this->transmission = $transmission;
        $this->fuel_type = $fuel_type;
        $this->passengers = $passengers;
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
        $instance->transmission = $arr['transmission'] ?? null;
        $instance->fuel_type = $arr['fuel_type'] ?? null;
        $instance->passengers = $arr['passengers'] ?? null;
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
    public function filter(callable $callback): array
    {
        $cars = $this->all();
        return array_filter($cars, $callback);
    }
}
?>