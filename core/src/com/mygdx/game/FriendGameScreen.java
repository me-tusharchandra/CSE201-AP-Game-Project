package com.mygdx.game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.TankStars.Image;

import java.awt.*;

public class FriendGameScreen implements Screen {
    private final TankStars game;
    private OrthographicCamera cam;
    private Image background;
    public static Tank tank1;
    EnemyTank tank2;
    private TiledMap terrain ;
    private OrthogonalTiledMapRenderer renderer;

    public FriendGameScreen(TankStars game) {
        this.game = game;
    }

    @Override
    public void show() {
        background = new TankStars.Image(new Texture(Gdx.files.internal("GameScreen/blueBackground.png")), 0, 0, TankStars.WIDTH, TankStars.HEIGHT);
        terrain = new TmxMapLoader().load("GameScreen/Map.tmx");
        renderer = new OrthogonalTiledMapRenderer(terrain);
        cam = new OrthographicCamera(TankStars.WIDTH, TankStars.HEIGHT);
        cam.setToOrtho(false, TankStars.WIDTH, TankStars.HEIGHT);

        tank1 = new Tank(new Sprite(new Texture(Gdx.files.internal("Tanks/Abrams1.png")), 0, 0, 130, 73), (TiledMapTileLayer) terrain.getLayers().get(0));
        tank1.init();

        tank2 = new EnemyTank(new Sprite(new Texture(Gdx.files.internal("Tanks/Coalition.png")), 0, 0, 167, 73), (TiledMapTileLayer) terrain.getLayers().get(0));
        tank2.init();
    }

    @Override
    public void render(float deltaTime) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        cam.update();
        game.batch.setProjectionMatrix(cam.combined);

        game.batch.begin();
        game.drawImage(background);
        game.batch.end();

        renderer.setView(cam);
        renderer.render();

        game.batch.begin();
        tank1.draw(game.batch);
        tank2.draw(game.batch);
        game.batch.end();

        if (tank1.shoot) {
            tank1.playable = false;
            tank1.shoot = false;
            tank2.playable = true;
        } else if (tank2.shoot) {
            tank1.playable = true;
            tank2.playable = false;
            tank2.shoot = false;
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void dispose() {

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
}