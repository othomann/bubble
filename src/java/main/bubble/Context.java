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
package bubble;

import bubble.Grid;
import bubble.gui.Adaptor;
import bubble.gui.GuiMain;

import java.util.LinkedList;
import java.util.Iterator;

public class Context {

	private static Context instance = null;
	private Grid grid = null;
	private GuiMain frame = null;
	private LinkedList<Square> selected = new LinkedList<Square>();
	private int score = 0;

	public static synchronized Context instance() {
		if (instance == null)
			instance = new Context();
		return instance;
	}

	public void initialize(int nb_width, int nb_height) {
		frame = new GuiMain(nb_width * Square.size, nb_height * Square.size);
		grid = new Grid(nb_width, nb_height);
		refresh();
	}

	public Iterator<Square> getSquares() {
		return grid.getSquares();
	}

	private void removeSelected() {
		Iterator<Square> iterator = selected.iterator();
		while (iterator.hasNext()) {
			Square sq = iterator.next();
			sq.select(false);
		}
		selected.clear();
	}

	public void select(int x, int y) {
		int _x = Adaptor.adaptX(grid, x, frame.getWidth());
		int _y = Adaptor.adaptY(grid, y, frame.getHeight());

		Square source = grid.get(_x, _y);
		if (source == null)
			return ;

		if (source.isSelected() == false) {
			removeSelected();
			selected = grid.select(source);
		} else {
			score += selected.size() * selected.size();
			grid.remove(selected);
			refresh();
			removeSelected();
		}
		refresh();
	}

	private void refresh() {
		frame.refresh(Adaptor.adaptGrid(grid, frame.getWidth(),
			frame.getHeight()));
	}

	public int getScore() {
		return score;
	}

}
