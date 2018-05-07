package com.michal.network.service;

import com.michal.network.domain.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;//fix
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class EntityServiceImpl implements EntityService {

    private static Logger logger = LoggerFactory.getLogger(EntityServiceImpl.class);


    @Override
    public void ingestData(File entityRelationships, File entityProperties) {
        Map<String, Node> entityNameToNode = new HashMap<>();
        List<String> relationshipsRows = readFile("entity_relationships.txt");
        List<String> entityPropertiesRows = readFile("entity_properties.txt");
        relationshipsRows.remove(0);//remove header row
        entityPropertiesRows.remove(0);//remove header row
        relationshipsRows.forEach(rRow -> {
            Map<String, String> columnNameToValue = parseRelationshipsLine(rRow);
            String id1 = columnNameToValue.get("id1");
            if(entityNameToNode.containsKey(id1)){

            } else {
                Node existingNode = entityNameToNode.get(id1);//need to do this for id2 as well
                //TODO create a set out of the existing ids in edges and make sure your id doesn't exist already to enforce uniqueness



            }
        });
        //iterate over each one
        //get a map of the column keys and vals
        // check if the map has that name yet
        // if not, create new node
        // set each property based on the info in the line
        // add it to the map
        // add the id2 value to the map
        // else, add any new info you have to the existing node.
            // check to make sure no dups in edges
        // save in db.
        


    }
    //TODO: refactor these two similar methods into one method
    private static Map<String, String> parseRelationshipsLine(String line) {
        Map<String, String> columnNameToValue = new HashMap<>();
        String[] values = line.split("\\s+");
        if(values.length == 5) {
            columnNameToValue.put("relationship", values[0]);//make these key names constants
            columnNameToValue.put("id1", values[1]);
            columnNameToValue.put("type1", values[2]);
            columnNameToValue.put("id2", values[3]);
            columnNameToValue.put("type2", values[4]);
        } else {
            logger.warn("parsing did not go as expected for relationships line: " + line);
        }
        return columnNameToValue;
    }

    private static Map<String, String> parsePropertiesLine(String line) {
        Map<String, String> columnNameToValue = new HashMap<>();
        String[] values = line.split("\\s+");
        if(values.length >= 3) {
            columnNameToValue.put("id", values[0]);
            columnNameToValue.put("property", values[1]);
            if(values.length > 3) {
                columnNameToValue.put("value", values[2] + values[3]);//TODO: fix -> this will cut off if the entity has > 2 names
            } else {
                columnNameToValue.put("value", values[2]);
            }
        } else {
            logger.warn("parsing did not go as expected for properties line: " + line);
        }
        return columnNameToValue;

    }
    private static List<String> readFile(String fileName){
        //TODO: make sure that lines() enforces order
        List<String> result = new ArrayList<>();
        try {
            BufferedReader reader = Files.newBufferedReader(Paths.get(fileName));
            Stream<String> lines = reader.lines();
            lines.forEach(line -> result.add(line));
            lines.close();
        } catch (IOException io) {
            io.printStackTrace();
        }
        return result;
    }
}
