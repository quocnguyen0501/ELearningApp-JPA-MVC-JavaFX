package helper.sort_transition;

import helper.ColorConst;
import helper.CommonFunc;
import helper.ultilities_transition.FillRectangle;
import helper.ultilities_transition.SwapElements;
import javafx.animation.SequentialTransition;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class SelectionSort {
    public static SequentialTransition selectionSort(ArrayList<StackPane> dataList, double speed, int szer, int space) {
        int[] tab = CommonFunc.dataListToArray(dataList);
        SequentialTransition sqt = new SequentialTransition();

        int tmp, indexMin;
        for (int m = 0; m < tab.length - 1; m++) {
            indexMin = m;
            Rectangle r1 = (Rectangle) dataList.get(indexMin).getChildren().get(0);
            sqt.getChildren().add(FillRectangle.fillRectangle(r1, ColorConst.COLOR_RECT_BLUE, ColorConst.COLOR_RECT_RED));
            for (int n = m + 1; n < tab.length; n++) {
                Rectangle r2 = (Rectangle) dataList.get(n).getChildren().get(0);
                sqt.getChildren().add(FillRectangle.fillRectangle(r2, ColorConst.COLOR_RECT_BLUE, ColorConst.COLOR_RECT_GREEN));
                if (tab[n] < tab[indexMin]) {
                    sqt.getChildren().add(FillRectangle.fillRectangle(r1, ColorConst.COLOR_RECT_RED, ColorConst.COLOR_RECT_BLUE));
                    sqt.getChildren().add(FillRectangle.fillRectangle(r2, ColorConst.COLOR_RECT_GREEN, ColorConst.COLOR_RECT_RED));

                    r1 = (Rectangle) dataList.get(n).getChildren().get(0);
                    indexMin = n;
                } else {
                    sqt.getChildren().add(FillRectangle.fillRectangle(r2, ColorConst.COLOR_RECT_GREEN, ColorConst.COLOR_RECT_BLUE));
                }
            }
            if (indexMin != m) {
                tmp = tab[m];
                tab[m] = tab[indexMin];
                tab[indexMin] = tmp;

                int orygSpace = space;
                int orygSzer = szer;
                space = space * (indexMin - m);
                szer = szer * (indexMin - m);

                Rectangle r2 = (Rectangle) dataList.get(m).getChildren().get(0);
                sqt.getChildren().add(FillRectangle.fillRectangle(r2, ColorConst.COLOR_RECT_BLUE, ColorConst.COLOR_RECT_RED));

                sqt.getChildren().add(SwapElements.swapElements(dataList.get(m), dataList.get(indexMin), dataList, speed, szer, space));

                sqt.getChildren().add(FillRectangle.fillRectangle(r1, ColorConst.COLOR_RECT_RED, ColorConst.COLOR_RECT_YELLOW));
                sqt.getChildren().add(FillRectangle.fillRectangle(r2, ColorConst.COLOR_RECT_RED, ColorConst.COLOR_RECT_BLUE));

                space = orygSpace;
                szer = orygSzer;
            } else {
                sqt.getChildren().add(FillRectangle.fillRectangle(r1, ColorConst.COLOR_RECT_RED, ColorConst.COLOR_RECT_YELLOW));
            }
        }
        Rectangle rLast = (Rectangle) dataList.get(tab.length - 1).getChildren().get(0);
        sqt.getChildren().add(FillRectangle.fillRectangle(rLast, ColorConst.COLOR_RECT_BLUE, ColorConst.COLOR_RECT_YELLOW));
        return sqt;
    }
}
