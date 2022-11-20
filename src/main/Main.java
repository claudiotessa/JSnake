package main;

import javax.swing.JFrame;

/**
 * Main
 */
public class Main {

    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("JSnake");

        GameManager gameManager = new GameManager();
        window.add(gameManager);
        window.pack(); // force the windows to be resized and fit subcomponents (gameManager)
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gameManager.startGameThread();
    }
}