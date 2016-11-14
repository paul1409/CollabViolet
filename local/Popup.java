package local;

import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * @author Paul Nguyen Creates a popup alerting the users that the file has been
 *         edited
 */
public class Popup {
  /**
   * Main Class
   * @param args arguments
   */
  public static void main(final String[] args) {
    final JFrame parent = new JFrame();
    JButton button = new JButton();

    button.setText("File has been edited");
    parent.add(button);
    parent.pack();
    parent.setVisible(true);
    parent.setSize(400, 100);

    button.addActionListener(new java.awt.event.ActionListener() {
      @Override
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        System.exit(0);
      }
    });

  }
}
