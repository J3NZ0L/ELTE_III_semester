const startButton = document.querySelector("#buttonStartGame")
const gamePageDiv = document.querySelector("#gamePage")
const menuPageDiv = document.querySelector("#menuPage")

const difficultyButtons = document.querySelectorAll(".difficulty-button")

startButton.addEventListener("click", onStartButtonClick)

difficultyButtons.forEach(diffButton => {
  diffButton.addEventListener("click", toggleSelectedDifficulty)
})

function toggleSelectedDifficulty(e){
    difficultyButtons.forEach(diffButton => {
      diffButton.classList.toggle("selected")
    })
}

function onStartButtonClick(e){
    //toggleDisplay(gamePageDiv);
    toggleMenuAndGamePage()
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
  
  // Initialize with 5x5 grid
  createMatrix();
