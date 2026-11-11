package view;

import java.util.Calendar;
import util.Validation;
import model.Task;

import java.util.List;

public class TaskView {
    
    private static final double PLAN_STEP = 0.5;

    private static final String ROW_FORMAT = "%-5s %-20s %-15s %-30s %-10s %-15s %-15s";
    /**
     * Năm hiện tại, dùng làm cận trên cho năm sinh.
     */
    private static final int CURRENT_YEAR
            = Calendar.getInstance().get(Calendar.YEAR);
    private static Validation validation;

    public TaskView() {
        validation = new Validation();
    }

    public void showMenu() {
        showMessage("============ Task Program ==============");
        showMessage("1. Add Task");
        showMessage("2. Delete Task");
        showMessage("3. Display Task");
        showMessage("4. Exit");
    }

    /**
     * Nhập lựa chọn trong menu.
     *
     * @param min lựa chọn nhỏ nhất
     * @param max lựa chọn lớn nhất
     * @return lựa chọn hợp lệ
     */
    public int inputChoice(int min, int max) {
        return validation.inputInt("Enter your choice: ", min, max);
    }

    public void showMessage(String massage) {
        System.out.println(massage);
    }

    public int inputId(List<Task> task){
        return validation.inputInt("Id: ", 1, Integer.MAX_VALUE);
        
    }
    private String inputDate(){
        while(true){
            String date = validation.inputDate(
                    "Enter graduation date (dd-MM-yyyy): ");
            int year = Integer.parseInt(date.substring(6));
            
            if (year <= CURRENT_YEAR) {
                return date;
            }
            
            showMessage("Invalid Date!");
        }
    
    }
    public Task inputTask() {
        String requirementName = validation.inputString("Requirement Name: ",
                Validation.NAME_REGEX);
        int taskId = validation.inputInt("Task Type: ", 1, 4);

        String date = inputDate();
        
        double from = validation.inputDouble("From: ",Task.MIN_PLAN, Task.MAX_PLAN - PLAN_STEP, PLAN_STEP);
        double planTo = validation.inputDouble("To: ",from + PLAN_STEP, Task.MAX_PLAN, PLAN_STEP);
        
        String assignee = validation.inputString("Assignee:", Validation.STRING_REGEX_NO_NULL);
        String reviewer = validation.inputString("Reviewer: ", Validation.STRING_REGEX_NO_NULL);
        
        return new Task(taskId, requirementName, date, from, planTo, assignee, reviewer);
    }

    public void displayTask(List<Task> task) {
        showMessage("------------------------ Task -------------------------------");
        showMessage(String.format(ROW_FORMAT, "ID", "Name", "Task Type",
                "Date", "Time", "Assignee", "Reviewer"));

        for (Task lstask : task) {
            showMessage(String.format(ROW_FORMAT, lstask.getId(),
                    lstask.getRequirementName(),
                    Task.TASK_LIST[lstask.getTaskTypeID() - 1],
                    lstask.getDate(), lstask.getTime(),
                    lstask.getAssignee(),
                    lstask.getReviewer()));
        }

    }
}
