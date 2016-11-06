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

public class Square {
	private Color color;
	private int x;
	private int y;
	private boolean selected;
	public static final int size = 30;

	public Square(Color color, int x, int y) {
		this.color = color;
		this.x = x;
		this.y = y;
	}

	public int x() {
		return this.x;
	}

	public int y() {
		return this.y;
	}

	public Color color() {
		if (!selected)
			return this.color;
		else
			return new Color(155, 57, 236);
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public boolean isEquals(Square sq) {
		return color.isEquals(sq.color);
	}

	public void select(boolean status) {
		selected = status;
	}

	public boolean isSelected() {
		return selected;
	}

	public String toString() {
		return "{ " + x + ", " + y + " } = " + color;
	}
}
