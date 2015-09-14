package com.darwin.demo.ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.Rectangle2D;

class RomanClockFace extends ClockFace {

	private String getRomanNumeral(int number) {
		switch (number) {
		case 1: // '\001'
			return "I";

		case 2: // '\002'
			return "II";

		case 3: // '\003'
			return "III";

		case 4: // '\004'
			return "IV";

		case 5: // '\005'
			return "V";

		case 6: // '\006'
			return "VI";

		case 7: // '\007'
			return "VII";

		case 8: // '\b'
			return "VIII";

		case 9: // '\t'
			return "IX";

		case 10: // '\n'
			return "X";

		case 11: // '\013'
			return "XI";

		case 12: // '\f'
		default:
			return "XII";
		}
	}

	protected void paintComponent(Graphics graphics) {
		paintFace(graphics, Math.min(getWidth(), getHeight()));
	}

	protected void paintFace(Graphics graphics, int size) {
		Point center = new Point(size / 2, size / 2);
		int radius = center.x;
		int margin = radius / 20;
		int w = size;
		border = new BasicStroke(Math.max(1.0F, (float) w / 150F), 1, 1);
		secondHand = new BasicStroke(Math.max(1.0F, (float) w / 75F), 1, 1);
		minuteHand = new BasicStroke(Math.max(1.0F, (float) w / 38F), 1, 1);
		hourHand = new BasicStroke(Math.max(1.5F, (float) w / 20F), 1, 1);
		ticks = new BasicStroke(1.0F);
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
		Font font = getFont();
		g.setFont(font.deriveFont(1, size / 12));
		g.setColor(new Color(0, 0, 0, 128));
		g.setStroke(border);
		g.draw(new java.awt.geom.Ellipse2D.Float(0.0F, 0.0F, size - 1, size - 1));
		g.draw(new java.awt.geom.Ellipse2D.Float(margin, margin, size - margin
				* 2 - 1, size - margin * 2 - 1));
		g.translate(center.x, center.y);
		g.setColor(getForeground());
		int numbers = (radius * 3) / 4;
		for (int i = 0; i < 12; i++) {
			double theta = (6.2831853071795862D * (double) i) / 12D;
			String str = getRomanNumeral((i + 2) % 12 + 1);
			Rectangle2D rect = g.getFontMetrics().getStringBounds(str, g);
			g.drawString(
					str,
					Math.round((double) numbers * Math.cos(theta)
							- rect.getWidth() / 2D),
					Math.round((double) numbers * Math.sin(theta)
							+ (double) (margin * 2)));
		}

		for (int i = 0; i < 60; i++) {
			g.setColor(getForeground());
			g.setStroke(ticks);
			g.drawLine(radius - margin * 2, 0, radius - margin, 0);
			if (i % 5 == 0)
				g.drawLine(radius - margin * 3, 0, radius - margin, 0);
			if ((i + 15) % 60 == getMinute()) {
				g.setStroke(minuteHand);
				g.drawLine(0, 0, radius - margin * 4, 0);
			}
			if (((i + 15) % 60 == getHour() * 5 + (getMinute() * 5) / 60)
					|| ((i + 15) % 60 == (getHour() - 12) * 5
							+ (getMinute() * 5) / 60)) {
				g.setStroke(hourHand);
				g.drawLine(0, 0, radius / 2, 0);
			}
			if ((i + 15) % 60 == getSecond()) {
				g.setColor(new Color(255, 0, 0, 128));
				g.setStroke(secondHand);
				g.drawLine(0, 0, radius - margin * 4, 0);
			}
			g.rotate(0.10471975511965977D);
		}

		g.dispose();
	}

	// private Image getIconImage() {
	// BufferedImage image = new BufferedImage(64, 64, 2);
	// Graphics2D g = image.createGraphics();
	// g.setRenderingHint(RenderingHints.KEY_RENDERING,
	// RenderingHints.VALUE_RENDER_QUALITY);
	// java.awt.Composite old = g.getComposite();
	// g.setComposite(AlphaComposite.Clear);
	// g.fillRect(0, 0, image.getWidth(), image.getHeight());
	// g.setComposite(old);
	// paintFace(g, 64);
	// g.dispose();
	// return image;
	// }

	private Stroke border;
	private Stroke secondHand;
	private Stroke minuteHand;
	private Stroke hourHand;
	private Stroke ticks;

	RomanClockFace(final Dimension size) {
		setPreferredSize(size);
		setSize(size);
		setOpaque(false);
	}

	public Shape getShape() {
		final String METHOD_SIGNATURE = "getShape";
		return new java.awt.geom.Ellipse2D.Float(0.0F, 0.0F, getSize().height,
				getSize().width);
	}
}
