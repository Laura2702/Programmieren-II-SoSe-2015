import java.util.Scanner;

/**
 * The type Crawler.
 *
 * @author Max Mustermann 1234567 Gruppe 42z
 * @author Erika Musterfrau 1234567 Gruppe 42z
 */
public class Crawler {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        MazeGenerator mg = new RecursiveBacktracker();
        Level m = new Level(mg.generate(31, 71));
        Scanner sc = new Scanner(System.in);
        Player p = new Player();
        while (!p.isDefeated()) {
            System.out.println(m);
            m.showPrompt();
            String input = sc.nextLine();
            if (input.isEmpty()) {
                System.out.println("Leere Eingabe, bitte einen Befehl eingeben");
            } else {
                char direction = input.charAt(0);
                if (!(m.canMove(direction) || direction == 'i')) {
                    System.out.println("Ungueltiger Befehl");
                } else {
                    if (direction == 'i') {
                        System.out.printf("Gold: %d%n", p.getGold());
                        System.out.println("Inventar:");
                        System.out.println(p.getInventar());
                    } else {
                        m.move(direction);
                        m.handleCurrentFieldEvent(p);
                    }

                }
            }
        }
    }
}
