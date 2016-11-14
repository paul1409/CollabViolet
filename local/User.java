package local;
/**
 * @author Paul Nguyen
 * A user that creates/edits a UML file
 */
public class User {

  private String name;

  /**
   * Sets a user's name
   * @param name name
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Gets the name of the user
   * @return name name
   */
  public String getName() {
    return name;
  }
}
