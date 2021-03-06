package Client;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;

import Server.Message;

public class Reciever extends Thread {	

	private static final int PORT = 1919;			
	private final static String HOST = "localhost";
	private InputStream is;
	private Socket sock;
	
	@Override
	public void run() {				
		try {
			sock = new Socket(HOST, PORT);		
			System.out.println("Connecting...");
						
			byte[] fileArray = new byte[1024];			
			InputStream is = sock.getInputStream();	
						
		    FileOutputStream fos = new FileOutputStream("ID" +new Date().getTime()+".txt");			  
		    BufferedOutputStream bos = new BufferedOutputStream(fos);
		    int bytesRead ;
		    while((bytesRead =  is.read(fileArray)) > 0){
		    	 bos.write(fileArray, 0, bytesRead);		    	
		    }
		    
		    System.out.println("RECIVING DONE !");	
		    
		    bos.flush();
		    bos.close();
		    is.close();	
		    sock.close();
		    
							
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
}