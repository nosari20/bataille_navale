package com.cad.ui.gamescreen;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class GridComponent extends JPanel {
	private final int SIZE = 10;
	private final int FIELD_SIZE = 34; // = 32px because of the border
	private final JButton gameFieldGUI[][] = new JButton[10][10];

	public GridComponent() {
		this.init();
		this.buildFields();

	}

	public void init() {
		this.setLayout(new GridBagLayout());
		this.setPreferredSize(new Dimension(340, 340));
		this.setBackground(new Color(131, 209, 232));
		this.setBorder(BorderFactory.createLineBorder(new Color(32, 156, 185)));
	}

	public void buildFields() {
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;

		for (int col = 0; col < 10; col++) {
			for (int row = 0; row < 10; row++) {
				JButton button = new JButton();
				button.setBackground(new Color(131, 209, 232));
				button.setBorder(BorderFactory.createLineBorder(new Color(32, 156, 185)));
				button.setCursor(new Cursor(Cursor.HAND_CURSOR));
				button.setPreferredSize(new java.awt.Dimension(FIELD_SIZE, FIELD_SIZE));
				button.addMouseListener(new MouseAdapter() {
					@Override
					public void mousePressed(MouseEvent event) {
						JButton button = (JButton) event.getSource();
						Rectangle rectangle = button.getBounds();
						Point point = button.getLocation();

						// claculate field position
						int row = point.y / rectangle.height;
						int col = point.x / rectangle.width;

						// shoot on field
						// shoot(col, row);
					}
				});

				// add button to GUI grid-manager
				setField(col, row, button);

				// set field position
				c.gridx = col;
				c.gridy = row;

				// add field to the grid
				this.add(button, c);
			}
		}
	}

	public void displayShip(String type, int x, int y, boolean horizontal, int length) {
		// System.out.printf("type: %s, x: %d, y: %d, horizontal: %b length:
		// %d\n", type, x, y, horizontal, length);

		// create new button
		JButton button = new JButton();
		button.setOpaque(false);
		button.setContentAreaFilled(false);
		button.setCursor(new Cursor(Cursor.HAND_CURSOR));
		button.setBorder(BorderFactory.createLineBorder(new Color(32, 156, 185)));
		// add click listener for sunken ships
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent event) {
				System.out.println("Ship sunken");
			}
		});

		// grid prefs for the new button
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;

		// set position
		c.gridx = x;
		c.gridy = y;

		// button dimensions
		int width = 34;
		int height = 34;

		// occupy ship field in grid
		if (horizontal) {
			// horizontal
			c.gridwidth = length;
			c.gridheight = 1;
			width = 34 * length;
		} else {
			// vertical
			c.gridwidth = 1;
			c.gridheight = length;
			height = 34 * length;
		}

		// maybe update array reference ?!
		// setField(x, y, button);

		// select the ship type's preferences
		button.setPreferredSize(new Dimension(width, height));

		// remove old elements
		removeFields(x, y, horizontal, length);
		setFieldIcon(button, "");

		// add new button and update the grid

		this.add(button, c);
		this.revalidate();
		this.repaint();

	}

	private void setFieldIcon(JButton field, String imgPath) {
		try {
			Image img = ImageIO.read(getClass().getResource("ship2.png"));
			field.setIcon(new ImageIcon(img));
		} catch (IOException e) {
			System.out.println("Couldn't set field icon: " + e);
		}
	}

	private void removeFields(int x, int y, boolean horizontal, int length) {

		for (int i = 0; i < length; i++) {
			if (horizontal) {
				this.remove(getField(x + i, y));
			} else {
				this.remove(getField(x, y + i));
			}
		}

	}

	private JButton getField(int x, int y) {
		return gameFieldGUI[y][x];
	}

	private boolean shoot(int x, int y) {
		// calls GameField's shoot method
		System.out.printf("At cell %d,%d\n", x, y);
		return true;// gameField.shoot(x, y);
	}

	public void setField(int x, int y, JButton obj) {
		gameFieldGUI[y][x] = obj;
	}

}
