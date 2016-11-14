package local;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.URL;


/**
 * The sender class send data to server with ID
 * 
 * @author reyoungwang
 *
 */
public class Sender {
  private File cache;
  private int id;

  /**
   * Construct sender
   * 
   * @param cache the file to send
   * @param id the id of this violet
   */
  public Sender(File cache, int id) {
    this.cache = cache;
    this.id = id;
  }

  /**
   * The send method
   * 
   * @throws Exception too many exceptions
   */
  public void send() throws Exception {
    Socket sock = new Socket("104.198.158.232", 9000);
    OutputStream os = sock.getOutputStream();
    OutputStreamWriter osw = new OutputStreamWriter(os);
    BufferedWriter bw = new BufferedWriter(osw);
    bw.write(id);
    bw.flush();
    byte[] mybytearray = new byte[1024];
    InputStream is = sock.getInputStream();
    FileOutputStream fos = new FileOutputStream(cache);
    BufferedOutputStream bos = new BufferedOutputStream(fos);
    int bytesRead = is.read(mybytearray, 0, mybytearray.length);
    bos.write(mybytearray, 0, bytesRead);
    bos.close();
    sock.close();
  }
  
  public static void main(String args[]) throws IOException {
	  Socket sock = new Socket("104.198.158.232", 9000);
	  System.out.println(sock.isConnected());
	  sock.close();
	  System.out.println(sock.isConnected());
	  URL url = new URL("http://localhost:9000/test");
	  BufferedInputStream bis = new BufferedInputStream(url.openStream());
	  byte[] test = new byte[4];
	  for(int i = 0; i < 4 ; i++)
		  test[i] = 1;
	  bis.read(test,0, 4);
	  System.out.println(test);
  }
}