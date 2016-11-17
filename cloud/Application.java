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
     * @return
     */
    public Result newRoom() {
        Room e = new Room(count);
        if(!roomList.add(e))
            return badRequest();
        count++;
        return ok(""+count);
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
            r.addInfo(command);
            return ok();
        } else {
            return badRequest("Opps!");
        }
        
    }

}
