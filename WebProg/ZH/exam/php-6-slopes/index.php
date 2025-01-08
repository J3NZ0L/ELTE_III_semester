<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Task 6</title>
    <link rel="stylesheet" href="src/index.css">
</head>
<body>
    <h1>6. Sípályák / Slopes</h1>
    <form>
        <label for="name">Név / Name</label>
        <input type="text" name="name" id="name">

        <label for="length">Hossz (m) / Length (m)</label>
        <input type="number" name="length" id="length">

        <label for="color">Szín / Color</label>
        <select name="color" id="color">
            <option value="green">Zöld / Green</option>
            <option value="blue">Kék / Blue</option>
            <option value="red">Piros / Red</option>
            <option value="black">Fekete / Black</option>
        </select>

        <label for="danger">Veszélyek / Dangers</label>  
        <div><input type="checkbox" name="danger[]" id="danger-learners" value="learners"><label for="danger-learners">Tanulók / Learners</label></div>
        <div><input type="checkbox" name="danger[]" id="danger-ice" value="ice"><label for="danger-ice">Jég / Ice</label></div>
        <div><input type="checkbox" name="danger[]" id="danger-fog" value="fog"><label for="danger-fog">Köd / Fog</label></div>
        <div><input type="checkbox" name="danger[]" id="danger-accident" value="accident"><label for="danger-accident">Baleset / Accident</label></div>
        
        <input type="submit" value="+">
    </form>

    <table>
        <thead>
            <tr>
                <th>Név / Name</th>
                <th>Szín / Color</th>
                <th>Törlés / Delete</th>
                <th>Szerkesztés / Edit</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td>Signal</td>
                <td>green</td>
                <td>🚯</td>
                <td>✏️</td>
            </tr>
        </tbody>
    </table>
</body>
</html>