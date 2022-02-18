package helper;
import helper.ultilities_transition.SwapElements;
import javafx.animation.SequentialTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

import static helper.UrlViewsConst.SIGNUP_VIEW;

public class CommonFunc {
    public static int[] dataListToArray(ArrayList<StackPane> dataList) {
        int[] tab = new int[dataList.size()];
        for (int i = 0; i < tab.length; i++) {
            tab[i] = Integer.parseInt(dataList.get(i).getId());
        }
        return tab;
    }

    public static void replace(int[] tab, int start, int end,
                               ArrayList<StackPane> dataList, SequentialTransition sqt, double speed, int szer, int space) {
        if (tab.length > 0 && start < tab.length && end < tab.length) {
            int tmp = tab[start];  //  replace
            tab[start] = tab[end];
            tab[end] = tmp;

            int orygSpace = space;
            int orygSzer = szer;
            space = space * (end - start);
            szer = szer * (end - start);
            sqt.getChildren().add(SwapElements.swapElements(dataList.get(start), dataList.get(end), dataList, speed, szer, space));
            space = orygSpace;
            szer = orygSzer;
        }
    }

    public static boolean isNumeric (String str) {
        if (str == null) {
            return false;
        }

        try {
            int num = Integer.parseInt(str.trim());
        } catch (NumberFormatException nfe) {
            return false;
        }

        return true;
    }
}
