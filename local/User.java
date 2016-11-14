package local;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import cloud.Room;

/**
 * @author Paul Nguyen A user that creates/edits a UML file
 */
public class User {

  private String name;
  private ArrayList<String> info;
  private int hash;

  /**
   * Sets a user's name
   * 
   * @param name name
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Gets the name of the user
   * 
   * @return name name
   */
  public String getName() {
    return name;
  }

  /**
   * Alerts the user in changes
   */
  public void alert() {
    final JFrame parent = new JFrame();
    JOptionPane.showMessageDialog(parent, "File has been updated");
  }

  /**
   * Sets hashcode
   * @param hash Room's hashcode
   */
  public void setHash(int hash) {
    this.hash = hash;
  }

  /**
   * Sets info
   * @param info Room's info
   */
  public void setInfo(ArrayList<String> info) {
    this.info = info;
  }
}