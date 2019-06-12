package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXSpinner;
import com.jfoenix.controls.JFXTextField;
import application.Main;
import javafx.event.ActionEvent;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
class RunPython implements Runnable {
	private String pyPath;
	private String pyCommand;
	private int moduleFlag; // kor : 1 / english : 2 / summary : 3 / saveas : 4
	
	SampleController sp = new SampleController();
	public RunPython() {
		this.pyPath = null;
		this.pyCommand = null; // fix current directory
	}
	public RunPython(String pyPath) {
		this.pyPath = pyPath;
		this.pyCommand = "python " + this.pyPath; // fix current directory
	}
	
	
	public RunPython(String pyPath, String args) {
		this.pyPath = pyPath;
		this.pyCommand = "python " + this.pyPath + " " + args; 
	}
	
	// 명진
	public RunPython(String pyPath, String args, int flag) {
		this.pyPath = pyPath;
		this.pyCommand = "python " + this.pyPath + " " + args; 
		this.moduleFlag = flag;
	}
	
	public RunPython(String pyPath, String args, int flag, String args1) {
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
}*/

public class SampleController {

	// Event Listener on JFXButton.onAction
	public static String text = "this text is static variable";
	int cnt = 0;
	@FXML
	private JFXCheckBox isKorean;
	@FXML
	private JFXTextField filePath, summaryText;
	@FXML
	private Pane step1, step2, step3, step4;
	@FXML
	public JFXButton btnStep1, btnStep2, btnStep3, btnStep4;
	@FXML
	public JFXButton btnOptimizasion, btnSaveAs, btnPreview, btnUploadFile, btnMakeText, btnSummarize;
	@FXML
	private JFXSpinner textSpinner, summarySpinner;
	public void showPreviewDialog() throws IOException {
		Stage dialog = new Stage(StageStyle.UTILITY);
		dialog.initModality(Modality.WINDOW_MODAL);
		dialog.setTitle("Preview");
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Preview.fxml"));

		Parent root1 = (Parent) fxmlLoader.load();

		Scene scene = new Scene(root1);
		dialog.setScene(scene);
		dialog.show();
	}
	
