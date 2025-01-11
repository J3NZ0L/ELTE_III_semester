<?php
include_once "helper/jsonstorage.php";

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
    
    public function getNextId(): int
    {
        $cars = $this->all();
        $ids = array_map(function($car){
            return $car->id;
        }, $cars);
        return max($ids) + 1;
    }

    public function allFilteredBy($start_date = null, $end_date = null, $transmission = null, $passenger_number = null, $min_price = null, $max_price = null): array
    {
        $filterfunc = function($car) use ($start_date, $end_date, $transmission, $passenger_number, $min_price, $max_price){
            if ($start_date && $end_date && $start_date <= $end_date){
                $form_start_datetime = DateTime::createFromFormat('Y-m-d', $start_date);
                $form_end_datetime = DateTime::createFromFormat('Y-m-d', $end_date);
                $reservations = (new ReservationRepository())->all();
                foreach ($reservations as $reservation){
                    if ($reservation->car_id == $car->id){
                        $reservation_start_datetime = new DateTime($reservation->start_date);
    $reservation_end_datetime = new DateTime($reservation->end_date);
                        if ($form_start_datetime >= $reservation_start_datetime && $form_start_datetime <= $reservation_end_datetime){
                            return false;
                        }
                        if ($form_end_datetime >= $reservation_start_datetime && $form_end_datetime <= $reservation_end_datetime){
                            return false;
                        }
                    }
                }
            }
            if ($transmission && strtolower($car->transmission) != strtolower($transmission)){
                return false;
            }
            if ($passenger_number && $car->passengers < $passenger_number){
                return false;
            }
            if ($min_price && $car->daily_price_huf < $min_price){
                return false;
            }
            if ($max_price && $car->daily_price_huf > $max_price){
                return false;
            }
            return true;
        };
        
        return array_filter($this->all(), $filterfunc);
    }
}
?>