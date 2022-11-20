package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import utilities.Vector2Int;

public class Input implements KeyListener {

    Vector2Int direction = Vector2Int.right();

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_W:
            case KeyEvent.VK_UP:
                if (!direction.equals(Vector2Int.down())) {
                    direction = Vector2Int.up();
                }
                break;
            case KeyEvent.VK_S:
            case KeyEvent.VK_DOWN:
                if (!direction.equals(Vector2Int.up())) {
                    direction = Vector2Int.down();
                }
                break;
            case KeyEvent.VK_D:
            case KeyEvent.VK_RIGHT:
                if (!direction.equals(Vector2Int.left())) {
                    direction = Vector2Int.right();
                }
                break;
            case KeyEvent.VK_A:
            case KeyEvent.VK_LEFT:
                if (!direction.equals(Vector2Int.right())) {
                    direction = Vector2Int.left();
                }
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    public Vector2Int getDirection() {
        return direction;
    }

    public void setDefaultDirection() {
        direction = Vector2Int.right();
    }
}
