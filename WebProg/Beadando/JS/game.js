import { baseEasyLevels, baseHardLevels } from "./levels.js"

const startButton = document.querySelector("#buttonStartGame")
const gamePageDiv = document.querySelector("#gamePage")
const menuPageDiv = document.querySelector("#menuPage")
const gameFieldDiv = document.querySelector("#gameField")


const inGamePlayerNameP = document.querySelector("#inGamePlayerName")

const difficultyButtons = document.querySelectorAll(".difficulty-button")

let startTime;
let timerInterval;

let difficulty = "hard";

let size;

let gameFieldModel;

startButton.addEventListener("click", onStartButtonClick)

difficultyButtons.forEach(diffButton => {
  diffButton.addEventListener("click", toggleSelectedDifficulty)
})

gameFieldDiv.addEventListener("click", onPrimaryMouseButtonClick)

gameFieldDiv.addEventListener("contextmenu", onSecondaryMouseButtonClick)



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
  const siblingDivs = Array.from(closestDiv.parentNode.querySelectorAll("div")); // Get all div siblings
  const flatIndex = siblingDivs.indexOf(closestDiv); // Get the index of the clicked div
  // calculate the appropriate matrix index
  const x = Math.floor(flatIndex/size);
  const y = flatIndex%size;
  return [x, y]
}

function toggleSelectedDifficulty(e){
  if (!e.currentTarget.classList.contains("selected")) {
    difficultyButtons.forEach(diffButton => {
      diffButton.classList.toggle("selected")
    })
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
  const gameFieldDivs = Array.from(document.querySelector("#gameField").querySelectorAll("div"));

  const x = arrOfCoords[0]
  const y = arrOfCoords[1]
  const flatIndex = x*size+y

  const numOfRotations = gameFieldModel[x][y].getNumberOfRotations()
  const regex = new RegExp(`\\b${numOfRotations}\\b`);

  for (let className of gameFieldDivs[flatIndex].classList) {
    if (className.includes("Tile")) {
      gameFieldDivs[flatIndex].classList.replace(className, gameFieldModel[x][y].getStyleClass()); 
    }
    if (className.includes("rotate") && !regex.test(className)){

      gameFieldDivs[flatIndex].classList.replace(className, `rotate-${numOfRotations*90}`);
    } else if (!className.includes("rotate") && numOfRotations>0){

      gameFieldDivs[flatIndex].classList.add(`rotate-${numOfRotations*90}`)
    }
  }
}

function onStartButtonClick(e){
    toggleMenuAndGamePage()
    createStaticMatrix();
    initGame();
}

const tileTypes = ["emptyTile", "bridgeTile", "mountainTile", "oasisTile"]

const railedTileTypes = {
  "emptyTile": ["curve-rail", "straight-rail"],
  "bridgeTile": "bridge-rail",
  "mountainTile": "mountain-rail",
}

const possibleTravelDirectionMap = {
}

const Direction = Object.freeze({
  NORTH: 0,
  EAST: 1,
  SOUTH: 2,
  WEST: 3
})

function isCoordinateOfSide(x, y){
  return x==0 || y==0 || x==size-1 || y==size-1
}


function toggleMenuAndGamePage(){
    menuPageDiv.classList.toggle("hidden");
    gamePageDiv.classList.toggle("hidden");
}


function createStaticMatrix() {
    const gamefield = document.querySelector("#gameField");
    size = parseInt(document.querySelector(".difficulty-button.selected").textContent.charAt(0))
    // Clear any existing cells
    gamefield.innerHTML = "";
  
    // Apply appropriate class for grid size
    gamefield.className = size === 5 ? "easy" : "hard";

    let numberOfLevel = Math.floor(Math.random()*5)
    // Generate cells for the matrix
    let numOfRotations = 0
    let levels = size === 5 ? baseEasyLevels : baseHardLevels; 

    gameFieldModel = levels[numberOfLevel]

    for (let i = 0; i < size; i++) {
      for (let j =0; j< size; j++){
        const cell = document.createElement("div");
        cell.classList.add(gameFieldModel[i][j].getStyleClass())
        if (gameFieldModel[i][j].isToBeRotated()){
          numOfRotations = gameFieldModel[i][j].getNumberOfRotations()
          cell.classList.add(`rotate-${numOfRotations*90}`)
        }
        gamefield.appendChild(cell);

      }  
    }
  }
  
function initGame(){
  const playerName = document.querySelector("#playerNameInput").value
  inGamePlayerNameP.innerHTML = playerName

  startTime = Date.now(); // Capture the starting time (milliseconds)
  timerInterval = setInterval(updateElapsedTime, 1000); // Update every second
}

function updateElapsedTime() {
  // Calculate elapsed time in seconds
  let elapsedTime = Math.floor((Date.now() - startTime) / 1000);

  // Calculate minutes and seconds
  let minutes = Math.floor(elapsedTime / 60);  // Divide by 60 to get minutes
  let seconds = elapsedTime % 60;  // Remainder is the seconds

  // Format minutes and seconds with leading zeros if necessary
  minutes = minutes < 10 ? '0' + minutes : minutes;
  seconds = seconds < 10 ? '0' + seconds : seconds;

  // Update the displayed time
  document.querySelector("#elapsedTime").textContent = `${minutes}:${seconds}`;
}

// To stop the timer when the game ends (e.g., on game over):
function stopGame() {
  clearInterval(timerInterval); // Stop the timer
}
