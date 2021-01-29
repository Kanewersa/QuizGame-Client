package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import java.lang.reflect.InvocationTargetException;

public class QuizGame extends ApplicationAdapter {
	SpriteBatch batch;
	SceneManager sceneManager;

	@Override
	public void create () {
		NetworkManager.instantiate();
		batch = new SpriteBatch();
		sceneManager = new SceneManager();
		sceneManager.loadScene(MenuScene.class);
	}

	@Override
	public void render () {
		float deltaTime = Gdx.graphics.getDeltaTime();

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		sceneManager.update(deltaTime);
		sceneManager.draw(batch, deltaTime);
	}

	@Override
	public void dispose () {
		batch.dispose();
	}
}
