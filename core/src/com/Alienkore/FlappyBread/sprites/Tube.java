package com.Alienkore.FlappyBread.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

/**
 * Created by David on 23/08/2015.
 */
public class Tube {
	// Cosnstantes
	public static final int TUBE_WIDTH = 52;
	private static final int FLUCTUATION = 130;
	private static final int TUBE_GAP = 100;
	private static final int LOWEST_OPENING = 120;

	// Texturas de los tubos y vector de movimiento
	private Texture topTube, bottomTube;
	private Vector2 posTopTube, posBotTube;

	private Rectangle boundsTop, boundsBot;

	// Variable aleatoria
	private Random rand;

	// Constructor recibe la posición en el eje x
	public Tube(float x) {
		// inicializamos las texturas
		topTube = new Texture("toptube.png");
		bottomTube = new Texture("bottomtube.png");
		// inicializamos la variable aleatoria
		rand = new Random();

		// Determinamos la posicion de los dos tubos en el espacio. El eje x se
		// lo pasamosy es fijo, pero el eje y es la suma de una valor aleatorio
		// FLUCTUACION + TUBE_GAP que es el espacio que queremos que haya entre
		// los dos tubos, mas
		// LOWEST_OPENING que es la posición mas baja que puede tomar
		posTopTube = new Vector2(x, rand.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING);
		// La posicion del tubo inferior es la posición fija horizontal. La
		// posición vertical es la posicion vertical de tubo superior menos su
		// altura
		posBotTube = new Vector2(x, posTopTube.y - TUBE_GAP - bottomTube.getHeight());
		// Cuando se crean los tubos se crea en una posición horizontal fija

		boundsTop = new Rectangle(posTopTube.x, posTopTube.y, topTube.getWidth(), topTube.getHeight());
		boundsBot = new Rectangle(posBotTube.x, posBotTube.y, bottomTube.getWidth(), bottomTube.getHeight());
	}

	// Metodo reposicionar.
	public void reposition(float x) {
		posTopTube.set(x, rand.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING);
		posBotTube.set(x, posTopTube.y - TUBE_GAP - bottomTube.getHeight());
		boundsTop.setPosition(posTopTube.x,posTopTube.y);
		boundsBot.setPosition(posBotTube.x,posBotTube.y);
	}
public boolean collide(Rectangle player){
	return player.overlaps(boundsTop)||player.overlaps(boundsBot);
};
	public Texture getBottomTube() {
		return bottomTube;
	}

	public Texture getTopTube() {
		return topTube;
	}

	public Vector2 getPosTopTube() {
		return posTopTube;
	}

	public Vector2 getPosBotTube() {
		return posBotTube;
	}

	public void dispose() {
		topTube.dispose();
		bottomTube.dispose();
	}
}