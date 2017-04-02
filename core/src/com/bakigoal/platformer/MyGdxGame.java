package com.bakigoal.platformer;

import com.badlogic.gdx.Game;
import com.bakigoal.platformer.samples.SpriteBatchScreen;

public class MyGdxGame extends Game {

  @Override
  public void create() {
    setScreen(new SpriteBatchScreen());
  }
}
