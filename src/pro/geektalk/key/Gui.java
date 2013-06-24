package pro.geektalk.key;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class Gui extends Application {

	private TextField tf;

	@Override
	public void start(Stage stage) throws Exception {

		final BorderPane layout = new BorderPane();
		// layout.setPadding(new Insets(20, 0, 20, 20));

		final FlowPane pane = new FlowPane();
		pane.setHgap(5);

		layout.setTop(pane);

		tf = new TextField();
		tf.setStyle("-fx-border:5px solid black;");
		tf.setPromptText("# keys at once");
		tf.setTooltip(new Tooltip(tf.getPromptText()));
		tf.setText(tf.getPromptText());
		pane.getChildren().add(tf);

		Button btn = new Button("Start");
		btn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				start();

			}

		});
		pane.getChildren().add(btn);

		Scene scene = new Scene(layout, 200, 15);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
	}

	private void start() {

	}

}
