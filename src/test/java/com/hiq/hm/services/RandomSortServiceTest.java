package com.hiq.hm.services;

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
import static org.junit.Assert.assertTrue;

/**
 * Created by Alan Tibbetts on 2016-09-13.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class RandomSortServiceTest {

    @Autowired
    private RandomSortService randomSortService;

    @Autowired
    private RandomSortDao randomSortDao;

    @Test
    public void sort_ok() {
        RandomSortResult sortResult = randomSortService.sort("1,3,2");
        assertNotNull(sortResult);
        assertEquals("1,2,3", RandomSortResult.convertToCsv(sortResult.getSortedList()));
        assertTrue(sortResult.getNumberOfSwaps() > 0);
    }

    @Test
    public void loadResults_ok() {
        List<Integer> unsortedList = Arrays.asList(4, 2, 3);
        List<Integer> sortedList = Arrays.asList(2, 3, 4);

        RandomSortResult randomSortResult = new RandomSortResult(LocalDateTime.now(), unsortedList);
        randomSortResult.setSortedList(sortedList);
        randomSortResult.setNumberOfSwaps(20);

        randomSortDao.save(randomSortResult);

        List<RandomSortResult> sortResultList = randomSortService.loadResults(10);
        assertNotNull(sortResultList);
        assertEquals(1, sortResultList.size());
    }

}
