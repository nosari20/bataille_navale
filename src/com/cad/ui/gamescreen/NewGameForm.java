package com.cad.ui.gamescreen;

import com.cad.bataille_navale.jeu.BatailleNavale;
import com.cad.bataille_navale.joueurs.JoueurBatailleNavale;
import com.cad.bataille_navale.joueurs.RandomStrategyComputer;
import com.cad.codesUtils.BatailleNavalleJoueurCote;
import com.cad.codesUtils.epoque.Epoque;
import com.cad.jeu_abstrait.Jeu;
import com.cad.jeu_abstrait.Joueur;
import com.cad.ui.GameUI;
import com.cad.ui.MainGUI;
import com.cad.ui.sprites_repository.SpriteFontRepository;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class NewGameForm  extends JPanel {

    private JTextField input_name = new JTextField(20);
    private JLabel label_name = new JLabel("Nom");
    private JComboBox input_epoque = new JComboBox(Epoque.values());
    private JLabel label_epoque = new JLabel("Epoque");
    private JComboBox input_difficulty = new JComboBox();
    private JLabel label_difficulty = new JLabel("Difficulté");
    private JButton validate = new JButton("Créer");

    public NewGameForm() {
        super();
        setBorder(new EmptyBorder(10, 10, 10, 10));
        this.setLayout(new BorderLayout());



        // Form
        JLabel title = new JLabel("Créer une nouvelle partie");
        title.setFont(new Font(title.getFont().getName(), Font.PLAIN,title.getFont().getSize()*2));
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setVerticalAlignment(JLabel.CENTER);
        add(title, BorderLayout.NORTH);

        JPanel form = new JPanel();
        GridLayout l = new GridLayout(4,1);
        l.setVgap(10);
        form.setLayout(l);

        JPanel panel_name = new JPanel(new GridLayout(2,1));
        panel_name.add(label_name);
        panel_name.add(input_name);
        form.add(panel_name);
        input_name.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                validate.setEnabled(input_name.getText().length()!=0);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {

            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });


        JPanel panel_epoque = new JPanel(new GridLayout(2,1));
        panel_epoque.add(label_epoque);
        panel_epoque.add(input_epoque);
        form.add(panel_epoque);


        JPanel panel_difficulty = new JPanel(new GridLayout(2,1));
        panel_difficulty.add(label_difficulty);
        panel_difficulty.add(input_difficulty);
        form.add(panel_difficulty);

        validate.setEnabled(input_name.getText().length()!=0);
        validate.addActionListener(event -> {
				new MainGUI(input_name.getText());
		});

        

        //new MainGUI();
        form.add(validate);

        add(form, BorderLayout.CENTER);


    }


}