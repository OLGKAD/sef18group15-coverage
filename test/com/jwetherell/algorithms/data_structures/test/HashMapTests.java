package com.jwetherell.algorithms.data_structures.test;

import static org.junit.Assert.*;

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
    public void testClearMethod () {
        /**
         * Clearing an empty map should result in a size of 0.
         * After inserting one item, the new size should be 0 when cleared.
         */
        HashMap<Integer, Integer> map1 = new HashMap<>(HashMap.Type.CHAINING);
        map1.clear();
        assertEquals(0, map1.size());
        map1.put(2, 3);
        assertEquals(1, map1.size());
        map1.clear();
        assertEquals(0, map1.size());

        HashMap<Integer, Integer> map2 = new HashMap<>(HashMap.Type.PROBING);
        map2.clear();
        assertEquals(0, map2.size());
        map2.put(3, 2);
        assertEquals(1, map2.size());
        map2.clear();
        assertEquals(0, map2.size());
    }
}
