/** MazeGenerator Interface
 *  @author Laura Pichlmeier 4753524 Gruppe 3b
 *  @author Sophie Duehn 4577449 Gruppe 3b
 *  @author Sophie Unverzagt 4568856 Gruppe 3b
 */
public interface MazeGenerator {
    /** Wall */
    static final char WALL = 'x';
    /** Free */
    static final char FREE = '.';
    /** Start */
    static final char START = 'S';
    /** Battle */
    static final char BATTLE = 'B';
    /** Schmiede */
    static final char SMITHY = 'T';
    /** Brunnen */
    static final char WELL = 'O';
    /** Ziel */
    static final char GOAL = 'Z';
    /**Haendler*/
    static final char SHOP = '$';
    /**Questgeber*/
    static final char QUEST = 'Q';
    
    /**
     *  @param height gibt Hoehe an
     *  @param width gibt Breite an
     *  @return char[][] 
     */
    public char[][] generate(int height, int width);
}
