package helper.sort_algorithm;

public class QuickSort {
    private static void replace(int[] tab, int start, int end) {
        if (tab.length > 0 && start < tab.length && end < tab.length) {
            int tmp = tab[start];  //  replace
            tab[start] = tab[end];
            tab[end] = tmp;
        }
    }

    private static void quickSort(int[] tab, int beginPoint, int endPoint) {
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
                    replace(tab, pointLeftToRight, pointRightToLeft);
                }
            }
            replace(tab, beginPoint, pointRightToLeft);
            quickSort(tab, beginPoint, pointRightToLeft - 1); // sorts left part
            quickSort(tab, pointRightToLeft + 1, endPoint);   // orts right part
        }
    }
    public static int [] sort (int []tab) {
        if (tab.length > 0) {
            quickSort(tab, 0, tab.length - 1);
        }

        return tab;
    }
}
