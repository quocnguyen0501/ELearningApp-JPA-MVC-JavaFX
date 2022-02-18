package helper.sort_transition;

import helper.CommonFunc;
import javafx.animation.SequentialTransition;
import javafx.scene.layout.StackPane;

import java.util.ArrayList;

public class QuickSort {
    public static SequentialTransition quickSort(ArrayList<StackPane> dataList, double speed, int szer, int space) {
        int[] tab = CommonFunc.dataListToArray(dataList);
        SequentialTransition sqt = new SequentialTransition();

        if (tab.length > 0) {
            quickSort(tab, 0, tab.length - 1, dataList, sqt, speed, szer, space);
        }
        return sqt;
    }

    private static void quickSort(int[] tab, int beginPoint, int endPoint,
                                  ArrayList<StackPane> dataList, SequentialTransition sqt, double speed, int szer, int space) {
        int pointLeftToRight = beginPoint;
        int pointRightToLeft = endPoint;

        if (endPoint - beginPoint >= 1) {
            //one element is not sorted
            int start = tab[beginPoint];

            while (pointRightToLeft > pointLeftToRight) {
                while (tab[pointLeftToRight] <= start &&
                        pointLeftToRight <= endPoint &&
                        pointRightToLeft > pointLeftToRight) {
                    pointLeftToRight++;  // iterate from left to right
                }
                while (tab[pointRightToLeft] > start &&
                        pointRightToLeft >= beginPoint &&
                        pointRightToLeft >= pointLeftToRight) {
                    pointRightToLeft--;  // iterate from right to left
                }
                if (pointRightToLeft > pointLeftToRight) {
                    CommonFunc.replace(tab, pointLeftToRight, pointRightToLeft, dataList, sqt, speed, szer, space);
                }
            }
            CommonFunc.replace(tab, beginPoint, pointRightToLeft, dataList, sqt, speed, szer, space);

            quickSort(tab, beginPoint, pointRightToLeft - 1, dataList, sqt, speed, szer, space); // sorts left part
            quickSort(tab, pointRightToLeft + 1, endPoint, dataList, sqt, speed, szer, space);   // orts right part
        }
    }
}
