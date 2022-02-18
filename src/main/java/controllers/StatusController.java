package controllers;

import animations.Shaker;
import com.jfoenix.controls.JFXButton;
import helper.Reactions;
import helper.URIConst;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import models.*;
import services.IPostCommentService;
import services.IPostReactionEmoticonService;
import services.IUserPostService;
import services.impl.PostCommentService;
import services.impl.PostReactionEmoticonService;
import services.impl.UserPostService;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

import static helper.UrlViewsConst.COMMENT_VIEW;
import static helper.UrlViewsConst.STATUS_VIEW;

public class StatusController implements Initializable {
    @FXML
    private Label caption;

    @FXML
    private ImageView imgAngry;

    @FXML
    private ImageView imgCare;

    @FXML
    private ImageView imgHaha;

    @FXML
    private ImageView imgLike;

    @FXML
    private ImageView imgLove;

    @FXML
    private ImageView imgPost;

    @FXML
    private ImageView imgProfile;

    @FXML
    private ImageView imgProfile1;

    @FXML
    private ImageView imgSad;

    @FXML
    private ImageView imgWow;

    @FXML
    private HBox likeContainer;

    @FXML
    private Label nbComments;

    @FXML
    private Label nbShares;

    @FXML
    private HBox reactionContainer;

    @FXML
    private Label time;

    @FXML
    private Label username;

    @FXML
    private Label idPost;

    @FXML
    private Label nbReaction;

    @FXML
    private ImageView imgReaction;

    @FXML
    private Label reactionName;

    @FXML
    private HBox commentContainer;

    @FXML
    private Line line;

    @FXML
    private JFXButton btnShowAllComments;

    @FXML
    private VBox commentUserContainer;

    @FXML
    private TextField contentComment;

    private final IUserPostService userPostService = new UserPostService();
    private final IPostCommentService commentService = new PostCommentService();

