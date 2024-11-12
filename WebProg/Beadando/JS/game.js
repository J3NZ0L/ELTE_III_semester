import { baseEasyLevels, baseHardLevels } from "./levels.js"

const startButton = document.querySelector("#buttonStartGame")
const gamePageDiv = document.querySelector("#gamePage")
const menuPageDiv = document.querySelector("#menuPage")
const gameFieldDiv = document.querySelector("#gameField")
const difficultyButtons = document.querySelectorAll(".difficulty-button")
const elapsedTimeSpan = document.querySelector("#elapsedTime")
const inGamePlayerNameP = document.querySelector("#inGamePlayerName")

let startTime
let timerInterval

let difficulty = "hard"
let size

let gameFieldModel

startButton.addEventListener("click", onStartButtonClick)

gameFieldDiv.addEventListener("click", onPrimaryMouseButtonClick)

gameFieldDiv.addEventListener("contextmenu", onSecondaryMouseButtonClick)

difficultyButtons.forEach(diffButton => {
  diffButton.addEventListener("click", toggleSelectedDifficulty)
})

function toggleMenuAndGamePage(){
  menuPageDiv.classList.toggle("hidden")
  gamePageDiv.classList.toggle("hidden")
}

function onPrimaryMouseButtonClick(e){
  if (e.target.matches('#gameField div')){
    tryToPlaceRail(getXY(e))
  }
}

function onSecondaryMouseButtonClick(e){
  if (e.target.matches('#gameField div')){
    e.preventDefault()
    tryToRotateRail(getXY(e))
  }
}

function getXY(e){
  const closestDiv = e.target.closest("div")
  const siblingDivs = Array.from(closestDiv.parentNode.querySelectorAll("div")) // collect all div siblings into an array
  const flatIndex = siblingDivs.indexOf(closestDiv) // get flat index of the div
  // calculate the corresponding matrix index
  const x = Math.floor(flatIndex/size)
  const y = flatIndex%size
  return [x, y]
}

function toggleSelectedDifficulty(e){
  if (!e.currentTarget.classList.contains("selected")) {
    difficultyButtons.forEach(diffButton => {
      diffButton.classList.toggle("selected")
    })
  }
  if (size==5){
    console.log("belep7re")
    size==7
  } else {
    console.log("belep5re")
    size==5
  }
  
}

function tryToPlaceRail(arrOfCoords){
  const x = arrOfCoords[0]
  const y = arrOfCoords[1]
  if (gameFieldModel[x][y].isRailable()){
    if (gameFieldModel[x][y].placeRail()){
      updateDisplay(arrOfCoords)
    }
  }
}

function tryToRotateRail(arrOfCoords){
  const x = arrOfCoords[0]
  const y = arrOfCoords[1]
  if (gameFieldModel[x][y].isRotatable()){
    gameFieldModel[x][y].rotate()
    updateDisplay(arrOfCoords)
  }
}

function updateDisplay(arrOfCoords){
  const gameFieldDivs = Array.from(document.querySelector("#gameField").querySelectorAll("div"))

  const x = arrOfCoords[0]
  const y = arrOfCoords[1]
  const flatIndex = x*size+y

  const numOfRotations = gameFieldModel[x][y].getNumberOfRotations()
  const regex = new RegExp(`\\b${numOfRotations}\\b`) // regex for searching for the value of numOfRotations in the list of classes
  const currentClassName = gameFieldModel[x][y].getStyleClass()

  for (let className of gameFieldDivs[flatIndex].classList) {
    if (className.includes("Tile") && className !== currentClassName) {
      gameFieldDivs[flatIndex].classList.replace(className, currentClassName) 
    }
    if (className.includes("rotate") && !regex.test(className)){
      gameFieldDivs[flatIndex].classList.replace(className, `rotate-${numOfRotations*90}`)
    } else if (!className.includes("rotate") && numOfRotations>0){
      gameFieldDivs[flatIndex].classList.add(`rotate-${numOfRotations*90}`)
    }
  }
}

function onStartButtonClick(e){
    toggleMenuAndGamePage()
    createStaticMatrix()
    initGame()
}

// currently unused (might be needed for a feature later)
function isCoordinateOfSide(x, y){
  return x==0 || y==0 || x==size-1 || y==size-1
}

function createStaticMatrix() {
    size = parseInt(document.querySelector(".difficulty-button.selected").textContent.charAt(0))
    
    // apply class corresponding to grid size
    gameFieldDiv.className = size === 5 ? "easy" : "hard"

    let numberOfLevel = Math.floor(Math.random()*5)
    // Generate cells for the matrix
    let numOfRotations = 0
    let levels = size === 5 ? baseEasyLevels : baseHardLevels 

    gameFieldModel = levels[numberOfLevel]

    for (let i = 0; i < size; i++) {
      for (let j =0; j< size; j++){
        const cell = document.createElement("div")
        cell.classList.add(gameFieldModel[i][j].getStyleClass())
        if (gameFieldModel[i][j].isToBeRotated()){
          numOfRotations = gameFieldModel[i][j].getNumberOfRotations()
          cell.classList.add(`rotate-${numOfRotations*90}`)
        }
        gameFieldDiv.appendChild(cell)
      }  
    }
  }
  
function initGame(){
  const playerName = document.querySelector("#playerNameInput").value
  inGamePlayerNameP.innerHTML = playerName

  // start and set the timer to update
  startTime = Date.now()
  timerInterval = setInterval(updateElapsedTime, 1000) 
}

function updateElapsedTime() {
  let elapsedTime = Math.floor((Date.now() - startTime) / 1000) // in seconds

  let minutes = Math.floor(elapsedTime / 60)  
  let seconds = elapsedTime % 60 

  // format minutes and seconds with optional leading zeros
  minutes = minutes < 10 ? '0' + minutes : minutes
  seconds = seconds < 10 ? '0' + seconds : seconds

  // update displayed time
  elapsedTimeSpan.textContent = `${minutes}:${seconds}`
}

// currently unused (might be needed for a feature later)
function stopTimer() {
  clearInterval(timerInterval)
}
