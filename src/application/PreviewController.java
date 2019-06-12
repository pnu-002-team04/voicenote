package application;

import com.jfoenix.controls.JFXTextArea;
import javafx.fxml.FXML;


public class PreviewController {
	@FXML
	private JFXTextArea preivewTextArea;
	
	@FXML private void initialize() {
		SampleController.text = SampleController.text.replace("EXIT", "");
		preivewTextArea.setText(SampleController.text);
	}
	
	public void setText(String text) {
	}
	public PreviewController() {
		//preivewTextArea.setText(this.text);
		System.out.println("soicem");
		
	}
}
