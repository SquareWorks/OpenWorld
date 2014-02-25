package com.squareworks.openworld.client;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;


public class SynchronizedTable extends Thread implements Serializable{
	
	/**
	 * 
	 */
	private Map<String, String> values = new HashMap<String, String>();
	private Socket connection;
	private static final long serialVersionUID = 3197354870936515477L;

	public SynchronizedTable(String host, int port) throws UnknownHostException, IOException{
		connection = new Socket(host, port);
		start();
	}
	
	@Override
	public void run() {
		try {
			DataInputStream in = new DataInputStream(connection.getInputStream());
			DataOutputStream out = new DataOutputStream(connection.getOutputStream());
			while(connection.isConnected()){
				if(in.available() > 0){
					String[] val = in.readUTF().split(",");
					values.put(val[0], val[1]);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public double getDouble(String key){
		return Double.parseDouble(values.get("d" + key));
	}
	
	public String getString(String key){
		return values.get("s" + key);
	}
	
	public int getInt(String key){
		return Integer.parseInt(values.get("i" + key));
	}
}
