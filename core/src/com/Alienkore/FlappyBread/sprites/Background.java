package com.Alienkore.FlappyBread.sprites;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by David on 23/08/2015.
 */
public class Background {


    private Texture background;

    public Background() {
        this.background = new Texture("bg.png");
    }

    public Texture getBackground() {
        return background;
    }


    public void dispose(){
        background.dispose();
    }

}
