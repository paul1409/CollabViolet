package local;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Popup {
  public static void main(final String[] args) {
    final JFrame parent = new JFrame();
    JButton button = new JButton();

    button.setText("File has been edited");
    parent.add(button);
    parent.pack();
    parent.setVisible(true);
    parent.setSize(400,100);
/*
    button.addActionListener(new java.awt.event.ActionListener() {
      @Override
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        String name = JOptionPane.showInputDialog(parent, "What is your name?", null);
      }
    });
    */
  }
}
