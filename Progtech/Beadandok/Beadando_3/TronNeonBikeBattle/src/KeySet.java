public class KeySet{
    private final int upKey, downKey, leftKey, rightKey;
    private final int movementLength;

    public KeySet(int upKey, int downKey, int leftKey, int rightKey, int movementLength) {
        this.upKey = upKey;
        this.downKey = downKey;
        this.leftKey = leftKey;
        this.rightKey = rightKey;
        this.movementLength = movementLength;
    }

    public int getDX(int key, int prevDx){
        if ((key == upKey || key == downKey) && prevDx !=0){
            return 0;
        }
        if (prevDx != 0){
            return prevDx;
        }
        if (key  == leftKey){
            return -movementLength;
        }
        if (key == rightKey){
            return movementLength;
        }
        else return prevDx;
    }

    public int getDY(int key, int prevDy){
        if ((key == leftKey || key == rightKey) && prevDy != 0){
            return 0;
        }
        if (prevDy != 0){
            return prevDy;
        }
        if (key  == upKey) {
            return -movementLength;
        }
        if (key == downKey){
            return movementLength;
        }
        else return prevDy;
    }

    public boolean contains(int key){
        if (key == upKey || key == downKey || key == leftKey || key == rightKey){
            return true;
        }
        return false;
    }


}
