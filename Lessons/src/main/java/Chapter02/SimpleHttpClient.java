/**
 * 
 */
package Chapter02;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 * @author HomeUser
 *
 */
public class SimpleHttpClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		Socket socket = new Socket("www.daum.net", 80);
		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		PrintStream out = new PrintStream(socket.getOutputStream());
		
		//1. 요청라인
		out.println("GET / HTTP/1.1");
		
		//2. 헤더정보
		out.println("Host: www.daum.net");
		out.println("User-Agent: Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");

		//3. 공백라인
		out.println();
		
		String line = null;
		while((line = in.readLine()) != null) {
			System.out.println(line);
		}
		
		in.close();
		out.close();
		socket.close();
	}

}
