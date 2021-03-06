/*
 * $Id:  TikZUncertainProcessNode.java 13:55:09 Meike Aichele$
 * $URL$
 * ---------------------------------------------------------------------
 * This file is part of SBML2LaTeX, a program that creates
 * human-readable reports for given SBML files.
 * 
 * Copyright (C) 2008-2014 by the University of Tuebingen, Germany.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 * ---------------------------------------------------------------------
 */
package org.sbml.totikz;

import org.sbml.jsbml.ext.layout.CurveSegment;
import org.sbml.jsbml.ext.layout.Point;

import de.zbit.sbml.layout.AbstractSBGNProcessNode;
import de.zbit.sbml.layout.UncertainProcessNode;

/**
 * @author Meike Aichele
 * @since 1.0
 * @version $Rev$
 */
public class TikZUncertainProcessNode extends AbstractSBGNProcessNode<String>
implements UncertainProcessNode<String> {

  public TikZUncertainProcessNode() {
    super();
    setLineWidth(TikZLayoutBuilder.DEFAULT_LINE_WIDTH);
  }

  /* (non-Javadoc)
   * @see de.zbit.sbml.layout.SBGNNode#draw(double, double, double, double, double, double)
   */
  @Override
  public String draw(double x, double y, double z, double width,
    double height, double depth) {
    StringBuilder sb = new StringBuilder();
    sb.append(TikZ.drawShapeRectangle("black", getLineWidth(), x - width/2d, y - height/2d, x + width/2d, y + height/2d));
    sb.append(TikZ.drawText(x, y, "anchor = center", "phv", "?"));
    return sb.toString();
  }

  /* (non-Javadoc)
   * @see de.zbit.sbml.layout.ProcessNode#draw(double, double, double, double, double, double, double, org.sbml.jsbml.ext.layout.Point)
   */
  @Override
  public String draw(double x, double y, double z, double width,
    double height, double depth, double rotationAngle, Point rotationCenter) {
    StringBuilder sb = new StringBuilder();
    sb.append(TikZ.drawShapeRectangle("black", getLineWidth(), x - width/2d, y - height/2d, x + width/2d, y + height/2d, rotationAngle, rotationCenter));
    sb.append(TikZ.drawText(x, y, "anchor = center, rotate = " + (rotationAngle % 90), "phv", "?"));
    return sb.toString();
  }

  /* (non-Javadoc)
   * @see de.zbit.sbml.layout.SBGNProcessNode#drawLineSegment(org.sbml.jsbml.ext.layout.CurveSegment, double, org.sbml.jsbml.ext.layout.Point)
   */
  @Override
  public String drawCurveSegment(CurveSegment segment, double rotationAngle, Point rotationCenter) {
    String lineSegment = null;

    Point start = segment.getStart();
    double x1 = start.getX();
    double y1 = start.getY();
    Point end = segment.getEnd();
    double x2 = end.getX();
    double y2 = end.getY();

    if ((rotationAngle % 180) == 0) {
      lineSegment = TikZ.drawLine("black", getLineWidth(), x1, y1, x2, y2);
    } else {
      lineSegment = TikZ.drawLine("black", getLineWidth(), x1, y1, x2, y2, rotationAngle, rotationCenter);
    }
    return lineSegment;
  }

}
