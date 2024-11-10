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

const basePossibleDirections = {
  "emptyTile": [0, 1, 2, 3],
  "bridgeTile": [0, 2],
  "mountainTile": [1, 2],
  "oasisTile": [],
}

class Tile{
  placeRail(){
  }

  rotateTile(){

  }

  rotateDirections( numOfTimes){
    this.possibleEntrances.forEach(element => {
      element=(element+numOfTimes)%4
    });
  }

  incTravelDirection(){

  }

  isRailable(){
    return true
  }

  getStyleClass(){
  }
}
class EmptyTile extends Tile{
  constructor(){
    this.possibleEntrances = basePossibleDirections["emptyTile"]
    this.possibleTravelDirection = basePossibleDirections["emptyTile"]
    this.hasStraightRail = False
    this.hasCurveRail = False

    this.rotateDirections(numberOfRotations)
  }

  placeStraightRail(){
    this.hasStraightRail = true
  }

  getStyleClass(){
    if (this.hasStraightRail){
      return "Tile-straight-rail"
    } else if (this.hasCurveRail){
      return "Tile-curve_rail"
    } else {
      return "Tile-empty"
    }
  }

  canHaveStraightRail(){
    return true
  }

  canHaveCurveRail(){
    return true
  }
}

class MountainTile extends Tile{
  constructor(numberOfRotations){
    this.possibleEntrances = basePossibleDirections["mountainTile"]
    this.possibleTravelDirection = basePossibleDirections["mountainTile"]
    this.numberOfRotations = numberOfRotations
    this.hasRail = False

    this.rotateDirections(numberOfRotations)
  }

  getStyleClass(){
    return this.hasRail ? "Tile-mountain_rail": "Tile-mountain"
  }
}

class BridgeTile extends Tile{
  constructor(numberOfRotations){
    this.possibleEntrances = basePossibleDirections["mountainTile"]
    this.possibleTravelDirection = basePossibleDirections["mountainTile"]
    this.numberOfRotations = numberOfRotations
    this.hasRail = False

    this.rotateDirections(numberOfRotations)
  }

  getStyleClass(){
    return this.hasRail ? "Tile-bridge_rail" : "Tile-bridge"
  }
}

class Oasis extends Tile{

  isRailable(){
    return false
  }

  getStyleClass(){
    return "Tile-oasis"
  }
}

const baseEasyLevels = [
  [
    [new EmptyTile(), new MountainTile(1), new EmptyTile(), new EmptyTile(), new Oasis()],
    [new EmptyTile(), new EmptyTile(), new EmptyTile(), new BridgeTile(0), new Oasis()],
    [new BridgeTile(), new EmptyTile(), new MountainTile(2), new EmptyTile(), new EmptyTile()],
    [new EmptyTile(), new EmptyTile(), new EmptyTile(), new Oasis(), new EmptyTile()],
    [new EmptyTile(), new EmptyTile(), new MountainTile(3), new EmptyTile(), new EmptyTile()]
  ],
  [
    [new Oasis(), new EmptyTile(), new BridgeTile(3), new EmptyTile(), new EmptyTile()],
    [new EmptyTile(), new MountainTile(2), new EmptyTile(), new EmptyTile(), new MountainTile(2)]
    [new BridgeTile(0), new Oasis(), new MountainTile(3), new EmptyTile(), new EmptyTile()]
    [new EmptyTile(),new EmptyTile(),new EmptyTile(), new Oasis(),new EmptyTile()]
    [new EmptyTile(),new EmptyTile(),new EmptyTile(),new EmptyTile(),new EmptyTile()]
  ],
  [
    [new EmptyTile(),new EmptyTile(),new BridgeTile(3), new EmptyTile(),new EmptyTile()],
    [new EmptyTile(),new EmptyTile(),new EmptyTile(),new EmptyTile(),new BridgeTile(2)],
    [new EmptyTile(), new MountainTile(2), new BridgeTile(0), new EmptyTile(),new EmptyTile()],
    [new EmptyTile(), new Oasis(), new EmptyTile(),new EmptyTile(),new EmptyTile()],
    [new EmptyTile(), new BridgeTile(3), new EmptyTile(),new EmptyTile(), new MountainTile(2)]
  ],
  [
    [new EmptyTile(),new EmptyTile(),new EmptyTile(), new BridgeTile(3), new EmptyTile(),]
    [new EmptyTile(),new EmptyTile(),new EmptyTile(),new EmptyTile(),new EmptyTile()],
    [new BridgeTile(0), new EmptyTile(), new MountainTile(2), new EmptyTile(), new MountainTile(2)],
    [new EmptyTile(),new EmptyTile(),new EmptyTile(),new EmptyTile(),new EmptyTile()],
    [new EmptyTile(),new EmptyTile(), new Oasis(), new BridgeTile(3), new EmptyTile(),]
  ],
  [
    [new EmptyTile(),new EmptyTile(), new BridgeTile(3), new EmptyTile(),new EmptyTile()],
    [new EmptyTile(), new MountainTile(0), new EmptyTile(),new EmptyTile(),new EmptyTile()],
    [new BridgeTile(0), new EmptyTile(),new EmptyTile(), new MountainTile(3), new EmptyTile(),],
    [new EmptyTile(),new EmptyTile(), new BridgeTile(0), new Oasis(), new EmptyTile()],
    [new EmptyTile(), new MountainTile(2), new EmptyTile(), new EmptyTile(),new EmptyTile()]
    ]
]


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
    console.log(size)
    // Clear any existing cells
    playfield.innerHTML = "";
  
    // Apply appropriate class for grid size
    playfield.className = size === 5 ? "small" : "large";
  
    // Generate cells for the matrix
    for (let i = 0; i < size * size; i++) {
      const cell = document.createElement("div");
      cell.classList.add("Tile-empty")
      // Optionally, set a background image for each cell
      //cell.style.backgroundImage = "url('path/to/image.png')";
  
      playfield.appendChild(cell);
    }
  }
  
function initGame(){
  const playerName = document.querySelector("#playerNameInput").value
  console.log(playerName)
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
