package a2_Services;

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
		try {
			System.out.println("Port: " + port + " offen");
			ServerSocket serverSocket = new ServerSocket(port);
			Socket socket = serverSocket.accept();
			
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			BufferedReader reader = new BufferedReader (new InputStreamReader(socket.getInputStream()));
			
			writer.write("Service gestartet ");
			writer.newLine();
			writer.flush();
			
			while (true) {
				String readLine = reader.readLine();
				if(readLine.equals("date")) {
					writer.write(Clock.date());
					
				} else if(readLine.equals("time")) {
					writer.write(Clock.time());
					
				} else {
					System.out.println("Ende");
					serverSocket.close();
					break;
				}
				writer.newLine();
				writer.flush();
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	
	}
