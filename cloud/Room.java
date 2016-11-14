package cloud;
import java.util.ArrayList;
import local.User;

public class Room {

  private int roomNumber;
  private String password;
  private ArrayList<String> info;
  private ArrayList<User> users;

  public Room(int roomNumber) {
    this.roomNumber = roomNumber;
  }

  public boolean checkPassword(String aPassword) {
    if (aPassword == null) return false;
    return aPassword.equals(password);
  }

  public int getRoomNumber() {
    return roomNumber;
  }

  public void addInfo(String newChange) {
    info.add(newChange);
  }

  public void setPassword(String aPassword) {
    this.password = aPassword;
  }

  public boolean checkName(String name) {
    if (users.isEmpty()) return false;
    for (User u : users)
      if (u.getName() == name) return true;
    return false;
  }

  public void addUser(User u) {
    users.add(u);
  }

  public void ping() {
    for (User u : users) {}
  }
}
