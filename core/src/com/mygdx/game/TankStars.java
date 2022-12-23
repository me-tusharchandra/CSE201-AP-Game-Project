package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class TankStars extends Game {
	public static final int HEIGHT = 640, WIDTH = 1280; // Set height and width of window
	public SpriteBatch batch;

	@Override
	public void create() {
		batch = new SpriteBatch();
		setScreen(new LoadingScreen(this));
	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void dispose() {	}

	public void drawImage(Image image) {
		batch.draw(image.texture, image.x, image.y, image.w, image.h);
	}

	public static class Image {
		public Texture texture;
		public int x, y, w, h;

		public Image(Texture texture, int x, int y, int w, int h) {
			this.texture = texture;
			this.x = x;
			this.y = y;
			this.w = w;
			this.h = h;
		}

		public boolean isHovered() {
			if (Gdx.input.getX() > x && Gdx.input.getX() < x + w) {
				if (Gdx.input.getY() > TankStars.HEIGHT - (h + y) && Gdx.input.getY() < TankStars.HEIGHT - y) {
					return true;
				}
			}
			return false;
		}
	}
}

class ImageButton extends Button {
	ImageButton (String imagePath) {
		super(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal(imagePath)))));
	}

	ImageButton (String imagePath, int width, int height) {
		super(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal(imagePath)))));
		super.setHeight(height);
		super.setWidth(width);
	}
}
