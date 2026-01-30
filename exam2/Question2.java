/*
Sortting of employees records based on differnet parameters according to requirements.
Create and store employee records as a collection of object and the record format is employee name, jon, salary and date of joining.
Sort them based on:
1. Salary ascending order
2. Salary descending order
3. Date of joining ascending order
4. Date of joining descending order
Develop a menu driven program.
*/
import java.util.*;
class Employee{
    private String name;
    private double salary;
    private String job;
    private String dateOfJoining;

    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setSalary(double salary){
        this.salary = salary;
    }
    public double getSalary(){
        return this.salary;
    }
    public void setJob(String job){
        this.job = job;
    }
    public String getJob(){
        return this.job;
    }
    public void setDateOfJoining(String dateOfJoining){
        this.dateOfJoining = dateOfJoining;
    }
    public String getDateOfJoining(){
        return this.dateOfJoining;
    }
}
public class Question2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of employees:");
        int n = sc.nextInt();
        sc.nextLine();
        List<Employee> emp = new ArrayList<>();
        for(int i = 0; i < n; i++){
            Employee employee = new Employee();
            System.out.println("Enter employee name:");
            employee.setName(sc.nextLine());
            System.out.println("Enter employee job:");
            employee.setJob(sc.nextLine());
            System.out.println("Enter employee salary:");
            employee.setSalary(sc.nextDouble());
            sc.nextLine();
            System.out.println("Enter employee date of joining:");
            employee.setDateOfJoining(sc.nextLine());
            System.out.println("--------------------");
            emp.add(employee);
        }
        while(true){
            System.out.println("-----MENU-----");
            System.out.println("1. Salary in ascending order");
            System.out.println("2. Salary in descending order");
            System.out.println("3. Date of joining in ascending order");
            System.out.println("4. Date of joining in descending order");
            System.out.println("5. Exit");
            int choice = sc.nextInt();
            sc.nextLine();

            if(choice == 1){
                Salary.salaryAscendingOrder(emp);
            }
            else if(choice == 2){
                Salary.salaryDescendingOrder(emp);
            }
            else if (choice == 3){
                DateOfJoining.dateOfJoiningAscendingOrder(emp);
            }
            else if(choice == 4){
                DateOfJoining.dateOfJoiningDescendingOrder(emp);
            }
            else if(choice == 5){
                break;
            }
            else{
                System.out.println("Invalid Input");
            }
        }
        // for(Employee employee : emp){
        //     System.out.println(employee.getName());
        // }
        sc.close();
    }
}

class Salary{

    static void salaryAscendingOrder(List<Employee> emp){
        Collections.sort(emp, new Comparator<Employee>(){
            public int compare(Employee e1, Employee e2) {
                return Double.compare(e1.getSalary(), e2.getSalary());
            }
            
        });

        for(Employee employee : emp){
            System.out.println(employee.getName()+" "+employee.getSalary());
        }
    }

    static void salaryDescendingOrder(List<Employee> emp){
        Collections.sort(emp, new Comparator<Employee>(){
            public int compare(Employee e1, Employee e2) {
                return Double.compare(e2.getSalary(), e1.getSalary());
            }
            
        });

        for(Employee employee : emp){
            System.out.println(employee.getName()+" "+employee.getSalary());
        }
    }    
}

class DateOfJoining{

    static void dateOfJoiningAscendingOrder(List<Employee> emp){
        Collections.sort(emp, new Comparator<Employee>(){
            public int compare(Employee e1, Employee e2) {
                //return  (int)e1.getDateOfJoining().substring(6,10).compareTo(e2.getDateOfJoining().substring(6,10));

                String s1[] = e1.getDateOfJoining().split("-");
                String s2[] = e2.getDateOfJoining().split("-");

                if(!s1[2].equals(s2[2])){
                    return s1[2].compareTo(s2[2]);
                }
                else{
                    if(!s1[1].equals(s2[1])){
                        return s1[1].compareTo(s2[1]);
                    }
                    else{
                        return s1[0].compareTo(s2[0]);
                    }
                }

            }
            
        });

        for(Employee employee : emp){
            System.out.println(employee.getName()+" "+employee.getDateOfJoining());
        }
    }

    static void dateOfJoiningDescendingOrder(List<Employee> emp){
        Collections.sort(emp, new Comparator<Employee>(){
            public int compare(Employee e1, Employee e2) {
                //return  (int)e2.getDateOfJoining().substring(6,10).compareTo(e1.getDateOfJoining().substring(6,10));

                String s1[] = e1.getDateOfJoining().split("-");
                String s2[] = e2.getDateOfJoining().split("-");

                if(!s1[2].equals(s2[2])){
                    return s2[2].compareTo(s1[2]);
                }
                else{
                    if(!s1[1].equals(s2[1])){
                        return s2[1].compareTo(s1[1]);
                    }
                    else{
                        return s2[0].compareTo(s1[0]);
                    }
                }
            }
            
        });

        for(Employee employee : emp){
            System.out.println(employee.getName()+" "+employee.getDateOfJoining());
        }
    }
}
