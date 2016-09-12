package com.hiq.hm;

import java.util.List;

/**
 * Created by Alan Tibbetts on 2016-09-12.
 */
public class RandomSortResult {

    private final List<Integer> inputList;
    private int numberOfSwaps;
    private List<Integer> sortedList;

    public RandomSortResult(final List<Integer> numberList) {
        this.inputList = numberList;
    }

    public List<Integer> getInputList() {
        return inputList;
    }

    public int getNumberOfSwaps() {
        return numberOfSwaps;
    }

    public void setNumberOfSwaps(int numberOfSwaps) {
        this.numberOfSwaps = numberOfSwaps;
    }

    public List<Integer> getSortedList() {
        return sortedList;
    }

    public void setSortedList(List<Integer> sortedList) {
        this.sortedList = sortedList;
    }

    public void incrementSwapCount() {
        numberOfSwaps++;
    }
}
