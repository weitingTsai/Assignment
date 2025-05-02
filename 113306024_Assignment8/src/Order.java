import java.util.ArrayList;

public class Order {
	private int totalAmount;
	private ArrayList<Item> items;
	private ArrayList<Integer> quantities;

	public Order() {
		items = new ArrayList<Item>();
		quantities = new ArrayList<Integer>();
	}

	public void addItems(Item item) {
		items.add(item);
	}

	public void addQuantity(int quantity) {
		quantities.add(quantity);
	}

	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}

	public int getTotalAmount() {
		return totalAmount;
	}

	public ArrayList<Item> getItems() {
		return items;
	}

	public ArrayList<Integer> getQuantity() {
		return quantities;
	}

	public void clearOrder() {
		items.clear();
		quantities.clear();
		totalAmount = 0;
	}
}