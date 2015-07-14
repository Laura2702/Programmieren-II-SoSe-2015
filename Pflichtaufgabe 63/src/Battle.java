import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * Dies ist Pflichtaufgabe 70.
 * 
 * @author Sophie Duehn 4577449 Gruppe 3b
 * @author Laura Pichlmeier 4573524 Gruppe 3b
 * @author Sophie Unverzagt 4568856 Gruppe 3b
 * @version 1.2
 */
public class Battle implements ActionListener {
    /** history(Letzte/s Aktion/Ereignis) */
    private JTextArea history;
    /** Der Player */
    private Player player;
    /** Das Monster */
    private Monster monster;
    /** Hp vom Player */
    private JLabel pHp;
    /** Atk vom Player */
    private JLabel pAtk;
    /** Ap vom Player */
    private JLabel pAp;
    /** Hp vom Monster */
    private JLabel mHp;
    /** Atk vom Monster */
    private JLabel mAtk;
    /** Das Fenster */
    private JFrame jfr;
    /** Deathclock(SCP-049) */
    private int clock = 0;
    /** Rundenzähler(für Deathclock) */
    private int r = 1;
    private boolean hatCooldown;
    boolean kampf = true;

    JButton angriff = new JButton("Angriff");
    JButton feuerball = new JButton("Feuerball");
    JButton wasserkanone = new JButton("Wasserkanone");
    JButton stampfer = new JButton("Stampfer");
    JButton item = new JButton("Item");

