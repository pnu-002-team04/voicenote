package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.beans.EventHandler;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;
import application.Main;
import application.runPython;
import javafx.event.ActionEvent;

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
	private JFXButton btnStep1, btnStep2, btnStep3, btnStep4;
	@FXML
	private JFXButton btnOptimizasion, btnSaveAs, btnPreview, btnUploadFile;

	private void saveTextToFile(String content, File file) {
		try {
			PrintWriter writer;
			writer = new PrintWriter(file);
			writer.println(content);
			Main.file_path = content;
			writer.close();
		} catch (IOException ex) {
			// Logger.getLogger(SaveFileWithFileChooser.class.getName()).log(Level.SEVERE,
			// null, ex);
		}
	}

	@FXML
	public void initialize() {
		btnUploadFile.setOnAction((ActionEvent e) -> {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setInitialDirectory(new File("./"));
			fileChooser.setTitle("Open Music File");
			Stage dialog = new Stage();
			File f = fileChooser.showOpenDialog(dialog);
			
			String aFilePath = f.getAbsolutePath();
			Main.file_path = aFilePath;
			filePath.setText(aFilePath);
		});
		btnSaveAs.setOnAction((ActionEvent e) -> {
			Stage dialog = new Stage();

			DirectoryChooser directoryChooser = new DirectoryChooser();
			File selectedDirectory = directoryChooser.showDialog(dialog);
			String dirPath = selectedDirectory.getAbsolutePath();
			runPython arp = new runPython("./speech_to_text2.py", SampleController.text, 0, dirPath);
			
			
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

		btnPreview.setOnAction((ActionEvent e) -> {
			try {
				
				// erase noise 실행이 안되서 일단은 주석으로 해놓음 (주석 부분이 erase noise 실행 제대로 되면, 주석 해제하면 됨)
//				Main.denoise_file_path = "./Denoise_reconstruction.wav";
//				runPython arp = new runPython("./speech_to_text.py", Main.denoise_file_path, 0);
				
				/*runPython arp = new runPython("./speech_to_text.py", Main.file_path, 0);
				Thread arpThread = new Thread(arp);
				arpThread.start();
				arpThread.join();*/
				boolean isSelected = isKorean.isSelected();
				String summaryN = summaryText.getText();
				System.out.println(isSelected);
				if(isSelected) {
					runPython arp = new runPython("./speech_to_text_ko.py", Main.file_path, 0);
					Thread arpThread = new Thread(arp);
					arpThread.start();
					arpThread.join();
				}
				else {
						runPython arp = new runPython("./speech_to_text.py", Main.file_path, 0);
						Thread arpThread = new Thread(arp);
						arpThread.start();
						arpThread.join();
				}
				// for summarization
//				if(summaryN != "") {
//					runPython summary_t = new runPython("./summaryText.py", "test.tmp" + " " + summaryN, 0);
//					Thread summaryThread = new Thread(summary_t);
//					summaryThread.start();
//					summaryThread.join();
//				}
				
				Stage dialog = new Stage(StageStyle.UTILITY);
				dialog.initModality(Modality.WINDOW_MODAL);
				dialog.setTitle("Preview");
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Preview.fxml"));

				Parent root1 = (Parent) fxmlLoader.load();

				Scene scene = new Scene(root1);
				dialog.setScene(scene);
				dialog.show();

			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});

		// erase noise
		btnOptimizasion.setOnAction((ActionEvent e) -> {
			runPython rp = new runPython("./denoise/denoising.py", Main.file_path);
			Thread rpThread = new Thread(rp);
			rpThread.start();
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
}
