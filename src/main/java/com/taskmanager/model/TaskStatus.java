package com.taskmanager.model;


public enum TaskStatus {
	NEW("NEW"),
	IN_PROGRESS("IN PROGRESS"),
	COMPLETE("COMPLETE");

	private String status;

	TaskStatus(String status) {
		this.status = status;
	}
}