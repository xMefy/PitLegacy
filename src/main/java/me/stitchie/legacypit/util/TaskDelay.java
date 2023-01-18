package me.stitchie.legacypit.util;

public final class TaskDelay {

	private static int nextTaskDelay = 0;

	private TaskDelay() {

	}

	public static int getNextTaskDelay() {

		if (nextTaskDelay > 20) {
			nextTaskDelay = 0;
		}
		return nextTaskDelay++;
	}
}
