package com.bakigoal.platformer;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.bakigoal.platformer.samples.SpriteBatchScreen;
import com.bakigoal.platformer.samples.TextureAtlasScreen;
import com.bakigoal.platformer.screens.MenuScreen;

public class MyGdxGame extends Game {

  @Override
  public void create() {
//    setScreen(new MenuScreen());
    setScreen(SampleScreens.TEXTURE_ATLAS);
  }

  private void setScreen(SampleScreens screen) {
    setScreen(screen.screen);
  }

  private enum SampleScreens {
    SPRITE_BATCH(new SpriteBatchScreen()),
    TEXTURE_ATLAS(new TextureAtlasScreen());

    private final Screen screen;

    SampleScreens(Screen screen) {
      this.screen = screen;
    }
  }
}
