abstract class Shape{
    abstract double area();
}
class Rectangle extends Shape{
    int length;
    int width;
    Rectangle(int length, int width){
        this.length = length;
        this.width = width;
    }
    @Override
    double area() {
        return length * width;
    }
}
class Triangle extends Shape{
    int height;
    int base;
    Triangle(int height, int base){
        this.height = height;
        this.base = base;
    }
    @Override
    double area() {
        return (height * base)/2.0;
    }
}
class Circle extends Shape{
    int radius;
    Circle(int radius){
        this.radius = radius;
    }
    @Override
    double area() {
        return radius * radius * Math.PI;
    }
}
public class Main41 {
    public static void main(String[] args) {
        Shape[] Shapes = new Shape[3];
        Shapes[0]=new Rectangle(5,5);
        Shapes[1]=new Triangle(5,5);
        Shapes[2]=new Circle(5);
        double sum=0;
        double larg = 0;
        for(int i=0;i<3;i++){
            System.out.printf("%.2f",Shapes[i].area());
            System.out.print("\n");
            sum+=Shapes[i].area();
            if(larg<Shapes[i].area()){
                larg=Shapes[i].area();
            }
        }
        System.out.println("The sum is: "+sum);
        System.out.println("The largest area: "+larg);
    }
}