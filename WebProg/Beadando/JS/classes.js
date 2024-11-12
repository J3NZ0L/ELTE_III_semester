import { basePossibleDirections } from "./consts.js";

// base class of all tiles
class Tile{
    placeRail(){
    }
  
    isToBeRotated(){
        return false
    }

    isRotatable(){
        return false
    }
  
    // currently unused, might be needed for future features
    rotateDirections( numOfTimes){
      this.possibleEntrances.forEach(element => {
        element=(element+numOfTimes)%4
      });
    }
  
    isRailable(){
      return true
    }
  
    getStyleClass(){
    }
  }

export class EmptyTile extends Tile{
    #numberOfRotations
    constructor(){
        super()
        this.possibleEntrances = basePossibleDirections["emptyTile"]
        this.possibleTravelDirection = basePossibleDirections["emptyTile"]
        this.hasStraightRail = false
        this.hasCurveRail = false
        this.numberOfRotations = 0

    }

    placeRail(){
        if (!this.hasStraightRail && !this.hasCurveRail){
            this.hasStraightRail = true
        } else {
            this.hasStraightRail = !this.hasStraightRail
            this.hasCurveRail = !this.hasCurveRail
        }
        return true
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
    isRotatable(){
        return this.hasStraightRail || this.hasCurveRail
    }

    rotate(){
        this.numberOfRotations=(this.numberOfRotations+1)%4
    }
  
    getNumberOfRotations(){
        return this.numberOfRotations
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

    placeRail(){
        if (!this.hasRail){
            this.hasRail = true
            return true
        } 
        return false
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

    placeRail(){
        if (!this.hasRail){
            this.hasRail = true
            return true
        } 
        return false
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
  

