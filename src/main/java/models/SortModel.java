package models;

import helper.sort_transition.*;
import helper.sort_transition.InsertionSort;
import javafx.animation.SequentialTransition;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Random;

public class SortModel {
    private int szer = 0;
    private int space = 0;
    private int qtElements;
    private ArrayList arl = new ArrayList();

    public SortModel() {
    }

    public int getSpaceEl() {
        return space;
    }

    // Generate elements to sort; hight = hight of panel
    public ArrayList<StackPane> generateDataList(int qtElements, double hight) {
        this.qtElements = qtElements;
        ArrayList<StackPane> dataList = new ArrayList<>();

        int width = 600;// dataPane.widthProperty().intValue();
        szer = width / qtElements;

        if (szer > 1) {
            szer =  width / qtElements;
            space =  1 + (width / (10 * qtElements));
        }
        if (szer < 1) {
            szer = 1;
            space = 0;
        }
        int rectHeigth;
        arl.clear();
        generateArl();

        for (Object o : arl) {
            rectHeigth = (int) o;
            int d = (int) hight - 120 + (rectHeigth * 4);
            /*(rectHeigth * 10) + 50*/
            Rectangle rectangle = new Rectangle(szer, d);
            rectangle.setFill(Color.valueOf("#ADD8E6"));

            Text text = new Text(String.valueOf(rectHeigth));

            StackPane stackPane = new StackPane();
            stackPane.setPrefSize(rectangle.getWidth(), rectangle.getHeight());
            stackPane.setId(String.valueOf(rectHeigth));
            stackPane.getChildren().addAll(rectangle, text);

            dataList.add(stackPane);
        }
        return dataList;
    }

    public boolean checkArl(int chk) {
        for (Object o : arl) {
            if (o.equals(chk)) {
                return true;
            }
        }
        return false;
    }

    public void generateArl() {
        Random random = new Random();
        int check1;
        int s = qtElements;
        while (s > 0) {
            check1 = random.nextInt(qtElements);
            if (checkArl(check1)) {
                check1 = random.nextInt(qtElements);
            } else {
                arl.add(check1);
                s--;
            }
        }
    }

    public SequentialTransition bubbleSort(ArrayList<StackPane> dataList, double speed) {
        return BubbleSort.bubbleSort(dataList, speed, szer, space);
    }

    public SequentialTransition insertionSort(ArrayList<StackPane> dataList, double speed) {
        return InsertionSort.insertionSort(dataList, speed, szer, space);
    }

    public SequentialTransition selectionSort(ArrayList<StackPane> dataList, double speed) {
        return SelectionSort.selectionSort(dataList, speed, szer, space);
    }

    public SequentialTransition quickSort(ArrayList<StackPane> dataList, double speed) {
        return QuickSort.quickSort(dataList, speed, szer, space);
    }

    public SequentialTransition heapSort(ArrayList<StackPane> dataList, double speed) {
        return HeapSort.heapSort(dataList, speed, szer, space);
    }
}
