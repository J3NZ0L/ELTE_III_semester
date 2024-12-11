public class KeySet{
    private final int upKey, downKey, leftKey, rightKey;
    private final int movementLength;

    /**
     *         if (key == upKey && dy == 0) {
     *             dx = 0; dy = -CELL_SIZE; // Up
     *         } else if (key == downKey && dy == 0) {
     *             dx = 0; dy = CELL_SIZE; // Down
     *         } else if (key == leftKey && dx == 0) {
     *             dx = -CELL_SIZE; dy = 0; // Left
     *         } else if (key == rightKey && dx == 0) {
     *             dx = CELL_SIZE; dy = 0; // Right
     *         }
     * @param upKey
     * @param downKey
     * @param leftKey
     * @param rightKey
     * @param movementLength
     */

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
        else throw new IllegalArgumentException("The got key is not to be handled");
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
        else throw new IllegalArgumentException("The got key is not to be handled");
    }

    public boolean contains(int key){
        if (key == upKey || key == downKey || key == leftKey || key == rightKey){
            return true;
        }
        return false;
    }


}
