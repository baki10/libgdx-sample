package com.bakigoal.platformer.samples;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class SpriteBatchScreen extends AbstractScreen {

  private static final Color BG_COLOR = new Color(0.39f, 0.58f, 0.92f, 1.0f);
  private static final float WORLD_TO_SCREEN = 1.0f / 100.0f;
  private static final float SCENE_WIDTH = 12.80f;
  private static final float SCENE_HEIGHT = 7.20f;

  private Color oldColor;

  /**
   * The	 camera 	member	will	help	us	define	what	portion	of	the	world	will	be	seen	on	the screen
   */
  private OrthographicCamera camera;
  /**
   * For our resolution	handling	mechanism,	we need	a	 Viewport 	object
   */
  private Viewport viewport;
  /**
   * Render	calls	will	be	issued	through	 SpriteBatch ,
   * which	provides a	simple	mechanism	to	draw	quads	associated	to	textures	with	extreme	efficiency
   */
  private SpriteBatch batch;
  /**
   * we	will	use	 cavemanTexture 	to	hold	the	image	data	of	our little	caveman
   */
  private Texture cavemanTexture;

  @Override
  public void show() {
    camera = new OrthographicCamera();
    viewport = new FitViewport(SCENE_WIDTH, SCENE_HEIGHT, camera);
    batch = new SpriteBatch();
    oldColor = new Color();

    cavemanTexture = new Texture(Gdx.files.internal("data/caveman.png"));
    cavemanTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
  }

  @Override
  public void dispose() {
    // The Texture and SpriteBatch 	objects	allocate	resources	that	need	to	be	freed	up
    batch.dispose();
    cavemanTexture.dispose();
  }

  @Override
  public void resize(int width, int height) {
    viewport.update(width, height, false);
  }

  @Override
  public void render(float delta) {
    // Every frame, we clear the screen with a background color and render our game scene from scratch.
    Gdx.gl.glClearColor(BG_COLOR.r, BG_COLOR.g, BG_COLOR.b, BG_COLOR.a);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    batch.setProjectionMatrix(camera.combined);
    // The SpriteBatch class optimizes our render requests to achieve high performance even on mobile devices
    // In order for it to do its job, we are required to call its	 begin() method before any draw calls
    // and its end() method once we are done
    batch.begin();
    drawScene(batch);
    batch.end();
  }

  private void drawScene(SpriteBatch batch) {
    int width = cavemanTexture.getWidth();
    int height = cavemanTexture.getHeight();
    // the center of the texture
    float originX = width * 0.5f;
    float originY = height * 0.5f;

    // Render caveman centered on the screen
    batch.draw(cavemanTexture,            // Texture
        //the world space coordinates where we want to draw
        -originX, -originY,          // x, y
        // coordinates in pixels of our texture that we consider to be the origin starting from the bottom-left corner.
        // In our case, we want the origin to be the center of the texture
        originX, originY,          // originX, originY
        width / 2, height,            // width, height
        WORLD_TO_SCREEN, WORLD_TO_SCREEN,  // scaleX, scaleY
        0.0f,                // rotation
        // srcX, srcY, srcWidth, and srcHeight allow us to select a limited portion of the texture to be drawn
        0, 0,                // srcX, srcY
        width / 2, height,            // srcWidth, srcHeight
        true, false);            // flipX, flipY

    // Render caveman on the top left corner at 2x size
    batch.draw(cavemanTexture,
        -4.0f - originX, 1.5f - originY,
        originX, originY,
        width, height,
        WORLD_TO_SCREEN * 2.0f, WORLD_TO_SCREEN * 2.0f,
        0.0f,
        0, 0,
        width, height,
        false, false);

    // Render caveman on the bottom left corner at 0.5x size
    batch.draw(cavemanTexture,
        -4.0f - originX, -1.5f - originY,
        originX, originY,
        width, height,
        WORLD_TO_SCREEN * 0.5f, WORLD_TO_SCREEN * 0.5f,
        0.0f,
        0, 0,
        width, height,
        false, false);

    // Render caveman on top right corner at 2x size and rotated 45 degrees
    batch.draw(cavemanTexture,
        4.0f - originX, 1.5f - originY,
        originX, originY,
        width, height,
        WORLD_TO_SCREEN * 2.0f, WORLD_TO_SCREEN * 2.0f,
        45.0f,
        0, 0,
        width, height,
        false, false);

    // Render caveman on bottom right corner at 1.5x size and flipped around X and Y
    batch.draw(cavemanTexture,
        4.0f - originX, -1.5f - originY,
        originX, originY,
        width, height,
        WORLD_TO_SCREEN * 1.5f, WORLD_TO_SCREEN * 1.5f,
        0.0f,
        0, 0,
        cavemanTexture.getWidth(), height,
        true, true);

    // Save batch color
    oldColor.set(batch.getColor());

    // Render blue caveman
    batch.setColor(Color.CYAN);
    batch.draw(cavemanTexture,
        -2.0f - originX, -originY,
        originX, originY,
        width, height,
        WORLD_TO_SCREEN, WORLD_TO_SCREEN,
        0.0f,
        0, 0,
        width, height,
        false, false);

    // Render red caveman
    batch.setColor(Color.RED);
    batch.draw(cavemanTexture,
        -originX, -originY + 2.0f,
        originX, originY,
        width, height,
        WORLD_TO_SCREEN, WORLD_TO_SCREEN,
        0.0f,
        0, 0,
        width, height,
        false, false);

    // Render green caveman
    batch.setColor(Color.GREEN);
    batch.draw(cavemanTexture,
        2.0f - originX, -originY,
        originX, originY,
        width, height,
        WORLD_TO_SCREEN, WORLD_TO_SCREEN,
        0.0f,
        0, 0,
        width, height,
        false, false);

    // Render yellow caveman
    batch.setColor(Color.YELLOW);
    batch.draw(cavemanTexture,
        -originX, -originY - 2.0f,
        originX, originY,
        width, height,
        WORLD_TO_SCREEN, WORLD_TO_SCREEN,
        0.0f,
        0, 0,
        width, height,
        false, false);

    batch.setColor(oldColor);
  }
}
