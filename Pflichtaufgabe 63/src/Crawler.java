import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Dies ist Pflichtaufgabe 63.
 * 
 * @author Sophie Duehn 4577449 Gruppe 3b
 * @author Laura Pichlmeier 4573524 Gruppe 3b
 * @author Sophie Unverzagt 4568856 Gruppe 3b
 * @version 1.1
 */
public class Crawler {
	/** itempool: alle Items */
	public static List<Item> itempool = new Baum<Item>();
	/** questpool: alle Quests */
	public static List<Quest> questpool = new Baum<Quest>();
	private static String SAVE_GAME = "save.ser";
	/** player */
	private static Player player = new Player();
	/** shop (Haendler) */
	private static Shop shop = new Shop();
	private static Monster monster;

	/** itempool: alle Items */

	/**
	 * Spieler und Map werden erstellt Solange der Spieler nicht "tot" ist und
	 * solange das Zielfeld nicht erreicht ist, bewegt er sich durch das Level
	 * 
	 * @param args
	 *            keine Funktion
	 */
	public static void main(String[] args) {
		System.out.println(Paths.get(".").toAbsolutePath().normalize());
		int x = 1;
		int y = 1;
		BufferedReader br;

		/**
		 * liest .csv Dateien ein
		 */
		try {
			br = Files.newBufferedReader(Paths.get("item.csv"));
			String line = null;
			line = br.readLine();
			while ((line = br.readLine()) != null) {
				String[] splits = line.split(", ");
				String name = splits[0];
				double value = Double.parseDouble(splits[1].trim());
				double weight = Double.parseDouble(splits[2].trim());
				Item temp = new Item(name, value, weight);
				itempool.insert(temp);
			}
			br = Files.newBufferedReader(Paths.get("quest.csv"));
			String line2 = null;
			line2 = br.readLine();
			while ((line2 = br.readLine()) != null) {
				String[] splits = line2.split(", ");
				String name = splits[0];
				String pre = splits[1];
				pre.trim();
				String item = splits[2];
				item.trim();
				int quantity = Integer.parseInt(splits[3].trim());
				Quest temp = new Quest(name, pre, item, quantity);
				questpool.insert(temp);
			}

		} catch (IOException e) {
			System.err.println("Datei konnte nicht geöffnet werden.");
			e.printStackTrace();
		}
		shop.init();
		monster = new Monster();
		monster = monster.monsterGenerate(monster);

		System.out.println(
				"\nBitte waehle die Groesse deines Spielfeldes." + "\nDas Feld muss mind. 5x5 Felder gross sein.");
		Scanner s = new Scanner(System.in);
		System.out.println("\nBreite des Feldes:");
		int xGroesse = s.nextInt();
		System.out.println("\nHoehe des Feldes:");
		int yGroesse = s.nextInt();
		System.out.println("\nDas von dir eingegebene Spielfeld hat die Groesse: " + xGroesse + "x" + yGroesse);

		MazeGenerator gen = new RecursiveBacktracker();
		char[][] mapData = gen.generate(xGroesse, yGroesse);

		Level m = new Level(mapData);
		Scanner k = new Scanner(System.in);
		System.out.println("Soll das Spiel geladen werden j/n");
		if (k.nextLine().equals("j")) {
			try {
				FileInputStream fileInputStream = new FileInputStream(SAVE_GAME);
				ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
				player = (Player) objectInputStream.readObject();
				objectInputStream.close();
			} catch (IOException | ClassNotFoundException e) {
				System.out.println("Das Spiel konnte nicht geladen werden. Neues Spiel wird gestartet.");

			}
		}
		System.out.println("\nDu bist in einer geheimnisvollen Welt gelandet.\n"
				+ "In dieser gibt es Schmieden <T> und Heilbrunnen <O>.\n"
				+ "Jedoch gibt es auch viele boese Monster, die du besiegen musst, um dein Ziel, \n"
				+ "welches als <Z> gekennzeichnet ist, zu erreichen.\n"
				+ "leere Felder sind normale Felder, auf denen keine Ereignis geschieht\n"
				+ "und Felder, die mit einem <x> gekennzeichnet sind, kannst du nicht betreten."
				+ "\nDeine Position wird als <P> angezeigt.\n" + "Viel Spass und Erfolg.");
		System.out.println("\n" + m.toString());
		while (!player.isDefeated()) {
			bewegung(m);
			System.out.println("\n" + m.toString());
			switch (m.getFeld()) {
			case 'O':
				m.heilbrunnen(player);
				break;
			case 'T':
				m.schmiede(player);
				m.replaceFeld(m.getPlayerPositionx(), m.getPlayerPositiony(), '.');
				System.out.println("Die Schmiede ist weitergezogen.\n");
				break;
			case 'B':
				new Battle(monster, player);
				Sync.waitForBattleEnd();
				break;
			case '$':
				System.out.println("\n Du triffst auf einen Händler.");
				System.out.println(
						"\n Du kannst Gegenstände kaufen<buy> oder verkaufen<sell> oder mit einer beliebigen anderen Eingabe weiterziehen.");
				Scanner abfrage = new Scanner(System.in);
				String eingabe = abfrage.nextLine();
				if (eingabe.equals("buy")) {
					System.out.println(shop.inventar.toString());
					System.out.println(
							"\n Welches Item möchtest du kaufen? Bitte gib die Position in Inventar des Händlers an.");
					System.out.println(("\nGold:") + player.getGold());
					Scanner whichItem = new Scanner(System.in);
					String a = whichItem.nextLine();
					int thisItem = Integer.parseInt(a);
					if (player.getGold() < shop.inventar.getItem(thisItem).getValue()) {
						System.out.println("Du hast zu wenig Gold, um dieses Item zu kaufen.");
					} else {
						m.buy(player, shop, thisItem);
						System.out.println(player.inventar.toString());
					}

				}

				if (eingabe.equals("sell")) {
					System.out.println(player.inventar.toString());
					if (player.inventar.isEmpty()) {

					} else {
						System.out.println(
								"\n Welches Item möchtest du verkaufen? Bitte gib die Position im Inventar an.");
						Scanner whichItem = new Scanner(System.in);
						String a = whichItem.nextLine();
						int thisItem = Integer.parseInt(a);
						m.sell(player, shop, thisItem);
						System.out.println(player.inventar + ("\nGold: ") + player.getGold());
					}
				}
				break;
			case 'Q':
				System.out.println("Du triffst einen Questgeber. Willst du Quest annehmen<an> oder abgeben<ab>?");
				Scanner entscheidung = new Scanner(System.in);
				String b = entscheidung.nextLine();
				if (b.equals("an")) {

					for (int i = 1; i <= questpool.length(); i++) {
						boolean vorquesterledigt = false;
						if (questpool.getItem(i).prequest.isEmpty()) {
							vorquesterledigt = true;
						}
						for (int j = 1; j <= questpool.length(); j++) {
							if (questpool.getItem(i).prequest.equals(questpool.getItem(j).name)) {
								if (questpool.getItem(j).getErledigt()) {
									vorquesterledigt = true;
								}
							}
						}
						if (!questpool.getItem(i).getErledigt() && vorquesterledigt
								&& !questpool.getItem(i).getAngenommen()) {
							System.out.println(i + " - " + questpool.getItem(i));
						}
					}
					System.out.println("Welche Quest möchtest du annehmen?");
					Scanner questfrage = new Scanner(System.in);
					String dq = questfrage.nextLine();
					int diesequest = Integer.parseInt(dq);
					questpool.getItem(diesequest).setAngenommen(true);
				}
				if (b.equals("ab")) {
					for (int i = 1; i <= questpool.length(); i++) {
						if (questpool.getItem(i).getAngenommen()) {
							System.out.println(i + " - " + questpool.getItem(i));
						}
					}
					System.out.println("Welche Quest möchtest du abgeben?");
					Scanner questfrage = new Scanner(System.in);
					String dq = questfrage.nextLine();
					int diesequest = Integer.parseInt(dq);
					int counter = 0;
					for (int i = 1; i <= player.inventar.length(); i++) {
						if (player.inventar.getItem(i).getName().equals(questpool.getItem(diesequest).item)) {
							counter++;
						}
					}
					if (counter >= questpool.getItem(diesequest).quantity) {
						for (int i = 1; i <= questpool.getItem(diesequest).quantity; i++) {
							Item deletable = new Item(questpool.getItem(diesequest).item);
							player.inventar.delete(deletable);
						}

						questpool.getItem(diesequest).setAngenommen(false);
						questpool.getItem(diesequest).setErledigt(true);
						System.out.println("Du hast die Quest erfolgreich abgeschlossen.");
					} else {
						System.out.println("Du hast nicht genug Items!");
					}
				}
			default:
				break;
			}
			if (m.zielfeld()) {
				boolean finished = true;
				for (int i = 1; i <= questpool.length(); i++) {
					finished = finished && questpool.getItem(i).getErledigt();
				}
				if (finished) {
					break;
				} else {
					System.out.println("Du hast noch nicht alle Quests abgeschlossen.");
				}
			}
		}
		if (m.zielfeld()) {
			System.out.println("\nHerzlichen Glueckwunsch! Du hast das Spiel gewonnen.");
		} else {
			System.out.println("\nGame Over. Du bist leider gestorben.");
		}
	}

