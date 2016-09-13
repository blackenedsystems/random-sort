package com.hiq.hm.model;

import com.hiq.hm.model.RandomSort;
import com.hiq.hm.model.RandomSortResult;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertTrue;

/**
 * Created by Alan Tibbetts on 2016-09-12.
 */
public class RandomSortTest {

    private Random random = new Random(System.currentTimeMillis());

    @Test
    public void sort_fixedList_ok() {
        List<Integer> numberList = Arrays.asList(45, 3, 12);

        RandomSort randomSort = new RandomSort();
        RandomSortResult randomSortResult = randomSort.sort(numberList);

        assertTrue(randomSortResult.getNumberOfSwaps() > 0);
        checkListIsSorted(randomSortResult);
    }

    @Test
    public void sort_alreadySortedList_ok() {
        List<Integer> sortedList = Arrays.asList(5,6,7);

        RandomSort randomSort = new RandomSort();
        RandomSortResult randomSortResult = randomSort.sort(sortedList);

        assertTrue(randomSortResult.getNumberOfSwaps() == 0);
        checkListIsSorted(randomSortResult);
    }

    @Test
    public void sort_emptyList_ok() {
        RandomSort randomSort = new RandomSort();
        RandomSortResult randomSortResult = randomSort.sort(new ArrayList<>());

        assertTrue(randomSortResult.getNumberOfSwaps() == 0);
        assertTrue(randomSortResult.getSortedList().isEmpty());
    }

    @Test(expected = RuntimeException.class)
    public void sort_nullList() {
        RandomSort randomSort = new RandomSort();
        RandomSortResult randomSortResult = randomSort.sort(null);

    }

    @Test
    public void sort_randomList_ok() {
        List<Integer> randomNumberList = createList(10);

        RandomSort randomSort = new RandomSort();
        RandomSortResult randomSortResult = randomSort.sort(randomNumberList);

        checkListIsSorted(randomSortResult);
    }

    @Ignore
    @Test
    public void sort_longerRandomList_ok() {
        // A list of 20 numbers takes a stupidly long time to sort!!!
        List<Integer> randomNumberList = createList(20);

        RandomSort randomSort = new RandomSort();
        RandomSortResult randomSortResult = randomSort.sort(randomNumberList);

        checkListIsSorted(randomSortResult);
    }

    private List<Integer> createList(int numberOfNumbers) {
        List<Integer> randomNumberList = new ArrayList<>(numberOfNumbers);
        for (int i=0; i<numberOfNumbers; i++) {
            randomNumberList.add(random.nextInt(100));
        }
        return randomNumberList;
    }

    private void checkListIsSorted(RandomSortResult randomSortResult) {
        int min = randomSortResult.getSortedList().get(0);
        for (Integer integer : randomSortResult.getSortedList()) {
            assertTrue("List of numbers not sorted correctly", integer >= min);
            min = integer;
        }
    }

}
