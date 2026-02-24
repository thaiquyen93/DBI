/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author Admin
 */
public class Inputter {
    Scanner sc = new Scanner(System.in);
    
        // 1) Nhập chuỗi (không rỗng, đã trim)
    public String getString(String mess) {
        System.out.print(mess);
        String s = sc.nextLine().trim();
        while (s.isEmpty()) {
            System.out.print("Input cannot be empty. " + mess);
            s = sc.nextLine().trim();
        }
        return s;
    }
    
    public boolean getYesNo(String mess){
        String rs = getString(mess);
        return rs.equalsIgnoreCase("Y");
    }
    
    public int getSalary(String mess) {
    while (true) {
        try {
            String temp = getString(mess);

            // check regex: chỉ cho phép số nguyên dương
            if (!temp.matches("^\\d+$")) {
                System.out.println("Salary must be a positive integer.");
                continue;
            }

            int salary = Integer.parseInt(temp);

            // check business rule
            if (salary < 1000) {
                System.out.println("Salary must be at least 1000 USD.");
                continue;
            }

            return salary;
        } catch (NumberFormatException e) {
            System.out.println("Invalid salary format.");
        }
    }
}


    public String getStringEmpty(String msg, String regex) {
        while (true) { // Vòng lặp kiểm tra điều kiện nhập
            System.out.print(msg); // Hiển thị thông báo nhập
            String input = sc.nextLine().trim(); // Nhập dữ liệu và loại bỏ khoảng trắng

            if (input.isEmpty()) { // Nếu nhập rỗng, trả về null
                return null;
            }

            if (Acceptable.isValid(input, regex)) { // Kiểm tra chuỗi theo regex
                return input; // Trả về chuỗi hợp lệ
            } else {
                System.err.print("Input not valid. Please re-input: "); // Thông báo lỗi
            }
        }
    }
        public String getStringEmpty(String msg) {
        while (true) { // Vòng lặp kiểm tra điều kiện nhập
            System.out.print(msg); // Hiển thị thông báo nhập
            String input = sc.nextLine().trim(); // Nhập dữ liệu và loại bỏ khoảng trắng

            if (input.isEmpty()) { // Nếu nhập rỗng, trả về null
                return null;
            }
            else return input;
        }
    }

    // 2) Nhập chuỗi + kiểm regex (dùng Acceptable.isValid)
    public String getStringAndCheck(String mess, String pattern) {
        while (true) {
            String s = getString(mess);
            if (Acceptable.isValid(s, pattern)) {
                return s;
            }
            System.out.println("Invalid format. Please try again!");
        }
    }

    // 3) Nhập số nguyên
    public int getInt(String mess) {
        while (true) {
            String s = getString(mess);
            try {
                return Integer.parseInt(s);
            } catch (NumberFormatException e) {
                System.out.println("Invalid integer. Please enter again!");
            }
        }
    }
    public int getInt(String mess, int min, int max) {
    while (true) {
        try {
            int n = Integer.parseInt(getString(mess));

            if (n < min || n > max) {
                System.out.println("Please enter a number in range [" + min + ", " + max + "]");
            } else {
                return n;
            }

        } catch (NumberFormatException e) {
            System.out.println("Invalid integer. Please enter again!");
        }
    }
}
    public int getInt(String mess, int min) {
    while (true) {
        try {
            int n = Integer.parseInt(getString(mess));

            if (n < min) {
                System.out.println("Duration must be a positive integer in months, minimum " + min +" month.");
            } else {
                return n;
            }

        } catch (NumberFormatException e) {
            System.out.println("Invalid integer. Please enter again!");
        }
    }
}
        public Date getDateFromUser(String mess, boolean isLoop) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false); // Bật chế độ kiểm tra ngày nghiêm ngặt
        Date date = null;
        boolean valid = false;

        do {
            String inputDate = getString(mess); // Lấy chuỗi từ bàn phím

            // Trường hợp để trống khi Update
            if (inputDate.isEmpty() && !isLoop) {
                return null;
            }

            try {
                date = sdf.parse(inputDate); // Chuyển chuỗi sang Date
                Date now = new Date();   // Lấy ngày hiện tại

                // Kiểm tra nếu ngày nhập vào phải sau ngày hiện tại
                if (date.after(now)) {
                    valid = true;
                } else {
                    System.out.println("The start date must be in the future!");
                }
            } catch (ParseException e) {
                System.out.println("Invalid date format! Please use dd/MM/yyyy.");
            }
        } while (isLoop && !valid);
        return date;
    }
    
}
