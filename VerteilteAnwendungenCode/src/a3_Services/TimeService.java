package a3_Services;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TimeService {
	static int port = 75;

	public static void main(String[] args) {
		try (ServerSocket serverSocket = new ServerSocket(port)) {
			while (true) {
				try (Socket socket = serverSocket.accept()) {
					System.out.println("Akzeptiere neue Verbindung von: " + socket.getRemoteSocketAddress().toString());

					BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
					BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

					writer.write("Time service");
					writer.newLine();
					writer.flush();

					while (true) {
						String commandString = reader.readLine();

						if (commandString == null) {
							break;
						}

						System.out.println("Input:" + commandString);
						if (commandString.equals("date")) {
							writer.write(Clock.date());
							System.out.println("Datum");
						} else if (commandString.equals("time")) {
							writer.write(Clock.time());
							System.out.println("Zeit");
						} else {
							System.out.println("Wird geschlossen...");
							socket.close();
							break;
						}

						writer.newLine();
						writer.flush();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
