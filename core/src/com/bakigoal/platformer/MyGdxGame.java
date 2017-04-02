package com.bakigoal.platformer;

import com.badlogic.gdx.Game;
import com.bakigoal.platformer.screens.MenuScreen;

public class MyGdxGame extends Game {

  @Override
  public void create() {
    setScreen(new MenuScreen());
  }
}
