package com.cad.motor2d.sprites;

import java.awt.Image;
import java.awt.List;
import java.util.ArrayList;

public class Animation {

	private class Frame {

		int duration;
		Sprite sprite;

		public Frame(Sprite s, int d) {
			sprite = s;
			duration = d;
		}
	}

	private int frameCount;
	private int frameDelay;
	private int currentFrame;
	private int animationDirection;
	private int totalFrames;

	private boolean stopped;
	private ArrayList<Frame> frames = new ArrayList<Frame>();

	public Animation(Sprite[] frames, int frameDelay) {
		this.frameDelay = frameDelay;
		this.stopped = true;

		for (int i = 0; i < frames.length; i++) {
			addFrame(frames[i], frameDelay);
		}

		this.frameCount = 0;
		this.frameDelay = frameDelay;
		this.currentFrame = 0;
		this.animationDirection = 1;
		this.totalFrames = this.frames.size();

	}

	public void start() {
		if (!stopped) {
			return;
		}

		if (frames.size() == 0) {
			return;
		}

		stopped = false;
	}

	public void stop() {
		if (frames.size() == 0) {
			return;
		}

		stopped = true;
	}

	public void restart() {
		if (frames.size() == 0) {
			return;
		}

		stopped = false;
		currentFrame = 0;
	}

	public void reset() {
		this.stopped = true;
		this.frameCount = 0;
		this.currentFrame = 0;
	}

	private void addFrame(Sprite frame, int duration) {
		if (duration <= 0) {
			System.err.println("Invalid duration: " + duration);
			throw new RuntimeException("Invalid duration: " + duration);
		}

		frames.add(new Frame(frame, duration));
		currentFrame = 0;
	}

	public Sprite getSprite() {
		Sprite p = frames.get(currentFrame).sprite;
		update();
		return p;
	}

	public void update() {
		if (!stopped) {
			frameCount++;

			if (frameCount > frameDelay) {
				frameCount = 0;
				currentFrame += animationDirection;

				if (currentFrame > totalFrames - 1) {
					currentFrame = 0;
				} else if (currentFrame < 0) {
					currentFrame = totalFrames - 1;
				}
			}
		}

	}

}