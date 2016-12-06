package local;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Base64;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

import local.CommandData;


/**
 * The sender class send data to server with ID
 * 
 * @author reyoungwang
 *
 */
public class Sender {
  private String id;

  /**
   * Construct sender
   * 
   * @param id the id of this violet
   */
  public Sender(String id) {
    this.id = id;
  }

   /**
    public void send() {
        String dest = "http://localhost:9000/addAction/" + id + "/";
        URL url;
        try {
            FileReader fr = new FileReader((File) cache);
            BufferedReader br = new BufferedReader(fr);
            StringBuilder sb = new StringBuilder();
            while (br.readLine() != null) {
                sb.append(br.readLine());
            }
            String parame = sb.toString();
            System.out.println(parame);
            byte[] postData = parame.getBytes(StandardCharsets.UTF_8);
            int postDataLength = postData.length;
            url = new URL(dest);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setInstanceFollowRedirects(false);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("charset", "utf-8");
            connection.setRequestProperty("content-type", "text/plain; charset=utf-8");
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
    **/
    
  /**
   * The send method
   * 
   * @throws IOException too many exceptions
   * @param content some content
   */
  public void sendString(String content) {
    try {
      String dest = "http://104.198.99.184:9000/addAction/" + id + "/";
      URL url = new URL(dest);
      // FileReader fr = new FileReader((File) cache);
      // BufferedReader br = new BufferedReader(fr);
      // StringBuilder sb = new StringBuilder();
      // while (br.readLine() != null) {
      // sb.append(br.readLine());
      // }
      // String parame = URLEncoder.encode(content,"UTF-8");
      String parame = content;
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
      connection.setDoOutput(true);
      connection.setRequestMethod("POST");
      connection.setRequestProperty("content-type", "text/plain; charset=utf-8");
      OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
      writer.write(parame);
      writer.close();
      // br.close();
      int response = connection.getResponseCode();
    }
    catch (IOException e) {
      e.printStackTrace();

    }
  }

  /**
   * 
   * @param is the input stream
   * @return the string result
   * @throws IOException
   */
  public static String inputStreamToString(InputStream is) throws IOException {
    BufferedReader br = null;
    StringBuilder sb = new StringBuilder();
    String line;
    br = new BufferedReader(new InputStreamReader(is));
    while ((line = br.readLine()) != null) {
      sb.append(line);
    }
    br.close();
    return sb.toString();
  }
}