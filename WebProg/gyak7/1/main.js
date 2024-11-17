const loc = document.querySelector("#loc")
const lat = document.querySelector("#lat")
const lon = document.querySelector("#lon")
const table = document.querySelector("#tbody")

document.querySelector("#locbtn").
addEventListener("click", () => getCoordinates(loc.value))
document.querySelector("#wtrbtn").
addEventListener("click", () => getWeather())

const days = ["Vasárnap", "Hétfő", "Kedd", "Szerda", "Csütörtök", "Péntek", "Szombat"]

async function getCoordinates(x){
    const response = await fetch(`https://nominatim.openstreetmap.org/search?q=${x}&format=json`)
    // aszinkron fuggvenyhivas, hatterben fusson, nem blokkolja igy a fo szalat - ezen belul van az await azaz varas
    const json = await response.json()
    console.log(json)
    const place = json[0]
    lat.value = place.lat
    lon.value = place.lon
    console.log(`Coordinates: ${place.lat}, ${place.lon}`)
    //getWeather()
    document.querySelector("#weather").hidden = false
}

async function getWeather(){
    const response = await fetch(`https://api.openweathermap.org/data/2.5/forecast?lat=${lat.value}&lon=${lon.value}&units=metric&lang=hu&appid=e4e2f880a497d44e56bacd76053336b1`)

    const json = response.json()
    console.log(json)

    fillTable(json.list)
}

function fillTable(list){
    for (let i=0; i<10; i++){
        const date = new Date(list[i].dt * 1000)

        let cell = table.rows[1].insertCell()
        cell.innerText = days[date.getDay()]
        cell.appendChild(document.createElement("img")).src = `http://openweathermap.org/img/wn/${list[i].weather[0].icon}@2x.png`
        
        cell = table.rows[2].insertCell()
        cell.innerText =  list[i].weather[0].description

        cell = table.rows[3].insertCell()
        cell.innerText = list[i].main.temp + ("˚C")

        cell = table.rows[4].insertCell()
        cell.innerText = list[i].clouds.all + "%"

        cell = table.rows[5].insertCell()
        cell.innerText = date.toLocaleTimeString("hu").slice(0,-3)
    }
}