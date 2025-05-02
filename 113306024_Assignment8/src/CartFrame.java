import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class CartFrame extends JFrame {
	private int frame_width = 400;
	private int frame_height = 300;
	private int field_width = 10;

	private JPanel operatePanel, overallPanel;
	private JTextArea infoArea;
	private JLabel itemLabel, quantityLabel;
	private JComboBox<String> itemCombo;
	private JTextField quantityField;
	private JButton addButton, buyButton;

	private Order order;

	private String[] itemNames = { "Shirt", "Coat", "Pants", "Shoes" };
	private int[] itemPrices = { 499, 1320, 799, 2180 };

	public CartFrame() {
		super("Shopping cart");
		setSize(frame_width, frame_height);
		order = new Order();
		createItemComp();
		createButton();
		createInfoArea();
		createPanel();
	}

	public void createItemComp() {
		itemCombo = new JComboBox<>(itemNames);
		quantityField = new JTextField(field_width);
	}

	public void createButton() {
		addButton = new JButton("Add to cart");
		buyButton = new JButton("Check out");

		Dimension halfWidth = new Dimension(190, 30);
		addButton.setPreferredSize(halfWidth);
		buyButton.setPreferredSize(halfWidth);

		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = itemCombo.getSelectedIndex();
				String quantityText = quantityField.getText();
				try {
					int quantity = Integer.parseInt(quantityText);
					if (quantity <= 0) {
						JOptionPane.showMessageDialog(CartFrame.this,
								"CheckPositiveError: The number must be greater than 0", "Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
					Item item = new Item(itemNames[index], itemPrices[index]);
					order.addItems(item);
					order.addQuantity(quantity);
					infoArea.setText("Action completed");
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(CartFrame.this, "CheckNumberError: Please enter a valid number",
							"Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		buyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Item> items = order.getItems();
				ArrayList<Integer> quantities = order.getQuantity();

				StringBuilder sb = new StringBuilder();
				int total = 0;
				sb.append("Item  Price  Quantity").append("\n");

				for (int i = 0; i < items.size(); i++) {
					Item item = items.get(i);
					int quantity = quantities.get(i);
					int subtotal = item.getPrice() * quantity;
					sb.append(item.getName()).append("  ").append(item.getPrice()).append("    ").append(quantity)
							.append("\n");
					total += subtotal;
				}

				order.setTotalAmount(total);
				sb.append("------------------------------").append("\n");
				sb.append("The total amount:").append(order.getTotalAmount());
				infoArea.setText(sb.toString());
				order.clearOrder();
			}
		});
	}

	public void createInfoArea() {
		infoArea = new JTextArea(11, 37);
		infoArea.setEditable(false);
	}

	public void createPanel() {
		itemLabel = new JLabel("Item");
		quantityLabel = new JLabel("Quantity");

		JPanel itemQuantityPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
		itemQuantityPanel.add(Box.createHorizontalStrut(27), itemLabel);
		itemQuantityPanel.add(itemLabel);
		itemQuantityPanel.add(itemCombo);
		itemQuantityPanel.add(Box.createHorizontalStrut(50), itemCombo);
		itemQuantityPanel.add(quantityLabel);
		itemQuantityPanel.add(quantityField);

		Dimension halfWidth = new Dimension(180, 30);
		addButton.setPreferredSize(halfWidth);
		buyButton.setPreferredSize(halfWidth);

		operatePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
		operatePanel.add(addButton);
		operatePanel.add(buyButton);

		overallPanel = new JPanel();
		overallPanel.setLayout(new BoxLayout(overallPanel, BoxLayout.Y_AXIS));
		overallPanel.add(itemQuantityPanel);
		overallPanel.add(operatePanel);

		JPanel infoPanel = new JPanel();
		infoPanel.add(infoArea);

		setLayout(new BorderLayout());
		add(overallPanel, BorderLayout.NORTH);
		add(infoPanel, BorderLayout.CENTER);
	}
}
