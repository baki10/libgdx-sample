package com.bakigoal.platformer;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.bakigoal.platformer.samples.AnimatedSpriteScreen;
import com.bakigoal.platformer.samples.FrameBufferScreen;
import com.bakigoal.platformer.samples.OrthographicCameraScreen;
import com.bakigoal.platformer.samples.ParticleEffectsScreen;
import com.bakigoal.platformer.samples.PooledEffectsScreen;
import com.bakigoal.platformer.samples.ShapeRendererScreen;
import com.bakigoal.platformer.samples.SpriteBatchScreen;
import com.bakigoal.platformer.samples.SpriteScreen;
import com.bakigoal.platformer.samples.TextureAtlasScreen;
import com.bakigoal.platformer.samples.ViewportScreen;

public class MyGdxGame extends Game {

  @Override
  public void create() {
//    setScreen(new MenuScreen());
    setScreen(SampleScreens.FRAME_BUFFER);
  }

  private void setScreen(SampleScreens screen) {
    setScreen(screen.screen);
  }

  private enum SampleScreens {
    SPRITE_BATCH(new SpriteBatchScreen()),
    TEXTURE_ATLAS(new TextureAtlasScreen()),
    SPRITES(new SpriteScreen()),
    SPRITES_ANIMATION(new AnimatedSpriteScreen()),
    ORTHOGRAPHIC_CAMERA(new OrthographicCameraScreen()),
    SHAPE_RENDERER(new ShapeRendererScreen()),
    VIEWPORT(new ViewportScreen()),
    PARTICLE_EFFECT(new ParticleEffectsScreen()),
    POOLED_EFFECT(new PooledEffectsScreen()),
    FRAME_BUFFER(new FrameBufferScreen());

    private final Screen screen;

    SampleScreens(Screen screen) {
      this.screen = screen;
    }
  }
}
