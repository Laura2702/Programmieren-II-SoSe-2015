/**
 * RecursiveBacktracker Klasse implementiert MazeGenerator Interface
 * 
 * @author Laura Pichlmeier 4573524 Gruppe 3b
 * @author Sophie Duehn 4577449 Gruppe 3b
 * @author Sophie Unverzagt 4568856 Gruppe 3b
 */
public class RecursiveBacktracker implements MazeGenerator {
	/**
	 * RecursiveBacktracker Constructor
	 */
	RecursiveBacktracker() {
	}

	/**
	 * generiert eine Map
	 * 
	 * @param height
	 *            Hoehe der Map
	 * @param width
	 *            Breite der Map
	 * @return map
	 */
	public char[][] generate(int height, int width) {
		if (height < 5 || width < 5) {
			throw new IllegalArgumentException(
					"Das Feld muss mindestens 5x5 gross sein.");
		}
		height -= (height % 2 == 1) ? 0 : 1;
		width -= (width % 2 == 1) ? 0 : 1;
		char[][] map = new char[width][height];
		boolean[][] besucht = new boolean[(width - 1) / 2][(height - 1) / 2];
		int haendler = 0;
		int quest = 0;
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				if (j % 2 == 0 || i % 2 == 0) {
					map[i][j] = WALL;

				} else {

					if (quest < 1) {
						map[i][j] = QUEST;
						quest++;
					}else	if (haendler < 2) {
							map[i][j] = SHOP;
							haendler++;
					} else {
						int rand = (int) (Math.random() * 8);

						if (rand == 1 || rand == 4) {
							map[i][j] = BATTLE;
						} else if (rand == 2) {
							map[i][j] = SMITHY;
						} else if (rand == 3) {
							map[i][j] = WELL;
						} else if (rand == 5) {
							map[i][j] = SHOP;
						} else {
							map[i][j] = FREE;
						}
					}
				}
			}
		}
		int startx = (int) (Math.random() * ((width - 1) / 2));
		int starty = (int) (Math.random() * ((height - 1) / 2));
		map[startx * 2 + 1][starty * 2 + 1] = START;

		map = recursive(map, besucht, startx, starty);
		int[][] sackgasse = new int[((width - 1) * (height - 1)) / 2][2];
		int sackgassen = 0;
		for (int i = 0; i < ((width - 1) / 2); i++) {
			for (int j = 0; j < ((height - 1) / 2); j++) {
				int wand = 0;
				wand += (map[i * 2][j * 2 + 1] == WALL) ? 1 : 0;
				wand += (map[i * 2 + 2][j * 2 + 1] == WALL) ? 1 : 0;
				wand += (map[i * 2 + 1][j * 2] == WALL) ? 1 : 0;
				wand += (map[i * 2 + 1][j * 2 + 2] == WALL) ? 1 : 0;
				if (wand == 3) {
					sackgasse[sackgassen][0] = i;
					sackgasse[sackgassen][1] = j;
					sackgassen += 1;
				}
			}
		}
		int zielx = startx;
		int ziely = starty;
		int zfSackgasse;
		while (zielx == startx && ziely == starty) {
			zfSackgasse = (int) (Math.random() * sackgassen);
			zielx = sackgasse[zfSackgasse][0];
			ziely = sackgasse[zfSackgasse][1];
		}
		map[zielx * 2 + 1][ziely * 2 + 1] = GOAL;
		return map;
	}

	/**
	 * Entfernt die Wand zu einem unbesuchten Nachbarn und ruft sich selbst mit
	 * dem Nachbarn nochmal auf
	 * 
	 * @param map
	 *            Karte
	 * @param besucht
	 *            besuchte Felder
	 * @param positionx
	 *            aktuelle x Position
	 * @param positiony
	 *            aktuelle y Position
	 * @return map
	 */
	public char[][] recursive(char[][] map, boolean[][] besucht, int positionx,
			int positiony) {
		besucht[positionx][positiony] = true;
		int[] frei = new int[5];
		frei[4] = 1;
		while (frei[4] > 0) {
			frei[4] = 0;
			if (positionx > 0) {
				if (!besucht[positionx - 1][positiony]) {
					frei[frei[4]] = 1; // links
					frei[4]++;
				}
			}
			if (positionx < besucht.length - 1) {
				if (!besucht[positionx + 1][positiony]) {
					frei[frei[4]] = 2; // rechts
					frei[4]++;
				}
			}
			if (positiony > 0) {
				if (!besucht[positionx][positiony - 1]) {
					frei[frei[4]] = 3; // oben
					frei[4]++;
				}
			}
			if (positiony < besucht[positionx].length - 1) {
				if (!besucht[positionx][positiony + 1]) {
					frei[frei[4]] = 4; // unten
					frei[4]++;
				}
			}

			int nachbar = (frei[4] > 0) ? frei[(int) (Math.random() * frei[4])]
					: 0;
			switch (nachbar) {
			case 1:
				map[positionx * 2][positiony * 2 + 1] = FREE;
				map = recursive(map, besucht, positionx - 1, positiony);
				break;
			case 2:
				map[positionx * 2 + 2][positiony * 2 + 1] = FREE;
				map = recursive(map, besucht, positionx + 1, positiony);
				break;
			case 3:
				map[positionx * 2 + 1][positiony * 2] = FREE;
				map = recursive(map, besucht, positionx, positiony - 1);
				break;
			case 4:
				map[positionx * 2 + 1][positiony * 2 + 2] = FREE;
				map = recursive(map, besucht, positionx, positiony + 1);
				break;
			default:
				break;
			}
		}
		return map;
	}
}
