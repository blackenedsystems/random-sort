package com.hiq.hm;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Alan Tibbetts on 2016-09-12.
 */
public class RandomSortResult {

    private final LocalDateTime created;
    private final List<Integer> inputList;
    private int numberOfSwaps;
    private List<Integer> sortedList;

    public RandomSortResult(final LocalDateTime created, final List<Integer> numberList) {
        this.created = created;
        this.inputList = numberList;
    }

    public LocalDateTime getCreated() {
        return created;
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

    public static List<Integer> convertToList(final String numbers) {
        List<String> items = Arrays.asList(numbers.split("\\s*,\\s*"));
        List<Integer> integers = new ArrayList<>();
        for (String item : items) {
            integers.add(Integer.parseInt(item));
        }
        return integers;
    }

    public static String convertToCsv(final List<Integer> numbers) {
        return StringUtils.collectionToDelimitedString(numbers, ",");
    }
}
