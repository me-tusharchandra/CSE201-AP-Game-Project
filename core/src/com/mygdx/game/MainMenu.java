package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.TankStars;
import com.mygdx.game.TankStars.Image;

public class MainMenu implements Screen {
    final private TankStars game;
    final private OrthographicCamera cam;
    final private Viewport gamePort;
    final private Image background, vsFriendButton, vsFriendButtonClicked, buttonsBackground,
            loadButton, loadButtonClicked, exitButtonClicked, exitButton, vsAIButton, vsAIButtonClicked,
            tank, logo, settingsButton = null;
    final private int buttonWidth = 230, buttonHeight = 80;
    int logoW = 218, logoH = 132;
    int logoX = (TankStars.WIDTH - logoW)/2, logoY = 480;
    double time;

    public MainMenu(TankStars game) {
        this.game = game;
        // Set up a camera view and adjust it in first quadrant
        cam = new OrthographicCamera(TankStars.WIDTH, TankStars.HEIGHT);
        cam.setToOrtho(false, TankStars.WIDTH, TankStars.HEIGHT);

        // assigning values
        gamePort = new FitViewport(TankStars.WIDTH, TankStars.HEIGHT, cam);
        background = new Image(new Texture(Gdx.files.internal("GameScreen/blueBackground.png")), 0, 0, TankStars.WIDTH, TankStars.HEIGHT);
        buttonsBackground = new Image(new Texture(Gdx.files.internal("MainMenu/buttonsBackground.png")), 704, 0, 576, 640);
        tank = new Image(new Texture(Gdx.files.internal("MainMenu/tank.png")), 150, 0, 400, 400);
        logo = new TankStars.Image(new Texture(Gdx.files.internal("MainMenu/tankStarsLogo.png")), 250, 450 , logoW, logoH);

        int buttonsX = (buttonsBackground.x + TankStars.WIDTH - buttonWidth)/2;
        int buttonsY = 400;

        vsFriendButton = new Image(new Texture(Gdx.files.internal("MainMenu/vsFriendButton1.png")), buttonsX, buttonsY, buttonWidth, buttonHeight);
        vsFriendButtonClicked = new Image(new Texture(Gdx.files.internal("MainMenu/vsFriendButton2.png")), buttonsX, buttonsY, buttonWidth, buttonHeight);
        vsAIButton = new Image(new Texture(Gdx.files.internal("MainMenu/vsAIButton1.png")), buttonsX, buttonsY - 100, buttonWidth, buttonHeight);
        vsAIButtonClicked = new Image(new Texture(Gdx.files.internal("MainMenu/vsAIButton2.png")), buttonsX, buttonsY - 100, buttonWidth, buttonHeight);
        loadButton = new Image(new Texture(Gdx.files.internal("MainMenu/loadGameButton1.png")), buttonsX, buttonsY - 200, buttonWidth, buttonHeight);
        loadButtonClicked = new Image(new Texture(Gdx.files.internal("MainMenu/loadGameButton2.png")), buttonsX, buttonsY - 200, buttonWidth, buttonHeight);
        exitButton = new Image(new Texture(Gdx.files.internal("MainMenu/exitButton1.png")), buttonsX, buttonsY - 300, buttonWidth, buttonHeight);
        exitButtonClicked = new Image(new Texture(Gdx.files.internal("MainMenu/exitButton2.png")), buttonsX, buttonsY - 300, buttonWidth, buttonHeight);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        time += delta;
        ScreenUtils.clear(0,0,0,0);

        cam.update();
        game.batch.setProjectionMatrix(cam.combined);

        game.batch.begin();
        game.drawImage(background);
        game.drawImage(buttonsBackground);
        game.drawImage(vsFriendButton);
        game.drawImage(vsAIButton);
        game.drawImage(loadButton);
        game.drawImage(exitButton);
        game.drawImage(tank);
        game.drawImage(logo);

        if (tank.isHovered()) {
            if (Gdx.input.isTouched()) {
                ;
            }
        }

        if (vsFriendButton.isHovered()) {
            if (Gdx.input.isTouched()) {
                game.drawImage(vsFriendButtonClicked);
                game.drawImage(vsFriendButton);
                game.setScreen(new FriendGameScreen(game));
            }
        }

        if (vsAIButton.isHovered()) {
            if (Gdx.input.isTouched()) {
                game.drawImage(vsAIButtonClicked);
                game.drawImage(vsAIButton);
                game.setScreen(new AIGameScreen(game));
            }
        }

        if (loadButton.isHovered()) {
            if (Gdx.input.isTouched()) {
                game.drawImage(loadButton);
                game.drawImage(loadButtonClicked);
                // Load Game
            }
        }

        if (exitButton.isHovered()) {
            if (Gdx.input.isTouched()) {
                game.drawImage(vsAIButtonClicked);
                game.drawImage(vsAIButton);
                Gdx.app.exit();
            }
        }

        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}