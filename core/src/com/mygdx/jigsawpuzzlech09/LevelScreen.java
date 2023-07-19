package com.mygdx.jigsawpuzzlech09;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class LevelScreen extends BaseScreen {
    private Label messageLabel;

    @Override
    public void initialize() {
        fitViewport = new FitViewport(800, 600);
        camera.setToOrtho(false, 800, 600);

        BaseActor background = new BaseActor(0, 0, mainStage);
        background.loadTexture("background.jpg");

        int numberRows = 3;
        int numberCols = 3;

        Texture texture = new Texture(Gdx.files.internal("sun.jpg"), true);
        int imageWidth = texture.getWidth();
        int imageHeight = texture.getHeight();
        int pieceWidth = imageWidth / numberCols;
        int pieceHeight = imageHeight / numberRows;

        TextureRegion[][] temp = TextureRegion.split(texture, pieceWidth, pieceHeight);

        for (int r = 0; r < numberRows; r++) {
            for (int c = 0; c < numberCols; c++) {
                // create puzzle piece at random location on left half of screen
                int pieceX = MathUtils.random(0, 400 - pieceWidth);
                int pieceY = MathUtils.random(0, 600 - pieceHeight);
                PuzzlePiece pp = new PuzzlePiece(pieceX, pieceY, mainStage);

                Animation<TextureRegion> anim = new Animation<TextureRegion>(1, temp[r][c]);
                pp.setAnimation(anim);
                pp.setRow(r);
                pp.setCol(c);

                int marginX = (400 - imageWidth) / 2;
                int marginY = (600 - imageHeight) / 2;

                int areaX = (400 + marginX) + pieceWidth * c;
                int areaY = (600 - marginY - pieceHeight) - pieceHeight * r;

                PuzzleArea pa = new PuzzleArea(areaX, areaY, mainStage);
                pa.setSize(pieceWidth, pieceHeight);
                pa.setBoundaryRectangle();
                pa.setRow(r);
                pa.setCol(c);
            }
        }

        messageLabel = new Label("...", BaseGame.labelStyle);
        messageLabel.setColor(Color.CYAN);
        uiTable.add(messageLabel).expandX().expandY().bottom().pad(50);
        messageLabel.setVisible(false);
    }

    @Override
    public void update(float dt) {
//        Vector3 touchPos = new Vector3();
//        touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
//        camera.unproject(touchPos);

        boolean solved = true;
        for (BaseActor actor : BaseActor.getList(mainStage, "com.mygdx.jigsawpuzzlech09.PuzzlePiece")) {
            PuzzlePiece pp = (PuzzlePiece) actor;

            if (!pp.isCorrectlyPlaced())
                solved = false;
        }

        if (solved) {
            messageLabel.setText("You win!");
            messageLabel.setVisible(true);
        } else {
            messageLabel.setText("...");
            messageLabel.setVisible(false);
        }
    }
}
