package drooser;


import java.util.List;

import static java.util.Collections.swap;

public class QuickSort {

    /**
     * Sorts the given List in place
     * @param toSort the List to sort. Throws an error if its null
     */
    public void sort(List<Integer> toSort) {
        if (toSort == null) {
            throw new IllegalArgumentException();
        }
        else {
            quickSort(toSort, 0, toSort.size()-1);
        }
    }

    public void quickSort(List<Integer> toSort, int left, int right) {

        if (left >= right) {
            return;
        }

        int pivot = toSort.get((left + right) / 2);
        int index = partition(toSort, left, right, pivot);
        quickSort(toSort, left, index -1);
        quickSort(toSort, index, right);

    }

    public int partition(List<Integer> toSort, int left, int right, int pivot) {

        while(left <= right) {
            while(toSort.get(left) < pivot) {
                left++;
            }
            while(toSort.get(right) > pivot) {
                right--;
            }
            if (left <= right) {
                swap(toSort, left, right);
                left++;
                right--;
            }
        }
        return left;
    }
}
