package helper.sort_transition;

import helper.ColorConst;
import helper.CommonFunc;
import helper.ultilities_transition.FillRectangle;
import helper.ultilities_transition.SwapElements;
import javafx.animation.SequentialTransition;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class InsertionSort {
    public static SequentialTransition insertionSort(ArrayList<StackPane> dataList, double speed, int szer, int space) {
        int[] tab = CommonFunc.dataListToArray(dataList);
        SequentialTransition sqt = new SequentialTransition();

        int j = 0;
        boolean check = false;
        int tmp;

        for (int k = 1; k < tab.length; k++) {
            check = false;

            tmp = tab[k];
            Rectangle r1 = (Rectangle) dataList.get(k).getChildren().get(0);
            sqt.getChildren().add(FillRectangle.fillRectangle(r1, ColorConst.COLOR_RECT_BLUE, ColorConst.COLOR_RECT_RED));

            j = k - 1; //k = j + 1

            while (j >= 0 && tab[j] > tmp) {
                check = true;
                Rectangle r2 = (Rectangle) dataList.get(j).getChildren().get(0);
                sqt.getChildren().add(FillRectangle.fillRectangle(r2, ColorConst.COLOR_RECT_BLUE, ColorConst.COLOR_RECT_YELLOW));

                tab[j + 1] = tab[j];
                sqt.getChildren().add(FillRectangle.fillRectangle(r2, ColorConst.COLOR_RECT_YELLOW, ColorConst.COLOR_RECT_GREEN));
                sqt.getChildren().add(SwapElements.swapElements(dataList.get(j), dataList.get(j + 1), dataList, speed, szer, space));
                j--;
            }
            Rectangle r3 = (Rectangle) dataList.get(k - 1).getChildren().get(0);
            if (!check) {
                sqt.getChildren().add(FillRectangle.fillRectangle(r3, ColorConst.COLOR_RECT_BLUE, ColorConst.COLOR_RECT_YELLOW));
                sqt.getChildren().add(FillRectangle.fillRectangle(r3, ColorConst.COLOR_RECT_YELLOW, ColorConst.COLOR_RECT_GREEN));
            }

            sqt.getChildren().add(FillRectangle.fillRectangle(r1, ColorConst.COLOR_RECT_RED, ColorConst.COLOR_RECT_GREEN));
            tab[j + 1] = tmp;
        }
        return sqt;
    }
}
