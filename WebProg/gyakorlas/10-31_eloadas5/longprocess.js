// Using a timer to break up a long-running task
const table = document.getElementsByTagName("tbody");
let i = 0;
const max = 1999;
setTimeout(function(){
  for (let step = i + 500; i < step; i++ ) {
    const tr = document.createElement("tr");
    for (let t = 0; i < 6; i++ ){
      const td = document.createElement("td");
      td.appendChild( document.createTextNode("" + t));
      tr.appendChild( td );
    }
    table.appendChild( tr );
  }

  if ( i < max ) {
    setTimeout( arguments.callee, 0 );
  }
}, 0);