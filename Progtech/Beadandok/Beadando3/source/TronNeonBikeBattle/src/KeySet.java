public class KeySet{
    private final int upKey, downKey, leftKey, rightKey;
    private final int movementLength;

    /**
     * Constructs a KeySet object with specified key bindings and movement length.
     *
     * @param upKey the key code representing upward movement.
     * @param downKey the key code representing downward movement.
     * @param leftKey the key code representing leftward movement.
     * @param rightKey the key code representing rightward movement.
     * @param movementLength the length of movement per key press.
     */
    public KeySet(int upKey, int downKey, int leftKey, int rightKey, int movementLength) {
        this.upKey = upKey;
        this.downKey = downKey;
        this.leftKey = leftKey;
        this.rightKey = rightKey;
        this.movementLength = movementLength;
    }

    /**
     * Determines the change in the horizontal direction (dx) based on the provided key input and the previous dx value.
     *
     * @param key The currently pressed key, represented as an integer.
     * @param prevDx The previous horizontal movement value.
     * @return The updated horizontal movement value (dx). Returns 0 if the key represents an up or down direction
     *         and there was a previous horizontal movement. Returns the previous dx if no relevant key is pressed.
     *         Returns -movementLength if the key represents a left direction, and movementLength if it represents a right direction.
     */
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

    /**
     * Computes the vertical movement (delta Y) based on the provided key input and previous movement value.
     *
     * @param key the key code representing the input direction
     * @param prevDy the previous vertical movement value
     * @return the calculated vertical movement value; it may depend on the input direction, previous movement,
     *         and predefined movement configuration
     */
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

    /**
     * Checks whether the specified key is part of the set of defined keys
     * (upKey, downKey, leftKey, rightKey) within this KeySet instance.
     *
     * @param key the key to be checked against the defined keys.
     * @return {@code true} if the key matches one of the defined keys, otherwise {@code false}.
     */
    public boolean contains(int key){
        if (key == upKey || key == downKey || key == leftKey || key == rightKey){
            return true;
        }
        return false;
    }


}
