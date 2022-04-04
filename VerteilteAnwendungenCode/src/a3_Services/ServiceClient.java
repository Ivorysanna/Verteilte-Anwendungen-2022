package a3_Services;

import java.net.Socket;
import java.net.UnknownHostException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class ServiceClient {

	public static void main(String[] args) {
		for(int i = 0; i <= 10; i++) {
			System.out.println("Neue Anfrage");
			System.out.println(timeFromServer("127.0.0.1"));			
		}

	}

	public static String timeFromServer(String ipAddress) {

		try (Socket socket = new Socket(ipAddress, TimeService.port)) {
			BufferedWriter printWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			String message = "time";

			if (!bufferedReader.readLine().equals("time service")) {
				socket.close();
				return "Wrong service";
			}

			printWriter.write(message);
			printWriter.newLine();

			// Nachricht abschicken
			printWriter.flush();

			// Antwort erhalten
			String resultString = bufferedReader.readLine();

			socket.close();

			return resultString;
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}
}
