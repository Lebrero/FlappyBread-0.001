package com.Alienkore.FlappyBread.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by David on 23/08/2015.
 */
public class Bird {
    private static final int GRAVITY = -15;
    private int ROTATION = 0;
    private float ROTACION_VEL = 150;

    private Texture bird;

    private TextureRegion birdRegion;
    private Vector3 position;
    private Vector3 velocity;

    public Vector3 getRotation() {
        return rotation;
    }

    private Vector3 rotation;

    public Bird(int x, int y) {
        position = new Vector3(x, y, 0);
        velocity = new Vector3(x, y, 0);
        rotation = new Vector3(0, 0, 0);

        bird = new Texture("bird.png");
        birdRegion = new TextureRegion(bird);
    }

    public void update(float dt) {
        velocity.add(0, GRAVITY, 0);
        rotation.scl(dt);
        velocity.scl(dt);

        position.add(0, velocity.y, 0);

        rotation.scl(1/dt);
        velocity.scl(1 / dt);

        rotation.set(ROTATION,0,0);

        if (rotation.x <= -90) {
            rotation.x = -90;
        } else if (rotation.x >= 70) {
            rotation.x = 70;
        }
        ROTATION-=ROTACION_VEL*dt;
    }

    public Texture getBird() {
        return bird;
    }

    public Vector3 getPosition() {
        return position;
    }

    public void jump() {

        velocity.y = 250;
        ROTATION=50;



    }

    public TextureRegion getBirdRegion() {
        return birdRegion;
    }
}
