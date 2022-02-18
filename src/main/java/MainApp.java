import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static helper.UrlViewsConst.*;

public class MainApp extends Application{
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource(LOGIN_VIEW));
        Scene scene = new Scene(fxmlLoader.load(), 1009, 664);
        stage.setResizable(false);
        stage.setTitle("Sort Algorithm Visualization");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
