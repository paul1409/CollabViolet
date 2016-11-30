package controllers;

import play.mvc.*;

import java.io.File;
import java.util.ArrayList;
import akka.util.ByteString;
import com.fasterxml.jackson.databind.JsonNode;

import Application.Room;

/**
 * Class to handler everything
 * @author reyoungwang
 *
 */
public class Application extends Controller {
  private ArrayList<Room> roomList = new ArrayList<Room>();
  private File[] initialStatus = new File[100];
  private int count = 0;

  /**
   * new Room request
   * @return success with room number or failed
   */
  public Result newRoom() {
    Room e = new Room(count);
    roomList.add(e);
    count++;
    e.addIP(request().remoteAddress());
    return ok("" + (count - 1));
  }

  /**
   * Enter room with number
   * @param id the room id
   * @return success or failed
   */
  public Result join(int id) {
    if (id < count) {
      Room r = roomList.get(id);
      r.addIP(request().remoteAddress());
      return ok("" + "success!");
    } else return badRequest("No such room");
  }

  /**
   * Automatically response to check request
   * @param id the room number
   * @param number the client's count
   * @return the new adding
   */
  public Result checkUpdate(int id, int number) {
    Room r = roomList.get(id);
    if (r.isIn(request().remoteAddress())) {
      if (r.isNewest(number)) return status(888, "No Update");
      else return ok(r.sync(number));
    } else return badRequest("Error");
  }

  /**
   * add action to room
   * @param roomID the room to add
   * @return success or failed
   */
  @BodyParser.Of(BodyParser.AnyContent.class)
  public Result addAction(int roomID) {

    String command = request().body().asText();
    Room r = roomList.get(roomID);
    String ip = request().remoteAddress();
    if (r.isIn(ip)) {
      r.addCommand(command);
      return ok("Success");
    } else {
      return badRequest("Opps!");
    }

  }

  /**
   * Reset the server
   * @return success
   */
  public Result reset() {
    roomList = new ArrayList<Room>();
    initialStatus = new File[100];
    count = 0;
    return ok("success");
  }

  /**
   * Doesn't work now. This method for initializing syncing
   * @param id the roomID
   * @return result
   */
  public Result initR(int id) {
    File file = request().body().asRaw().asFile();
    if (file == null) return badRequest("Error!");
    initialStatus[id] = file;
    return ok();
  }

  /**
   * Demo what command we have
   * @param id the room id
   * @return what's in the commandlist
   */
  public Result demo(int id) {
    StringBuilder sb = new StringBuilder();
    Room r = roomList.get(id);
    ArrayList<String> list = r.getCommandList();
    for (int i = 0; i < list.size(); i++) {
      sb.append(i + ": " + list.get(i) + "\n");
    }
    return ok(sb.toString());
  }

  // public Result initG(int id) {
  // File file = initialStatus.get(id);

  // }

}
