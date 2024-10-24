
// Source: /home/zoltanjeney/Documents/ELTE directories/ELTE 3rd semester/WebProg/AllSrc.js


// Source: /home/zoltanjeney/Documents/ELTE directories/ELTE 3rd semester/WebProg/09-26/4/index.js
document.addEventListener("input", onInput)

function onInput(e){
    if (e.target.matches(".szam")){
        if (e.target.value.match(/\s/) || isNaN(e.target.value)){
            //\s a spaceket talalja meg
            e.target.value = e.target.value.slice(0,-1)
            //ez esetben az eddigi marad az ertek, -1 a veget vagja le
        }
    }
}

// Source: /home/zoltanjeney/Documents/ELTE directories/ELTE 3rd semester/WebProg/09-26/3/index.js
const list = document.querySelector("ul")

list.addEventListener("click",onClick)

let x = false
let prev

function onClick(e){
    if (e.target.matches("ul > li")){
        if (!x){
            prev = e.target
            x = true
        } 
        else {
            let temp = prev.innerText
            prev.innerText = e.target.innerText
            e.target.innerText = temp            
            x = false
        }
    }
}

// Source: /home/zoltanjeney/Documents/ELTE directories/ELTE 3rd semester/WebProg/09-26/8/index.js
let x
let arr = []

let players

const startBtn = document.querySelector("#startBtn")

startBtn.addEventListener("click", setup)

const steps = document.querySelector("#steps")

const currentPlayer = document.querySelector("#currentPlayer")

const table = document.querySelector("tbody")
table.addEventListener("click", onClick)

let turnedUp = 0

let prevCell
let canPlay = true
function onClick(e){
    //ha cellara klikkelunk, nem varunk es a cella ures
    if (e.target.matches("td")&& canPlay && e.target.innerText == ""){
        //cella kiszamolasa
        const row = e.target.closest("tr").rowIndex
        const col = e.target.closest("td").cellIndex
        const cell = table.rows[row].cells[col]
        cell.innerText = arr[row*x +col]
        turnedUp++
        if(turnedUp%2 == 0){
            //leptunk
            steps.innerText++
            currentPlayer.innerText = steps.innertext %players + 1
            if (cell.innerText != prevCell.innerText){
                canPlay = false
                setTimeout(() => {
                    canPlay = truecelll.innerText = ""
                    prevCell.innerText=""
                }, 500)
                turnedUp =-2
            } else if (turnedUp == x*x){
                stepsinnerText += "Kész"
            }
        }
    }
}

// Source: /home/zoltanjeney/Documents/ELTE directories/ELTE 3rd semester/WebProg/09-26/2/index.js
const px = document.querySelector("body > p:nth-of-type(1) > span")
const time = document.querySelector("body > p:nth-of-type(2) > span")

let prevX = 0
let prevY = 0

let distSum = 0
let clicks = 0

let prevT = 0
let tSum = 0

document.addEventListener("click", onClick)

function onClick(e){
    clicks++
    calcAvgDist(e)
    calcAvgTime(e)
}

function calcAvgDist(e){
    console.log(`e.screenX - prevX ${e.screenX} - ${prevX}, e.screenY - prevY: ${e.screenY} - ${prevY}`)

    distSum += Math.sqrt(Math.pow(e.screenX - prevX, 2) + Math.pow(e.screenY - prevY, 2))
    px.innerText = distSum / clicks
    prevX = e.screenX
    prevY = e.screenY
}

function calcAvgTime(e){
    if (prevT == 0)
        prevT = Date.now()
    tSum += Date.now() - prevT
    time.innerText = tSum / clicks / 1000
    prevT = Date.now()
}

// Source: /home/zoltanjeney/Documents/ELTE directories/ELTE 3rd semester/WebProg/09-26/7/index.js
document.querySelector("tbody").addEventListener("click", onClick)

let x = true

function onClick(e){
    if (e.target.innerText==""){
        e.target.innerText=x?"X":"0"
        x=!x
    }
}

// Source: /home/zoltanjeney/Documents/ELTE directories/ELTE 3rd semester/WebProg/09-26/1/index.js
const p = document.querySelector("p")
p.addEventListener("click", onClick)

