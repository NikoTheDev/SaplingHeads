package com.vervedev.saplingheads.leaderboards;

import java.util.Comparator;
import java.util.Map;

public class ValueComparator implements Comparator<String> {
	 
    Map<String, Integer> base;
    public ValueComparator(Map<String, Integer> map) {
        this.base = map;
    }

    public int compare(String a, String b) {
        if (base.get(a) >= base.get(b)) {
            return -1;
        } else {
            return 1;
        }
    }
}
