package com.cad.ui.gamescreen;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class GameStartScreen extends JFrame {

    // menu
    private JMenuBar menuBar = new JMenuBar();

    private JMenu file = new JMenu("Fichier");
    private JMenuItem new_game = new JMenuItem("Nouvelle partie");
    private JMenuItem load_game = new JMenuItem("Charger partie");
    private JMenuItem leave = new JMenuItem("Quitter");

    private JButton nouvelle = new JButton("Nouvelle partie");
    private JButton charger = new JButton("Charger partie");
    private JButton quitter = new JButton("Quitter");

    // panels
    private CardLayout layoutManager = new CardLayout();
    private String[] listContent = {"HOME", "NEW_GAME"};
    private NewGameForm newGameForm = new NewGameForm(this);


    public GameStartScreen(){
        super();
        // general

        this.setMinimumSize(new Dimension(800,400));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(layoutManager);
        this.setVisible(true);

        // menu
        this.new_game.addActionListener(event ->{
            layoutManager.show(this.getContentPane(),listContent[1]);
        });
        file.add(new_game);

        load_game.addActionListener(event ->{

        });

        file.add(load_game);

        file.addSeparator();

        leave.addActionListener(event ->{
            System.exit(0);
        });
        file.add(leave);

        menuBar.add(file);
        this.setJMenuBar(menuBar);

        JPanel home = new JPanel();
        home.setBorder(new EmptyBorder(10, 10, 10, 10));
        GridLayout l = new GridLayout(3,1);
        l.setVgap(10);
        home.setLayout(l);
        home.add(nouvelle);
        nouvelle.addActionListener(event ->{
            layoutManager.show(this.getContentPane(),listContent[1]);
        });
        home.add(charger);
        quitter.addActionListener(event ->{
            System.exit(0);
        });
        home.add(quitter);

        //panels
        this.add(home,listContent[0]);
        this.add(newGameForm,listContent[1]);



    }

    public void destroyJFrame(){
        this.dispose();
    }

}