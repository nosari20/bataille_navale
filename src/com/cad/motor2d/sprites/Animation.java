package com.cad.motor2d.sprites;

import java.awt.Image;
import java.awt.List;
import java.util.ArrayList;

public class Animation {


	private int frameCount;
	private int currentFrame;
	private int animationDirection;
	private int totalFrames;
	private boolean loop;
	private boolean stopped;
	private ArrayList<Sprite> frames = new ArrayList<Sprite>();

	public Animation(Sprite[] frames) {
		this.stopped = true;
		this.loop = false;

		for (int i = 0; i < frames.length; i++) {
			addFrame(frames[i]);
		}

		this.frameCount = 0;
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
	
	public boolean isFinished(){
		return stopped;
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
	
	public void loop(boolean l){
		loop = l;
	}

	private void addFrame(Sprite frame) {
		frames.add(frame);
	}

	public Sprite getSprite() {
		return frames.get(currentFrame);
	}

	public void update() {
		//System.out.println("curr = " + currentFrame +  "tot = " +totalFrames);
		if(!loop && currentFrame == totalFrames -1){
			stop();
		}
		
		if (!stopped) {
			currentFrame++;

			if (currentFrame > totalFrames - 1) {
				currentFrame = 0;
			} else if (currentFrame < 0) {
				currentFrame = totalFrames - 1;
			}

		}

	}

}