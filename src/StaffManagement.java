import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class StaffManagement {
    Scanner sc = new Scanner(System.in);
    String op;
    List<StaffMember> staffData = new ArrayList<>(Arrays.asList(
            new Volunteer(1, "Dara", "PP", 300),
            new SalariesEmployee(2, "Sokha", "SR", 400, 20),
            new HourlySalaryEmployee(3, "Mony", "BTB", 40, 11.5),
            new HourlySalaryEmployee(4, "Vann", "KPC", 35, 14.0),
            new Volunteer(5, "Chan", "TK", 500),
            new SalariesEmployee(6, "Koko", "KPC", 500, 22),
            new Volunteer(7, "Mora", "KPC", 320)
    ));


    public void displayMain(){
        do{
            try {

                CellStyle numberStyle = new CellStyle(CellStyle.HorizontalAlign.center);
                Table t = new Table(1, BorderStyle.UNICODE_ROUND_BOX, ShownBorders.SURROUND_HEADER_AND_COLUMNS);
                t.setColumnWidth(0, 35,40);
                t.addCell("STAFF MANAGEMENT SYSTEM", numberStyle);
                t.addCell("1. Insert Empployee");
                t.addCell("2. Update Empployee");
                t.addCell("3. Display Empployee");
                t.addCell("4. Remove Empployee");
                t.addCell("5. Exit");
                System.out.println(t.render());

                System.out.println("---------------------");
                System.out.print("=> Choose the Option = ");
                op = sc.nextLine();
                switch (op){
                    case "1":
                        InsertEmployee();
                        break;
                    case "2":
                        updateEmployee();
                        break;
                    case "3":
                        displayEmployee();
                        break;
                    case "4":
                        removeEmplyee();
                        break;
                    case "5":
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid Option. Please choose again.");
                }
            } catch (Exception e) {
                System.out.println("An error : " + e.getMessage());
                e.printStackTrace();
            }
        }while (!op.equalsIgnoreCase("0"));
    }

    public void InsertEmployee(){
        CellStyle numberStyle = new CellStyle(CellStyle.HorizontalAlign.center);
        Table t2 = new Table(4, BorderStyle.UNICODE_ROUND_BOX, ShownBorders.ALL);
        System.out.println("\n==============>> Insert Employee <<==============");
        String opInsert;
        System.out.println("Choose Type:");
        t2.addCell("1. Volunteer", numberStyle);
        t2.addCell("2. Salaries Employee", numberStyle);
        t2.addCell("3. Hourly Employee", numberStyle);
        t2.addCell("4. Back", numberStyle);
        System.out.println(t2.render());
        System.out.print("=> Enter Type Number : ");
        opInsert = sc.nextLine();
        int staffID = staffData.size()+1;
        switch (opInsert){
            case "1":
                System.out.println("=> ID : "+ staffID);
                System.out.print("=> Enter Name: ");
                String volunteerName = sc.nextLine();
                System.out.print("=> Enter Address: ");
                String volunteerAddress = sc.nextLine();
                System.out.print("=> Enter Salary: ");
                double volunteerSalary = 0;
                try {
                    volunteerSalary = Double.parseDouble(sc.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input! Salary must be a number.");
                    return;
                }
                staffData.add(new Volunteer(staffID, volunteerName, volunteerAddress, volunteerSalary));

                System.out.println("*You added Employee Type of Volunteer successfully!\n\n");
                break;
            case "2":
                System.out.println("=> ID : "+ staffID);
                System.out.print("=> Enter Name: ");
                String sEmpName = sc.nextLine();
                System.out.print("=> Enter Address: ");
                String sEmpAddress = sc.nextLine();

                double sEmpSalary = 0;
                double sEmpBonus = 0;
                try {
                    System.out.print("=> Enter Salary: ");
                    sEmpSalary = Double.parseDouble(sc.nextLine());
                    System.out.print("=> Enter Bonus: ");
                    sEmpBonus = Double.parseDouble(sc.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input! Salary and Bonus must be numbers.");
                    return;
                }

                staffData.add(new SalariesEmployee(staffID, sEmpName, sEmpAddress, sEmpSalary, sEmpBonus));
                System.out.println("*You added Salaries Employee successfully!\n\n");
                break;
            case "3":
                System.out.println("=> ID : "+ staffID);
                System.out.print("=> Enter Name: ");
                String hEmpName = sc.nextLine();
                System.out.print("=> Enter Address: ");
                String hEmpAddress = sc.nextLine();
                System.out.print("=> Enter Hours Worked: ");
                int hEmpHours = 0;
                double hEmpRate = 0;
                try {
                    System.out.print("=> Enter Hours Worked: ");
                    hEmpHours = Integer.parseInt(sc.nextLine());
                    System.out.print("=> Enter Hourly Rate: ");
                    hEmpRate = Double.parseDouble(sc.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input! Hours must be an integer, and Rate must be a number.");
                    return;
                }
                staffData.add(new HourlySalaryEmployee(staffID, hEmpName, hEmpAddress, hEmpHours, hEmpRate));
                System.out.println("*You added HourlySalary Employee successfully!\n\n");
                break;
            case "4":
                return;
            default:
                System.out.println("Invalid Option Insert Employee. Please choose again.");
        }
    }

    public void displayEmployee(){
        final int PAGE_SIZE = 3;
        int currentPage = 0;
        int totalEmployees = staffData.size();
        int totalPages = (int) Math.ceil((double) totalEmployees / PAGE_SIZE);

        CellStyle numberStyle = new CellStyle(CellStyle.HorizontalAlign.center);
        Table t1 = new Table(9, BorderStyle.UNICODE_ROUND_BOX, ShownBorders.ALL);
        System.out.println("==============>> Display Employee <<==============\n");
        if (staffData.isEmpty()) {
            System.out.println("--------------------------");
            System.out.println("|  No employees available. |");
            System.out.println("--------------------------");
            return;
        }
        t1.setColumnWidth(0,20,30);
        t1.setColumnWidth(1,10,20);
        t1.setColumnWidth(2,15,20);
        t1.setColumnWidth(3,15,20);
        t1.setColumnWidth(4,15,20);
        t1.setColumnWidth(5,15,20);
        t1.setColumnWidth(6,10,20);
        t1.setColumnWidth(7,15,20);
        t1.setColumnWidth(8,15,20);

        t1.addCell("Type", numberStyle);
        t1.addCell("ID", numberStyle);
        t1.addCell("Name", numberStyle);
        t1.addCell("Address", numberStyle);
        t1.addCell("Salary", numberStyle);
        t1.addCell("Bonus", numberStyle);
        t1.addCell("Hours", numberStyle);
        t1.addCell("Rate", numberStyle);
        t1.addCell("Pay", numberStyle);
        staffData.forEach(staff->{
            if (staff instanceof Volunteer) {
                Volunteer volunteer = (Volunteer) staff;
                t1.addCell("Volunteer", numberStyle);
                t1.addCell(String.valueOf(volunteer.id), numberStyle);
                t1.addCell(volunteer.name, numberStyle);
                t1.addCell(volunteer.address, numberStyle);
                t1.addCell(String.valueOf(volunteer.getSalary()), numberStyle);
                t1.addCell("---", numberStyle);
                t1.addCell("---", numberStyle);
                t1.addCell("---", numberStyle);
                t1.addCell(String.valueOf(volunteer.pay()), numberStyle);
            }
            else if (staff instanceof SalariesEmployee) {
                SalariesEmployee salariesEmployee = (SalariesEmployee) staff;
                t1.addCell("SalariesEmployee", numberStyle);
                t1.addCell(String.valueOf(salariesEmployee.id), numberStyle);
                t1.addCell(salariesEmployee.name, numberStyle);
                t1.addCell(salariesEmployee.address, numberStyle);
                t1.addCell(String.valueOf(salariesEmployee.getSalary()), numberStyle);
                t1.addCell(String.valueOf(salariesEmployee.getBunus()), numberStyle);
                t1.addCell("---", numberStyle);
                t1.addCell("---", numberStyle);
                t1.addCell(String.valueOf(salariesEmployee.pay()), numberStyle);
            }
            else if (staff instanceof HourlySalaryEmployee) {
                HourlySalaryEmployee hourlySalaryEmployee = (HourlySalaryEmployee) staff;
                t1.addCell("HourlySalaryEmployee", numberStyle);
                t1.addCell(String.valueOf(hourlySalaryEmployee.id), numberStyle);
                t1.addCell(hourlySalaryEmployee.name, numberStyle);
                t1.addCell(hourlySalaryEmployee.address, numberStyle);
                t1.addCell("---", numberStyle);
                t1.addCell("---", numberStyle);
                t1.addCell(String.valueOf(hourlySalaryEmployee.getHourWorked()), numberStyle);
                t1.addCell(String.valueOf(hourlySalaryEmployee.getRate()), numberStyle);
                t1.addCell(String.valueOf(hourlySalaryEmployee.pay()), numberStyle);
            }
        });
        System.out.println(t1.render());
        System.out.println("Page : "+(currentPage+1)+"/"+totalPages);
        String pageOp;
        do{
            System.out.println("1.First Page \t 2.Next Page \t 3.Previous Page \t 4.Last Page \t 5.Exit");
            System.out.print("=> Choose : ");
            pageOp = sc.nextLine();
            switch (pageOp){
                case "1":
                    break;
                case "2":
                    break;
                case "3":
                    break;
                case "4":
                    break;
                case "5":
                    return;
                default:
                    System.out.println("Invalid Option for Pagination.");
            }
        }while (!pageOp.equalsIgnoreCase("0"));
        System.out.println("\n");
    }


    public void updateEmployee() {
        CellStyle numberStyle = new CellStyle(CellStyle.HorizontalAlign.center);
        System.out.print("\nEnter Employee ID to update: ");
        int updateID = sc.nextInt();
        sc.nextLine(); // Consume newline

        // Find the staff member
        StaffMember staffToUpdate = null;
        for (StaffMember staff : staffData) {
            if (staff.getId() == updateID) {
                staffToUpdate = staff;
                break;
            }
        }

        if (staffToUpdate == null) {
            System.out.println("Employee ID not found. Please try again.");
            return;
        }
        displayStaff(staffToUpdate);

        String opUpdate;
        do {
            System.out.println("\nChoose a column to update:");
            if (staffToUpdate instanceof Volunteer) {
                System.out.println("1. Name   2. Address   3. Salary   0. Cancel");
            } else if (staffToUpdate instanceof SalariesEmployee) {
                System.out.println("1. Name   2. Address   3. Salary   4. Bonus   0. Cancel");
            } else if (staffToUpdate instanceof HourlySalaryEmployee) {
                System.out.println("1. Name   2. Address   3. Hours Worked   4. Rate   0. Cancel");
            }

            System.out.print("=> Select an option: ");
            opUpdate = sc.nextLine();

            switch (opUpdate) {
                case "1":
                    System.out.print("=> Change Name To: ");
                    String newName = sc.nextLine();
                    staffToUpdate.setName(newName);
                    displayUpdatedStaff(staffToUpdate);
                    break;

                case "2":
                    System.out.print("=> Change Address To: ");
                    String newAddress = sc.nextLine();
                    staffToUpdate.setAddress(newAddress);
                    displayUpdatedStaff(staffToUpdate);
                    break;

                case "3":
                    if (staffToUpdate instanceof Volunteer) {
                        System.out.print("=> Change Salary To: ");
                        double newSalary = sc.nextDouble();
                        sc.nextLine(); // Consume newline
                        ((Volunteer) staffToUpdate).setSalary(newSalary);
                    } else if (staffToUpdate instanceof SalariesEmployee) {
                        System.out.print("=> Change Salary To: ");
                        double newSalary = sc.nextDouble();
                        sc.nextLine(); // Consume newline
                        ((SalariesEmployee) staffToUpdate).setSalary(newSalary);
                    } else if (staffToUpdate instanceof HourlySalaryEmployee) {
                        System.out.print("=> Change Hours Worked To: ");
                        int newHours = sc.nextInt();
                        sc.nextLine(); // Consume newline
                        ((HourlySalaryEmployee) staffToUpdate).setHourWorked(newHours);
                    }
                    displayUpdatedStaff(staffToUpdate);
                    break;

                case "4":
                    if (staffToUpdate instanceof SalariesEmployee) {
                        System.out.print("=> Change Bonus To: ");
                        double newBonus = sc.nextDouble();
                        sc.nextLine(); // Consume newline
                        ((SalariesEmployee) staffToUpdate).setBunus(newBonus);
                        displayUpdatedStaff(staffToUpdate);
                    } else if (staffToUpdate instanceof HourlySalaryEmployee) {
                        System.out.print("=> Change Hourly Rate To: ");
                        double newRate = sc.nextDouble();
                        sc.nextLine(); // Consume newline
                        ((HourlySalaryEmployee) staffToUpdate).setRate(newRate);
                        displayUpdatedStaff(staffToUpdate);
                    }
                    break;

                case "0":
                    return;

                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        } while (!opUpdate.equals("0"));
    }

    public void displayStaff(StaffMember staff) {
        CellStyle numberStyle = new CellStyle(CellStyle.HorizontalAlign.center);
        Table table;

        if (staff instanceof Volunteer) {
            table = new Table(6, BorderStyle.UNICODE_ROUND_BOX, ShownBorders.ALL);
            table.setColumnWidth(0,20,30);
            table.setColumnWidth(1,15,20);
            table.setColumnWidth(2,15,20);
            table.setColumnWidth(3,15,20);
            table.setColumnWidth(4,15,20);
            table.setColumnWidth(5,15,20);

            table.addCell("Type", numberStyle);
            table.addCell("ID", numberStyle);
            table.addCell("Name", numberStyle);
            table.addCell("Address", numberStyle);
            table.addCell("Salary", numberStyle);
            table.addCell("Pay", numberStyle);

            table.addCell("Volunteer", numberStyle);
            table.addCell(String.valueOf(staff.getId()), numberStyle);
            table.addCell(staff.getName(), numberStyle);
            table.addCell(staff.getAddress(), numberStyle);
            table.addCell(String.valueOf(((Volunteer) staff).getSalary()), numberStyle);
            table.addCell(String.valueOf(staff.pay()), numberStyle);
        }
        else if (staff instanceof SalariesEmployee) {
            table = new Table(7, BorderStyle.UNICODE_ROUND_BOX, ShownBorders.ALL);
            table.setColumnWidth(0,20,30);
            table.setColumnWidth(1,15,20);
            table.setColumnWidth(2,15,20);
            table.setColumnWidth(3,15,20);
            table.setColumnWidth(4,15,20);
            table.setColumnWidth(5,15,20);
            table.setColumnWidth(6,15,20);

            table.addCell("Type", numberStyle);
            table.addCell("ID", numberStyle);
            table.addCell("Name", numberStyle);
            table.addCell("Address", numberStyle);
            table.addCell("Salary", numberStyle);
            table.addCell("Bonus", numberStyle);
            table.addCell("Pay", numberStyle);

            table.addCell("Salaried Employee", numberStyle);
            table.addCell(String.valueOf(staff.getId()), numberStyle);
            table.addCell(staff.getName(), numberStyle);
            table.addCell(staff.getAddress(), numberStyle);
            table.addCell(String.valueOf(((SalariesEmployee) staff).getSalary()), numberStyle);
            table.addCell(String.valueOf(((SalariesEmployee) staff).getBunus()), numberStyle);
            table.addCell(String.valueOf(staff.pay()), numberStyle);
        }
        else if (staff instanceof HourlySalaryEmployee) {
            table = new Table(7, BorderStyle.UNICODE_ROUND_BOX, ShownBorders.ALL);
            table.setColumnWidth(0,20,30);
            table.setColumnWidth(1,15,20);
            table.setColumnWidth(2,15,20);
            table.setColumnWidth(3,15,20);
            table.setColumnWidth(4,15,20);
            table.setColumnWidth(5,15,20);
            table.setColumnWidth(6,15,20);

            table.addCell("Type", numberStyle);
            table.addCell("ID", numberStyle);
            table.addCell("Name", numberStyle);
            table.addCell("Address", numberStyle);
            table.addCell("Hours Worked", numberStyle);
            table.addCell("Rate", numberStyle);
            table.addCell("Pay", numberStyle);

            table.addCell("Hourly Employee", numberStyle);
            table.addCell(String.valueOf(staff.getId()), numberStyle);
            table.addCell(staff.getName(), numberStyle);
            table.addCell(staff.getAddress(), numberStyle);
            table.addCell(String.valueOf(((HourlySalaryEmployee) staff).getHourWorked()), numberStyle);
            table.addCell(String.valueOf(((HourlySalaryEmployee) staff).getRate()), numberStyle);
            table.addCell(String.valueOf(staff.pay()), numberStyle);
        }
        else {
            return;
        }
        System.out.println(table.render());
    }

    public void displayUpdatedStaff(StaffMember staff) {
        displayStaff(staff);
    }

    public void removeEmplyee(){
        int removeID;
        try {
            System.out.println("==============>> Remove Employee <<==============\n");
            System.out.print("Enter ID to Remove : ");
            removeID = sc.nextInt();
            sc.nextLine();
            boolean removed = staffData.removeIf(staff -> staff.getId() == removeID);

            if (removed) {
                System.out.println("ID " + removeID + " has been removed successfully.");
            } else {
                System.out.println("Employee ID not found. Please try again.");
            }
        } catch (Exception e) {
            System.out.println("Error :" + e.getMessage());
            e.printStackTrace();
        }
    }
}
