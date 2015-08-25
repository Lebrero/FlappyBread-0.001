package com.Alienkore.FlappyBread.states;

import com.Alienkore.FlappyBread.FlappyBread;
import com.Alienkore.FlappyBread.sprites.Background;
import com.Alienkore.FlappyBread.sprites.Bird;
import com.Alienkore.FlappyBread.sprites.Tube;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

/**
 * Created by David on 23/08/2015.
 */
public class PlayStates extends State {
	// Esta es la clase que se encarga de mostrar el juego. Extiende de State.
	// Esto quiere decir que hereda los metodos, el constructor, las variables
	// de State. Cuando se acceda a la pila de states (primer elemento por
	// ejemplo)se accede de la siguiente manera: se introduce un el state
	// deseado(este mismo), y despues se accede a sus metodos update y render
	// que han sido sobrescritos con la anotacion @overdrive

	private static final int TUBE_SPACING = 125;
	private static final int TUBE_COUNT = 4;

	// Creamos un pajaro
	private Bird bird;
	// Creamos un fondo
	private Background bg;
	// Creamos un array de Tubes
	private Array<Tube> tubes;

	public PlayStates(GameStateManager gsm) {
		// llamamos al constructor del padre
		super(gsm);
		// inicializamos
		this.bird = new Bird(50, 200);
		this.bg = new Background();
		this.tubes = new Array<Tube>();

		cam.setToOrtho(false, FlappyBread.WIDTH / 2, FlappyBread.HEIGHT / 2);

		// Llenamos el array de tubos, le enviamos x. Multiplicamos el espacio
		// entre los tubos mas lo que mide los tubos por un numero i. De esta
		// manera hemos llenado de tubos el array con su posicion x calculada

		for (int i = 1; i <= TUBE_COUNT; i++) {
			tubes.add(new Tube(i * (TUBE_SPACING + Tube.TUBE_WIDTH)));
		}
	}

	@Override
	protected void handleInput() {
		if (Gdx.input.justTouched()) {
			bird.jump();
		}
	}

	@Override
	public void update(float dt) {

		// Metodo actualizar
		handleInput();

		// mandamos delta a bird
		bird.update(dt);

		// La camara sigue al pajaro le añadimos 80 para que el pajaro se sitúe
		// a la derecha
		cam.position.x = bird.getPosition().x + 80;

		// Esto es lo que permite que se situe las pantallas
		for (int i = 0; i < tubes.size; i++) {
			Tube tube = tubes.get(i);

			// Calculamos la posicion idonea para hacer el reposicionamiento
			if (cam.position.x - (cam.viewportWidth / 2) > tube.getPosTopTube().x + tube.getTopTube().getWidth()) {
				tube.reposition(tube.getPosTopTube().x + ((Tube.TUBE_WIDTH + TUBE_SPACING) * TUBE_COUNT));
			}
		}
		cam.update();
	}

	@Override
	public void render(SpriteBatch sb) {

		sb.setProjectionMatrix(cam.combined);

		sb.begin();
		// Estamos dibujando el fondo el la misma posicion que la camara (el
		// efecto es que la sigue)
		sb.draw(bg.getBg(), cam.position.x - (cam.viewportWidth / 2), 0);
		
		//Hemos utilizado este metodo para poder controlar la rotacion
		sb.draw(bird.getBirdRegion(), bird.getPosition().x, bird.getPosition().y, bird.getBird().getWidth() / 2,
				bird.getBird().getHeight() / 2, bird.getBird().getWidth(), bird.getBird().getHeight(), 1, 1,
				bird.getRotation().x);

		for (Tube tube : tubes) {
			sb.draw(tube.getTopTube(), tube.getPosTopTube().x, tube.getPosTopTube().y);
			sb.draw(tube.getBottomTube(), tube.getPosBotTube().x, tube.getPosBotTube().y);
		}

		sb.end();
	}

	@Override
	public void dispose() {
		bg.dispose();
		bird.dispose();

		for (Tube tube : tubes)
			tube.dispose();
	}
}
