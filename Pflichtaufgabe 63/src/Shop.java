/**
 * Klasse Shop erstellt einen Shop
 * 
 * @author Laura Pichlmeier 4573524 Gruppe 3b
 * @author Sophie Duehn 4577449 Gruppe 3b
 * @author Sophie Unverzagt 4568856 Gruppe 3b
 * 
 */
public class Shop extends Character {
	/**
	 * Shop-Constructor
	 * 
	 */
	public Shop() {
		super(1000,1000,500,0.9,new Inventar<Item>());
		
	}
	/**
	 * Initialisierung des Shops: Shop wird mit Items gefüllt
	 */
	public  void Init() {
		
		inventar.insert(new Item());
		inventar.insert(new Item());
		inventar.insert(new Item());
	}
	


}
