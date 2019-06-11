package application;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import javafx.application.Platform;
import javafx.fxml.FXML;


public class PreviewController {
	private String text;
	@FXML
	private JFXTextArea preivewTextArea;
	
	@FXML private void initialize() {
		preivewTextArea.setText(SampleController.text.replace("EXIT", ""));
	}
	
	public void setText(String text) {
		this.text = text;
	}
	public PreviewController() {
		//preivewTextArea.setText(this.text);
		System.out.println("soicem");
		
	}
}
