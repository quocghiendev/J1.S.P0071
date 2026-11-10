package controller;

import java.util.ArrayList;
import java.util.List;

import model.Task;
import view.TaskView;

public class TaskController {

    /**
     * Danh sách ứng viên đang được quản lý (lưu trong ArrayList).
     */
    private final List<Task> tasks;

    /**
     * View dùng để nhập và xuất dữ liệu.
     */
    private final TaskView view;

    /**
     * Tạo Controller với danh sách rỗng và View mặc định.
     */
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

            /*
             * Luồng nhập đã đóng (Ctrl+D / Ctrl+Z hoặc file input hết dữ
             * liệu): không thể đọc tiếp nên thông báo và kết thúc.
             */
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

            /*
             * Menu có 5 chức năng đánh số từ 1 đến 5; View đọc và kiểm tra
             * để lựa chọn luôn nằm trong khoảng này.
             */
            int choice = view.inputChoice(1, 4);

            switch (choice) {
                case 1:
                    addTask();
                    break;
                case 2:
                    deleteTask();
                    break;
                case 3:
                    displayTask();
                    break;
                case 4:
                    view.showMessage("Program terminated.");
                    return;

                default:

                    /* Không xảy ra vì View đã kiểm tra lựa chọn hợp lệ. */
                    view.showMessage("Invalid choice.");
                    break;
            }
        }
    }

    private void addTask() {
        Task newtask = view.inputTask();
        tasks.add(newtask);
        view.showMessage("Task add successfully!");
    }

    private void displayTask() {
        if (tasks.isEmpty()) {
            view.showMessage("List is empty!");
            return;
        }
        view.displayTask(tasks);
    }

    private void deleteTask() {
        if (tasks.isEmpty()) {
            view.showMessage("List is empty!");
            return;
        }

        int id = view.inputId(tasks);

        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getId() == id) {
                tasks.remove(i);
                view.showMessage("Task deleted successfully!");
                return;
            }
        }

        view.showMessage("Id not found!");
    }
}
