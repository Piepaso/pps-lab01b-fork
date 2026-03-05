package it.unibo.pps.e3;

import it.unibo.pps.grid.Pair;
import it.unibo.pps.grid.Position;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import javax.swing.event.MouseInputListener;

import java.util.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

public class GUI extends JFrame {
    
    private static final long serialVersionUID = -6218820567019985015L;
    private final Map<JButton, Pair<Integer,Integer>> buttons = new HashMap<>();
    private final Logics logics;
    
    public GUI(int size, int minesCount) {
        //this.logics = new LogicsImpl(size, minesCount);
        var mines = Set.of(new Position(3, 2), new Position(1, 1));
        this.logics = new LogicsImpl(size, mines);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(100*size, 100*size);
        
        JPanel panel = new JPanel(new GridLayout(size,size));
        this.getContentPane().add(BorderLayout.CENTER,panel);
        
        ActionListener onClick = (e)->{
            final JButton bt = (JButton)e.getSource();
            final Pair<Integer,Integer> pos = buttons.get(bt);
            boolean aMineWasFound = logics.reveal(new Position(pos));
            if (aMineWasFound) {
                quitGame();
                JOptionPane.showMessageDialog(this, "You lost!!");
            } else {
                drawBoard();            	
            }
            boolean isThereVictory = logics.isThereVictory();
            if (isThereVictory){
                quitGame();
                JOptionPane.showMessageDialog(this, "You won!!");
                System.exit(0);
            }
        };

        MouseInputListener onRightClick = new MouseInputAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                final JButton bt = (JButton)e.getSource();
                if (bt.isEnabled()){
                    final Pair<Integer, Integer> pos = buttons.get(bt);
                    logics.toggleFlag(new Position(pos));
                }
                drawBoard(); 
            }
        };
                
        for (int i=0; i<size; i++){
            for (int j=0; j<size; j++){
                final JButton jb = new JButton(" ");
                jb.addActionListener(onClick);
                jb.addMouseListener(onRightClick);
                this.buttons.put(jb,new Pair<>(i,j));
                panel.add(jb);
            }
        }
        this.drawBoard();
        this.setVisible(true);
    }
    
    private void quitGame() {
        this.drawBoard();
    	for (var entry: this.buttons.entrySet()) {
            var button = entry.getKey();
            if (logics.isThereMine(new Position(entry.getValue()))) {
                button.setText("*");
            }
            button.setEnabled(false);
    	}
    }

    private void drawBoard() {
        for (var entry: this.buttons.entrySet()) {
            String content;
            Cell cell = logics.getCellAtPosition(new Position(entry.getValue()));
            if (cell.isHidden()) {
                content = cell.hasFlag() ? "F" : "";
            } else {
                content = String.valueOf(cell.getNumber());
            }
            entry.getKey().setText(content);
    	}
    }
    
}
