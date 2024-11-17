<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <h1>Űrlap</h1>
<?php
    class Question
    {
        public $text;
        public $answers;
        public $correct;

        public function __construct(string $text, array $answers, array $correct)
        {
            $this->text = $text;
            $this->answers = $answers;
            $this->correct = $correct;
        }
    }

    $arr = array(
        new Question("Milyen évet írunk?", ["2012", "2020", "2024"], [1,2]),
        new Question("Milyen szinu az eg?", ["sarga", "zold", "kek"], [0,1]),
    );
    //var_dump($arr); // debuggolashoz hasznos lehet
    $id = 0;

    for ($i = 0; $i < count($arr); $i++){
        $q = $arr[$i];
        echo "<h2>".$q->text."</h2>";
        for ($j=0; $j<count($q->answers);$j++){
            echo "\n".'<input type="checkbox" id="'.$id.'" name="'.$i.'">';
        echo '<label for="'.$id.'">'.$q->answers[$j].'</label><br>';
        $id++;
        }
    }
?>
</body>
</html>