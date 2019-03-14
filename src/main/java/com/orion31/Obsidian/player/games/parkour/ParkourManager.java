package com.orion31.Obsidian.player.games.parkour;

import java.util.ArrayList;

import com.orion31.Obsidian.Messenger;
import com.orion31.Obsidian.ObsidianException;

public class ParkourManager {
    private static ArrayList<ParkourCourse> registeredCourses = new ArrayList<ParkourCourse>();

    public static void addCourse(ParkourCourse course) {
	registeredCourses.add(course);
    }

    public static ParkourCourse getCourse(String name) throws ObsidianException {
	name = Messenger.stripColorCodes(name);
	for (ParkourCourse course : registeredCourses) {
	    String courseName = Messenger.stripColorCodes(course.getName());
	    if (courseName.equalsIgnoreCase(name))
		return course;
	}
	throw new ObsidianException("Could not find parkour course: &c" + name);
    }
}
