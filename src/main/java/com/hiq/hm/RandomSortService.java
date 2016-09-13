package com.hiq.hm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Alan Tibbetts on 2016-09-12.
 */
@Service
public class RandomSortService {

    @Autowired
    private RandomSort randomSort;

    @Autowired
    private RandomSortDao randomSortDao;

    @Transactional
    public RandomSortResult sort(final String numbers) {
        List<Integer> integers = RandomSortResult.convertToList(numbers);
        RandomSortResult randomSortResult = randomSort.sort(integers);
        randomSortDao.save(randomSortResult);
        return randomSortResult;
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<RandomSortResult> loadResults(final int maxEntries) {
        return randomSortDao.loadResults(maxEntries);
    }
}
