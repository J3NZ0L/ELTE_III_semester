const divMap = document.querySelector("#map");
const divInformation = document.querySelector("#information");
const divLines = document.querySelector("#lines");

const formNewLine = document.querySelector("#new-line-form");
const inputNewName = document.querySelector("#new-name");
const inputNewX1 = document.querySelector("#new-x1");
const inputNewY1 = document.querySelector("#new-y1");
const inputNewX2 = document.querySelector("#new-x2");
const inputNewY2 = document.querySelector("#new-y2");
const buttonSelect = document.querySelector("#select");

const inputSelectedName = document.querySelector("#selected-name");
const buttonStart = document.querySelector("#start");
const buttonAutomatic = document.querySelector("#automatic");

const lines = [
  {
    name: "Line1",
    stations: [
      {x: 31.344086751431767, y: 73.14579827087277},
      {x: 6.860215301632762, y: 45.750359578024494},
    ],
    activeStation: 0,
    elevatorState: "waiting", // "travelling"
    travelTime: 3,
    waitingTime: 10,
    startTime: -Infinity,
    automatic: false
  },
  {
    name: "Line2",
    stations: [
      {x: 48.9677430123982, y: 72.93667278466782},
      {x: 63.98924868449711, y: 28.602069709218696},
    ],
    activeStation: 0,
    elevatorState: "waiting", // "travelling"
    travelTime: 10,
    waitingTime: 5,
    startTime: -Infinity,
    automatic: false
  },
  {
    name: "Line3",
    stations: [
      {x: 31.935484612538023, y: 72.51842181225793},
      {x: 21.645161829289165, y: 40.31309693669583},
    ],
    activeStation: 0,
    elevatorState: "waiting",
    travelTime: 8,
    waitingTime: 15,
    startTime: -Infinity,
    automatic: false
  },
];

addEventListener('load', () =>{
  var counter = 1;
  lines.forEach(element => {
    var div_station = document.createElement("div");
    div_station.setAttribute("class", "status");
    div_station.textContent = element.name +": "+element.elevatorState+ " ";
    counter++;
    var progress_station = document.createElement("progress");
    progress_station.setAttribute("value",0);
    progress_station.setAttribute("max",10);
    div_station.appendChild(progress_station);
    divInformation.appendChild(div_station);

    var div_line = document.createElement("div");
    div_line.setAttribute("class","line");
    div_line.setAttribute("data-name",element.name);
    var div_station1 = document.createElement("div");
    var div_station2 = document.createElement("div");
    div_station1.setAttribute("class","station");
    div_station2.setAttribute("class","station");
    div_station1.setAttribute("style","top: "+element.stations[0].y+"%; left: "+element.stations[0].x+"%");
    div_station2.setAttribute("style","top: "+element.stations[1].y+"%; left: "+element.stations[1].x+"%");
    div_elevator = document.createElement("div");
    div_elevator.setAttribute("class","elevator");
    div_elevator.setAttribute("style","top: "+element.stations[0].y+"%; left: "+element.stations[0].x+"%; transition-duration: "+element.travelTime+"s");
    div_line.appendChild(div_station1);
    div_line.appendChild(div_station2);
    div_line.appendChild(div_elevator);
    divLines.appendChild(div_line);


    divLines.addEventListener("click", ({target}) => {
      inputSelectedName.value = target.parentElement.getAttribute("data-name");
    })

    buttonStart.addEventListener("click", () => {
      var line = inputSelectedName.value;
      var selectedLine = lines.find(x => x.name == line);
      selectedLine.activeStation = 1;
      selectedLine.elevatorState = "travelling";
      var targetDivs = Array.prototype.slice.call(document.getElementsByClassName("line"));
      console.log(targetDivs);
      var targetDiv;
      targetDivs.forEach(element => {
        if(element.getAttribute("data-name") == line){
          targetDiv = element;
        }
      })
      if(targetDiv.childNodes[2].getAttribute("style") == "top: "+selectedLine.stations[0].y+"%; left: "+selectedLine.stations[0].x+"%; transition-duration: "+selectedLine.travelTime+"s"){
        targetDiv.childNodes[2].setAttribute("style","top: "+selectedLine.stations[1].y+"%; left: "+selectedLine.stations[1].x+"%; transition-duration: "+selectedLine.travelTime+"s")
      } else {
        targetDiv.childNodes[2].setAttribute("style","top: "+selectedLine.stations[0].y+"%; left: "+selectedLine.stations[0].x+"%; transition-duration: "+selectedLine.travelTime+"s")
      }
      setTimeout(() => {
        selectedLine.elevatorState = "waiting";
        selectedLine.startTime = Date.now() + selectedLine.waitingTime;
        selectedLine.activeStation = 0;
      }, selectedLine.travelTime * 1000);
    })

    buttonAutomatic.addEventListener("click", ({target}) => {
      var line = inputSelectedName.value;
      const selectedLine = lines.find(x => x.name == line);
      if(line.automatic){
        line.automatic = false;
      } else {
        line.automatic = true;
      }
    })

  });

})

