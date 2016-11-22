package local;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;



/**
 * The sender class send data to server with ID
 * 
 * @author reyoungwang
 *
 */
public class Sender {
  private Object cache;
  private String id;

  /**
   * Construct sender
   * 
   * @param cache the file to send
   * @param id the id of this violet
   */
  public Sender(Object cache, String id) {
    this.cache = cache;
    this.id = id;
  }

  /**
   * The send method
   * 
   * @throws IOException too many exceptions
   */
  public void send() {
    
    String dest = "http://localhost:9000/addAction/" + id + "/";
    URL url;
    try {
        FileReader fr = new FileReader((File)cache);
        BufferedReader br = new BufferedReader(fr);
        StringBuilder sb = new StringBuilder();
        while(br.readLine() != null)
        {
            sb.append(br.readLine());
        }
        String parame = sb.toString();
        System.out.println(parame);
        byte[] postData = parame.getBytes(StandardCharsets.UTF_8);
        int postDataLength = postData.length;
        url = new URL(dest);
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setDoOutput(true);
        connection.setInstanceFollowRedirects( false );
        connection.setRequestMethod("POST");
        connection.setRequestProperty("charset", "utf-8");
        connection.setRequestProperty("Content-Length", Integer.toString(postDataLength));
        connection.setUseCaches(false);
        DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
        wr.write(postData);
        int response = connection.getResponseCode();
        System.out.println(response);
    } catch (IOException e) {
        e.printStackTrace();
    }
   
  }

  /**
   * Test run
   * @param args arguments
   * @throws IOException Exception
   */
  public static void main(String args[]) throws IOException {
    try {
      Socket sock = new Socket("104.198.158.232", 9000);
      System.out.println(sock.isConnected());
      sock.close();
      System.out.println(sock.isConnected());
      URL url = new URL("http://localhost:9000/test");
      BufferedInputStream bis = new BufferedInputStream(url.openStream());
      byte[] test = new byte[4];
      for (int i = 0; i < 4; i++)
        test[i] = 1;
      bis.read(test, 0, 4);
      System.out.println(test);
    }
    catch (ConnectException e) {
      System.out.println("Failed to connect");
    }
  }
}