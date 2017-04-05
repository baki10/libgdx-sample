

package com.bakigoal.platformer.samples;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class PooledEffectsScreen extends AbstractScreen {
	private static final float SCENE_WIDTH = 12.80f;
	private static final float SCENE_HEIGHT = 7.20f;
	
	private OrthographicCamera camera;
	private Viewport viewport;
	private SpriteBatch batch;
	private ParticleEffectPool pool;
	private Array<ParticleEffectPool.PooledEffect> activeEffects;
	private Vector3 touchPos;
	
	@Override
	public void show() {
		camera = new OrthographicCamera();
		viewport = new FitViewport(SCENE_WIDTH, SCENE_HEIGHT, camera);
		batch = new SpriteBatch();
		touchPos = new Vector3();
		
		ParticleEffect explosionEffect = new ParticleEffect();
		explosionEffect.load(Gdx.files.internal("data/explosion.particle"), Gdx.files.internal("data"));
		pool = new ParticleEffectPool(explosionEffect, 10, 100);
		activeEffects = new Array<ParticleEffectPool.PooledEffect>();
		
		Gdx.input.setInputProcessor(this);
	}

	@Override
	public void dispose() {
		batch.dispose();
	}

	@Override
	public void render(float deltaTime) {
		Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		
		for (int i = 0; i < activeEffects.size; ) {
			ParticleEffectPool.PooledEffect effect = activeEffects.get(i);
			
			if (effect.isComplete()) {
				pool.free(effect);
				activeEffects.removeIndex(i);
			}
			else {
				effect.draw(batch, deltaTime);
				++i;
			}
		}
		
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
		viewport.update(width, height);
	}
	
	@Override
	public boolean touchDown (int screenX, int screenY, int pointer, int button) {
		ParticleEffectPool.PooledEffect effect = pool.obtain();
		
		if (effect != null) {
			touchPos.set(screenX, screenY, 0.0f);
			camera.unproject(touchPos);
			
			activeEffects.add(effect);
			effect.setPosition(touchPos.x, touchPos.y);
		}
		
		return true;
	}
}
