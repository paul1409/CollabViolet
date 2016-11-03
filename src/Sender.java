import java.io.*;
import java.net.*;

public class Sender {
	private File cache;
	private int id;

	public Sender(File cache, int id) {
		this.cache = cache;
		this.id = id;

	}

	public void send() throws Exception {
		Socket sock = new Socket("104.198.158.232", 8080);
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
}
