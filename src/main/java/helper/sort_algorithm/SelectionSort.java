package helper.sort_algorithm;

public class SelectionSort {
    public static int [] sort (int []tab) {
        int tmp, indexMin = 0;
        for (int m = 0; m < tab.length - 1; m++) {
            indexMin = m;
            for (int n = m + 1; n < tab.length; n++) {
                if (tab[n] < tab[indexMin]) {
                    indexMin = n;
                }
            }
            if (indexMin != m) {
                tmp = tab[m];
                tab[m] = tab[indexMin];
                tab[indexMin] = tmp;
            }
        }
        return tab;
    }
}
