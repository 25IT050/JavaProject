import java.nio.channels.Pipe;

abstract class Shape{
    abstract double area();
}
class Circle extends Shape{
    int radias;
    Circle(){
        radias=0;
    }
    Circle(int r){
        this.radias=r;
    }
    @Override
    double area(){
        return Math.PI*(radias*radias);
    }
}
class Rectangle extends Shape {
    int length , witdh;
    Rectangle(){
        length=0;
        witdh=0;
    }
    Rectangle(int l,int w){
        this.length=l;
        this.witdh=w;
    }
    @Override
    double area(){
        return length*witdh;
    }
}
class Triangle extends Shape {
    int base,hight;
    Triangle(){
        base=0;
        hight=0;
    }
    Triangle(int b,int h){
        this.base=b;
        this.hight=h;
    }
    @Override
    double area(){
        return (base*hight)/2.0;
    }
}

public class Main {
    public static void main(String[] args){
        Shape[] shapes = new Shape[3];
        shapes[0]=new Circle(5);
        shapes[1]=new Rectangle(5,5);
        shapes[2]=new Triangle(5,5);
        double total=0,large=0;
        for(int i=0;i<3;i++){
            total=total+shapes[i].area();
            System.out.printf("%.2f%n",shapes[i].area());
            if(large<shapes[i].area()){
                large=shapes[i].area();
            }
        }
        System.out.println("Total = " + total);
        System.out.println("Largest = " + large);
    }
}
