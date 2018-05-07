package com.michal.network.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import java.sql.Array;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Node {
    private String name;
    private String phone;//storing as a string in case some of the data has dashes or parens
    private String data_id;//comes from the ingested data
    @Id @GeneratedValue
    private Long node_id;//unique id that comes from db
    private String make;//this will be null if not a car
    private String model;//this will be null if not a car
    private Array edges;//each edge object will be a string, data_id|relationship_type
    private String type;//what type of entity this is. person, car, school, district

//make a method for isDirectional-> if type is not person, return true;
    //problem: can't have primary key in the edge because it doesn't exist yet when adding it


}
