package controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import model.Task;
import view.TaskView;

public class TaskController {

    /**
     * Danh sách task đang được quản lý (lưu trong ArrayList).
     */
    private final List<Task> tasks;

    /**
     * View dùng để nhập và xuất dữ liệu.
     */
    private final TaskView view;

    public TaskController() {
        tasks = new ArrayList<>();
        view = new TaskView();
    }

    /**
     * Chạy chương trình cho tới khi người dùng chọn thoát hoặc luồng nhập bị
     * đóng.
     */
    public void run() {
        try {
            runMenu();
        } catch (IllegalStateException e) {
            view.showMessage("\n" + e.getMessage()
                    + " Program terminated.");
        }
    }

    /**
     * Lặp hiển thị menu, đọc lựa chọn và gọi chức năng tương ứng cho tới khi
     * người dùng chọn thoát.
     */
    private void runMenu() {
        while (true) {
            view.showMenu();

            /* Menu có 4 chức năng; View đảm bảo lựa chọn nằm trong 1..4. */
            int choice = view.inputChoice(1, 4);

            switch (choice) {
                case 1:
                    addTask();
                    break;
                case 2:
                    menuDelete();
                    break;
                case 3:
                    displayTask();
                    break;
                case 4:
                    view.showMessage("Program terminated.");
                    return;
            }
        }
    }

    private void addTask() {
        Task newtask = view.inputTask();
        tasks.add(newtask);
        view.showMessage("Task add successfully!");
    }

    /**
     * Xóa task theo id.
     *
     * @param id id dạng chuỗi
     * @throws NumberFormatException nếu id không phải số nguyên
     * @throws Exception nếu id không tồn tại
     */
    public void deleteTask(int id) throws Exception {
        if (!tasks.removeIf(t -> t.getId() == id)) {
            throw new Exception("Id not found!");
        }
    }

    /**
     * Lấy danh sách task, sắp xếp tăng dần theo ID.
     *
     * @return danh sách task
     */
    public List<Task> getDataTasks() {
        tasks.sort(Comparator.comparingInt(Task::getId));
        return tasks;
    }

    private void menuDelete() {
        if (tasks.isEmpty()) {
            view.showMessage("List is empty!");
            return;
        }
        view.showMessage("---------Del Task------");

        /* Đọc id ngoài try để lỗi "input closed" vẫn thoát được chương trình. */
        int id = view.inputId(tasks);
        try {
            deleteTask(id);
            view.showMessage("Task deleted successfully!");
        } catch (NumberFormatException | NullPointerException e) {
            view.showMessage("Invalid id!");
        } catch (Exception e) {
            view.showMessage(e.getMessage()); // chỗ này in ra lỗi exception
        }
    }

    private void displayTask() {
        List<Task> list = getDataTasks();
        if (list.isEmpty()) {
            view.showMessage("List is empty!");
            return;
        }
        view.displayTask(list);
    }
}