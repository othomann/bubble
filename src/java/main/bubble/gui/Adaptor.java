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

import bubble.Color;
import bubble.Grid;
import bubble.Square;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Iterator;

public class Adaptor {

	private static java.awt.Color convertColor(int r, int g, int b) {
		if (r > 255)
			r = 255;
		else if (r < 0)
			r = 0;
		if (g > 255)
			g = 255;
		else if (g < 0)
			g = 0;
		if (b > 255)
			b = 255;
		else if (b < 0)
			b = 0;

		return new java.awt.Color(r, g, b);
	}

	public static BufferedImage adaptGrid(Grid grid, int rwidth, int rheight) {
		BufferedImage buffer = new BufferedImage(rwidth, rheight,
			BufferedImage.TYPE_INT_ARGB);

		int cell_size = Square.size;
		int delta_width = (rwidth - (grid.getWidth() * Square.size)) / 2;
		int delta_height = (rheight - (grid.getHeight() * Square.size)) / 2;

		Graphics2D g = buffer.createGraphics();
		g.setColor(java.awt.Color.BLACK);
		g.fillRect(0, 0, rwidth, rheight);

		Iterator<Square> it = grid.getSquares();
		while (it.hasNext()) {
			Square sq = it.next();
			if (sq == null)
				continue;

			bubble.Color col = sq.color();
			for (int i = 0; i < 10; i++) {
				g.setColor(convertColor(col.r - (i * 5),
					col.g - (i * 5), col.b - (i * 5)));
				g.fillRect(sq.x() * Square.size + delta_width + i,
					sq.y() * Square.size + delta_height + i,
					Square.size - (i * 3), Square.size - (i * 3));
			}
		}

		return buffer;
	}

	public static int adaptX(Grid grid, int x, int width) {
		int cell_size = Square.size;
		int delta = (width - (grid.getWidth() * cell_size)) / 2;

		return (x - delta) / cell_size;
	}

	public static int adaptY(Grid grid, int y, int height) {
		int cell_size = Square.size;
		int delta = (height - (grid.getHeight() * cell_size)) / 2;

		return (y - delta) / cell_size;
	}

}
