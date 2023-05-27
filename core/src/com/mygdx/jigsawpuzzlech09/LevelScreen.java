package com.mygdx.jigsawpuzzlech09;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector3;

public class LevelScreen extends BaseScreen {
    @Override
    public void initialize() {
        camera.setToOrtho(false, 800, 600);
    }

    @Override
    public void update(float dt) {
        Vector3 touchPos = new Vector3();
        touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
        camera.unproject(touchPos);
    }
}
