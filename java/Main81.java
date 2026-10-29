import java.util.Scanner;
class dividebyzero extends Exception{
    dividebyzero(String s1){
        super(s1);
    }
}
class Main81{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        boolean ans = false;
        while (!ans) {
            try{
                int finalans ;
                System.out.println("enetr the number 1st: ");
                int num1 = Integer.parseInt(sc.nextLine());
                System.out.println("enetr yhe number 2nd: ");
                int num2 = Integer.parseInt(sc.nextLine());
                System.out.println("enetr the operation(+,-,*,/)");
                char ope1 = sc.nextLine().charAt(0);
                if(ope1=='+'){
                    finalans = num1+num2;
                }
                else if(ope1=='-'){
                    finalans=num1-num2;
                }
                else if(ope1=='*'){
                    finalans=num1*num2;
                }
                else if(ope1=='/'){
                    if(num2==0){
                        throw new dividebyzero("Cannot divide by zero.");
                    }
                    finalans = num1 / num2;
                }
                else {
                    System.out.println("Invalid operator.");
                        continue;
                }
                System.out.println("Result = " + finalans);
                ans = true;
            }
            catch (dividebyzero e) {
                System.out.println(e.getMessage());
            }
            catch (NumberFormatException e) {
                System.out.println("enter valide number");
            }
            finally{
                System.out.println("successful");
            }
        }
    }
}