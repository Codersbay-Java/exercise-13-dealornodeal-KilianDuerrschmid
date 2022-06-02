package application;

import java.util.*;

public class Briefcase {

    static Map<Integer, Integer> briefcases;

    public Briefcase() {
        //default hashmap with all cases
        briefcases = new HashMap<Integer, Integer>() {{
            put(1, (1 / 100));
            put(2, 1);
            put(3, 5);
            put(4, 10);
            put(5, 25);
            put(6, 50);
            put(7, 75);
            put(8, 100);
            put(9, 200);
            put(10, 300);
            put(11, 400);
            put(12, 500);
            put(13, 750);
            put(14, 1000);
            put(15, 5000);
            put(16, 10000);
            put(17, 25000);
            put(18, 50000);
            put(19, 75000);
            put(20, 100000);
            put(21, 200000);
            put(22, 300000);
            put(23, 400000);
            put(24, 500000);
            put(25, 750000);
            put(26, 1000000);
        }};
    }

    public static void printNumbers() {
        //printing the numbers between rounds
        for (Integer number : briefcases.keySet()) {
            String key = number.toString();
            System.out.print("[" + key + "] ");
        }
    }

    public static Map<Integer, Integer> shuffleBriefcases() {
        //randomizing the order of the cases
        Briefcase bc = new Briefcase();
        List<Integer> list = new ArrayList<>(bc.briefcases.keySet());
        Collections.shuffle(list);
        Map<Integer, Integer> briefcasesShuffled = new LinkedHashMap<>();
        list.forEach(k -> briefcasesShuffled.put(list.get(k - 1), bc.briefcases.get(k)));

        return briefcasesShuffled;
    }

    @Override
    public String toString() {
        String value = null;
        for (Integer name : briefcases.keySet()) {
            value = briefcases.get(name).toString();
        }
        return value;
    }
}
