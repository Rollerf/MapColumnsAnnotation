package com.rollerf.mapcolumns;

import com.rollerf.mapcolumns.model.Person;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ObjectToMapFieldsUnitTest {
    @Test
    void givenObjectNotMappedThenExceptionThrown() throws MapColumnsException {
        Object object = new Object();
        ObjectToMapConverter mapConverter = new ObjectToMapConverter();
        assertThrows(MapColumnsException.class, () -> mapConverter.getColumnsMapped(object));
    }

    @Test
    void givenObjectMappedFieldsThenTrueReturned() throws MapColumnsException {
        Person person = new Person("soufiane", "cheouati", "34", "caldas");
        ObjectToMapConverter mapper = new ObjectToMapConverter();
        Map<String, String> columnsMapped = mapper.getColumnsMapped(person);
        Map<String, String> testMap = new HashMap<>();
        testMap.put("FIRST_NAME", person.getFirstName());
        testMap.put("LAST_NAME", person.getLastName());
        testMap.put("AGE", person.getAge());

        assertEquals(testMap, columnsMapped);
    }
}
