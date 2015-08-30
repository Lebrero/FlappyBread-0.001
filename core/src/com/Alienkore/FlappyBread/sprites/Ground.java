package com.Alienkore.FlappyBread.sprites;

import com.badlogic.gdx.graphics.Texture;

public class Ground {

	private Texture ground;

	public Ground() {
		ground = new Texture("ground.png");
	}

	public Texture getGround() {
		return ground;
	}

	public void dispose() {
		ground.dispose();
	}
}
