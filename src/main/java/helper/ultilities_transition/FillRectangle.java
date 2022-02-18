package helper.ultilities_transition;

import javafx.animation.FillTransition;
import javafx.animation.ParallelTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class FillRectangle {
    public static ParallelTransition fillRectangle (Rectangle rectangle, String oldColor, String newColor) {
        FillTransition fillTransition = new FillTransition();

        fillTransition.setDuration(Duration.millis(500));

        fillTransition.setFromValue(Color.valueOf(oldColor));
        fillTransition.setToValue(Color.valueOf(newColor));

        fillTransition.setShape(rectangle);

        ParallelTransition parallelTransition = new ParallelTransition();
        parallelTransition.getChildren().addAll(fillTransition);

        return parallelTransition;
    }

    public static ParallelTransition fill2Rectangle (Rectangle rectangle_01, Rectangle rectangle_02, String oldColor, String newColor) {
        FillTransition f1 = new FillTransition();

        f1.setDuration(Duration.millis(500));

        f1.setFromValue(Color.valueOf(oldColor));
        f1.setToValue(Color.valueOf(newColor));

        f1.setShape(rectangle_01);

        FillTransition f2 = new FillTransition();

        f2.setDuration(Duration.millis(500));

        f2.setFromValue(Color.valueOf(oldColor));
        f2.setToValue(Color.valueOf(newColor));

        f2.setShape(rectangle_02);

        ParallelTransition parallelTransition = new ParallelTransition();
        parallelTransition.getChildren().addAll(f1, f2);

        return parallelTransition;
    }
}
