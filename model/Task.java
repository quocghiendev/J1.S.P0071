/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author admin
 */
public class Task {

    public static final String[] TASK_LIST = {"Code", "Test", "Design", "Review"};
    public static final double MIN_PLAN = 8.0;
    public static final double MAX_PLAN = 17.5;
    private static int count = 0;
    private final int Id;
    private int taskTypeID;
    private String requirementName;
    private String date;
    private double from;
    private double planToPlan;
    private String assignee;
    private String reviewer;

    public Task() {
        this.Id = ++count;
    }

    public Task(int taskTypeID, String requirementName, String date, double from, double planToPlan, String assignee, String reviewer) {
        this.Id = ++count;
        this.taskTypeID = taskTypeID;
        this.requirementName = requirementName;
        this.date = date;
        this.from = from;
        this.planToPlan = planToPlan;
        this.assignee = assignee;
        this.reviewer = reviewer;
    }

    public int getTaskTypeID() {
        return taskTypeID;
    }

    public int getId() {
        return Id;
    }

    public String getRequirementName() {
        return requirementName;
    }

    public String getDate() {
        return date;
    }

    public double getFrom() {
        return from;
    }

    public double getPlanToPlan() {
        return planToPlan;
    }
    public double getTime(){
        return planToPlan - from;
    }
    public String getAssignee() {
        return assignee;
    }

    public String getReviewer() {
        return reviewer;
    }

    public void setTaskTypeID(int taskTypeID) {
        this.taskTypeID = taskTypeID;
    }

    public void setRequirementName(String requirementName) {
        this.requirementName = requirementName;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setFrom(double from) {
        this.from = from;
    }

    public void setPlanToPlan(double planToPlan) {
        this.planToPlan = planToPlan;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public void setReviewer(String reviewer) {
        this.reviewer = reviewer;
    }

}