function onClick(e){
    //a)
    console.log(this)
    //b)
    console.log(e.type)
    //c)
    console.log(e.button)
    //d)
    console.log(`${e.screenX}, ${e.screenY}`)
    //e)
    console.log(e.target)
    //f)
    if (e.target.matches("p span")){
        console.log(e.target.innerText)
    }
    //g)
    if (e.target.matches("p a")){
        if (e.target.innerText == "libero"){
            e.preventDefault()
        }
    }

}

// Source: /home/zoltanjeney/Documents/ELTE directories/ELTE 3rd semester/WebProg/09-26/5/index.js
document.addEventListener("click", onClick)

function onClick(e){
    if (e.target.matches("a")){
        if (!e.target.href.includes("elte")){
            e.preventDefault()
        }
    }
}

// Source: /home/zoltanjeney/Documents/ELTE directories/ELTE 3rd semester/WebProg/gyakorlas/feladatsor_3/6/cinema.js
const movies = [
    {
        "title": "Inception",
        "year": 2010,
        "duration": 148,
        "director": "Christopher Nolan"
    },
    {
        "title": "The Matrix",
        "year": 1999,
        "duration": 136,
        "director": "The Wachowskis"
    },
    {
        "title": "Interstellar",
        "year": 2014,
        "duration": 169,
        "director": "Christopher Nolan"
    },
    {
        "title": "Pulp Fiction",
        "year": 1994,
        "duration": 154,
        "director": "Quentin Tarantino"
    },
    {
        "title": "The Godfather",
        "year": 1972,
        "duration": 175,
        "director": "Francis Ford Coppola"
    },
    {
        "title": "The Dark Knight",
        "year": 2008,
        "duration": 152,
        "director": "Christopher Nolan"
    },
    {
        "title": "Fight Club",
        "year": 1999,
        "duration": 139,
        "director": "David Fincher"
    },
    {
        "title": "Forrest Gump",
        "year": 1994,
        "duration": 142,
        "director": "Robert Zemeckis"
    },
    {
        "title": "The Lord of the Rings: The Fellowship of the Ring",
        "year": 2001,
        "duration": 178,
        "director": "Peter Jackson"
    },
    {
        "title": "The Lord of the Rings: The Two Towers",
        "year": 2002,
        "duration": 179,
        "director": "Peter Jackson"
    },
    {
        "title": "The Lord of the Rings: The Return of the King",
        "year": 2003,
        "duration": 201,
        "director": "Peter Jackson"
    },
    {
        "title": "The Shawshank Redemption",
        "year": 1994,
        "duration": 142,
        "director": "Frank Darabont"
    },
    {
        "title": "The Social Network",
        "year": 2010,
        "duration": 120,
        "director": "David Fincher"
    },
    {
        "title": "Gladiator",
        "year": 2000,
        "duration": 155,
        "director": "Ridley Scott"
    },
    {
        "title": "The Wolf of Wall Street",
        "year": 2013,
        "duration": 180,
        "director": "Martin Scorsese"
    },
    {
        "title": "Schindler's List",
        "year": 1993,
        "duration": 195,
        "director": "Steven Spielberg"
    },
    {
        "title": "Titanic",
        "year": 1997,
        "duration": 195,
        "director": "James Cameron"
    },
    {
        "title": "Avatar",
        "year": 2009,
        "duration": 162,
        "director": "James Cameron"
    },
    {
        "title": "Braveheart",
        "year": 1995,
        "duration": 178,
        "director": "Mel Gibson"
    },
    {
        "title": "The Grand Budapest Hotel",
        "year": 2014,
        "duration": 99,
        "director": "Wes Anderson"
    }
]

const tbody = document.querySelector("tbody")
document.addEventListener("DOMContentLoaded", createHeaderForMoviesTable)
document.addEventListener("DOMContentLoaded", updatetBody)

function createHeaderForMoviesTable(e){
    header = tbody.insertRow(0)
    for (let key in movies[0]){
        let newCell = document.createElement("th")
        newCell.textContent = key
        header.appendChild(newCell)
    }
}

const searchBar = document.getElementById("search")
const searchBtn = document.getElementById("searchBtn")

searchBtn.addEventListener("click", updatetBody)

