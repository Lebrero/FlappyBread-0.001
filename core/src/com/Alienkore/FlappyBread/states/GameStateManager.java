package com.Alienkore.FlappyBread.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

/**
 * Created by David on 23/08/2015.
 */
public class GameStateManager {
	// GameStateManager es una pila de States, tiene metodos para meter states,
	// recuperar states y demás. Tiene un metodo render y update que accede al
	// State superior y lo renderiza y actualiza. Estos dos metodos son a los
	// que accede FlappyBread.java para mostrar toda la informacion
	private Stack<State> states;

	public GameStateManager() {
		states = new Stack<State>();
	}

	// Metodo que maneja la pila de states. Introduce elemento en la pila
	public void push(State state) {
		states.push(state);
	}

	// Metodo que maneja la pila de states. Borra el ultimo elemento de la pila
	public void pop(State state) {
		states.pop();
	}

	// Metodo que maneja la pila de states. Borra el ultimo elemento de la pila
	// e introduce uno
	public void set(State state) {
		states.pop();
		states.push(state);
	}

	// Metodo update del primer state
	public void update(float dt) {
		states.peek().update(dt);
	}

	// Metodo render del primer state
	public void render(SpriteBatch sb) {
		states.peek().render(sb);
	}

}
