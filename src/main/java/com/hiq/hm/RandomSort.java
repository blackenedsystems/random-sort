package com.hiq.hm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Alan Tibbetts on 2016-09-12.
 */
@Component
public class RandomSort {

    private static final Logger LOGGER = LoggerFactory.getLogger(RandomSort.class);

    private Random random = new Random();

    /**
     * @param unsortedList the list of integers to be sorted
     * @return a RandomSortResult object containing the unsorted and sorted lists plus the number of swaps used to sort the list.
     */
    public RandomSortResult sort(final List<Integer> unsortedList) {
        if (unsortedList == null) {
            throw new RuntimeException("Cannot sort a null list!");
        }

        RandomSortResult randomSortResult = new RandomSortResult(LocalDateTime.now(), unsortedList);

        List<Integer> sortedList = new ArrayList<>(unsortedList);
        if (!sortedList.isEmpty()) {
            performSort(sortedList, randomSortResult);
        }

        LOGGER.debug("Sorted in {} swaps", randomSortResult.getNumberOfSwaps());

        randomSortResult.setSortedList(sortedList);
        return randomSortResult;
    }

    private void performSort(final List<Integer> unsortedList, final RandomSortResult randomSortResult) {
        while (!isListSorted(unsortedList)) {
            swapNumbers(unsortedList);
            randomSortResult.incrementSwapCount();
        }
    }

    private void swapNumbers(final List<Integer> randomNumberList) {
        int firstNumber = random.nextInt(randomNumberList.size());
        int secondNumber = random.nextInt(randomNumberList.size());
        while (secondNumber == firstNumber) {
            secondNumber = random.nextInt(randomNumberList.size());
        }

        Integer temp = randomNumberList.get(firstNumber);
        randomNumberList.set(firstNumber, randomNumberList.get(secondNumber));
        randomNumberList.set(secondNumber, temp);
    }

    private boolean isListSorted(final List<Integer> randomNumberList) {
        int min = randomNumberList.get(0);
        for (Integer number : randomNumberList) {
            if (number < min) {
                return false;
            }
            min = number;
        }
        return true;
    }
}
