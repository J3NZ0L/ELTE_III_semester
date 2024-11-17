const canvas = document.getElementById('canvas');
const ctx = canvas.getContext('2d');

const sprite = {
  currentFrame: 0,
  image: new Image(),
  frameCount: 16,
  frameTime: 0.03,
  elapsedTime: 0,
};

function update(dt) {
  sprite.elapsedTime += dt;
  if (sprite.elapsedTime > sprite.frameTime) {
    sprite.currentFrame = (sprite.currentFrame + 1) % sprite.frameCount;
    sprite.elapsedTime -= sprite.frameTime;
  }
}

function draw() {
  // Clear the canvas
  ctx.clearRect(0, 0, canvas.width, canvas.height);

  // Calculate the source position in the sprite sheet
  const cf = sprite.currentFrame
  let spriteX
  let spriteY
  if (Math.floor(cf%32)<16){
     spriteX = cf % 4;
     spriteY = Math.floor(cf / 4);
  }
  else {
     spriteX = 3-cf % 4;
     spriteY = 3-Math.floor(cf / 4);
  }
  // Draw the current frame
  ctx.drawImage(
    sprite.image,
    spriteX * 128,          // Source X
    spriteY * 128,          // Source Y
    127,                    // Source width
    127,                    // Source height
    canvas.width / 2 - 64,  // Target X
    canvas.height / 2 - 64, // Target Y
    128,                    // Target width
    128                     // Target height
  );
}

let lastTime = 0;
function gameLoop(timestamp) {
  const dt = (timestamp - lastTime) / 1000; // Convert ms to seconds
  lastTime = timestamp;

  update(dt); // Update sprite frame based on delta time
  draw();     // Draw the current frame

  requestAnimationFrame(gameLoop); // Continue the loop
}

function init() {
  sprite.image.src = 'spritesheet.png';
  sprite.image.addEventListener('load', function () {
    lastTime = performance.now(); // Initialize lastTime
    requestAnimationFrame(gameLoop); // Start the animation loop
  });
}

init();
