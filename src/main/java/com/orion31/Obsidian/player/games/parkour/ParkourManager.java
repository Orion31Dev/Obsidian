package com.orion31.Obsidian.player.games.parkour;

import java.util.ArrayList;

public class ParkourManager {
    private static ArrayList<ParkourCourse> registeredCourses = new ArrayList<ParkourCourse>();
    
    public static void addCourse(ParkourCourse course) {
	registeredCourses.add(course);
    }
    
    public static ParkourCourse getCourse() {
	return registeredCourses.get(0); // TODO
    }
}
