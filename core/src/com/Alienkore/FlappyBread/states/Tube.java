package com.Alienkore.FlappyBread.states;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

/**
 * Created by David on 23/08/2015.
 */
public class Tube {

    private static final int FLUCTUACION =130 ;
    private static final int TUBEGAP = 120;
    private static final int LOWEST_OPENING =120 ;
    private Texture topTube,bottomTube;
    private Vector2 posTopTube, posBottomTube;
    private Random rand;
    
    
    public Tube(float x){
        rand= new Random();
        topTube= new Texture("toptube.png");
        bottomTube= new Texture("bottomtube.png");
        
        posTopTube= new Vector2(x, rand.nextInt(FLUCTUACION+TUBEGAP+LOWEST_OPENING));
        posBottomTube= new Vector2(x,posTopTube.y-TUBEGAP-bottomTube.getHeight());
    }

    public Texture getBottomTube() {
        return bottomTube;
    }

    public Texture getTopTube() {
        return topTube;
    }

    public Vector2 getPosTopTube() {
        return posTopTube;
    }

    public Vector2 getPosBottomTube() {
        return posBottomTube;
    }
}
