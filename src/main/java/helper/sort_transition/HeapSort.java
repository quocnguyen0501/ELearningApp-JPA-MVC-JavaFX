package helper.sort_transition;

import helper.CommonFunc;
import javafx.animation.SequentialTransition;
import javafx.scene.layout.StackPane;

import java.util.ArrayList;

public class HeapSort {
    private static int idx;

    public static SequentialTransition heapSort(ArrayList<StackPane> dataList, double speed, int szer, int space) {
        int[] tab = CommonFunc.dataListToArray(dataList);
        SequentialTransition sqt = new SequentialTransition();

        createHeap(tab, sqt, dataList, speed, szer, space);

        for (int i = idx; i > 0; i--) {

            //swap tab[0], tab[i]:
            CommonFunc.replace(tab, 0, i, dataList, sqt, speed, szer, space);

            idx = idx - 1;
            maxHeap(tab, 0, sqt, dataList, speed, szer, space);
        }
        return sqt;
    }


    private static void createHeap(int[] data, SequentialTransition sqt,
                            ArrayList<StackPane> dataList, double speed, int szer, int space) {
        idx = data.length - 1;
        for (int i = idx / 2; i >= 0; i--) {
            maxHeap(data, i, sqt, dataList, speed, szer, space);
        }
    }

    private static void maxHeap(int[] data, int i, SequentialTransition sqt,
                         ArrayList<StackPane> dataList, double speed, int szer, int space) {
        int left = 2 * i;
        int right = 2 * i + 1;
        int largest;
        if (left <= idx && data[left] > data[i]) {
            largest = left;
        } else {
            largest = i;
        }

        if (right <= idx && data[right] > data[largest]) {
            largest = right;
        }
        if (largest != i) {
            //swap tab[i], tab[largest]:
            CommonFunc.replace(data, i, largest, dataList, sqt, speed, szer, space);
            maxHeap(data, largest, sqt, dataList, speed, szer, space);
        }
    }
}
