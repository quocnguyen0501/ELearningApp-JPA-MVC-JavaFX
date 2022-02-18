package helper.sort_transition;

import helper.ColorConst;
import helper.ultilities_transition.FillRectangle;
import helper.ultilities_transition.SwapElements;
import javafx.animation.SequentialTransition;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

import static helper.CommonFunc.dataListToArray;

public class BubbleSort {
    public static SequentialTransition bubbleSort(ArrayList<StackPane> dataList, double speed, int szer, int space) {
        int[] tab = dataListToArray(dataList);
        SequentialTransition sqt = new SequentialTransition();
        int tmp;
        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab.length - i - 1; j++) {
                Rectangle r1 = (Rectangle) dataList.get(j).getChildren().get(0);
                Rectangle r2 = (Rectangle) dataList.get(j + 1).getChildren().get(0);

                sqt.getChildren().add(FillRectangle.fill2Rectangle(r1, r2, ColorConst.COLOR_RECT_BLUE, ColorConst.COLOR_RECT_YELLOW));

                if (tab[j] > tab[j + 1]) {
                    tmp = tab[j + 1];
                    tab[j + 1] = tab[j];
                    tab[j] = tmp;
                    sqt.getChildren().add(SwapElements.swapElements(dataList.get(j), dataList.get(j + 1), dataList, speed, szer, space));
                }
                sqt.getChildren().add(FillRectangle.fill2Rectangle(r1, r2, ColorConst.COLOR_RECT_YELLOW, ColorConst.COLOR_RECT_BLUE));
            }
            Rectangle rectSorted = (Rectangle) dataList.get(tab.length - 1 - i).getChildren().get(0);
            sqt.getChildren().add(FillRectangle.fillRectangle(rectSorted, ColorConst.COLOR_RECT_BLUE, ColorConst.COLOR_RECT_RED));
        }
        return sqt;
    }
}
