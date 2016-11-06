/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package bubble.gui;

import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.GridLayout;

public class GuiGrid extends JPanel {

	private JLabel label = null;
	private GuiGridMouseListener mouse = null;

	public GuiGrid(int width, int height) {
		label = new JLabel();
		mouse = new GuiGridMouseListener();
		this.setLayout(new GridLayout(1, 1, 0, 0));
		this.addMouseListener(mouse);
		this.setBackground(Color.BLACK);
		this.add(label);
	}

	public void setBuffer(BufferedImage buffer) {
		label.setIcon(new ImageIcon(buffer));
	}

}
