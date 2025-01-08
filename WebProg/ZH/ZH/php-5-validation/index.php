<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Task 5</title>
    <link rel="stylesheet" href="src/index.css">
</head>

<?php

$success = false;
$email = "";

if($_SERVER["REQUEST_METHOD"] == "GET"){
    $error = [];
    
    if(isset($_GET["name"])){
        if($_GET["name"] == ""){
            array_push($error,"Üres név mező");
        } else {        
        if(strlen($_GET["name"]) < 8){
            array_push($error,"Rövid név");
        }
        if(strlen($_GET["name"]) > 50){
            array_push($error,"Túl hosszú név");
        }}
    } else {
        array_push($error,"Üres név mező");
    }
    if(isset($_GET["email"])){
        if (!filter_var($_GET["email"], FILTER_VALIDATE_EMAIL)) {
            array_push($error, "Nem megfelelő email formátum");
        }
    } else {
        array_push($error,"Üres email mező");
    }
    if(isset($_GET["age"])){
        if($_GET["age"] == ""){
            array_push($error,"Üres életkor mező");
        } else{
            if(is_float($_GET["age"]) || str_contains($_GET["age"],",") || str_contains($_GET["age"],".")){
            array_push($error,"Az életkor csak egész szám lehet");
            } else {
            if($_GET["age"] > 99){
                array_push($error,"Az életkor túl nagy");
            }
            if($_GET["age"] < 18){
                array_push($error,"Az életkor túl kicsi");
            }
            }
        }
    } else {
        array_push($error,"Üres életkor mező");
    }
    
    $rooms = ["3bed", "4bed", "6bed-small", "6bed-medium", "6bed-large"];

    if(isset($_GET["room"])){
        if($_GET["room"] == ""){
            array_push($error,"Üres szoba mező");
        } else {
            $temp = false;
            foreach($rooms as $item){
                if($item == $_GET["room"]){
                    $temp = true;
                }
            }
            if($temp == false){
                array_push($error,"Nem megfelelő szoba mező");
            }
        }
    } else {
        array_push($error,"Üres szoba mező");
    }
    if(isset($_GET["travelers"])){
        if(($_GET["travelers"]) == ""){
            array_push($error,"Üres utasok mező");
        }
        $text = trim($_GET['travelers']);
        $textAr = explode("\n", $text);
        //$textAr = array_filter($textAr, 'trim');  
        if(count($textAr) > (int)$_GET["room"][0]){
            array_push($error,"Túl sok utas");
        } else {
            foreach ($textAr as $line) {
                $str=str_replace("\r","",$line);
                if(strlen($str) > 50){
                    array_push($error,"Túl hosszú utas név");
                }
                if(strlen($str) < 8){
                    array_push($error,"Túl rövid utas név");
                }
            } 
        }


    } else {
        array_push($error,"Üres utasok mező");
    }

    if(count($error) == 0){
        $success = true;
        $email = $_GET['email'];
    }

} 



?>

