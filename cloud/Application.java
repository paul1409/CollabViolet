package cloud;

import play.mvc.*;
import java.util.ArrayList;
import Application.Room;
/**
 * Class to handler everything
 * @author reyoungwang
 *
 */
public class Application extends Controller {
    private ArrayList<Room> roomList = new ArrayList<Room>();
    private int count = 0;
    
    /**
     * new Room request
     * @return success with room number or failed
     */
    public Result newRoom() {
        Room e = new Room(count);
        if(!roomList.add(e))
            return badRequest();
        count++;
        return ok(""+count);
    }
    
    /**
     * Enter room with number
     * @ return success or failed 
     */
    public Result join(int id) {
        if(id < count) {
            Room r = roomList.get(id);
            r.addIP(request().remoteAddress());
            return ok("" + "success!");
        } else
            return badRequest("No such room");
    }
    
    
    /**
     * Automatically response to check request
     * @param id the room number
     * @param number the client's count
     * @return the new adding
     */
    public Result checkUpdate(int id,int number) {
        Room r = roomList.get(id);
        if(r.isIn(request().remoteAddress())) {
            return ok(r.sync(number));
        } else
            return badRequest();
    }
    
    /**
     * add action to room
     * @param roomID the room to add
     * @return success or failed
     */
    public Result addAction(int roomID) {
        String command = request().body().asText();
        Room r = roomList.get(roomID);
        String ip = request().remoteAddress();
        if(r.isIn(ip)) {
            r.addCommand(command);
            return ok();
        } else {
            return badRequest("Opps!");
        }
        
    }

}
