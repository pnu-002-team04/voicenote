package application;

import com.jfoenix.controls.JFXTextArea;
import javafx.fxml.FXML;


public class PreviewController {
	@FXML
	private JFXTextArea preivewTextArea;
	
	@FXML private void initialize() {
		preivewTextArea.setText(SampleController.text.replace("EXIT", ""));
	}
	
	public void setText(String text) {
	}
	public PreviewController() {
		//preivewTextArea.setText(this.text);
		System.out.println("soicem");
		
	}
}
