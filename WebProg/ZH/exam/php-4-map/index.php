<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Task 4</title>
    <link rel="stylesheet" href="src/index.css">
</head>
<body>
    <h1></h1>
    <ul>
        <li data-id="example">Példa / Example (500 m)</li>
    </ul>
    <div>
        <svg width="1040" height="658">
            <image xlink:href="src/alpes10.gif" x="0" y="0" height="658px" width="1040px"/>
            <!-- Ilyen köröket és szövegeket kell a megfelelő helyre kirajzolni -->
            <!-- You need to draw circles and texts like this to the right place -->
            <circle
                cx="100"
                cy="200"
                fill="red"
                r="10"
                data-id="example"
            />
            <text
                x="100"
                y="180"
                fill="red"
                data-id="example"

                font-size="15"
                text-anchor="middle"
                alignment-baseline="middle"
                font-weight="bold"
            >Példa / Example</text>
        </svg>
    </div>
    

    <script src="src/script.js"></script> <!-- Ebben nem kell semmit sem csinálnod! / You don't have to do anything in this. -->
</body>
</html>