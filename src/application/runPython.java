package application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class runPython implements Runnable {
	private String pyPath;
	private String pyCommand;
	public runPython() {
		this.pyPath = "test.py";
		this.pyCommand = null; // fix current directory
	}
	public runPython(String pyPath) {
		this.pyPath = pyPath;
		this.pyCommand = "python " + this.pyPath; // fix current directory
	}
	public runPython(String pyPath, String args) {
		this.pyPath = pyPath;
		this.pyCommand = "python " + this.pyPath + " " + args; 
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			String s = null;
			Process p = Runtime.getRuntime().exec(this.pyCommand);
			System.out.println(this.pyCommand);
			BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
			while((s = in.readLine()) != null) {
				System.out.println(s);
			}
		} catch(IOException ie) {
			ie.printStackTrace();
			
		}
	}
}
