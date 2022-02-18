package helper.ultilities_transition;

import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Collections;

public class SwapElements {
    public static ParallelTransition swapElements(StackPane sp1, StackPane sp2, ArrayList<StackPane> list, double speed, int szer, int space) {
        TranslateTransition t1 = new TranslateTransition();
        TranslateTransition t2 = new TranslateTransition();
        t1.setDuration(Duration.millis(speed));
        t2.setDuration(Duration.millis(speed));
        ParallelTransition pt = new ParallelTransition();
        t1.setNode(sp1);
        t2.setNode(sp2);
        t1.setByX(szer + space);
        t2.setByX(-1 * (szer + space));
        pt.getChildren().addAll(t1, t2);
        Collections.swap(list, list.indexOf(sp1), list.indexOf(sp2));
        return pt;
    }
}