	@FXML
	public void initialize() {
		btnStep2.setDisable(true); 
		btnStep3.setDisable(true); 
		btnStep4.setDisable(true); 
		btnPreview.setDisable(true);
		btnSummarize.setDisable(true);
		textSpinner.setVisible(false);
		summarySpinner.setVisible(false);
		btnUploadFile.setOnAction((ActionEvent e) -> {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setInitialDirectory(new File("./"));
			fileChooser.setTitle("Open Music File");
			Stage dialog = new Stage();
			File f = fileChooser.showOpenDialog(dialog);
			if(f != null) {
				String aFilePath = f.getAbsolutePath();
				Main.file_path = aFilePath;
				filePath.setText(aFilePath);
				
				btnStep2.setDisable(false); 
				
			};
		});
		btnSaveAs.setOnAction((ActionEvent e) -> {
			Stage dialog = new Stage();

			DirectoryChooser directoryChooser = new DirectoryChooser();
			File selectedDirectory = directoryChooser.showDialog(dialog);
			String dirPath = selectedDirectory.getAbsolutePath();
			RunPython arp = new RunPython("./speech_to_text2.py", SampleController.text, 4, dirPath);
			
			
			Thread arpThread = new Thread(arp);
			arpThread.start();
			/*
//			
//			FileChooser fileChooser = new FileChooser();
//
//			// Set extension filter for text files
//			// convert txt to pdf or docx what user choose
//			FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
//			fileChooser.getExtensionFilters().add(extFilter);
//			Stage dialog = new Stage();
//
//			// Show save file dialog
//			File file = fileChooser.showSaveDialog(dialog);
//			String sampleText = "soicem"; // need to change our text which converted from audio to text
//			if (file != null) {
//				saveTextToFile(sampleText, file);
//			}
			*/
			
			
			
			
		});

		btnMakeText.setOnAction((ActionEvent e) -> {
			boolean isSelected = isKorean.isSelected();
			textSpinner.setVisible(true);
			if(isSelected) {
				RunPython arp = new RunPython("./speech_to_text_ko.py", Main.file_path, 1);
				Thread arpThread = new Thread(arp);
				arpThread.start();
				/*try {
					arpThread.join();
					btnPreview.setDisable(false);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}*/
			}
			else { // english
				RunPython arp = new RunPython("./speech_to_text.py", Main.file_path, 2);
				Thread arpThread = new Thread(arp);
				arpThread.start();
				
				
			}
		});
		
		btnSummarize.setOnAction((ActionEvent e) -> {
			
			boolean isSelected = isKorean.isSelected();
			summarySpinner.setVisible(true);
			if(!isSelected) {
				String summaryN = summaryText.getText();
				if(summaryN != "") {
					RunPython summary_t = new RunPython("./summaryText.py", "test.tmp" + " " + summaryN, 3);
					Thread summaryThread = new Thread(summary_t);
					summaryThread.start(); 
				}
			} else {
				System.out.println(text);
				btnPreview.setVisible(true);
			}
		});
		btnPreview.setOnAction((ActionEvent e) -> {
			Stage dialog = new Stage(StageStyle.UTILITY);
			dialog.initModality(Modality.WINDOW_MODAL);
			dialog.setTitle("Preview");
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Preview.fxml"));

			Parent root1;
			try {
				root1 = (Parent) fxmlLoader.load();
				Scene scene = new Scene(root1);
				dialog.setScene(scene);
				dialog.show();
				btnStep4.setDisable(false);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		

		// erase noise
		btnOptimizasion.setOnAction((ActionEvent e) -> {
			/*runPython rp = new runPython("./denoise/denoising.py", Main.file_path);
			Thread rpThread = new Thread(rp);
			rpThread.start();*/
			//rpThread.join();
			btnStep3.setDisable(false); 
		});
	}

	@FXML
	public void handleButtonAction(ActionEvent event) {
		// TODO Autogenerated
		// file upload
		if (event.getSource() == btnStep1) {
			step1.toFront();

		}

		// erase noise
		if (event.getSource() == btnStep2) {
			step2.toFront();
		}

		// preview
		if (event.getSource() == btnStep3) {
			
						
			step3.toFront();

		}

		// save
		if (event.getSource() == btnStep4) {
			
			step4.toFront();

		}
	}
	class RunPython implements Runnable {
		private String pyPath;
		private String pyCommand;
		private int moduleFlag; // kor : 1 / english : 2 / summary : 3 / saveas : 4
		
		SampleController sp = new SampleController();
		public RunPython() {
			this.pyPath = null;
			this.pyCommand = null; // fix current directory
		}
		public RunPython(String pyPath) {
			this.pyPath = pyPath;
			this.pyCommand = "python " + this.pyPath; // fix current directory
		}
		
		
		public RunPython(String pyPath, String args) {
			this.pyPath = pyPath;
			this.pyCommand = "python " + this.pyPath + " " + args; 
		}
		
		// 명진
		public RunPython(String pyPath, String args, int flag) {
			this.pyPath = pyPath;
			this.pyCommand = "python " + this.pyPath + " " + args; 
			this.moduleFlag = flag;
		}
		
		public RunPython(String pyPath, String args, int flag, String args1) {
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
							btnSummarize.setDisable(false);
							textSpinner.setVisible(false);
						} else if(this.moduleFlag == 2) { // english
							btnSummarize.setDisable(false);
							textSpinner.setVisible(false);
						} else if(this.moduleFlag == 3) { // summarization
							btnPreview.setDisable(false);
							summarySpinner.setVisible(false);
						} else if(this.moduleFlag == 4) { // save as
							
						}
					}
					SampleController.text += s;
					System.out.println(s);
				}
			} catch(IOException ie) {
				ie.printStackTrace();
				
			}
		}
	}
}
