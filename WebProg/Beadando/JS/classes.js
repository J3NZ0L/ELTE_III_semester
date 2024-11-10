import { basePossibleDirections } from "./consts.js";

class Tile{
    placeRail(){
    }
  
    isToBeRotated(){
        return false
    }
  
    rotateDirections( numOfTimes){
      this.possibleEntrances.forEach(element => {
        element=(element+numOfTimes)%4
      });
    }
  
    incTravelDirection(){
  
    }
  
    isRailable(){
      return true
    }
  
    getStyleClass(){
    }
  }
export class EmptyTile extends Tile{
    constructor(){
        super()
        this.possibleEntrances = basePossibleDirections["emptyTile"]
        this.possibleTravelDirection = basePossibleDirections["emptyTile"]
        this.hasStraightRail = false
        this.hasCurveRail = false
    }

    placeStraightRail(){
        this.hasStraightRail = true
    }

    getStyleClass(){
        if (this.hasStraightRail){
        return "Tile-straight-rail"
        } else if (this.hasCurveRail){
        return "Tile-curve_rail"
        } else {
        return "Tile-empty"
        }
    }

    canHaveStraightRail(){
        return true
    }

    canHaveCurveRail(){
        return true
    }
}

export class MountainTile extends Tile{
    #numberOfRotations
    constructor(numberOfRotations = 0){
        super()
        this.possibleEntrances = basePossibleDirections["mountainTile"]
        this.possibleTravelDirection = basePossibleDirections["mountainTile"]
        this.numberOfRotations = numberOfRotations
        this.hasRail = false

        this.rotateDirections(numberOfRotations)
    }

    getStyleClass(){
        return this.hasRail ? "Tile-mountain_rail": "Tile-mountain"
    }
    getNumberOfRotations(){
        return this.numberOfRotations
    }
    isToBeRotated(){
        return this.getNumberOfRotations()>0
    }
}

export class BridgeTile extends Tile{
    #numberOfRotations
    constructor(numberOfRotations = 0){
        super()
        this.possibleEntrances = basePossibleDirections["mountainTile"]
        this.possibleTravelDirection = basePossibleDirections["mountainTile"]
        this.numberOfRotations = numberOfRotations
        this.hasRail = false

        this.rotateDirections(numberOfRotations)
    }

    getStyleClass(){
        return this.hasRail ? "Tile-bridge_rail" : "Tile-bridge"
    }
    getNumberOfRotations(){
        return this.numberOfRotations
    }
    isToBeRotated(){
        return this.getNumberOfRotations()>0
    }
}

export class Oasis extends Tile{
    isRailable(){
        return false
    }
    getStyleClass(){
        return "Tile-oasis"
    }
}
  

