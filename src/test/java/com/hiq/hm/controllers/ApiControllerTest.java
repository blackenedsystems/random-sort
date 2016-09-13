package com.hiq.hm.controllers;

import com.hiq.hm.db.RandomSortDao;
import com.hiq.hm.model.RandomSortResult;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Alan Tibbetts on 2016-09-13.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ApiControllerTest {
    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private RandomSortDao randomSortDao;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void list_ok() throws Exception {
        List<Integer> unsortedList = Arrays.asList(4, 2, 3);
        List<Integer> sortedList = Arrays.asList(2, 3, 4);

        randomSortDao.save(createRandomSortResult(unsortedList, sortedList));
        randomSortDao.save(createRandomSortResult(unsortedList, sortedList));

        this.mockMvc.perform(get("/api/list?max=10").accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void list_limitReturnSize_ok() throws Exception {
        List<Integer> unsortedList = Arrays.asList(4, 2, 3);
        List<Integer> sortedList = Arrays.asList(2, 3, 4);

        randomSortDao.save(createRandomSortResult(unsortedList, sortedList));
        randomSortDao.save(createRandomSortResult(unsortedList, sortedList));

        this.mockMvc.perform(get("/api/list?max=1").accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    public void sort_ok() throws Exception {
        this.mockMvc.perform(get("/api/list?max=1").accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$", hasSize(0)));

        this.mockMvc.perform(post("/api/sort")
                .content("2,4,1")
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isOk());

        this.mockMvc.perform(get("/api/list?max=1").accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$", hasSize(1)));
    }

    private RandomSortResult createRandomSortResult(List<Integer> unsortedList, List<Integer> sortedList) throws InterruptedException {
        Thread.sleep(5);  // stops duplicate keys
        RandomSortResult randomSortResult = new RandomSortResult(LocalDateTime.now(), unsortedList);
        randomSortResult.setSortedList(sortedList);
        randomSortResult.setNumberOfSwaps(20);
        return randomSortResult;
    }
}
