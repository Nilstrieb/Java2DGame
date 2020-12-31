package core.general;

import objects.Init;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class Main extends JFrame {

    Master master;
    Timer timer;

    public Main() {
        initUI();
        timer = new Timer(1000/60, e -> {
            master.refresh();
        });
        timer.start();
    }

    private void initUI() {

        master = new Master();
        master.init();
        add(master.getRenderEngine());

        Init.init();

        setTitle("Points");
        int w = 1500;
        setSize(w, (int) (w / Master.SCREEN_RATIO));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Input.mousePressed();
            }
        });

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                Input.addPressedKey(e.getKeyCode());
            }

            @Override
            public void keyReleased(KeyEvent e) {
                System.err.println("released");
                Input.removePressKey(e.getKeyCode());
            }
        });
    }

    public static void main(String[] args) {


        EventQueue.invokeLater(() -> {

            Main ex = new Main();
            ex.setVisible(true);
        });
    }
}