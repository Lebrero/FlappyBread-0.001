package com.Alienkore.FlappyBread.states;

import com.Alienkore.FlappyBread.FlappyBread;
import com.Alienkore.FlappyBread.sprites.Background;
import com.Alienkore.FlappyBread.sprites.Bird;
import com.Alienkore.FlappyBread.sprites.Ground;
import com.Alienkore.FlappyBread.sprites.Tube;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
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
	private static final int GROUND_LIMIT = -50;

	private Vector2 groundPos1, groundPos2;

	// Creamos un pajaro
	private Bird bird;
	// Creamos un fondo
	private Background bg;
	// Creamos un suelo
	private Ground ground;
	// Creamos un array de Tubes
	private Array<Tube> tubes;

	public PlayStates(GameStateManager gsm) {
		// llamamos al constructor del padre
		super(gsm);
		// inicializamos
		this.bird = new Bird(50, 200);
		this.bg = new Background();
		this.ground = new Ground();
		this.tubes = new Array<Tube>();

		groundPos1 = new Vector2(cam.position.x - cam.viewportWidth / 2, GROUND_LIMIT);
		groundPos2 = new Vector2((cam.position.x - cam.viewportWidth / 2) + ground.getGround().getWidth(),
				GROUND_LIMIT);

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
		updateGround();
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

			if (tube.collide(bird.getBounds())) {
				gsm.set(new PlayStates(gsm));
			}

			if (bird.getPosition().y <= ground.getGround().getHeight() + GROUND_LIMIT) {
				gsm.set(new PlayStates(gsm));
			}
		}
		cam.update();

	}

	public void updateGround() {
		if (cam.position.x - (cam.viewportWidth / 2) > groundPos1.x + ground.getGround().getWidth())

			groundPos1.add(ground.getGround().getWidth() * 2, 0);

		if (cam.position.x - (cam.viewportWidth / 2) > groundPos2.x + ground.getGround().getWidth())

			groundPos2.add(ground.getGround().getWidth() * 2, 0);
	}

	@Override
	public void render(SpriteBatch sb) {

		sb.setProjectionMatrix(cam.combined);

		sb.begin();
		// Estamos dibujando el fondo el la misma posicion que la camara (el
		// efecto es que la sigue)
		sb.draw(bg.getBg(), cam.position.x - (cam.viewportWidth / 2), 0);

		// Hemos utilizado este metodo para poder controlar la rotacion
		sb.draw(bird.getBirdAnimation(), bird.getPosition().x, bird.getPosition().y, bird.getBirdAnimation().getRegionWidth() / 2,
				bird.getBirdAnimation().getRegionHeight() / 2, bird.getBirdAnimation().getRegionWidth(), bird.getBirdAnimation().getRegionHeight(), 1, 1,
				bird.getRotation().x);

		for (Tube tube : tubes) {
			sb.draw(tube.getTopTube(), tube.getPosTopTube().x, tube.getPosTopTube().y);
			sb.draw(tube.getBottomTube(), tube.getPosBotTube().x, tube.getPosBotTube().y);
		}

		sb.draw(ground.getGround(), groundPos1.x, groundPos1.y, ground.getGround().getWidth(),
				ground.getGround().getHeight());
		sb.draw(ground.getGround(), groundPos2.x, groundPos2.y, ground.getGround().getWidth(),
				ground.getGround().getHeight());
		sb.end();
	}

	@Override
	public void dispose() {
		bg.dispose();
		bird.dispose();
		ground.dispose();
		for (Tube tube : tubes)
			tube.dispose();
	}
}
