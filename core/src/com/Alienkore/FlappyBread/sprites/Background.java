package com.Alienkore.FlappyBread.sprites;

import com.badlogic.gdx.graphics.Texture;


public class Background {

	private Texture bg;
 

	public Background() {
	
		bg= new Texture("bg.png");
	}

	public Texture getBg() {
		return bg;
	}

	public void setBg(Texture bg) {
		this.bg = bg;
	}
	
	public void dispose(){
		bg.dispose();
	}
	
	
	
}
