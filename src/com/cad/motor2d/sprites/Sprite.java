package com.cad.motor2d.sprites;

import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;

public class Sprite {

	private Image image;

	public Sprite(Image i) {
		image = i;
	}

	public Image getImage() {
		return image;
	}

	public Sprite rotate(double angle) {
		angle = Math.toRadians(angle);
		Image image = getImage();
		double sin = Math.abs(Math.sin(angle)), cos = Math.abs(Math.cos(angle));
		int w = image.getWidth(null), h = image.getHeight(null);
		int neww = (int) Math.floor(w * cos + h * sin), newh = (int) Math.floor(h * cos + w * sin);

		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice gd = ge.getDefaultScreenDevice();
		GraphicsConfiguration gc = gd.getDefaultConfiguration();

		BufferedImage result = gc.createCompatibleImage(neww, newh, Transparency.TRANSLUCENT);
		Graphics2D g = result.createGraphics();
		g.translate((neww - w) / 2, (newh - h) / 2);
		g.rotate(angle, w / 2, h / 2);
		g.drawRenderedImage((RenderedImage) image, null);
		g.dispose();

		return new Sprite(result);
	}
	
	public Sprite flipH(){
		Image image = getImage();	
		int w = image.getWidth(null);
        int h = image.getHeight(null);
        BufferedImage flippedImage = new BufferedImage(w, h, ((BufferedImage) image).getType());
        Graphics2D g = flippedImage.createGraphics();
        g.drawImage(image, 0, 0, w, h, w, 0, 0, h, null);
        g.dispose();
		return new Sprite(flippedImage);
	}
	
	public Sprite flipV(){
		Image image = getImage();	
		int w = image.getWidth(null);
        int h = image.getHeight(null);
        BufferedImage flippedImage = new BufferedImage(w, h, ((BufferedImage) image).getType());
        Graphics2D g = flippedImage.createGraphics();
        g.drawImage(image, 0, 0, w, h, 0, h, w, 0, null);
        g.dispose();
		return new Sprite(flippedImage);
	}

}
