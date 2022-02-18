package controllers;

import animations.Shaker;
import com.jfoenix.controls.JFXButton;
import helper.URIConst;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import models.UserLogin;
import models.UserModel;
import models.UserPostModel;
import services.IUserPostService;
import services.impl.UserPostService;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

import static helper.UrlViewsConst.STATUS_VIEW;

public class PostStatusController implements Initializable {
    @FXML
    private JFXButton btnOpenImage;

    @FXML
    private JFXButton btnPostStatus;

    @FXML
    private TextField contentStatus;

    @FXML
    private ImageView image;

    @FXML
    private ImageView imgAvatar;

    @FXML
    private VBox postContainer;

    @FXML
    private VBox statusContainer;

    private static String URI;
    private final UserPostService userPostService = new UserPostService();

    @FXML
    void PostStatusHandler(ActionEvent event) {
        Image imageToBeSaved = this.image.getImage();

        if (this.contentStatus.getText().equals("") && imageToBeSaved == null) {
            Shaker shaker = new Shaker(this.contentStatus);
            shaker.shake();
        }
        else{
            String content = null;
            String img = null;
            if (!this.contentStatus.getText().equals("")) {
                content = this.contentStatus.getText();
            }
            if (imageToBeSaved != null && URI != null) {
                String pathSave = this.getURI();
                this.saveImage(imageToBeSaved, pathSave);
                img = this.getURLImgDisplay();
                URIConst.tempImgs.add(URI);
                URI = null;
            }

            UserPostModel post = new UserPostModel(content, img, new Timestamp(System.currentTimeMillis()),
                                                    UserLogin.getINSTANCE().getUserModel());
            userPostService.insert(post);

            //reset create cmt
            this.contentStatus.setText("");
            this.image.setVisible(false);
            this.image.setManaged(false);

            this.addPost(post);
        }
    }

    @FXML
    void chooseImageHandler(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose an image");
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg");
        fileChooser.getExtensionFilters().add(filter);
        File file = fileChooser.showOpenDialog(new Stage());

        if (file != null) {
            URI = file.toURI().toString();
            Image image = new Image(URI);
            this.image.setImage(image);
            this.image.setVisible(true);
            this.image.setManaged(true);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.setUserAvatar();
        this.image.setVisible(false);
        this.image.setManaged(false);

        this.showAllPost();
    }

    //===================== SAVE IMAGE ===========================
    private void saveImage (Image image, String pathSave) {
        File file = new File(pathSave);
        try {
            String typeImage = this.getTypeImage();
            ImageIO.write(SwingFXUtils.fromFXImage(image, null), typeImage, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getTypeImage () {
        int index = URI.lastIndexOf('.');
        return URI.substring(index + 1);
    }

    private String getURI () {
        int index = URI.lastIndexOf(File.separator);

        File fileCurrent = new File("");
        String currentDirectory = fileCurrent.getAbsolutePath();

        String folder = "/src/main/resources/assets/images/store_image/";
        folder = folder.replace('/', File.separator.charAt(0));

        return currentDirectory + folder + URI.substring(index + 1);
    }

    private String getURLImgDisplay () {
        int index = URI.lastIndexOf(File.separator);

        String folder = "/assets/images/store_image/";
        folder = folder.replace('/', File.separator.charAt(0));

        return folder + URI.substring(index + 1);
    }

    private void setUserAvatar () {
        UserModel user = UserLogin.getINSTANCE().getUserModel();

        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(user.getAvatar())));
        this.imgAvatar.setImage(image);
        Circle clip = new Circle();
        clip.setCenterX(25.0f);
        clip.setCenterY(25.0f);
        clip.setRadius(25.0f);
        this.imgAvatar.setClip(clip);
    }

    private List<UserPostModel> getListStatus () {
        IUserPostService userPostService = new UserPostService();
        return userPostService.selectAll();
    }

    private void showAllPost () {
        this.statusContainer.getChildren().removeAll(this.statusContainer.getChildren());

        if (userPostService.selectNumberOfAllPost() > 0) {
            List<UserPostModel> userPostModels = new ArrayList<>(getListStatus());
            for (UserPostModel post : userPostModels) {
                this.addPost(post);
            }
        }
    }

    private void addPost(UserPostModel post) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource(STATUS_VIEW));

            VBox postBox = fxmlLoader.load();

            StatusController statusController = fxmlLoader.getController();
            statusController.setData(post);

            this.statusContainer.getChildren().add(postBox);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
