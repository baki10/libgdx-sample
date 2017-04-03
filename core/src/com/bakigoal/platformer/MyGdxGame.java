package com.bakigoal.platformer;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.bakigoal.platformer.samples.AnimatedSpriteScreen;
import com.bakigoal.platformer.samples.SpriteBatchScreen;
import com.bakigoal.platformer.samples.SpriteScreen;
import com.bakigoal.platformer.samples.TextureAtlasScreen;

public class MyGdxGame extends Game {

  @Override
  public void create() {
//    setScreen(new MenuScreen());
    setScreen(SampleScreens.SPRITES_ANIMATION);
  }

  private void setScreen(SampleScreens screen) {
    setScreen(screen.screen);
  }

  private enum SampleScreens {
    SPRITE_BATCH(new SpriteBatchScreen()),
    TEXTURE_ATLAS(new TextureAtlasScreen()),
    SPRITES(new SpriteScreen()),
    SPRITES_ANIMATION(new AnimatedSpriteScreen());

    private final Screen screen;

    SampleScreens(Screen screen) {
      this.screen = screen;
    }
  }
}
