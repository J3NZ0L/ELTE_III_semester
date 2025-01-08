const inputNumberOfDefenders = document.querySelector("#numberOfDefenders");
const buttonStart = document.querySelector("#buttonStart");
const canvas = document.querySelector("canvas");
const ctx = canvas.getContext("2d");
const canvasWidth = canvas.width;
const canvasHeight = canvas.height;

// Data
let state = 0;   // 0 begin, 1 ingame, 2 lost, 3 won
const ball = {
  x: 30,
  y: canvasHeight / 2 - 8,
  width: 15,
  height: 15,
  vx: 0,
  ax: 0,
}
const gate = {
  x: canvasWidth - 40,
  y: canvasHeight / 2 -40,
  width: 40,
  height: 80,
}
let defenders = [];

// Time-based animation (from the lecture slide)
let lastFrameTime = performance.now();

function next(currentTime = performance.now()) {
  const dt = (currentTime - lastFrameTime) / 1000; // seconds
  lastFrameTime = currentTime;

  update(dt); // Update current state
  render(); // Rerender the frame
  requestAnimationFrame(next);
}

function update(dt) {
  
}

function render() {
  
}

// Start
const ballImage = new Image();
const defenderImage = new Image();
ballImage.src = "ball.png";
defenderImage.src = "defender.png";

// =============== Segédfüggvények =================

function isCollision(box1, box2) {
  return !(
    box2.y + box2.height < box1.y ||
    box1.x + box1.width < box2.x ||
    box1.y + box1.height < box2.y ||
    box2.x + box2.width < box1.x
  );
}

function random(a, b) {
  return Math.floor(Math.random() * (b - a + 1)) + a;
}