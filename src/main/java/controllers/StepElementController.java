package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import models.StepModel;
import services.impl.StepService;

import java.sql.Date;

import static helper.ImgConst.COMPLETE;
import static helper.ImgConst.UNCOMPLETED;

public class StepElementController {
    @FXML
    private Button btnComplete;

    @FXML
    private Button btnRemove;

    @FXML
    private Label stepDescription;

    @FXML
    private Label idStep;

    @FXML
    private ImageView imgState;

    @FXML
    void handleBtnCompleteClicked(ActionEvent event) {
        StepService stepService = new StepService();
        long idStep = Long.parseLong(this.idStep.getText());
        StepModel step = stepService.selectById(idStep);

        int countStepUpdate = 0;

        if (step.getState().equals("not complete")) {
            stepService.updateDateComplete(idStep, new Date(System.currentTimeMillis()));
            Image image = new Image(COMPLETE);
            imgState.setImage(image);
            countStepUpdate += 1;
        }
        if (step.getState().equals("complete")) {
            if (countStepUpdate == 0) {
                stepService.updateUnCompleted(idStep);
                Image image = new Image(UNCOMPLETED);
                imgState.setImage(image);
            }
        }
    }

    @FXML
    void handleBtnRemoveClicked(ActionEvent event) {
        StepService stepService = new StepService();
        long idStep = Long.parseLong(this.idStep.getText());
        stepService.delete(stepService.selectById(idStep));
    }

    public void setData (StepModel step) {
        idStep.setText(step.getId().toString());
        this.stepDescription.setText(step.getDescription());

        String urlImageState = "";
        if (step.getState().equals("complete")) {
            urlImageState = COMPLETE;
        }
        if (step.getState().equals("not complete")) {
            urlImageState = UNCOMPLETED;
        }
        Image image = new Image(urlImageState);
        imgState.setImage(image);
    }
}
