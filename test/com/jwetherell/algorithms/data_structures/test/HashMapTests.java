package com.jwetherell.algorithms.data_structures.test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.jwetherell.algorithms.data_structures.HashMap;
import com.jwetherell.algorithms.data_structures.test.common.JavaMapTest;
import com.jwetherell.algorithms.data_structures.test.common.MapTest;
import com.jwetherell.algorithms.data_structures.test.common.Utils;
import com.jwetherell.algorithms.data_structures.test.common.Utils.TestData;

public class HashMapTests {

    @Test
    public void testHashMap() {
        TestData data = Utils.generateTestData(1000);

        String mapName = "ProbingHashMap";
        HashMap<Integer,String> map = new HashMap<Integer,String>(HashMap.Type.PROBING);
        java.util.Map<Integer,String> jMap = map.toMap();

        assertTrue(MapTest.testMap(map, Integer.class, mapName, data.unsorted, data.invalid));
        assertTrue(JavaMapTest.testJavaMap(jMap, Integer.class, mapName, data.unsorted, data.sorted, data.invalid));

        mapName = "LinkingHashMap";
        map = new HashMap<Integer,String>(HashMap.Type.CHAINING);
        jMap = map.toMap();

        assertTrue(MapTest.testMap(map, Integer.class, mapName, data.unsorted, data.invalid));
        assertTrue(JavaMapTest.testJavaMap(jMap, Integer.class, mapName, data.unsorted, data.sorted, data.invalid));
    }
    @Test
    /*
     *Tests if the false branches are taken in the equals function and return the proper response
     */
    public void testEqualsFalseBranch(){
        HashMap<Integer,String> map = new HashMap<Integer,String>(HashMap.Type.PROBING);
        map.put(1,"test");
        assertFalse(map.equals(null));
        assertFalse(map.equals(1));
    }
    /*
     *Tests if the constructor for probing hashmap with an initialz size for the underlying array
     * correctly creates a hashmap.
     */
    @Test
    public void testConstructorWithSizeParam(){
        HashMap<Integer,String> map = new HashMap<Integer,String>(HashMap.Type.PROBING,2);
        HashMap<Integer,String> map2 = new HashMap<Integer,String>(HashMap.Type.CHAINING,2);
        assertNotNull(map);
        assertNotNull(map2);
    }
}
