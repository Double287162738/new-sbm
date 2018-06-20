package com.sbm.util;

import java.util.ArrayList;
import java.util.List;

public final class StringToListUtils {
    //String -> List<string>
    public static List<String> changeToList(String s, String interval) {
        List<String> result = new ArrayList<>();
        String[] strings = s.split(interval);
        for (String string : strings) {
            result.add(string);
        }
        return result;
    }

    //String -> List<Integer>
    public static List<Integer> changeToListInt(String s, String interval) {
        List<Integer> result = new ArrayList<>();
        String[] strings = s.split(interval);
        for (String string : strings) {
            result.add(Integer.valueOf(string));
        }
        return result;
    }

    //String -> List<Integer> Asc
    public static List<Integer> changeToListIntAsc(String s, String interval) {
        List<Integer> result = new ArrayList<>();
        String[] strings = s.split(interval);
        for (String string : strings) {
            result.add(Integer.valueOf(string));
        }
        for (int i = 0; i < result.size(); i++) {
            for (int j = 0; j < result.size() - 1 - i; j++) {
                if (result.get(j) > result.get(j + 1)) {
                    int tmp = result.get(j);
                    result.set(j, result.get(j + 1));
                    result.set(j + 1, tmp);
                }
            }
        }
        return result;
    }


    //String -> List<int> Desc
    public static List<Integer> changeToListIntDesc(String s, String interval) {
        List<Integer> result = new ArrayList<>();
        String[] strings = s.split(interval);
        for (String string : strings) {
            result.add(Integer.valueOf(string));
        }
        for (int i = 0; i < result.size(); i++) {
            for (int j = 0; j < result.size() - 1 - i; j++) {
                if (result.get(j) < result.get(j + 1)) {
                    int tmp = result.get(j);
                    result.set(j, result.get(j + 1));
                    result.set(j + 1, tmp);
                }
            }
        }
        return result;
    }
}
