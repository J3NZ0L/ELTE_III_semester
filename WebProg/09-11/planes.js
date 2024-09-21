function whichPlane(x, y){
    if (x==0 || y == 0){
        return 0
    }
    else if (x>0 && y>0){
        return 1
    }
    else if (x<0 && y>0){
        return 2
    }
    else if (x<0 && y<0){
        return 3
    }
    else if (x>0 && y<0){
        return 4
    }
}

const x = parseInt(prompt("X:"))
const y = parseInt(prompt("Y:"))

const result = whichPlane(x,y)
if (result > 0){
    alert(`The coordinates you provided are located in the ${result}. plane quadrant`)
} else {
    alert(`The provided point does not belong to any of the plane quadrants`)
}
