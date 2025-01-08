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

