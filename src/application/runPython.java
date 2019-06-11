package application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class runPython implements Runnable {
	private String pyPath;
	private String pyCommand;
	private int moduleFlag; // kor : 1 / english : 2 / summary : 3 / saveas : 4
	
	SampleController sp = new SampleController();
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
		this.moduleFlag = flag;
	}
	
	public runPython(String pyPath, String args, int flag, String args1) {
		this.pyPath = pyPath;
		this.pyCommand = "python " + this.pyPath + " " + "\"" + args + "\"" + " " + args1;
		this.moduleFlag = flag;
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
				if(s.contentEquals("EXIT")) {
					if(this.moduleFlag == 1) { // korea
						//sp.btnPreview.setDisable(false);
						//btnPreview.setDisable(false);
					} else if(this.moduleFlag == 2) { // english
						//sp.testEnglish();
						//System.out.println("soicem");
						//btnPreview.setDisable(false);
					} else if(this.moduleFlag == 3) { // summarization
						//sp.btnStep4.setDisable(false);
						//btnStep4.setDisable(false); 
					} else if(this.moduleFlag == 4) { // save as
						
					}
				}
				SampleController.text += s;
				System.out.println(s);
				System.out.println(s.contentEquals("EXIT"));
			}
			//System.out.println(SampleController.text);
		} catch(IOException ie) {
			ie.printStackTrace();
			
		}
	}
}
