import java.util.Scanner;

/**
 *  Dies ist Pflichtaufgabe 58.
 *  
 *  @author Sophie Duehn 4577449 Gruppe 3b
 *  @version 1.1
 */
public class Crawler {


    private static Player player = new Player();
    private static Shop shop = new Shop();
	
    /**
     *  Spieler und Map werden erstellt
     *  Solange der Spieler nicht "tot" ist und solange das Zielfeld
     *  nicht erreicht ist, bewegt er sich durch das Level
     *  @param args keine Funktion
     */
    public static void main(String[] args) {
    	

        int x = 1;
        int y = 1;
        System.out.println("\nBitte waehle die Groesse deines Spielfeldes."
            + "\nDas Feld muss mind. 5x5 Felder gross sein.");
        Scanner s = new Scanner(System.in);
        System.out.println("\nBreite des Feldes:");
        int xGroesse = s.nextInt();
        System.out.println("\nHoehe des Feldes:");
        int yGroesse = s.nextInt();
        System.out.println("\nDas von dir eingegebene Spielfeld hat die Groesse: " + xGroesse + "x" + yGroesse);

        MazeGenerator gen = new RecursiveBacktracker();
        char [][] mapData = gen.generate(xGroesse, yGroesse);
        
        Level m = new Level(mapData);
        System.out.println("\nDu bist in einer geheimnisvollen Welt gelandet.\n" 
            + "In dieser gibt es Schmieden <T> und Heilbrunnen <O>.\n" 
            + "Jedoch gibt es auch viele boese Monster, die du besiegen musst, um dein Ziel, \n"
            + "welches als <Z> gekennzeichnet ist, zu erreichen.\n"
            + "leere Felder sind normale Felder, auf denen keine Ereignis geschieht\n" 
            + "und Felder, die mit einem <x> gekennzeichnet sind, kannst du nicht betreten."
            + "\nDeine Position wird als <P> angezeigt.\n"
            + "Viel Spass und Erfolg.");        
        System.out.println("\n" + m.toString());
        while (!m.zielfeld() && !player.isDefeated()) {
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
                    kampf(player);
                    System.out.println("\n" + m.toString());
                    m.replaceFeld(m.getPlayerPositionx(), m.getPlayerPositiony(), '.');
                    break;
                case '$':
                	System.out.println("\n Du triffst auf einen Händler.\n Du kannst Gegenstände kaufen<b> oder verkaufen<s>");
                	 Scanner abfrage = new Scanner(System.in);
                     String eingabe = abfrage.nextLine();
                     if (eingabe.equals("b")){
                    	 m.buy(player, shop,1);
                    	 System.out.println(player.getGold());
                    	
                     }
                     
                     if (eingabe.equals("s")) {
                    	 m.sell(player, shop, 1);
                    	 System.out.println(player.getGold());
                     }
                	break;
                default:
                    break;
            }
        }
        if (m.zielfeld()) {
            System.out.println("\nHerzlichen Glueckwunsch! Du hast das Spiel gewonnen.");
        } else {
            System.out.println("\nGame Over. Du bist leider gestorben.");
        } 
    }
            
    /**
     *  Bewegung des Spielers wird abgefragt und ausgefuehrt
     *  @param level Level
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
        System.out.println("Du kannst dein Inventar <i> anzeigen lassen und du kannst dich nach" + ((linksfrei) ? " links (a)" : "") 
            + ((rechtsfrei) ? " rechts (d)" : "")
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
        if(eingabe.equals("i")) {
        	System.out.println(player.inventar.toString());
        	
        	
        }
    }
    
    /**
     *  Kampf wird simuliert
     *  @param player Player
     **/
    public static void kampf(Player player) {    
        int r;
        int clock = 0;
        Scanner s = new Scanner(System.in);        
        Monster monster1 = new Monster();
        Pikagirl pikagirl = new Pikagirl();
        SCP049 scp049 = new SCP049(); 
        Monster monster;
        switch ((int) (Math.ceil(Math.random() * 3))) {
            case 2:
                System.out.println("\nDu triffst auf Pikagirl!");
                monster = pikagirl;
                r = 0;
                break;
            case 3:
                System.out.println("\nDu triffst auf ein normales Monster!");            
                monster = monster1;
                r = 2;
                break;
            default:
                System.out.println("\nDu triffst auf SCP-049!");
                monster = scp049;
                r = 1;
                break; 
        }
        System.out.println("Dieses musst du bekaempfen.");
        System.out.println("Hierzu kannst du zwischen einem >Angriff< mit -a-, "
            + "\neinem >Item< zum Heilen mit -i- von 100HP \nund einer >Skill<-Auswahl mit -k- waehlen. \n");   
        System.out.println(player.toString() + "\n" + monster.toString());
        
        while ((!player.isDefeated()) && (!monster.isDefeated())) {
            System.out.println("\nWas willst du tun?");
            String eingabe = s.nextLine();
            
            if (eingabe.equals("a")) {
                switch (player.attack(monster)) {
                    case -1:
                        System.out.println("\nDas Monster ist ausgewichen");
                        break;
                    case -2:
                        System.out.println("\nDer Angriff scheint erfolgreich, "
                            + "\njedoch ist Pikagirl im letzten Moment ausgewichen.\n");
                        break;
                    default:
                        System.out.println("\nDer Angriff war erfolgreich");
                        break;
                }         
            } else if (eingabe.equals("i")) {
                if (player.heal()) {
                    System.out.println("\nHeilung war erfolgreich");
                } else {
                    if (player.getRemainingItemUses() == 0) {
                        System.out.println("\nDu hast keine Heiltraenke mehr uebrig");
                    } else {
                        System.out.println("\nDu hast bereits volles Leben!");
                    }    
                }
            } else if (eingabe.equals("k")) {
                System.out.println("Skills: " + player.getSkill(0).getName() + " (" + player.getSkill(0).getApCost() 
                    + "AP) - " + player.getSkill(1).getName() + " (" + player.getSkill(1).getApCost() + "AP) - "
                    + player.getSkill(2).getName() + " (" + player.getSkill(2).getApCost() + "AP)"); 
                String eingabeSkill = s.nextLine();
                for (int i = 0; i < 3; i++) {
                    if (eingabeSkill.equals(player.getSkill(i).getName())) {
                        if (player.getSkill(i).useSkill(player, monster)) {
                            System.out.println("\n" + player.getSkill(i).getName() + " wurde erfolgreich eingesetzt");
                        } else {
                            System.out.println("\n" + player.getSkill(i).getName() + " konnte nicht eingesetzt werden");
                        }                        
                        break;
                    }               
                }
            
            } else {
                System.out.println("\nUngueltige Aktion!");
            }
            System.out.println(player.toString() + "\n" + monster.toString() + "\n");
            
            if (monster.isDefeated()) {
            	
            	
                System.out.println("\nDu hast den Kampf gewonnen!");
                if (!monster.inventar.isEmpty()) {
                    player.inventar.insert(monster.inventar.getItem(1));
                    monster.inventar.delete();
                   }
                   System.out.println("\nDu hast folgende Gegenstände im Inventar: " + player.inventar.toString());
                  
                break;
            } else {
                System.out.println("Das Monster greift an\n");
                if (monster.attack(player) > -1) {
                    System.out.println("Der Angriff des Monsters war erfolgreich");
                    if (clock == 0) {
                        clock = 1;
                    }
                } else {
                    System.out.println("Du bist ausgewichen");
                }
                if (r == 1 && clock != 0) {
                    System.out.println("Deathclock is ticking");
                    if (clock == 4) {
                        scp049.deathclock(player);
                    } else {
                        clock++;
                    }
                }
            }
            System.out.println(player.regenerateAp() + "AP wurden regeneriert.");
            System.out.println(player.toString() + "\n" + monster.toString());
        }    
        if (player.isDefeated()) {
            System.out.println("\nDu hast den Kampf verloren.\n");
        }      
    }
}
