package helper.sort_algorithm;

public class InsertionSort {
    public static int [] sort (int []tab) {
        int j = 0;
        int tmp;
        for (int k = 1; k < tab.length; k++) {
            tmp = tab[k];
            j = k - 1;
            while (j >= 0 && tab[j] > tmp) {
                tab[j + 1] = tab[j];
                j--;
            }
            tab[j + 1] = tmp;
        }
        return tab;
    }
}
