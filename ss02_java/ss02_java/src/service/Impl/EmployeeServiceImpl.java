package service.Impl;

import models.Employee;
import service.Service;
import util.ReadAndWrite;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmployeeServiceImpl implements Service {
    private static List<Employee> employeeList = new ArrayList<>();// Tạo 1 arraylist có kiểu dl là employee để lưu trữ employee
    private static Scanner scanner = new Scanner(System.in);
    @Override
    public void display() {
        employeeList = (List<Employee>) ReadAndWrite.Read("F:\\Inter0322-NguyenThiHongNhung\\ss02_java\\ss02_java\\src\\data\\Employee.csv");
        for (Employee employee:employeeList){
            System.out.println(employee.toString());
        }



    }

    @Override
    public void addNew() {
        System.out.println("Nhập id: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.println("Nhập tên");
        String name = scanner.nextLine();
        System.out.println("Nhập tuổi");
        int age = Integer.parseInt(scanner.nextLine());
        System.out.println("Nhập giới tính");
        String gender = scanner.nextLine();
        System.out.println("Nhập chứng minh");
        String idCard = scanner.nextLine();
        System.out.println("Nhập Email");
        String email = scanner.nextLine();
        System.out.println("Nhập chức vụ");
        String level = scanner.nextLine();
        System.out.println("Nhập vị trí");
        String position = scanner.nextLine();
        System.out.println("Nhập lương");
        int salary = Integer.parseInt(scanner.nextLine());
        Employee employee = new Employee(id, name, age, gender, idCard, email, level,position, salary);
        employeeList.add(employee);// đưa vào employeeList để lưu trữ
        ReadAndWrite.write(employeeList, "F:\\Inter0322-NguyenThiHongNhung\\ss02_java\\ss02_java\\src\\data\\Employee.csv");





    }

    @Override
    public void edit() {

    }

    @Override
    public void delete() {

    }
}
