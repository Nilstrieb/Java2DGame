import core.Master;

import javax.swing.*;
import java.awt.*;
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
        add(master);

        setTitle("Points");
        setSize(1600, 900);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                master.mousePressed();
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