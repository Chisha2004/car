package com.evo.modal;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Person extends AnimatedPanel{

    private static final String CYCLE_IMG_DIR = "img" + File.separator + "walk_cycle";
    private Map<String, Integer> walkCycleIndex = new HashMap<>();
    private static final String WALK_CONTACT = "WALK_CONTACT";
    private static final String WALK_RECOIL = "WALK_RECOIL";
    private static final String WALK_PASSING = "WALK_PASSING";
    private static final String WALK_HIGH_POINT = "WALK_HIGH_POINT";

    private static String[] generateWalkCycleNames() {
        return new String[] {
                CYCLE_IMG_DIR + File.separator + "walking_contact.png",
                CYCLE_IMG_DIR + File.separator + "walking_recoil.png",
                CYCLE_IMG_DIR + File.separator + "walking_passing.png",
                CYCLE_IMG_DIR + File.separator + "walking_high_point.png"
        };
    }

    public Person(int id) {
        super(id, generateWalkCycleNames());

        walkCycleIndex.put(WALK_CONTACT, 0);
        walkCycleIndex.put(WALK_RECOIL, 1);
        walkCycleIndex.put(WALK_PASSING, 2);
        walkCycleIndex.put(WALK_HIGH_POINT, 3);
    }
}