<body>
    <h1>5. Jelentkezés a táborba / Apply to the trip</h1>
    <form novalidate>
        <label for="name">Név / Name</label>
        <input type="text" name="name" id="name">

        <label for="email">Email</label>
        <input type="email" name="email" id="email">

        <label for="age">Életkor / Age</label>
        <input type="number" name="age" id="age">

        <label for="room">Szoba / Room</label>
        <select name="room" id="room">
            <option value="3bed">3 ágyas / 3 beds</option>
            <option value="4bed">4 ágyas / 4 beds</option>
            <option value="6bed-small">6 ágyas (kicsi 40m²) / 6 beds (small 40m²)</option>
            <option value="6bed-medium">6 ágyas (közepes 52m²) / 6 beds (medium 52m²)</option>
            <option value="6bed-large">6 ágyas (tágas 60m²) / 6 beds (large 60m²)</option>
        </select>

        <label for="travelers">Egyéb utasok / Other travelers</label>
        <textarea name="travelers" id="travelers" cols="30" rows="10"></textarea>
        
        <input type="submit" value="Jelentkezem / Apply">
    </form>
    <div id="errors" <?php if(count($error) == 0){echo 'hidden';} ?>>
        <h2>Hiba! / Error!</h2>
        <ul>
            <?php 
            
            foreach($error as $item){
                echo '<li>';echo $item; echo'</li>';
            }
            
            ?>
        </ul>
    </div>

    <div id="success" <?php if($success != true) {echo 'hidden';} ?>>
        <h2>Sikeres jelentkezés! / Successful application!</h2>
        <div>
            Kiküldtük a vouchert az alábbi e-mail címre. / We sent the voucher to the following e-mail address.
            <span id="confirm-email"><?php echo $email; ?></span>
        </div>
    </div>

    <h2>Segítség a teszteléshez / Help for testing</h2>

    <h3>validate.php</h3>
    <ul>
        <li><a href="validate.php?">Minden hiányzik / Everything is missing</a></li>
        <li><a href="validate.php?name=Franz Joseph Otto Robert Maria Anton Karl Max Heinrich Sixtus Xaver Felix Renatus Ludwig Gaetan Pius Ignatius von Habsburg&email=giorgio@elte.hu&age=27&room=6bed-large&travelers=Valentino Pomzi%0D%0AVittorio Trio">Túl hosszú név / Too long Name</a></li>
        <li><a href="validate.php?name=Giorgio Battori&email=giorgio-elte.hu&age=27&room=6bed-large&travelers=Valentino Pomzi%0D%0AVittorio Trio">Helytelen e-mail formátum / Wrong e-mail format</a></li>
        <li><a href="validate.php?name=Giorgio Battori&email=giorgio@elte.hu&age=twentyseven&room=6bed-large&travelers=Valentino Pomzi%0D%0AVittorio Trio">Helytelen életkor formátum / Wrong age format</a></li>
        <li><a href="validate.php?name=Giorgio Battori&email=giorgio@elte.hu&age=10&room=6bed-large&travelers=Valentino Pomzi%0D%0AVittorio Trio">Túl fiatal / Too young</a></li>
        <li><a href="validate.php?name=Giorgio Battori&email=giorgio@elte.hu&age=100&room=6bed-large&travelers=Valentino Pomzi%0D%0AVittorio Trio">Túl öreg / Too old</a></li>
        <li><a href="validate.php?name=Giorgio Battori&email=giorgio@elte.hu&age=27&room=8bed&travelers=Valentino Pomzi%0D%0AVittorio Trio">Rossz szoba érték / Bad room value</a></li>
        <li><a href="validate.php?name=Giorgio Battori&email=giorgio@elte.hu&age=27&room=6bed-large&travelers=Valentino Pomzi%0D%0ADio">Túl rövid utas név / Too short traveler name</a></li>
        <li><a href="validate.php?name=Giorgio Battori&email=giorgio@elte.hu&age=27&room=6bed-large&travelers=Valentino Pomzi%0D%0AFranz Joseph Otto Robert Maria Anton Karl Max Heinrich Sixtus Xaver Felix Renatus Ludwig Gaetan Pius Ignatius von Habsburg">Túl hosszú utas név / Too long traveler name</a></li>
        <li><a href="validate.php?name=Giorgio Battori&email=giorgio@elte.hu&age=27&room=3bed&travelers=Valentino Pomzi%0D%0AVittorio Trio%0D%0APetro Miklo%0D%0AMireletto Pesto">Túl sok utas / Too many travelers</a></li>
        <li><a href="validate.php?name=Giorgio Battori&email=giorgio@elte.hu&age=27&room=6bed-large&travelers=Valentino Pomzi%0D%0AVittorio Trio">Minden helyes / Everything correct</a></li>
    </ul>

    <h3>Helyben / In-place</h3>
    <ul>
        <li><a href="index.php?">Minden hiányzik / Everything is missing</a></li>
        <li><a href="index.php?name=Franz Joseph Otto Robert Maria Anton Karl Max Heinrich Sixtus Xaver Felix Renatus Ludwig Gaetan Pius Ignatius von Habsburg&email=giorgio@elte.hu&age=27&room=6bed-large&travelers=Valentino Pomzi%0D%0AVittorio Trio">Túl hosszú név / Too long Name</a></li>
        <li><a href="index.php?name=Giorgio Battori&email=giorgio-elte.hu&age=27&room=6bed-large&travelers=Valentino Pomzi%0D%0AVittorio Trio">Helytelen e-mail formátum / Wrong e-mail format</a></li>
        <li><a href="index.php?name=Giorgio Battori&email=giorgio@elte.hu&age=twentyseven&room=6bed-large&travelers=Valentino Pomzi%0D%0AVittorio Trio">Helytelen életkor formátum / Wrong age format</a></li>
        <li><a href="index.php?name=Giorgio Battori&email=giorgio@elte.hu&age=10&room=6bed-large&travelers=Valentino Pomzi%0D%0AVittorio Trio">Túl fiatal / Too young</a></li>
        <li><a href="index.php?name=Giorgio Battori&email=giorgio@elte.hu&age=100&room=6bed-large&travelers=Valentino Pomzi%0D%0AVittorio Trio">Túl öreg / Too old</a></li>
        <li><a href="index.php?name=Giorgio Battori&email=giorgio@elte.hu&age=27&room=8bed&travelers=Valentino Pomzi%0D%0AVittorio Trio">Rossz szoba érték / Bad room value</a></li>
        <li><a href="index.php?name=Giorgio Battori&email=giorgio@elte.hu&age=27&room=6bed-large&travelers=Valentino Pomzi%0D%0ADio">Túl rövid utas név / Too short traveler name</a></li>
        <li><a href="index.php?name=Giorgio Battori&email=giorgio@elte.hu&age=27&room=6bed-large&travelers=Valentino Pomzi%0D%0AFranz Joseph Otto Robert Maria Anton Karl Max Heinrich Sixtus Xaver Felix Renatus Ludwig Gaetan Pius Ignatius von Habsburg">Túl hosszú utas név / Too long traveler name</a></li>
        <li><a href="index.php?name=Giorgio Battori&email=giorgio@elte.hu&age=27&room=3bed&travelers=Valentino Pomzi%0D%0AVittorio Trio%0D%0APetro Miklo%0D%0AMireletto Pesto">Túl sok utas / Too many travelers</a></li>
        <li><a href="index.php?name=Giorgio Battori&email=giorgio@elte.hu&age=27&room=6bed-large&travelers=Valentino Pomzi%0D%0AVittorio Trio">Minden helyes / Everything correct</a></li>
    </ul>

</body>
</html>