package com.Alienkore.FlappyBread;

import com.Alienkore.FlappyBread.states.GameStateManager;
import com.Alienkore.FlappyBread.states.MenuState;
import com.Alienkore.FlappyBread.states.PlayStates;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sun.media.jfxmedia.events.PlayerStateEvent;

public class FlappyBread extends ApplicationAdapter {

    public static final int WIDTH = 480;
    public static final int HEIGHT = 800;

    public static final String TITLE = "Flappy Bread";


    private GameStateManager gsm;
    
    private SpriteBatch batch;

    Texture img;

    @Override
    public void create() {
        batch = new SpriteBatch();
        gsm = new GameStateManager();
        Gdx.gl.glClearColor(0, 0, 0, 1);

        gsm.push(new PlayStates(gsm));

    }

    @Override
    public void render() {

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        gsm.update(Gdx.graphics.getDeltaTime());
        gsm.render((batch));
    }
}
