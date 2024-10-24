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