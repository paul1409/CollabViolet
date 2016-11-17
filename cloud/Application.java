package cloud;

import play.mvc.*;
import java.util.ArrayList;
import Application.Room;
public class Application extends Controller {
    private ArrayList<Room> roomList = new ArrayList<Room>();
    private int count = 0;
    
    public Result join() {
        Room e = new Room(count);
        if(!roomList.add(e))
            return badRequest();
        count++;
        return ok(""+count);
    }
    
    public Result addAction(int roomID) {
        String command = request().body().asText();
        request().remoteAddress();
        
    }

}
