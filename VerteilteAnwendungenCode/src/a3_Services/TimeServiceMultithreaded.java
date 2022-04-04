package a3_Services;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TimeServiceMultithreaded extends Thread {
	private Socket socket;

	public TimeServiceMultithreaded(Socket socket) {
		this.socket = socket;
	}

	private void Write(BufferedWriter writer, String message) {
		try {
			writer.write(message);
			writer.newLine();
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		try {
			System.out.println("Verbindung gestartet...");
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			Write(writer,"time service");
			
			while(!socket.isClosed()) {
				String answer = reader.readLine();
				try {
					
					if(answer == null) {
						socket.close();
						
					} else if (answer.equals("time")) {
						System.out.println("Time: ");
						writer.write(Clock.time());
						
					} else if (answer.equals("date")){
						System.out.println("Date: ");
						writer.write(Clock.date());
						
					} else {
						System.out.println("Verbindung wird geschlossen..");
						socket.close();
					}
					
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		}catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		try {
			try(ServerSocket serverSocket = new ServerSocket(75);){
				while(true) {
					try {
						TimeServiceMultithreaded thread = new TimeServiceMultithreaded(serverSocket.accept());
						thread.start();
					} catch(Exception e) {
						e.printStackTrace();
					}
				}
			}
		} catch(IOException e) {
			e.printStackTrace();
		}

	}

}
