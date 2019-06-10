package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class runPython implements Runnable {
	private String pyPath;
	private String pyCommand;
	public runPython() {
		this.pyPath = null;
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
	
	// ¸íÁø
	public runPython(String pyPath, String args, int flag) {
		this.pyPath = pyPath;
		this.pyCommand = "python " + this.pyPath + " " + args; 
	}
	
	public runPython(String pyPath, String args, int flag, String args1) {
		this.pyPath = pyPath;
		this.pyCommand = "python " + this.pyPath + " " + "\"" + args + "\"" + " " + args1;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			String s = null;
			Process p = Runtime.getRuntime().exec(this.pyCommand);
			System.out.println(this.pyCommand);
			BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
			SampleController.text = "";

			
			while((s = in.readLine()) != null) {
				System.out.println(this.pyPath);
				
				if(this.pyPath.equals("./speech_to_text.py")) {
					SampleController.text += s;
					System.out.println(SampleController.text);
				}
				if(this.pyPath.equals("./speech_to_text_ko.py")) {
					SampleController.text += s;
					System.out.println(SampleController.text);
				}
			}
		} catch(IOException ie) {
			ie.printStackTrace();
			
		}
	}
}
