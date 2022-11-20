package main;

import java.awt.*;

import javax.swing.JPanel;

import entities.Coin;
import entities.Player;
import utilities.Vector2Int;

public class GameManager extends JPanel implements Runnable {

    static final int ORIGINAL_TILE_SIZE = 16; // original tile size in px
    static final int SCALE = 4;
    public static final int MAX_COLUMN_COUNT = 16;
    public static final int MAX_ROW_COUNT = 12;

    public static final int TILE_SIZE = ORIGINAL_TILE_SIZE * SCALE;
    final int SCREEN_WIDTH = TILE_SIZE * MAX_COLUMN_COUNT;
    final int SCREEN_HEIGHT = TILE_SIZE * MAX_ROW_COUNT;

    Thread gameThread;

    static Input input = new Input();
    static Player player = new Player();
    static Coin coin = new Coin();
    static Vector2Int coinPosition;
    static int difficulty;
    static int score;

    public GameManager() {
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true); // renderizza tutto in un buffer offscreen
        this.addKeyListener(input);
        this.setFocusable(true);
        resetGame();
    }

    void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        // defines game loop
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null) {
            double timeBetweenMove = 1000000000 / difficulty;
            // this is the update
            currentTime = System.nanoTime();
            if (currentTime - lastTime >= timeBetweenMove) {
                update();
                repaint();
                lastTime = System.nanoTime();
            }
        }
    }

    public void update() {
        player.moveTowards(input.getDirection());
        Vector2Int snakeHeadPos = player.getHeadPosition();
        if (snakeHeadPos.equals(coinPosition)) {
            coinPosition = generateCoinPosition(player.getPositionsToArray());
            coin.setPosition(coinPosition);
            score += 1;
            if (score % 10 == 0) {
                difficulty++;
            }
            player.increaseLength();
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2D = (Graphics2D) g;
        player.draw(g2D);
        coin.draw(g2D);
        g2D.dispose();
    }

    static Vector2Int generateCoinPosition(Vector2Int[] occupiedPositions) {
        Vector2Int pos;
        boolean trov;
        do {
            trov = true;
            pos = new Vector2Int(
                    (int) (Math.random() * MAX_COLUMN_COUNT),
                    (int) (Math.random() * MAX_ROW_COUNT));

            for (int i = 0; i < occupiedPositions.length; i++) {
                if (pos.equals(occupiedPositions[i])) {
                    trov = false;
                }
            }
        } while (!trov);
        return pos;
    }

    public static void resetGame() {
        coinPosition = generateCoinPosition(player.getPositionsToArray());
        coin.setPosition(coinPosition);
        difficulty = 2;
        score = 1;
        player = new Player();
        input.setDefaultDirection();
    }

}
