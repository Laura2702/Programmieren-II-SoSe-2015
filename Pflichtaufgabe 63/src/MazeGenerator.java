/**
 * The interface Maze generator.
 *
 * @author Max Mustermann 1234567 Gruppe 42z
 * @author Erika Musterfrau 1234567 Gruppe 42z
 */
public interface MazeGenerator {

    /**
     * The WALLCHAR.
     */
    char WALLCHAR = '#';
    /**
     * The FREECHAR.
     */
    char FREECHAR = '.';
    /**
     * The STARTCHAR.
     */
    char STARTCHAR = 'S';
    /**
     * The BATTLECHAR.
     */
    char BATTLECHAR = 'B';
    /**
     * The SMITHYCHAR.
     */
    char SMITHYCHAR = 'T';
    /**
     * The WELLCHAR.
     */
    char WELLCHAR = 'O';
    /**
     * The GOALCHAR.
     */
    char GOALCHAR = 'Z';

    /**
     * Generate char [ ] [ ].
     *
     * @param height the height
     * @param width  the width
     *
     * @return the char [ ] [ ]
     */
    char[][] generate(int height, int width);
}
