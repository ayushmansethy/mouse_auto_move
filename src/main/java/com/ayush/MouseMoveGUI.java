package com.ayush;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Random;
import javax.swing.*;

public class MouseMoveGUI extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    private final JButton startButton;
    private final JButton stopButton;
    private final Robot robo;
    private final List<String> opList;
    static double x_center;
    static double y_center;

    public MouseMoveGUI() throws AWTException {
        setTitle("Mouse Mover");
        setSize(200, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        x_center = screenSize.getWidth() / 2;
        y_center = screenSize.getHeight() / 2;
        robo = new Robot();

        opList = List.of("+", "-");

        startButton = new JButton("Start");
        startButton.addActionListener(this);
        stopButton = new JButton("Stop");
        stopButton.addActionListener(this);

        JPanel panel = new JPanel();
        panel.add(startButton);
        panel.add(stopButton);
        add(panel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            new Thread(() -> {
                try {
                    while (true) {
                        int nextInt = new Random().nextInt(opList.size());
                        String opration = opList.get(nextInt);
                        switch (opration) {
                            case "+":
                                robo.mouseMove((int) x_center + getRandom(), (int) y_center + getRandom());
                                break;
                            case "-":
                                robo.mouseMove((int) x_center - getRandom(), (int) y_center - getRandom());
                                break;
                        }
                        Thread.sleep(7 * 1000);
                    }
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }).start();
        } else if (e.getSource() == stopButton) {
            System.exit(0);
        }
    }

    public static void main(String[] args) throws AWTException {
        MouseMoveGUI gui = new MouseMoveGUI();
        gui.setVisible(true);
    }

    public static int getRandom() {
        return (int) (Math.random() * 200 * 2) - 200;
    }
}
