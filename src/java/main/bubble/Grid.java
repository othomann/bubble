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

import bubble.Color;
import java.util.List;
import java.util.LinkedList;
import java.util.Iterator;
import java.util.Arrays;

public class Grid {

	private int width;
	private int height;
	private Square squares[][];
	private Color colors[];

	public Grid(int width, int height) {
		this.width = width;
		this.height = height;

		squares = new Square[width][];
		for (int i = 0; i < width; i++)
			squares[i] = new Square[height];

		colors = new Color[4];
		colors[0] = new Color(215, 81, 81);
		colors[1] = new Color(248, 139, 107);
		colors[2] = new Color(154, 255, 137);
		colors[3] = new Color(92, 181, 232);

		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				Color color = colors[(int)(Math.random() * (double)colors.length)];
				squares[i][j] = new Square(color, i, j);
			}
		}
	}

	public Iterator<Square> getSquares() {
		LinkedList<Square> list = new LinkedList<Square>();
		for (int i = 0; i < width; i++)
			list.addAll(Arrays.asList(squares[i]));
		return list.iterator();
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public Square get(int x, int y) {
		if (x >= 0 && x < width &&
			y >= 0 && y < height) {
			return squares[x][y];
		}
		return null;
	}

	private void findSelect(Square sq, LinkedList<Square> selected,
		boolean visited[][]) {
		Square north = get(sq.x(), sq.y() - 1);
		if (north != null && north.isEquals(sq) && !visited[north.x()][north.y()]) {
			north.select(true);
			selected.add(north);
			visited[north.x()][north.y()] = true;
			findSelect(north, selected, visited);
		}
		Square south = get(sq.x(), sq.y() + 1);
		if (south != null && south.isEquals(sq) && !visited[south.x()][south.y()]) {
			south.select(true);
			selected.add(south);
			visited[south.x()][south.y()] = true;
			findSelect(south, selected, visited);
		}
		Square east = get(sq.x() + 1, sq.y());
		if (east != null && east.isEquals(sq) && !visited[east.x()][east.y()]) {
			east.select(true);
			selected.add(east);
			visited[east.x()][east.y()] = true;
			findSelect(east, selected, visited);
		}
		Square west = get(sq.x() - 1, sq.y());
		if (west != null && west.isEquals(sq) && !visited[west.x()][west.y()]) {
			west.select(true);
			selected.add(west);
			visited[west.x()][west.y()] = true;
			findSelect(west, selected, visited);
		}
	}

	public LinkedList<Square> select(Square sq) {
		boolean visited[][] = new boolean[getWidth()][];
		LinkedList<Square> squares = new LinkedList<Square>();

		for (int i = 0; i < getWidth(); i++)
			visited[i] = new boolean[getHeight()];

		findSelect(sq, squares, visited);

		return squares;
	}

	private void rearrangeVerticale(Square sq) {
		int x = sq.x();
		int y = sq.y() - 1;
		Square north = null;
		while (north == null && y>= 0)
			north = squares[x][y--];

		if (north != null) {
			squares[sq.x()][sq.y()] = new Square(north.color(), sq.x(), sq.y());
			squares[sq.x()][sq.y() - 1] = null;
			rearrangeVerticale(north);
		}
	}

	private void pastRowFrom(int index) {
		for (int i = index; i + 1 < width; i++) {
			for (int j = 0; j < height; j++) {
				Square sq = squares[i + 1][j];
				if (sq != null)
					squares[i][j] = new Square(sq.color(), i, j);
			}
		}
		for (int j = 0; j < height; j++) {
			Color color = colors[(int)(Math.random() * (double)colors.length)];
			squares[width - 1][j] = new Square(color, width - 1, j);
		}
	}

	private void rearrangeHorizontale() {
		for (int i = 0; i + 1 < width; i++) {
			if (squares[i][height - 1] == null) {
				pastRowFrom(i);
				i--;
			}
		}
	}

	public void remove(LinkedList<Square> selected) {
		Iterator<Square> iterator = selected.iterator();
		while (iterator.hasNext()) {
			Square sq = iterator.next();
			squares[sq.x()][sq.y()] = null;
		}

		iterator = selected.iterator();
		while (iterator.hasNext()) {
			Square sq = iterator.next();
			if (squares[sq.x()][sq.y()] == null) {
				rearrangeVerticale(sq);
			}
		}
		rearrangeHorizontale();
	}

}
