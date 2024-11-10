// Get modal, button, and close elements
const modal = document.getElementById("descriptionModal");
const btn = document.getElementById("buttonDescription");
const closeBtn = document.querySelector(".modal .close");

// Open the modal
btn.onclick = function () {
  modal.style.display = "flex";
}

// Close the modal when the "×" is clicked
closeBtn.onclick = function () {
  modal.style.display = "none";
}

// Close the modal if the user clicks outside of the modal content
window.onclick = function (event) {
  if (event.target == modal) {
    modal.style.display = "none";
  }
}

