const modalDiv = document.getElementById("descriptionModal");
const descriptionButton = document.getElementById("buttonDescription");
const closeButton = document.querySelector(".modal .close");

// open modal
descriptionButton.onclick = function () {
  modalDiv.style.display = "flex";
}

// "close" modal when clicked on close button
closeButton.onclick = function () {
  modalDiv.style.display = "none";
}

// also "close" modal when clicked outside modal area
window.onclick = function (e) {
  if (e.target == modalDiv) {
    modalDiv.style.display = "none";
  }
}

