/**
 *  Die Klasse Level erzeugt ein Level
 *  @author Laura Pichlmeier 4573524 Gruppe 3b
 *  @author Sophie Duehn 4577449 Gruppe 3b
 *  @author Sophie Unverzagt 4568856 Gruppe 3b
 **/
public class Level {
    /** 2D - Array */ 
    private char[][] mapData;
    /** Spieler Position an der Stelle x */
    private int playerPositionx;
    /** Spieler Position an der Stelle y */ 
    private int playerPositiony;
    
    /**
     *  Constructor 
     *  @param mapData mapData
     **/
    public Level(char[][] mapData) {
        this.mapData = mapData;
        this.playerPositionx = 0;
        this.playerPositiony = 0;
        for (int i = 0; i < mapData.length; i++) {
            for (int j = 0; j < mapData[i].length; j++) {
                if (mapData[i][j] == 'S') {
                    this.playerPositionx = i;
                    this.playerPositiony = j;
                }
            }
        }
    }
    
    /**
     *  Map String wird erstellt
     *  @return result result
     **/
    public String toString() {
        String result = "";
        for (int i = 0; i < mapData.length; i++) {
            for (int j = 0; j < mapData[i].length; j++) {
                if (playerPositionx == i && playerPositiony == j) {
                    result = result + "|" + "P";
                } else {
                    result = result + "|" + mapData[i][j];
                }
            } 
            result = result + "\n";
        }
        result = result.replace(".", " "); 
        return result; 
    }     
    
    /**
     *  @return true, wenn Spieler auf Zielfeld ist
     **/
    public boolean zielfeld() {
        if (this.mapData[this.playerPositionx][this.playerPositiony] == 'Z') {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     *  @param x x-Wert
     *  @param y y-Wert
     *  @return true, wenn wand im Weg
     **/
    public boolean wand(int x, int y) {
        if (x >= 0 && y >= 0 && x < mapData.length) {
            if (y <= this.mapData[x].length) {
                if (this.mapData[x][y] != 'x') {
                    return false;
                }
            } 
        } 
        return true;
    }
    
    /**
     *  Schmiede: ATK werden erhoeht
     *  @param player Player
     **/
    public void schmiede(Player player) {
        player.atkerhoehen(5);
        System.out.println("Deine ATK wurde um 5 erhoeht.\n");
    }
    
    /**
     *  Heilbrunnen: HP und AP werden aufgefuellt
     *  @param player Player
     **/
    public void heilbrunnen(Player player) {
        player.hperhoehen();
        player.aperhoehen();
        System.out.println("Du wurdest vollgeheilt. \n");
    }
    /**
     * Buy :Item an Stelle i wird dem Spieler verkauft
     * @param player
     * @param shop
     * @param a
     */
    public void buy(Player player,Shop shop,int a){
    	
    	player.inventar.insert(shop.inventar.getItem(a));
    	player.setGold(player.getGold() - shop.inventar.getItem(a).getValue()*1.3);
    	shop.inventar.delete(shop.inventar.getItem(a));
    }
    /**
     * Sell: Item an der Stelle i des Spielers wird an den Shop verkauft.
     * @param player
     * @param shop
     * @param i
     * @return gold
     */
    public void sell(Player player, Shop shop,int i) {
    	shop.inventar.insert(player.inventar.getItem(i));
    	player.setGold(player.getGold() +  player.inventar.getItem(i).getValue());
    	player.inventar.delete(player.inventar.getItem(i));
	}
    /**
     *  @return playerPositionx
     **/
    public int getPlayerPositionx() {
        return this.playerPositionx;
    }
    
    /**
     *  @return playerPositiony
     **/
    public int getPlayerPositiony() {
        return this.playerPositiony;
    }
    
    /**
     *  @param x neue playerPositionx
     **/
    public void setPlayerPositionx(int x) {
        this.playerPositionx = x;
    }
    
    /**
     *  @param y neue playerPositiony
     **/
    public void setPlayerPositiony(int y) {
        this.playerPositiony = y;
    }   
    
    /**
     *  @return Feld
     **/
    public char getFeld() {
        return this.mapData[playerPositionx][playerPositiony];
    }
    
    /**
     *  @param x ersetzt Feld
     *  @param y ersetzt Feld
     *  @param z ersetzt bestimmten Buchstaben
     **/
    public void replaceFeld(int x, int y, char z) {
        this.mapData[x][y] = z;
    }        
}
