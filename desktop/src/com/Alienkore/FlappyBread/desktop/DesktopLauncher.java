package com.Alienkore.FlappyBread.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.Alienkore.FlappyBread.FlappyBread;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width=FlappyBread.WIDTH;
		config.height=FlappyBread.HEIGHT;
		config.title=FlappyBread.TITLE;

		new LwjglApplication(new FlappyBread(), config);
	}
}
