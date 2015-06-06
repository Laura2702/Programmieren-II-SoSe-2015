
public class Shop extends Character {
	
	public Shop() {
		super(1000,1000,500,0.9,new Inventar<Item>());
		inventar.insert(new Item());
		inventar.insert(new Item());
		inventar.insert(new Item());
	}
	


}
