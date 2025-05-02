import java.awt.Font;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Assignment7 {
	static OXGameManager manager = new OXGameManager();
	static JButton[] btns = new JButton[9];
	static JLabel score = new JLabel("O: 0 ; X: 0");
	static JLabel currentPlayer = new JLabel("Current Player: O");
	static JButton reStart = new JButton("ReStart");
	static JButton finish = new JButton("Finish");

	public static void main(String[] args) {

		// Implement GUI Layout//
		// The window title is "Frame" //
		// JFrame size is (600, 600) //
		// JFrame sets the layout to BorderLayout //
		JFrame f = new JFrame("Frame");
		f.setSize(600, 600);
		f.setLayout(new BorderLayout());

		Font font = new Font("Arial", Font.PLAIN, 30);
		score.setFont(font);
		currentPlayer.setFont(font);
		reStart.setFont(font);
		finish.setFont(font);
		// ====== Top info panel (NORTH) ======
		JPanel infoPanel = new JPanel(new GridLayout(1, 2));
		infoPanel.add(score);
		infoPanel.add(currentPlayer);
		f.add(infoPanel, BorderLayout.NORTH);
		// ====== Game board (CENTER) ======
		JPanel boardPanel = new JPanel(new GridLayout(3, 3));
		for (int i = 0; i < 9; i++) {
			JButton btn = new JButton(Integer.toString(i));
			btn.setFont(new Font("Arial", Font.PLAIN, 50));
			int index = i;
			// Implement the checker board button
			btn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					if (!manager.finish() && btn.getText().equals(String.valueOf(index))) {
						String sign = manager.play(index);
						btn.setText(sign);
						btn.setEnabled(false);
						updateInfo();
						String result = manager.checkWin();
						if (result != null) {
							if (result.equals("O")) {
								updateInfo();
								currentPlayer.setText("O wins!");
								for (JButton b : btns) {
									b.setEnabled(false);
								}
							} else if (result.equals("X")) {
								updateInfo();
								currentPlayer.setText("X wins!");
								for (JButton b : btns) {
									b.setEnabled(false);
								}
							} else {
								currentPlayer.setText("It's a draw!");
							}
						}
					}
				}
			});
			btns[i] = btn;
			boardPanel.add(btn);
		}
		f.add(boardPanel, BorderLayout.CENTER);

		// ====== Bottom control panel (SOUTH) ======
		JPanel controlPanel = new JPanel(new GridLayout(1, 2));
		controlPanel.add(reStart);
		controlPanel.add(finish);
		f.add(controlPanel, BorderLayout.SOUTH);
		// Implement the ReStart button
		reStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				for (JButton b : btns) {
					b.setEnabled(true);
				}
				manager.initialize();
				for (int i = 0; i < 9; i++) {
					btns[i].setText(String.valueOf(i));
				}
				updateInfo();
			}
		});
		// Implement the finish button
		finish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				f.dispose();
			}
		});
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}

	// Implement a method updateInfo() to update GUI display information //
	// Update the score label to show current scores of O and X //
	// Update the current player label to show whose turn is next //
	// This method should be called after each move or after the game is reset //
	// Example output: "O: 2 ; X: 1" and "Current Player: X" //
	private static void updateInfo() {
		score.setText("O: " + manager.getScoreO() + " ; X: " + manager.getScoreX());
		currentPlayer.setText("Current Player: " + manager.getCurrentPlayer());
	}
}
