package com.hiq.hm.db;

import com.hiq.hm.db.RandomSortDao;
import com.hiq.hm.model.RandomSortResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Alan Tibbetts on 2016-09-13.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class RandomSortDaoTest {

    @Autowired
    private RandomSortDao randomSortDao;

    @Test
    public void save_ok() {
        List<Integer> unsortedList = Arrays.asList(4, 2, 3);
        List<Integer> sortedList = Arrays.asList(2, 3, 4);

        RandomSortResult randomSortResult = new RandomSortResult(LocalDateTime.now(), unsortedList);
        randomSortResult.setSortedList(sortedList);
        randomSortResult.setNumberOfSwaps(20);

        randomSortDao.save(randomSortResult);

        List<RandomSortResult> randomSortResults = randomSortDao.loadResults(10);
        assertNotNull(randomSortResults);
        assertEquals(1, randomSortResults.size());

        RandomSortResult retrievedResult = randomSortResults.get(0);
        assertEquals(unsortedList, retrievedResult.getInputList());
        assertEquals(sortedList, retrievedResult.getSortedList());
        assertEquals(20, retrievedResult.getNumberOfSwaps());
    }
}
