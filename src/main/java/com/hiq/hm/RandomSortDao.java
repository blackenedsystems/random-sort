package com.hiq.hm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alan Tibbetts on 2016-09-13.
 */
@Component
public class RandomSortDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<RandomSortResult> loadResults(final int maxEntries) {
        List<RandomSortResult> results = new ArrayList<>();

        jdbcTemplate.setMaxRows(maxEntries);
        jdbcTemplate.query(
                "SELECT created, unsorted_list, sorted_list, number_of_swaps " +
                        "FROM results " +
                        "ORDER BY created DESC ",
                resultSet -> {
                    Timestamp created = resultSet.getTimestamp("created");
                    List<Integer> unsortedList = RandomSortResult.convertToList(resultSet.getString("unsorted_list"));
                    List<Integer> sortedList = RandomSortResult.convertToList(resultSet.getString("sorted_list"));

                    RandomSortResult randomSortResult = new RandomSortResult(created.toLocalDateTime(), unsortedList);
                    randomSortResult.setSortedList(sortedList);
                    randomSortResult.setNumberOfSwaps(resultSet.getInt("number_of_swaps"));

                    results.add(randomSortResult);
                }
        );

        return results;
    }

    public void save(final RandomSortResult randomSortResult) {
        jdbcTemplate.update(
                "INSERT INTO results (created, unsorted_list, sorted_list, number_of_swaps) " +
                        "VALUES (?, ?, ?, ?)",
                preparedStatement -> {
                    preparedStatement.setTimestamp(1, Timestamp.valueOf(randomSortResult.getCreated()));
                    preparedStatement.setString(2, RandomSortResult.convertToCsv(randomSortResult.getInputList()));
                    preparedStatement.setString(3, RandomSortResult.convertToCsv(randomSortResult.getSortedList()));
                    preparedStatement.setInt(4, randomSortResult.getNumberOfSwaps());
                });
    }

}
