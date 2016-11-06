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

import bubble.Context;
import bubble.Grid;
import bubble.Square;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GuiGridMouseListener implements MouseListener {

	@Override
	public void mouseClicked(MouseEvent e) {
		Context.instance().select(e.getX(), e.getY());
	}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {}

}
