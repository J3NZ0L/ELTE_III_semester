<?php
include_once "helper/jsonstorage.php";

class Reservation{
    public $id;
    public $car_id;
    public $user_email;
    public $start_date;
    public $end_date;

    public function __construct($id = null, $car_id = null, $user_email = null, $start_date = null, $end_date = null){
        $this->id = $id;
        $this->car_id = $car_id;
        $this->user_email = $user_email;
        $this->start_date = $start_date;
        $this->end_date = $end_date;
    }

    public static function from_array(array $arr): Reservation
    {
        $instance = new Reservation();
        $instance->id = $arr['id'] ?? null;
        $instance->car_id = $arr['car_id'] ?? null;
        $instance->user_email = $arr['user_email'] ?? null;
        $instance->start_date = $arr['start_date'] ?? null;
        $instance->end_date = $arr['end_date'] ?? null;
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
}
?>