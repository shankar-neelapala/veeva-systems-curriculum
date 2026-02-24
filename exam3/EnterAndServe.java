import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class EnterAndServe {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of operations: ");
        int n = sc.nextInt();
        sc.nextLine();
        List<Student> queue = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            System.out.println("Enter Operation type: ");
            String operation = sc.nextLine().toLowerCase();
            if(operation.equals("enter")){
                System.out.println("Enter name: ");
                String name = sc.nextLine();
                System.out.println("Enter cgpa: ");
                double cgpa = sc.nextDouble();
                sc.nextLine();
                System.out.println("Enter priority: ");
                int priority = sc.nextInt();
                sc.nextLine();
                queue.add(new Student(name, cgpa, priority));
            }
            else if(operation.equals("serve")){
                Collections.sort(queue, new SortStudents());
                System.out.println(queue.isEmpty() ? "Empty" : queue.remove(0));
            }
            else{
                System.out.println("Invalid operation");
            }
        }
        System.out.println("-------------------");
        for(Student s : queue){
            System.out.println(s.toString());
        }
        sc.close();
    }
}

class Student {
    String name;
    double cgpa;
    int priority;
    Student(String name, double cgpa, int priority){
        this.name = name;
        this.cgpa =  cgpa;
        this.priority = priority;
    }

    public String toString() {
        return name+" "+cgpa+" "+priority;
    }
}

class SortStudents implements Comparator<Student> {
    public int compare(Student s1, Student s2) {
        if (Double.compare(s2.cgpa, s1.cgpa) != 0) {
            return Double.compare(s2.cgpa, s1.cgpa);
        } else if (!s1.name.equals(s2.name)) {
            return s1.name.compareTo(s2.name);
        } else {
            return Integer.compare(s1.priority, s2.priority);
        }
    }
}