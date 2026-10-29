import java.util.Scanner;
enum Coin{
    ONE,TWO,FIVE,TEN
}
public class Pr1A1 {

    public static void main(String[] args){
        int total=15;
        int value=0;
        Scanner sc= new Scanner(System.in);
        while(value<total){
            String name =sc.next();
            Coin coin=Coin.valueOf(name);
            int sum=switch(coin){
                case ONE-> 1 ;
                case TWO-> 2 ;
                case FIVE-> 5 ;
                case TEN-> 10 ;
            };
            value+=sum;
            System.out.println("total = " + value);

        }
        System.out.println("Change = " +(total-value));
    }
}