    /**
     * Battle-Constructor: Erstellt ein Kampffenster.
     * 
     * @param monster
     * @param player
     */
    public Battle(Monster monster, Player player) {

        this.monster = monster;
        this.player = player;
        this.hatCooldown = false;

        Thread thread2 = new Thread() {
            public void run() {
                while (kampf) {
                    player.regenerateAp();
                    try {
                        this.sleep(5000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }

            }
        };
        thread2.start();
        // fenster
        jfr = new JFrame("Kampf");
        JPanel grid = new JPanel(new GridLayout(2, 1));
        jfr.add(grid);
        pHp = new JLabel("HP: " + player.hp);
        pAtk = new JLabel("Atk: " + player.atk);
        pAp = new JLabel("AP: " + player.getAp());
        mHp = new JLabel("HP: " + monster.hp);
        mAtk = new JLabel("Atk: " + monster.atk);

        // oben
        JPanel f = new JPanel(new GridLayout(2, 2));
        JPanel mstats = new JPanel(new GridLayout(3, 1));
        JPanel pstats = new JPanel(new GridLayout(4, 1));
        grid.add(f);
        f.add(mstats);
        mstats.add(new JLabel(monster.typ + ": "));
        mstats.add(mHp);
        mstats.add(mAtk);
        f.add(new JLabel(""));
        f.add(new JLabel(""));
        f.add(pstats);
        pstats.add(new JLabel("Player:"));
        pstats.add(pHp);
        pstats.add(pAtk);
        pstats.add(pAp);

        // unten
        JPanel q = new JPanel(new GridBagLayout());
        ScrollPane sp = new ScrollPane();
        history = new JTextArea("Was passiert ist:");
        sp.add(history);
                        
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 3;
        q.add(sp, c);
        c.gridwidth = 1;
        grid.add(q);
        // JButton angriff = new JButton("Angriff");
        // JButton feuerball = new JButton("Feuerball");
        // JButton wasserkanone = new JButton("Wasserkanone");
        // JButton stampfer = new JButton("Stampfer");
        // JButton item = new JButton("Item");
        item.setActionCommand("i");
        angriff.setActionCommand("a");
        feuerball.setActionCommand("0");
        wasserkanone.setActionCommand("1");
        stampfer.setActionCommand("2");

        c.gridx = 0;
        c.gridy = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        q.add(angriff, c);
        c.gridx = 0;
        c.gridy = 2;

        q.add(feuerball, c);
        c.gridx = 1;
        c.gridy = 2;
        q.add(wasserkanone, c);
        c.gridx = 2;
        c.gridy = 2;
        q.add(stampfer, c);
        c.gridx = 1;
        c.gridy = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        q.add(item, c);
        angriff.addActionListener(this);
        feuerball.addActionListener(this);
        wasserkanone.addActionListener(this);
        stampfer.addActionListener(this);
        item.addActionListener(this);

        jfr.pack();
        jfr.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jfr.setVisible(true);
    }

    /**
     * Aktionen, die ausgeführt werden sollen wenn die Buttons gedrückt werden.
     * angriff: Das Monster wird angegriffen. Fuegt Schaden in Höhe der Atk des
     * Players zu. feuerball: Spezialangriff. Fuegt 50 Schaden zu. stampfer:
     * Spezialangriff. Fuegt 100 Schaden zu. item: heilt den Spieler um 100 Hp.
     * 
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals("a") && !hatCooldown) {
            switch (player.attack(monster)) {
            case -1:
                history.append("Das Monster ist ausgewichen\n");
                break;
            case -2:
                history.append("Der Angriff scheint erfolgreich, jedoch ist Pikagirl im letzten Moment ausgewichen.\n");
                break;
            default:
                history.append("Der Angriff war erfolgreich\n");
                mHp.setText("Hp: " + monster.hp);
                break;
            }
            this.hatCooldown = true;
            angriff.setEnabled(false);
            Thread thread = new Thread() { // 1s Cooldown
                public void run() {
                    try {
                        this.sleep(1000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    hatCooldown = false;
                    angriff.setEnabled(true);
                }
            };

            thread.start();

        } else if (e.getActionCommand().equals("i")) {
            if (player.heal()) {
                history.append("Heilung war erfolgreich\n");
                pHp.setText("Hp: " + player.hp);
            } else {
                if (player.getRemainingItemUses() == 0) {
                    history.append("Du hast keine Heiltraenke mehr uebrig\n");
                } else {
                    history.append("Du hast bereits volles Leben!\n");
                }
            }

        } else if (e.getActionCommand().equals("0")) {
            if (player.getSkill(0).useSkill(player, monster)) {
                history.append("Feuerball wurde erfolgreich eingesetzt\n");
                mHp.setText("Hp: " + monster.hp);
                pAp.setText("Ap: " + player.getAp());
            } else {
                history.append("Feuerball wurde NICHT erfolgreich eingesetzt\n");
            }

        } else if (e.getActionCommand().equals("1")) {
            if (player.getSkill(1).useSkill(player, monster)) {
                history.append("Wasserkanone wurde erfolgreich eingesetzt\n");
                mHp.setText("Hp: " + monster.hp);
                pAp.setText("Ap: " + player.getAp());
            } else {
                history.append("Wasserkanone wurde NICHT erfolgreich eingesetzt\n");
            }

        } else if (e.getActionCommand().equals("2")) {
            if (player.getSkill(2).useSkill(player, monster)) {
                history.append("Stampfer wurde erfolgreich eingesetzt\n");
                pAp.setText("Ap: " + player.getAp());
                mHp.setText("Hp: " + monster.hp);
            } else {
                history.append("Stampfer wurde NICHT erfolgreich eingesetzt\n");
            }

        } else {
            history.append("Ungueltige Aktion!\n");
        }
        if (monster.isDefeated()) {
            kampf = false;
            history.append("Du hast den Kampf gewonnen! Schließe das Fenster um weiter zu spielen.\n");
            if (!monster.inventar.isEmpty()) {
                int length = monster.inventar.length();
                for (int i = 1; i <= length; i++) {
                    player.inventar.insert(monster.inventar.getItem(i));
                }
                monster.inventar.delete();
            }
            Sync.battleFinished();

        } else {
            Thread thread3 = new Thread() {
                public void run() {
                    history.append("Das Monster greift an\n");
                    if (monster.attack(player) > -1) {
                        history.append("Der Angriff des Monsters war erfolgreich\n");
                        pHp.setText("Hp: " + player.hp);
                        if (clock == 0) {
                            clock = 1;
                        }
                    } else {
                        history.append("Du bist ausgewichen\n");
                    }
                    if (r == 1 && clock != 0 && monster.typ.equals("Scp-049")) {
                        history.append("Deathclock is ticking\n");
                    }
                    if (clock == 4 && monster.typ.equals("Scp-049")) {
                        monster.deathclock(player);
                    } else {
                        clock++;

                    }
                    try {
                        this.sleep(2000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }

            };
            thread3.start();
        }
        r++;
        if (player.isDefeated()) {
            kampf = false;
            System.out.println("Du hast den Kampf verloren.\n Game Over.");
            System.exit(0);
        }
    }
}
