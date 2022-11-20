package entities;

import java.awt.*;

import main.GameManager;
import utilities.Vector2Int;

public class Coin {
    Vector2Int position = Vector2Int.zero();

    public void setPosition(Vector2Int position) {
        this.position = position;
    }

    public void draw(Graphics2D g2D) {
        int size = GameManager.TILE_SIZE / 2;
        g2D.setColor(Color.orange);
        g2D.fillOval(
                this.position.x * GameManager.TILE_SIZE + GameManager.TILE_SIZE / 2 - size / 2,
                this.position.y * GameManager.TILE_SIZE + GameManager.TILE_SIZE / 2 - size / 2,
                size, size);
    }
}
