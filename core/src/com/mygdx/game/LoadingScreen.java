package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class LoadingScreen implements Screen {

    private final TankStars game;
    OrthographicCamera cam;
    Viewport gamePort;

    private final Texture loading1, loading2, loading3, loading4, loading5, loading6, loading7, loading8, loading9, loading10, loading11, loading12;
    private double time;


    public LoadingScreen(TankStars game) {
        this.game = game;

        cam = new OrthographicCamera(TankStars.WIDTH, TankStars.HEIGHT);
        cam.setToOrtho(false, TankStars.WIDTH, TankStars.HEIGHT);

        gamePort = new FitViewport(1280, 640, cam);

        loading1 = new Texture("LoadingScreen/LoadingScreen1.png");
        loading2 = new Texture("LoadingScreen/LoadingScreen2.png");
        loading3 = new Texture("LoadingScreen/LoadingScreen3.png");
        loading4 = new Texture("LoadingScreen/LoadingScreen4.png");
        loading5 = new Texture("LoadingScreen/LoadingScreen5.png");
        loading6 = new Texture("LoadingScreen/LoadingScreen6.png");
        loading7 = new Texture("LoadingScreen/LoadingScreen7.png");
        loading8 = new Texture("LoadingScreen/LoadingScreen8.png");
        loading9 = new Texture("LoadingScreen/LoadingScreen9.png");
        loading10 = new Texture("LoadingScreen/LoadingScreen10.png");
        loading11 = new Texture("LoadingScreen/LoadingScreen11.png");
        loading12 = new Texture("LoadingScreen/LoadingScreen12.png");
    }

    @Override
    public void show() {

    }

    @Override
    public void render (float delta) {
        time += delta;
        ScreenUtils.clear(1, 0, 0, 1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();

        game.batch.draw(loading1, 0, 0, 640, 480 );
        if ((int)time == 1)
            game.batch.draw(loading2, 0, 0, 640, 480);
        else if (time > 1.5 && time < 2)
            game.batch.draw(loading3, 0, 0, 640, 480);
        else if (time > 2 && time < 2.5)
            game.batch.draw(loading4, 0, 0, 640, 480);
        else if (time > 2.5 && time < 3)
            game.batch.draw(loading5, 0, 0, 640, 480);
        else if (time > 3 && time < 3.5)
            game.batch.draw(loading6, 0, 0, 640, 480);
        else if (time > 3.5 && time < 4)
            game.batch.draw(loading7, 0, 0, 640, 480);
        else if (time > 4 && time < 4.5)
            game.batch.draw(loading8, 0, 0, 640, 480);
        else if (time > 4.5 && time < 5)
            game.batch.draw(loading9, 0, 0, 640, 480);
        else if (time > 5 && time < 5.5)
            game.batch.draw(loading10, 0, 0, 640, 480);
        else if (time > 5.5 && time < 6)
            game.batch.draw(loading11, 0, 0, 640, 480);
        else if (time == 6)
            game.batch.draw(loading12, 0, 0, 640, 480);
        else if (time > 6) {
            game.setScreen(new MainMenu(game));
        }

        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {

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
