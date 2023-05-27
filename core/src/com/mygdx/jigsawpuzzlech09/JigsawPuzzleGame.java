package com.mygdx.jigsawpuzzlech09;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class JigsawPuzzleGame extends BaseGame {
	public void create() {
		super.create();
		setActiveScreen(new LevelScreen());
	}
}