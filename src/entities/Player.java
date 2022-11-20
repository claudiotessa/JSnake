package entities;

import utilities.Vector2Int;
import java.awt.*;
import java.util.ArrayList;

import main.GameManager;

public class Player {
    ArrayList<Vector2Int> positions = new ArrayList<Vector2Int>();

    public Player() {
        positions.add(new Vector2Int(1, 1));
        positions.add(new Vector2Int(2, 1));
        positions.add(new Vector2Int(3, 1));
    }

    public void moveTowards(Vector2Int direction) {
        int headIndex = positions.size() - 1;

        for (int i = 0; i < positions.size() - 1; i++) {
            positions.set(i, positions.get(i + 1).valueOnly());
        }
        positions.set(
                headIndex,
                new Vector2Int(
                        positions.get(headIndex).x += direction.x,
                        positions.get(headIndex).y -= direction.y));

        // effetto pacman
        if (positions.get(headIndex).x >= GameManager.MAX_COLUMN_COUNT) {
            positions.set(headIndex, new Vector2Int(0, positions.get(headIndex).y));
        } else if (positions.get(headIndex).x < 0) {
            positions.set(
                    headIndex,
                    new Vector2Int(
                            GameManager.MAX_COLUMN_COUNT - 1,
                            positions.get(headIndex).y));
        }

        if (positions.get(headIndex).y >= GameManager.MAX_ROW_COUNT) {
            positions.set(headIndex, new Vector2Int(positions.get(headIndex).x, 0));
        } else if (positions.get(headIndex).y < 0) {
            positions.set(
                    headIndex,
                    new Vector2Int(
                            positions.get(headIndex).x,
                            GameManager.MAX_ROW_COUNT - 1));
        }

        if (collided()) {
            GameManager.resetGame();
        }
    }

    public void draw(Graphics2D g2D) {
        int size = GameManager.TILE_SIZE;

        g2D.setColor(Color.white);
        for (int i = 0; i < positions.size(); i++) {
            g2D.fillRect(
                    positions.get(i).x * GameManager.TILE_SIZE + GameManager.TILE_SIZE / 2 - size / 2,
                    positions.get(i).y * GameManager.TILE_SIZE + GameManager.TILE_SIZE / 2 - size / 2,
                    size, size);
        }
    }

    public Vector2Int[] getPositionsToArray() {
        return positions.toArray(new Vector2Int[0]);
    }

    public Vector2Int getHeadPosition() {
        return positions.get(positions.size() - 1).valueOnly();
    }

    public void increaseLength() {
        positions.add(0, positions.get(0).valueOnly());
    }

    boolean collided() {
        for (int i = 1; i < positions.size(); i++) {
            if (positions.get(0).equals(positions.get(i))) {
                return true;
            }
        }
        return false;
    }
}
