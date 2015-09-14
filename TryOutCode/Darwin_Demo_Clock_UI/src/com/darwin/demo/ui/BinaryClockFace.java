package com.darwin.demo.ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;

public class BinaryClockFace extends ClockFace {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final float LED_SIZE = 10;
	private static final float HOR_OFFSET = 3;
	private static final float HOR_MARGIN = 2;

	private static final float VERT_OFFSET = 3;
	private static final float VERT_MARGIN = 2;

	public BinaryClockFace(final Dimension size) {
		setPreferredSize(size);
		setSize(size);
		setOpaque(false);
	}

	@Override
	protected void paintComponent(Graphics graphics) {
		BasicStroke penColor = new BasicStroke(1.0F);

		Graphics2D g = (Graphics2D) graphics.create();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_QUALITY);
		Color bg = getBackground();
		g.setColor(new Color(bg.getRed(), bg.getGreen(), bg.getBlue()));
		g.fill(getShape());
		g.setColor(new Color(239, 29, 5, 128));
		g.setStroke(penColor);

		paintRowOfLeds(g, 1, getHour(), 24);
		paintRowOfLeds(g, 2, getMinute(), 60);
		paintRowOfLeds(g, 3, getSecond(), 60);

		g.dispose();
	}

	private void paintRowOfLeds(Graphics2D g, float row, int unit, int max) {
		if (max >= 32) {
			g.setColor(new Color(239, 29, 5, 128 + (unit >> 5 & 0x1) * 127));
			if ((unit >> 5 & 0x1) == 1)
				g.fill(new java.awt.geom.Ellipse2D.Float(HOR_OFFSET
						+ HOR_MARGIN, ((row - 1) * (LED_SIZE + VERT_MARGIN))
						+ VERT_OFFSET, LED_SIZE, LED_SIZE));
		}
		g.setColor(new Color(239, 29, 5, 128 + (unit >> 4 & 0x1) * 127));
		if ((unit >> 4 & 0x1) == 1)
			g.fill(new java.awt.geom.Ellipse2D.Float(HOR_OFFSET + HOR_MARGIN
					+ (LED_SIZE + HOR_MARGIN) * 1,
					((row - 1) * (LED_SIZE + VERT_MARGIN)) + VERT_OFFSET,
					LED_SIZE, LED_SIZE));
		g.setColor(new Color(239, 29, 5, 128 + (unit >> 3 & 0x1) * 127));
		if ((unit >> 3 & 0x1) == 1)
			g.fill(new java.awt.geom.Ellipse2D.Float(HOR_OFFSET + HOR_MARGIN
					+ (LED_SIZE + HOR_MARGIN) * 2,
					((row - 1) * (LED_SIZE + VERT_MARGIN)) + VERT_OFFSET,
					LED_SIZE, LED_SIZE));
		g.setColor(new Color(239, 29, 5, 128 + (unit >> 2 & 0x1) * 127));
		if ((unit >> 2 & 0x1) == 1)
			g.fill(new java.awt.geom.Ellipse2D.Float(HOR_OFFSET + HOR_MARGIN
					+ (LED_SIZE + HOR_MARGIN) * 3,
					((row - 1) * (LED_SIZE + VERT_MARGIN)) + VERT_OFFSET,
					LED_SIZE, LED_SIZE));
		g.setColor(new Color(239, 29, 5, 128 + (unit >> 1 & 0x1) * 127));
		if ((unit >> 1 & 0x1) == 1)
			g.fill(new java.awt.geom.Ellipse2D.Float(HOR_OFFSET + HOR_MARGIN
					+ (LED_SIZE + HOR_MARGIN) * 4,
					((row - 1) * (LED_SIZE + VERT_MARGIN)) + VERT_OFFSET,
					LED_SIZE, LED_SIZE));
		g.setColor(new Color(239, 29, 5, 128 + (unit & 0x1) * 127));
		if ((unit & 0x1) == 1)
			g.fill(new java.awt.geom.Ellipse2D.Float(HOR_OFFSET + HOR_MARGIN
					+ (LED_SIZE + HOR_MARGIN) * 5,
					((row - 1) * (LED_SIZE + VERT_MARGIN)) + VERT_OFFSET,
					LED_SIZE, LED_SIZE));
	}

	private Shape getLedShape() {
		return new java.awt.geom.Ellipse2D.Float(0.0F, 0.0F, LED_SIZE, LED_SIZE);
	}

	protected Shape getShape() {
		return new java.awt.geom.RoundRectangle2D.Float(0.0F, 0.0F, HOR_OFFSET
				+ (6 * (LED_SIZE + HOR_MARGIN)) + HOR_OFFSET, VERT_OFFSET
				+ (3 * (LED_SIZE + VERT_MARGIN)) + VERT_OFFSET, 10.0F, 10.0F);
	}
}
