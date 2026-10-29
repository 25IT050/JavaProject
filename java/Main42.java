abstract class Employee {
    String name ;
    int id;
    Employee(){
        name=null;
        id=0;
    }
    Employee(String n,int id){
        this.name=n;
        this.id=id;
    }
    abstract double monthlySalary();
}
class FullTime extends Employee{
    double fixd;
    FullTime(){
        super();
        fixd=00;
    }
    FullTime(String n,int id,double s){
        super(n,id);
        this.fixd=s;
    }
    @Override
    double monthlySalary(){
        return fixd;
    }
}
class PartTime extends Employee{
    int hours;
    double rate;
    PartTime(){
        super();
        hours=0;
        rate=0;
    }
    PartTime(String n, int id,int h,double rate){
        super(n,id);
        this.hours=h;
        this.rate=rate;
    }
    @Override
    double monthlySalary(){
        return hours*rate;
    }
}
class Intern extends Employee{
    double stipend;
    Intern(){
        super();
        stipend=0;
    }
    Intern(String n,int id,double stipend){
        super(n,id);
        this.stipend=stipend;
    }
    @Override
    double monthlySalary(){
        return stipend;
    }
}
public class Main42 {
    public static void main(String[] args){
         Employee[] emp = new Employee[3];

        emp[0] = new FullTime("Bhargavi", 101, 50000);
        emp[1] = new PartTime("Rahul", 102, 80, 200);
        emp[2] = new Intern("Priya", 103, 8000);

        double total = 0;

        for (int i = 0; i < emp.length; i++) {

            System.out.println("Name : " + emp[i].name);
            System.out.println("ID : " + emp[i].id);
            System.out.println("Salary : " + emp[i].monthlySalary());

            total += emp[i].monthlySalary();

            if (emp[i] instanceof Intern) {
                System.out.println("Note : This employee is an Intern.");
            }

            System.out.println();
        }

        System.out.println("Total Salary = " + total);
    }
}