function updatetBody(e){
    filterString = searchBar.value
    filterStringIsEmpty = filterString === ""
    if (!filterStringIsEmpty){
        tbody.innerHTML = ""
        createHeaderForMoviesTable()
    }
    if (tbody.rows.length != 1 && filterStringIsEmpty){
        return
    }
    for (let movieIndex in movies){
        if (filterString === "" || movies[movieIndex].title.toLowerCase().includes(filterString.toLowerCase())) {
            newRow = tbody.insertRow(-1)
            for (let propertyKey in movies[movieIndex]){
                let newCell = document.createElement("td")
                let newText = document.createTextNode(movies[movieIndex][propertyKey])
                newCell.appendChild(newText)
                newRow.appendChild(newCell)
            }
        }
    }
}






// Source: /home/zoltanjeney/Documents/ELTE directories/ELTE 3rd semester/WebProg/09-11/gcd.js
function gcd(a,b){
    if (a<b){
        c=a
        a=b
        b=c
    }
    remainder = a %b
    while (remainder > 0){
        a = b
        b = remainder
        remainder = a % b
    }
    return b
}

const a = parseInt(prompt("A:"))
const b = parseInt(prompt("A:"))

alert(`The greatest common divisor of the provided numbers is ${gcd(a,b)}`)

// Source: /home/zoltanjeney/Documents/ELTE directories/ELTE 3rd semester/WebProg/09-11/countEvens.js
const array = [1, 2, 3, 4, 5, 6, 7]

console.log(`Number of evens in array: ${array.reduce((a,b)=> a+ (b%2 == 0 ? 1 : 0), 0)}`)

// Source: /home/zoltanjeney/Documents/ELTE directories/ELTE 3rd semester/WebProg/09-11/planes.js
function whichPlane(x, y){
    if (x==0 || y == 0){
        return 0
    }
    else if (x>0 && y>0){
        return 1
    }
    else if (x<0 && y>0){
        return 2
    }
    else if (x<0 && y<0){
        return 3
    }
    else if (x>0 && y<0){
        return 4
    }
}

const x = parseInt(prompt("X:"))
const y = parseInt(prompt("Y:"))

const result = whichPlane(x,y)
if (result > 0){
    alert(`The coordinates you provided are located in the ${result}. plane quadrant`)
} else {
    alert(`The provided point does not belong to any of the plane quadrants`)
}


// Source: /home/zoltanjeney/Documents/ELTE directories/ELTE 3rd semester/WebProg/09-11/osztok.js
function aisdivisorofb(a, b){
    return b%a ===0;
}

const a = parseInt(prompt("Number A:"));
const b = parseInt(prompt("Number B:"));

console.log(`A is divisor of B: ${aisdivisorofb(a,b)}`)

// Source: /home/zoltanjeney/Documents/ELTE directories/ELTE 3rd semester/WebProg/09-11/searchFirstNegative.js
const array = [1, 2, 3, 5, 6]

function searchFirstNegative(l){
    i = 0
    while ( i< l.length && l[i]>0){
        i++
    } 
    if (i<l.length){
        return l[i]
    }
    else {
        return null
    }
}


console.log(searchFirstNegative(array))
console.log(l.find(a => a<0))

// Source: /home/zoltanjeney/Documents/ELTE directories/ELTE 3rd semester/WebProg/09-11/percentage.js
function xpercentageof(num, p){
    return num*p/100
}

console.log(` 105% of 7 is: ${xpercentageof(7, 105)}`)

// Source: /home/zoltanjeney/Documents/ELTE directories/ELTE 3rd semester/WebProg/09-11/c-to-fahr.js
function f2c(f){
    return (f - 32)* 5/9
}

console.log(`50 fahrenheit to celsius: ${f2c(50)}`)

// Source: /home/zoltanjeney/Documents/ELTE directories/ELTE 3rd semester/WebProg/09-11/readtrianglesides.js
const a = parseInt(prompt("side a:"));
const b = parseInt(prompt("side b:"));
const c = parseInt(prompt("side c:"));

if (a+b>c && a+c>b && b+c>a){
    console.log("The sides can make up a valid triangle");
}
else{
    console.log("The lengths you provided as the sides of a triangle are invalid!")
}

// Source: /home/zoltanjeney/Documents/ELTE directories/ELTE 3rd semester/WebProg/09-11/hello.js
console.log("Hello world")

