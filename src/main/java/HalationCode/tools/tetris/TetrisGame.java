package HalationCode.tools.tetris;

import com.badlogic.gdx.Game;

public class TetrisGame extends Game {
	@Override
	public void create() {		
		Assets.load();
		System.out.println("???");
		this.setScreen(new MenuScreen(this));
	}

	@Override
	public void dispose() {
		
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}
