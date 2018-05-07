package com.michal.network.service;

import com.michal.network.domain.Node;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class EntityH2Repository implements EntityRepository {

    JdbcTemplate jdbcTemplate;
    private static final String NODE_ID_COLUMN = "NODE_ID";//TODO make these an enum
    private static final String DATA_ID_COLUMN = "DATA_ID";
    private static final String EDGES_COLUMN = "EDGES";
    private static final String NAME_COLUMN = "NAME";
    private static final String PHONE_COLUMN = "PHONE";
    private static final String TYPE_COLUMN = "TYPE";
    private static final String MAKE_COLUMN = "MAKE";
    private static final String MODEL_COLUMN = "MODEL";



    @Override
    public void createTables() {
        String sql = "CREATE TABLE NODES(\n" +
                "  NODE_ID IDENTITY NOT NULL,\n" +
                "  NAME VARCHAR_IGNORECASE,\n" +
                "  PHONE VARCHAR_IGNORECASE,\n" +
                "  DATA_ID VARCHAR_IGNORECASE NOT NULL,\n" +
                "  TYPE VARCHAR_IGNORECASE,\n" +
                "  MAKE VARCHAR_IGNORECASE,\n" +
                "  MODEL VARCHAR_IGNORECASE,\n" +
                "  EDGES ARRAY,\n" +
                "  PRIMARY KEY (NODE_ID)\n" +
                ");";
        jdbcTemplate.execute(sql);//TODO catch and log error
    }

    class NodeRowMapper implements RowMapper<Node> {
        @Override
        public Node mapRow(ResultSet resultSet, int rowNum) throws SQLException {
           return setNodeProperties(resultSet);
        }

    }

    @Override//TODO refactor this to not be repeated somehow
    public Node mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        return setNodeProperties(resultSet);

    }

    private static Node setNodeProperties(ResultSet resultSet) throws SQLException{
        Node node = new Node();
        node.setNode_id(resultSet.getLong(NODE_ID_COLUMN));
        node.setData_id(resultSet.getString(DATA_ID_COLUMN));
        node.setEdges(resultSet.getArray(EDGES_COLUMN));
        node.setMake(resultSet.getString(MAKE_COLUMN));
        node.setModel(resultSet.getString(MODEL_COLUMN));
        node.setType(resultSet.getString(TYPE_COLUMN));
        node.setPhone(resultSet.getString(PHONE_COLUMN));
        node.setName(resultSet.getString(NAME_COLUMN));
        return node;
    }

    @Override
    public List<Node> findAll() {
        return jdbcTemplate.query("select * from NODES", new NodeRowMapper());
    }
    @Override
    public Node findById(long id) {
        return jdbcTemplate.queryForObject("select * from nodes where NODE_ID=?", new Object[] { id },
                new BeanPropertyRowMapper<Node>(Node.class));
    }

    /*
     * int return val is number of rows affected
     */
    @Override
    public int insert(Node node) {
        String separator = ", ";
        String columnNames = NODE_ID_COLUMN + separator +
                DATA_ID_COLUMN + separator +
                EDGES_COLUMN + separator +
                MAKE_COLUMN + separator +
                MODEL_COLUMN + separator +
                TYPE_COLUMN + separator +
                PHONE_COLUMN + separator +
                NAME_COLUMN + separator;

        String sql = "insert into NODES (" + columnNames +") " + "values(?,?,?,?,?,?,?,?)";
        return jdbcTemplate.update(sql, node.getNode_id(), node.getData_id(), node.getEdges(), node.getMake(), node.getModel(),
                node.getType(), node.getPhone(), node.getName());
    }

    @Override
    public int[] insertMany(List<Node> nodes) {
        return null;//return jdbcTemplate.batchUpdate()
    }
}