	/**
	 * Bewegung des Spielers wird abgefragt und ausgefuehrt
	 * 
	 * @param level
	 *            Level
	 **/
	public static void bewegung(Level level) {
		boolean linksfrei = false;
		boolean rechtsfrei = false;
		boolean obenfrei = false;
		boolean untenfrei = false;
		if (level.wand(level.getPlayerPositionx(), level.getPlayerPositiony() - 1) == false) {
			linksfrei = true;
		}
		if (level.wand(level.getPlayerPositionx(), level.getPlayerPositiony() + 1) == false) {
			rechtsfrei = true;
		}
		if (level.wand(level.getPlayerPositionx() - 1, level.getPlayerPositiony()) == false) {
			obenfrei = true;
		}
		if (level.wand(level.getPlayerPositionx() + 1, level.getPlayerPositiony()) == false) {
			untenfrei = true;
		}
		System.out.println("Du kannst dein Inventar <i> anzeigen lassen und du kannst dich nach"
				+ ((linksfrei) ? " links (a)" : "") + ((rechtsfrei) ? " rechts (d)" : "")
				+ ((obenfrei) ? " oben (w)" : "") + ((untenfrei) ? " unten (s)" : "") + " bewegen.");
		Scanner abfrage = new Scanner(System.in);
		String eingabe = abfrage.nextLine();
		if (eingabe.equals("a") && linksfrei) {
			level.setPlayerPositiony(level.getPlayerPositiony() - 1);
		}
		if (eingabe.equals("d") && rechtsfrei) {
			level.setPlayerPositiony(level.getPlayerPositiony() + 1);
		}
		if (eingabe.equals("w") && obenfrei) {
			level.setPlayerPositionx(level.getPlayerPositionx() - 1);
		}
		if (eingabe.equals("s") && untenfrei) {
			level.setPlayerPositionx(level.getPlayerPositionx() + 1);
		}
		if (eingabe.equals("i")) {
			System.out.println(player.inventar.toString());

		}
		if (eingabe.equals("z")) {
			try {
				FileOutputStream fileOutputStream = new FileOutputStream(SAVE_GAME);
				ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
				objectOutputStream.writeObject(player);
				fileOutputStream.flush();
				fileOutputStream.close();
			} catch (IOException e) {
				System.out.println("Konnte das Spiel nicht speichern.");
			}
		}
	}

}
