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
    //toggleDisplay(gamePageDiv);
    toggleMenuAndGamePage()
    createMatrix();
    initGame();
}

class Tile{

}

function toggleDisplay(element) {
    element.classList.toggle("hidden");
}

function toggleMenuAndGamePage(){
    menuPageDiv.classList.toggle("hidden");
    gamePageDiv.classList.toggle("hidden");
}

function createMatrix() {
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
  document.getElementById("elapsedTime").textContent = `${minutes}:${seconds}`;
}

// To stop the timer when the game ends (e.g., on game over):
function stopGame() {
  clearInterval(timerInterval); // Stop the timer
}
