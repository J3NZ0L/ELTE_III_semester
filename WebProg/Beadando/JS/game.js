import { baseEasyLevels } from "./levels.js"

const startButton = document.querySelector("#buttonStartGame")
const gamePageDiv = document.querySelector("#gamePage")
const menuPageDiv = document.querySelector("#menuPage")

const inGamePlayerNameP = document.querySelector("#inGamePlayerName")

const difficultyButtons = document.querySelectorAll(".difficulty-button")

let startTime;
let timerInterval;

startButton.addEventListener("click", onStartButtonClick)

difficultyButtons.forEach(diffButton => {
  diffButton.addEventListener("click", toggleSelectedDifficulty)
})

function toggleSelectedDifficulty(e){
  if (!e.currentTarget.classList.contains("selected")) {
    difficultyButtons.forEach(diffButton => {
      diffButton.classList.toggle("selected")
    })
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
    const playfield = document.querySelector("#playfield");
    const size = parseInt(document.querySelector(".difficulty-button.selected").textContent.charAt(0))
    // Clear any existing cells
    playfield.innerHTML = "";
  
    // Apply appropriate class for grid size
    playfield.className = size === 5 ? "easy" : "hard";

    let numberOfLevel = 4//Math.floor(Math.random()*5)
    console.log("N: ", numberOfLevel)
    // Generate cells for the matrix
    let numOfRotations = 0
    for (let i = 0; i < size; i++) {
      for (let j =0; j< size; j++){
        const cell = document.createElement("div");
        cell.classList.add(baseEasyLevels[numberOfLevel][i][j].getStyleClass())
        if (baseEasyLevels[numberOfLevel][i][j].isToBeRotated()){
          numOfRotations = baseEasyLevels[numberOfLevel][i][j].getNumberOfRotations()
          cell.classList.add(`rotate-${numOfRotations*90}`)
        }
        playfield.appendChild(cell);

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