// Source: /home/zoltanjeney/Documents/ELTE directories/ELTE 3rd semester/WebProg/09-19/10/counter.js

const substractButton = document.querySelector("#substract")
const addButton = document.querySelector("#add")
const numElement = document.querySelector("#num")

let num = numElement.value

addButton.addEventListener("click", addToNum)
substractButton.addEventListener("click",substractFromNum)

const min = 0
const max = 500

const delay = 800 //800 ms
const rate = 100
const delay2 = 3000
const rate2 = 30

let delayTimer
let rateTimer



function addToNum(){
    numElement.value = ++num
    updateDisable()
}
function substractFromNum(){
    numElement.value = --num
    updateDisable()
}
updateDisable()
function updateDisable(){
    addButton.disabled = (num ==max)
    substractButton.disabled = (num == min)
    if ((num ==min ) || (num == max)){
        killTimers()
    }
}

addButton.addEventListener("mousedown", () => {
    delayTimer = setTimeout(() => {
        rateTimer = setInterval(addToNum, rate)
    }, delay)
})

addButton.addEventListener("mouseup", killTimers)

substractButton.addEventListener("mousedown", () => {
    delayTimer = setTimeout(() => {
        rateTimer = setInterval(addToNum, rate)
    }, delay)
})

substractButton.addEventListener("mouseup", killTimers)

function killTimers(){
    clearTimeout(delayTimer)
    clearTimeout(rateTimer)
}

// Source: /home/zoltanjeney/Documents/ELTE directories/ELTE 3rd semester/WebProg/09-19/4/circlearea.js
const btn = document.querySelector("#btn")
const num = document.querySelector("#num")
const output = document.querySelector("output")
btn.addEventListener("click", circleArea)
function circleArea(){
    const r = num.value
    output.innerHTML = `Circle area: ${r * r * Math.PI}`
}

// Source: /home/zoltanjeney/Documents/ELTE directories/ELTE 3rd semester/WebProg/09-19/3/mult_table.js
const numInput = document.querySelector("#num")
const button = document.querySelector("#btn")
const tbody = document.querySelector("tbody")

button.addEventListener("click", degenerateTable)

function degenerateTable(){
    const n = numInput.value
    let row = tbody.insertRow()
    row.insertCell().textContent = 0
    for(let i=1; i<=n; i++){
        row.insertCell().textContent = i
    }
    for(let i=1; i<=n; i++){
        row = tbody.insertRow()
        row.insertCell().textContent = i
        for (let j = 1; j<=n; j++){
            row.insertCell().textContent = i * j
        }
    }
}

// Source: /home/zoltanjeney/Documents/ELTE directories/ELTE 3rd semester/WebProg/09-19/2/n_times_hw.js
const output = document.querySelector("output");
const numField = document.querySelector("#num");
const button = document.querySelector("#btn");
button.addEventListener("click", hello )

function hello(){
    for (let i=0; i<numField.value; i++){
        const p = document.createElement("p");
        p.textContent = "Hello world!";
        p.style.fontSize = 12 + i + "px";
        output.appendChild(p);
    }
}

// Source: /home/zoltanjeney/Documents/ELTE directories/ELTE 3rd semester/WebProg/09-19/6/copy.js
const sourceInput = document.querySelector("#src")
const destInputs = document.querySelectorAll("input.dest")
const button = document.querySelector("#button")

button.addEventListener("click", copySrcToDest)

function copySrcToDest(){
    for (const destInput of destInputs){
        destInput.value = sourceInput.value

    }
}

// Source: /home/zoltanjeney/Documents/ELTE directories/ELTE 3rd semester/WebProg/09-19/13/guess.js
const rand = Math.floor(Math.random() *100)
const guess = document.querySelector("#num")
const button = document.querySelector("#guess")
const output = document.querySelector("output")
alert(rand);
button.addEventListener("click", () => output.innerHTML = guess.value < rand ? "Guess is smaller" : guess.value > rand ? "Guess is bigger" : "Match")

// Source: /home/zoltanjeney/Documents/ELTE directories/ELTE 3rd semester/WebProg/09-19/5/loadimg.js
const text = document.querySelector("#text")
const button = document.querySelector("#btn")
const img = document.querySelector("img")

button.addEventListener("click", loadImg)

function loadImg(){
    img.src = text.value
}
