package com.Alienkore.FlappyBread.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by David on 23/08/2015.
 */
public class Bird {
	private static final int GRAVITY = -15;
	private static final int MOVEMENT = 120;

	private Rectangle bounds;

	private int ROTATION = 30;
	private float ROTACION_VEL = 100;
	
	private AnimationBird birdAnimation;

	// Texturas de pajaro
	private Texture bird;
	private TextureRegion birdRegion;
	// Variables que controlan el pajaro
	private Vector3 position;
	private Vector3 velocity;
	private Vector3 rotation;

	public Bird(int x, int y) {
		// Posición
		position = new Vector3(x, y, 0);
		// Velocidad
		velocity = new Vector3(x, y, 0);
		// Rotación
		rotation = new Vector3(0, 0, 0);

		bird = new Texture("birdanimation.png");
		birdRegion = new TextureRegion(bird);
		
		birdAnimation= new AnimationBird(birdRegion, 3, 0.5f);

		bounds = new Rectangle(x, y, birdRegion.getRegionWidth()/3, birdRegion.getRegionHeight());
	}

	public void update(float dt) {
		
		birdAnimation.update(dt);
		// aplicamos la velocidad
		velocity.add(0, GRAVITY, 0);

		rotation.scl(dt);
		velocity.scl(dt);

		position.add(MOVEMENT * dt, velocity.y, 0);

		rotation.scl(1 / dt);
		velocity.scl(1 / dt);

		// si la rotacion es menor o igual a -90 se queda en -90 y es mayor o
		// igual a 70 se queda en 70
		if (rotation.x <= -90) {
			rotation.x = -90;
		} else if (rotation.x >= 70) {
			rotation.x = 70;
		}
		// Seleccionamos rotación
		rotation.set(ROTATION, 0, 0);

		// restamos ROTACION_VEL en cada llamada
		ROTATION -= ROTACION_VEL * dt;

		bounds.setPosition(position.x, position.y);
	}

	public void jump() {
		// Cuando saltamos aplicamos una velocidad positiva en y de 350 y una
		// rotacion de 50
		velocity.y = 350;
		ROTATION = 50;

	}
	
	public Rectangle getBounds(){
		return bounds;
	}

	public Texture getBird() {
		return bird;
	}

	public Vector3 getPosition() {
		return position;
	}

	public Vector3 getRotation() {
		return rotation;
	}

	public TextureRegion getBirdRegion() {
		return birdRegion;
	}

	public void dispose() {
		bird.dispose();
	}

	public TextureRegion getBirdAnimation() {
		return birdAnimation.getFrame();
	}
	
	
}
