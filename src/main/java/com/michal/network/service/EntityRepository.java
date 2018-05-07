package com.michal.network.service;

import com.michal.network.domain.Node;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface EntityRepository {

    void createTables();
    Node mapRow(ResultSet resultSet, int rowNum) throws SQLException;
    List<Node> findAll();
    Node findById(long id);
    int insert(Node node);
    int[] insertMany(List<Node> nodes);
}