    IUserPostService postService = new UserPostService();
    IPostReactionEmoticonService postReactionEmoticonService = new PostReactionEmoticonService();
    private long startTime = 0;
    private Reactions currentReaction;
    private UserPostModel postModel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.line.setVisible(false);
        this.line.setManaged(false);
        this.commentContainer.setVisible(false);
        this.commentContainer.setManaged(false);
    }

    @FXML
    void handleBtnCommentClicked(ActionEvent event) {
        this.line.setVisible(true);
        this.line.setManaged(true);
        this.commentContainer.setVisible(true);
        this.commentContainer.setManaged(true);

        //Avatar
        Image image;
        image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(UserLogin.getINSTANCE().getUserModel().getAvatar())));
        this.imgProfile1.setImage(image);
        Circle clip = new Circle();
        clip.setCenterX(25.0f);
        clip.setCenterY(25.0f);
        clip.setRadius(25.0f);
        this.imgProfile1.setClip(clip);
    }

    @FXML
    void handleBtnShowAllCommentsClicked(ActionEvent event) {
        this.commentUserContainer.getChildren().removeAll(commentUserContainer.getChildren());

        UserPostModel post = userPostService.selectById(Long.valueOf(this.idPost.getText()));
        List<PostCommentModel> listComments = commentService.selectByPost(post);

        if (listComments != null) {
            for (PostCommentModel comment : listComments) {
                this.addComment(comment);
            }
        }
    }

    @FXML
    void handleBtnCreateCommentClicked(ActionEvent event) {
        String content = this.contentComment.getText();
        if (content.equals("")) {
            Shaker shaker = new Shaker(contentComment);
            shaker.shake();
        } else {
            UserModel user = UserLogin.getINSTANCE().getUserModel();
            UserPostModel post = userPostService.selectById(Long.valueOf(this.idPost.getText()));

            PostCommentModel comment = new PostCommentModel(content, new Timestamp(System.currentTimeMillis()), user, post);
            commentService.insert(comment);

            this.addComment(comment);
            this.contentComment.setText("");
        }
    }

    //Set time mouse click on emoji
    @FXML
    public void onLikeContainerPressed(MouseEvent mouseEvent) {
        startTime = System.currentTimeMillis();
    }

    @FXML
    public void onLikeContainerMouseReleased(MouseEvent mouseEvent) {
        //System.out.println("Start: " + startTime);
        long currentTime = System.currentTimeMillis();
        //System.out.println("Current: " + currentTime);
        if (currentTime - startTime > 0) {
            reactionContainer.setVisible(true);
        } else {
            if (reactionContainer.isVisible()) {
                reactionContainer.setVisible(false);
            }
            if (currentReaction == Reactions.NON) {
                setReaction(Reactions.LIKE);
            }
            else {
                setReaction(Reactions.NON);
            }
        }
    }

    @FXML
    public void onReactionImgPressed (MouseEvent mouseEvent) {
        UserModel user = UserLogin.getINSTANCE().getUserModel();
        UserPostModel post = postService.selectById(Long.valueOf(this.idPost.getText()));

        String icon;

        switch (((ImageView) mouseEvent.getSource()).getId()) {
            case "imgLove" -> {
                setReaction(Reactions.LOVE);
                icon = "Love";
            }
            case "imgCare" -> {
                setReaction(Reactions.CARE);
                icon = "Care";
            }
            case "imgHaha" -> {
                setReaction(Reactions.HAHA);
                icon = "Haha";
            }
            case "imgWow" -> {
                setReaction(Reactions.WOW);
                icon = "Wow";
            }
            case "imgSad" -> {
                setReaction(Reactions.SAD);
                icon = "Sad";
            }
            case "imgAngry" -> {
                setReaction(Reactions.ANGRY);
                icon = "Angry";
            }
            default -> {
                setReaction(Reactions.LIKE);
                icon = "Like";
            }
        }
        PostReactionEmoticonModel reactionEmoticonModel;

        //Check if was reaction
        if (postReactionEmoticonService.getNumberReactionEmoticonByUserAndPost(user, post) > 0) {
            reactionEmoticonModel = postReactionEmoticonService.selectByPostAndUser(user, post);
            reactionEmoticonModel.setReactionEmoticon(icon);
            postReactionEmoticonService.update(reactionEmoticonModel);
        } else {
           reactionEmoticonModel  = new PostReactionEmoticonModel(icon, user, post);
           postReactionEmoticonService.insert(reactionEmoticonModel);
        }
        reactionContainer.setVisible(false);
    }

    public void setReaction (Reactions reaction) {
        postModel = postService.selectById(Long.parseLong(this.idPost.getText()));

        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(reaction.getImgSrc())));
        this.imgReaction.setImage(image);
        this.reactionName.setText(reaction.getName());
        this.reactionName.setTextFill(Color.web(reaction.getColor()));

        if (currentReaction == Reactions.NON) {
            nbReaction.setText(String.valueOf(postModel.getNumberReactionEmoticon() + 1));
        }

        currentReaction = reaction;

        if (currentReaction == Reactions.NON) {
            nbReaction.setText(String.valueOf(postModel.getNumberReactionEmoticon() - 1));
        }
    }

    public void setData (UserPostModel post) {
        this.postModel = userPostService.selectById(post.getId());

        // set ID
        this.idPost.setText(postModel.getId().toString());

        //Avatar
        Image image;
        image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(postModel.getUser().getAvatar())));
        this.imgProfile.setImage(image);
        Circle clip = new Circle();
        clip.setCenterX(25.0f);
        clip.setCenterY(25.0f);
        clip.setRadius(25.0f);
        this.imgProfile.setClip(clip);

        this.username.setText(postModel.getUser().getFirstName() + " " + postModel.getUser().getSurname());
        time.setText(postModel.getDateCreate().toString());
        if (postModel.getWrittenText() != null && !postModel.getWrittenText().isEmpty()) {
            caption.setText(postModel.getWrittenText());
        }

        if (postModel.getImage() != null || !URIConst.tempImgs.isEmpty()) {
            Image imagePost;
            if (getClass().getResourceAsStream(postModel.getImage()) != null) {
                imagePost = new Image(Objects.requireNonNull(getClass().getResourceAsStream(postModel.getImage())));
            } else {
                String tempPath = this.handlePath(postModel.getImage());
                imagePost = new Image(Objects.requireNonNull(tempPath));
            }

            this.imgPost.setImage(imagePost);
        }
        else {
            imgPost.setVisible(false);
            imgPost.setManaged(false);
        }

        nbReaction.setText(String.valueOf(postModel.getNumberReactionEmoticon()));
        nbComments.setText(postModel.getNumberComment() + " comments");
        nbShares.setText(postModel.getNumberShare() + " shares");

        //Set reaction
        UserModel user = UserLogin.getINSTANCE().getUserModel();

        if (postReactionEmoticonService.getNumberReactionEmoticonByUserAndPost(user, post) > 0) {
            String reactionEmoticon = postReactionEmoticonService.getReactionEmoticonByUserAndPost(user, post);

            if (reactionEmoticon.equals("Love")) {
                currentReaction = Reactions.LOVE;
            }
            if (reactionEmoticon.equals("Care")) {
                currentReaction = Reactions.CARE;
            }
            if (reactionEmoticon.equals("Haha")) {
                currentReaction = Reactions.HAHA;
            }
            if (reactionEmoticon.equals("Wow")) {
                currentReaction = Reactions.WOW;
            }
            if (reactionEmoticon.equals("Sad")) {
                currentReaction = Reactions.SAD;
            }
            if (reactionEmoticon.equals("Angry")) {
                currentReaction = Reactions.ANGRY;
            }
            if (reactionEmoticon.equals("Like")) {
                currentReaction = Reactions.LIKE;
            }
        } else {
            currentReaction = Reactions.NON;
        }
        this.setReactionButton(currentReaction);
    }

    private void setReactionButton (Reactions reaction) {
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(reaction.getImgSrc())));
        this.imgReaction.setImage(image);
        this.reactionName.setText(reaction.getName());
        this.reactionName.setTextFill(Color.web(reaction.getColor()));
    }

    private UserPostModel getPost() {
        return postService.selectById(Long.valueOf(this.idPost.getText()));
    }

    private String handlePath(String dbPath) {
        for (String path: URIConst.tempImgs) {
            String nameFile = path.substring(path.lastIndexOf(File.separator) + 1);
            String dbImageFile = dbPath.substring(dbPath.lastIndexOf(File.separator) + 1);

            if (nameFile.equals(dbImageFile)) {
                return path;
            }
        }
        return null;
    }

    private void addComment (PostCommentModel comment) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource(COMMENT_VIEW));

            VBox postBox = fxmlLoader.load();

            CommentController commentController = fxmlLoader.getController();
            commentController.setData(comment);

            this.commentUserContainer.getChildren().add(postBox);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
