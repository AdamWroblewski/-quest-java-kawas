package com.codecool.quest;

import com.codecool.quest.logic.Drawable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;

public class Tiles {
    public static int TILE_WIDTH = 32;

    private static Image tileset = new Image("/tiles.png", 543 * 2, 543 * 2, true, false);
    private static Map<String, Tile> tileMap = new HashMap<>();

    public static class Tile {
        public final int x, y, w, h;
        Tile(int i, int j) {
            x = i * (TILE_WIDTH + 2);
            y = j * (TILE_WIDTH + 2);
            w = TILE_WIDTH;
            h = TILE_WIDTH;
        }
    }

    static {
        tileMap.put("empty", new Tile(0, 0));
        tileMap.put("wall", new Tile(10, 17));
        tileMap.put("floor", new Tile(2, 0));
        tileMap.put("player", new Tile(23, 0));
        tileMap.put("player with sword", new Tile(27, 0));
        tileMap.put("player with axe", new Tile(27, 1));
        /* Enemies: */
        tileMap.put("skeleton", new Tile(29, 6));
        tileMap.put("staggerState", new Tile(0, 15));
        tileMap.put("ghost1", new Tile(26, 6));
        tileMap.put("ghost2", new Tile(27, 6));

        tileMap.put("closeddoor Blue", new Tile(0, 9));
        tileMap.put("openeddoor Blue", new Tile(2, 9));
        tileMap.put("closeddoor Yellow", new Tile(1, 11));
        tileMap.put("openeddoor Yellow", new Tile(2, 11));
        tileMap.put("closeddoor Red", new Tile(0, 12));
        tileMap.put("openeddoor Red", new Tile(1, 12));
        tileMap.put("key Blue", new Tile(17, 23));
        tileMap.put("key Yellow", new Tile(16, 23));
        tileMap.put("key Red", new Tile(18, 23));
        tileMap.put("shield", new Tile(7, 26));
        tileMap.put("button", new Tile(28, 20));
        tileMap.put("stairs down", new Tile(21, 0));
        tileMap.put("ladder", new Tile(21, 1));
        tileMap.put("tree", new Tile(0, 1));
        tileMap.put("sword", new Tile(2, 28));
        tileMap.put("axe", new Tile(8, 29));
    }

    public static void drawTile(GraphicsContext context, Drawable d, int x, int y) {
        Tile tile = tileMap.get(d.getTileName());
        context.drawImage(tileset, tile.x, tile.y, tile.w, tile.h,
                x * TILE_WIDTH, y * TILE_WIDTH, TILE_WIDTH, TILE_WIDTH);
    }
}
