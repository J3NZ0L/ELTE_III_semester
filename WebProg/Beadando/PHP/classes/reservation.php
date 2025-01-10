<?php
include_once "helper/jsonstorage.php";

class Reservation{
    public $id;
    public $car_id;
    public $user_id;
    public $start_date;
    public $end_date;
    public $total_price_huf;

    public function __construct($id = null, $car_id = null, $user_id = null, $start_date = null, $end_date = null, $total_price_huf = null){
        $this->id = $id;
        $this->car_id = $car_id;
        $this->user_id = $user_id;
        $this->start_date = $start_date;
        $this->end_date = $end_date;
        $this->total_price_huf = $total_price_huf;
    }

    public static function from_array(array $arr): Reservation
    {
        $instance = new Reservation();
        $instance->id = $arr['id'] ?? null;
        $instance->car_id = $arr['car_id'] ?? null;
        $instance->user_id = $arr['user_id'] ?? null;
        $instance->start_date = $arr['start_date'] ?? null;
        $instance->end_date = $arr['end_date'] ?? null;
        $instance->total_price_huf = $arr['total_price_huf'] ?? null;
        return $instance;
    }

    public static function from_object(object $obj): Reservation
    {
        return self::from_array((array) $obj);
    }
}

class ReservationRepository
{
    private $storage;
    public function __construct(){
        $this->storage = new JsonStorage('data/reservations.json');
    }
    public function convert(array $arr): array
    {
        return array_map([Reservation::class, 'from_object'], $arr);
    }
    public function all()
    {
        return $this->convert($this->storage->all());
    }
    public function add(Reservation $reservation): string
    {
        return $this->storage->insert($reservation);
    }
    public function filter(callable $callback): array
    {
        $reservations = $this->all();
        return array_filter($reservations, $callback);
    }
}
?>