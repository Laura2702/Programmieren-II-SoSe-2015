/**
 * Die Klasse Map erzeugt eine Map
 * 
 * @author Laura Pichlmeier 4573524 Gruppe 3b
 * @author Sophie Duehn 4577449 Gruppe 3b
 * @author Sophie Unverzagt 4568856 Gruppe 3b
 **/
public class Map {
	private int currPx;
	private int currPy;
	char map[][];
	char Random[];
	int smithy = 0;
	int well = 0;
	int battle = 0;

	/**
	 * Setzt T, O, B und default in der Map
	 * 
	 **/

	public void Start() {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if (j % 2 == 0 || i == 0 || i == map.length - 1 || i % 2 == 0) {
					map[i][j] = '#';
				} else {
					switch ((int) Math.ceil(Math.random() * 3)) {
					case 1:
						if (smithy < 4) {
							map[i][j] = 'T';
							smithy++;
						}
					case 2:
						if (well < 4) {
							map[i][j] = 'O';
						}
					case 3:
						if (battle < 5) {
							map[i][j] = 'B';
						}
					default:
						map[i][j] = ' ';

					}

				}

			}
			int m = (int) Math.ceil(Math.random() * map.length);
			int k = (int) Math.ceil(Math.random() * map[m].length);
			map[m][k] = 'B';
			this.currPx = m;
			this.currPy = k;

		}
	}

	/**
	 * Vergleicht, ob Felder frei sind
	 * 
	 * @return frei
	 **/
	public boolean[] Frei() {
		boolean[] frei = new boolean[5];

		if (isBesucht(currPx, currPy - 2) == false) {
			frei[1] = true;
			return frei; // links
		}
		if (isBesucht(currPx, currPy + 2) == false) {
			frei[2] = true;
			return frei;// RECHTS
		}
		if (isBesucht(currPx - 2, currPy) == false) {
			frei[3] = true;
			return frei; // unten
		}
		if (isBesucht(currPx + 2, currPy) == false) {
			frei[4] = true;
			return frei;// oben
		} else {
			frei[0] = true;
			return frei;
		}
	}

	/**
	 * Kampf?
	 **/
	public void Move() {
		if (Frei()[1] = true) {
			map[currPx][currPy - 2] = 'B';
			map[currPx][currPy - 1] = '.';
			currPy = currPy - 2;

		} else if (Frei()[2] = true) {
			map[currPx][currPy + 2] = 'B';
			currPy = currPy + 2;
			map[currPx][currPy + 1] = '.';
		} else if (Frei()[3] = true) {
			map[currPx - 2][currPy] = 'B';
			map[currPx - 1][currPy] = '.';
			currPx = currPx - 2;
		} else if (Frei()[4] = true) {
			map[currPx + 2][currPy] = 'B';
			map[currPx + 1][currPy] = '.';
			currPx = currPx + 2;
		}

	}

	/**
	 * Vergleicht, ob B besucht ist
	 * 
	 * @param currPx
	 * @param currPy
	 * @return true, wenn besucht
	 * @return false, wenn nicht
	 **/

	public boolean isBesucht(int currPx, int currPy) {
		if (this.map[currPx][currPy] == 'B') {
			return true;
		}
		return false;
	}

	/**
	 * Generiert Map
	 * 
	 * @param height
	 * @param weight
	 * @return map
	 **/

	public char[][] generate(int height, int width) {
		char[][] map = new char[height][width];
		Start();
		while (Frei()[0] == false) {
			Move();
			return map;
		}
		return map;

	}

	/**
	 * Gets Map
	 * 
	 * @return map
	 **/
	public char[][] getMap() {
		return this.map;
	}

	/**
	 * Zeigt Map an
	 * 
	 * @param map
	 *            .length
	 * @return map
	 **/
	public static void Print(char[][] map) {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if (j % 2 == 0 || i == 0 || i == map.length - 1 || i % 2 == 0) {
					System.out.println(map[i][j]);
				}
			}
		}
	}

}
