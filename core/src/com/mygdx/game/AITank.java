package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;

import java.awt.*;
import java.awt.event.KeyEvent;

public class AITank extends Sprite {
    private Vector2 velocity = new Vector2();
    private float speed = 100, gravity = 100;
    private TiledMapTileLayer collisionLayer;
    private float startX, startY;
    private Robot r = new Robot();
    boolean playable = false;
    boolean shoot = false;

    public AITank(Sprite tank, TiledMapTileLayer collisionLayer) throws AWTException {
        super(tank);
        this.collisionLayer = collisionLayer;
    }

    public void draw(SpriteBatch spriteBatch) throws AWTException {
        update(Gdx.graphics.getDeltaTime());
        super.draw(spriteBatch);
    }

    public void init() {
        int y = 0;
        int x = 1100;
        for (int i=640; i>=0; i--) {
            if (collisionLayer.getCell(x, i) != null) {
                if (collisionLayer.getCell(x, i).getTile().getId() == 1) {
                    y = i;
                    break;
                }
            }
        }

        this.setPosition(x, y);

        int nextTileX = (int)(getX() + getWidth()/2) + 25;
        int nextTileY = 0;

        int prevTileX = (int)(getX() + getWidth()/2) - 25;
        int prevTileY = 0;

        for (int i=640; i>=0; i--) {
            if (collisionLayer.getCell(nextTileX, i) != null) {
                if (collisionLayer.getCell(nextTileX, i).getTile().getId() == 1) {
                    nextTileY = i;
                    break;
                }
            }
        }

        for (int i=640; i>=0; i--) {
            if (collisionLayer.getCell(prevTileX, i) != null) {
                if (collisionLayer.getCell(prevTileX, i).getTile().getId() == 1) {
                    prevTileY = i;
                    break;
                }
            }
        }

        double slope = (nextTileY - prevTileY);
        this.setRotation((float)slope);

        this.flip(true,false);
    }

    public void update(float delta) throws AWTException {
        velocity.y -= gravity * delta;

        if (getX() < 1) {
            setX(1);
        }
        if (getX() > 1150) {
            setX(1150);
        }

        float x = getX();
        float y = getY();

        if (playable) {
            int enemyX = (int)AIGameScreen.tank1.getX();
            int newPos = (int)Math.floor(Math.random() * (1150 - enemyX) + enemyX);

            if (newPos > getX()) {
                r.keyPress(KeyEvent.VK_RIGHT);
                if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                    int nextTileX = (int) (getX() + getWidth() / 2) + 25;
                    int nextTileY = 0;

                    int prevTileX = (int) (getX() + getWidth() / 2) - 25;
                    int prevTileY = 0;

                    for (int i = 640; i >= 0; i--) {
                        if (collisionLayer.getCell(nextTileX, i) != null) {
                            if (collisionLayer.getCell(nextTileX, i).getTile().getId() == 1) {
                                nextTileY = i;
                                break;
                            }
                        }
                    }

                    for (int i = 640; i >= 0; i--) {
                        if (collisionLayer.getCell(prevTileX, i) != null) {
                            if (collisionLayer.getCell(prevTileX, i).getTile().getId() == 1) {
                                prevTileY = i;
                                break;
                            }
                        }
                    }

                    double slope = (nextTileY - prevTileY);
                    this.setRotation((float) slope);
                    if (slope > 0)
                        y = prevTileY + 10;
                    else if (slope < 0)
                        y = prevTileY - 10;

                    x += 10;
                }
                r.keyRelease(KeyEvent.VK_RIGHT);
            } else if (newPos < getX()) {
                 r.keyPress(KeyEvent.VK_LEFT);
                 if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                    int nextTileX = (int)(getX() + getWidth()/2) + 25;
                    int nextTileY = 0;

                    int prevTileX = (int)(getX() + getWidth()/2) - 25;
                    int prevTileY = 0;

                    for (int i=640; i>=0; i--) {
                        if (collisionLayer.getCell(nextTileX, i) != null) {
                            if (collisionLayer.getCell(nextTileX, i).getTile().getId() == 1) {
                                nextTileY = i;
                                break;
                            }
                        }
                    }

                    for (int i=640; i>=0; i--) {
                        if (collisionLayer.getCell(prevTileX, i) != null) {
                            if (collisionLayer.getCell(prevTileX, i).getTile().getId() == 1) {
                                prevTileY = i;
                                break;
                            }
                        }
                    }

                    double slope = (prevTileY - nextTileY);
                    this.setRotation((float)-slope);
                    if (slope >= 0)
                        y = prevTileY - 10;
                    else
                        y = prevTileY;
                    x -= 10;
                }
                r.keyRelease(KeyEvent.VK_LEFT);
            }
            else {
                System.out.println("SHOOOOOT!!");
                shoot = true;
            }
        }

        if (velocity.y > speed) {
            velocity.y = speed;
        } else if (velocity.y < speed) {
            velocity.y = -speed;
        }
    }
}
