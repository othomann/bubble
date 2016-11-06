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

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;

import java.util.Iterator;

import bubble.Grid;
import bubble.Square;
import bubble.Context;

public class GuiMain extends JFrame {

	private GuiGrid grid = null;

	public GuiMain(int width, int height) {
		this.grid = new GuiGrid(width, height);
		this.setContentPane(this.grid);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setTitle("bubble breaker : 0");
		this.setSize(width, height);
		this.setVisible(true);
	}

	public void refresh(BufferedImage buffer) {
		grid.setBuffer(buffer);
		this.setTitle("bubble breaker : " +
			Integer.toString(Context.instance().getScore()));
	}

}
