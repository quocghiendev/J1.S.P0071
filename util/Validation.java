/*
 * Validation.java
 *
 * Phiên bản: 1.1
 * Ngày cập nhật: 06/11/2026
 * Tác giả: [Họ tên sinh viên] - [Mã sinh viên]
 *
 * Copyright (c) 2026 [Họ tên sinh viên]. All rights reserved.
 *
 * Mô tả: Lớp tiện ích đọc và kiểm tra dữ liệu nhập từ bàn phím cho chương
 * trình Quản lý.
 */
package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

/**
 *
 * @author admin
 */
public class Validation {

    /**
     * Định dạng ngày tốt nghiệp.
     */
    private static final String DATE_FORMAT = "dd-MM-yyyy";
    
    public static final String NAME_REGEX = "^[\\p{L}\\s]+$";
    
    public static final String STRING_REGEX_NO_NULL = ".+";

    /**
     * Mẫu chuỗi ngày: 2 chữ số ngày, 2 chữ số tháng, 4 chữ số năm. Dùng để chặn
     * năm viết thiếu chữ số (ví dụ 01/01/20) mà SimpleDateFormat vẫn chấp nhận.
     */
    private static final String DATE_REGEX = "\\d{2}-\\d{2}-\\d{4}";

    /**
     * Đối tượng đọc dữ liệu từ bàn phím.
     */
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Đọc một dòng từ bàn phím và bỏ khoảng trắng hai đầu.
     *
     * @return dòng đã đọc
     * @throws IllegalStateException nếu luồng nhập đã đóng
     */
    private String readLine() {
        if (!scanner.hasNextLine()) {
            throw new IllegalStateException("Input stream closed.");
        }
        return scanner.nextLine().trim();
    }

    /**
     * Nhập chuỗi khớp với mẫu regex cho trước.
     *
     * @param message nội dung nhắc nhập
     * @param regex mẫu mà chuỗi phải khớp
     * @return chuỗi hợp lệ
     */
    public String inputString(String message, String regex) {
        while (true) {
            System.out.print(message);
            String input = readLine();

            if (input.matches(regex)) {
                return input;
            }
            System.out.println("Invalid input. Please try again.");
        }
    }

    /**
     * Nhập số nguyên trong khoảng [min, max].
     *
     * @param message nội dung nhắc nhập
     * @param min giá trị nhỏ nhất
     * @param max giá trị lớn nhất
     * @return số nguyên hợp lệ
     */
    public int inputInt(String message, int min, int max) {
        while (true) {
            System.out.print(message);
            String input = readLine();

            try {
                int number = Integer.parseInt(input);

                if (number >= min && number <= max) {
                    return number;
                }
                System.out.println("Please enter a number from " + min
                        + " to " + max + ".");
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid integer number.");
            }
        }
    }

    /**
     * Đọc một số thực nằm trong đoạn [min, max].
     *
     * @param min giá trị nhỏ nhất được chấp nhận
     * @param max giá trị lớn nhất được chấp nhận
     * @return số thực hợp lệ người dùng nhập
     * @throws IllegalStateException nếu luồng nhập đã đóng
     */
    public double inputDouble(String massage,double min, double max, double step) {
        while (true) {
            try {
                System.out.println(massage);
                double value = Double.parseDouble(readLine());

                /*
                 * Double.parseDouble chấp nhận "NaN" và "Infinity";
                 * phép so sánh khoảng bên dưới loại bỏ cả hai trường hợp.
                 */
                if (((value >= min) && (value <= max))&& value % step == 0) {
                    return value;
                }
                System.out.println("Value must be from " + min + " to "
                        + max + ". Please re-enter:");
            } catch (NumberFormatException e) {

                /* Chuỗi nhập không phải số: báo lỗi và đọc lại. */
                System.out.println("Please enter a number:");
            }
        }
    }

    /**
     * Nhập ngày theo định dạng dd/MM/yyyy (không chấp nhận ngày không tồn tại,
     * ví dụ 31/02/2020).
     *
     * @param message nội dung nhắc nhập
     * @return ngày hợp lệ dưới dạng chuỗi dd/MM/yyyy
     */
    public String inputDate(String message) {
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
        format.setLenient(false);

        while (true) {
            System.out.print(message);
            String input = readLine();

            if (input.matches(DATE_REGEX)) {
                try {
                    format.parse(input);
                    return input;
                } catch (ParseException e) {

                    /* Rơi xuống thông báo lỗi bên dưới. */
                }
            }
            System.out.println("Invalid date. Format must be "
                    + DATE_FORMAT + ".");
        }
    }
}
