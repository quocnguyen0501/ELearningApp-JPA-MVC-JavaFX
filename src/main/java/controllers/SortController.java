package controllers;

import helper.CommonFunc;
import javafx.animation.SequentialTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.chart.BarChart;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import models.SortModel;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SortController implements Initializable {

    @FXML
    SplitPane drawPane;
    @FXML
    BarChart barChart;
    @FXML
    TextArea textArea;
    @FXML
    Button bubbleSortBtn;
    @FXML
    Button generateBtn;
    @FXML
    Button insertionSortBtn;
    @FXML
    Button selectionSortBtn;
    @FXML
    Button quickSortBtn;
    @FXML
    Button heapSortBtn;
    @FXML
    Button notVisibleBtn;
    @FXML
    Separator separator1;
    @FXML
    Separator separator2;
    @FXML
    Separator separator3;
    @FXML
    Separator separator4;
    @FXML
    Separator separator5;
    @FXML
    Separator separator6;
    @FXML
    AnchorPane dataPane;
    @FXML
    AnchorPane anchorPaneAlg;
    @FXML
    Slider speedSlider;
    @FXML
    private TextField textFieldSortInteger;

    private double speed = 400;
    private final SortModel sort = new SortModel();
    ArrayList<StackPane> dataList = new ArrayList<>();
    private int userNum;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        barChart.setVisible(false);
        bubbleSortBtn.setVisible(false);
        insertionSortBtn.setVisible(false);
        selectionSortBtn.setVisible(false);
        quickSortBtn.setVisible(false);
        heapSortBtn.setVisible(false);
        notVisibleBtn.setVisible(false);
        separator1.setVisible(false);
        separator2.setVisible(false);
        separator3.setVisible(false);
        separator4.setVisible(false);
        separator5.setVisible(false);
        separator6.setVisible(false);
    }

    private void setVisibleSortButtons(boolean visible) {
        bubbleSortBtn.setVisible(visible);
        insertionSortBtn.setVisible(visible);
        selectionSortBtn.setVisible(visible);
        quickSortBtn.setVisible(visible);
        heapSortBtn.setVisible(visible);
    }

    private void disableSpeedSlider() {
        speed = speedSlider.valueProperty().doubleValue();
        speedSlider.setDisable(true);
    }

    private void enableSpeedSlider() {
        speed = speedSlider.valueProperty().doubleValue();
        speedSlider.setDisable(false);
    }

    private int userSortNodeNumber() { // method return number of data to generate, which user entered in text field
        String s = textFieldSortInteger.getText();
        return Integer.parseInt(s);
    }

    private boolean validateUserNumber() { // method validate if user typed a valid number (range, no letter etc)
        //this.userNum = userNum;
        if ((textFieldSortInteger.getText() == null)) {
            return false;
        }
        try {
            userNum = userSortNodeNumber();
        } catch (Exception ex) {
            return false;
        }

        return userNum > 0 && userNum <= 46;
    }
    @FXML
    private void handleButtonGenerate(ActionEvent event) {
        if (validateUserNumber()) {
            double d = dataPane.heightProperty().doubleValue();
            //this.userNum = userNum;

            dataList = sort.generateDataList(userNum, d - 30);

            HBox hBox = new HBox(sort.getSpaceEl());

            dataPane.getChildren().clear();
            dataPane.getChildren().add(hBox);
            hBox.prefWidthProperty().bind(dataPane.widthProperty());
            hBox.prefHeightProperty().bind(dataPane.heightProperty());

            hBox.setPadding(new Insets(15, 12, 15, 12));
            hBox.setSpacing(sort.getSpaceEl());
            hBox.setStyle("-fx-background-color: #336699;");

            hBox.setAlignment(Pos.BASELINE_LEFT);

            hBox.getChildren().addAll(dataList);

            setVisibleSortButtons(true);
        }
    }

    @FXML
    private void bubbleSorting(ActionEvent event) {
        disableSpeedSlider();
        SequentialTransition st = sort.bubbleSort(dataList, speed);
        st.onFinishedProperty().set(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                enableSpeedSlider();
            }
        });
        st.play();
    }

    @FXML
    private void insertionSorting(ActionEvent event) {
        disableSpeedSlider();
        SequentialTransition st = sort.insertionSort(dataList, speed);
        st.onFinishedProperty().set(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                enableSpeedSlider();
            }
        });
        st.play();
    }

    @FXML
    private void selectionSorting(ActionEvent event) {
        disableSpeedSlider();
        SequentialTransition st = sort.selectionSort(dataList, speed);
        st.onFinishedProperty().set(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                enableSpeedSlider();
            }
        });
        st.play();
    }

    @FXML
    private void quickSortingData(ActionEvent event) {
        disableSpeedSlider();
        SequentialTransition st = sort.quickSort(dataList, speed);
        st.onFinishedProperty().set(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                enableSpeedSlider();
            }
        });
        st.play();
    }

    @FXML
    private void heapSortingData(ActionEvent event) {
        disableSpeedSlider();
        SequentialTransition st = sort.heapSort(dataList, speed);
        int[] tab = CommonFunc.dataListToArray(dataList);
        st.onFinishedProperty().set(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                enableSpeedSlider();
            }
        });
        st.play();
    }
}

