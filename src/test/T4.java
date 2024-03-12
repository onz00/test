package test;

import test.T4Bag.Shape;
import test.T4Bag.circle;
import test.T4Bag.parallelogram;
import test.T4Bag.triangle;

public class T4 {
    public static void main(String[] args) {
        triangle t=new triangle(1.3,1.3,3,1,2,3);
        double triangleArea=getArea(t);
        double trianglePerimeter=getPerimeter(t);
        getMessage(t,triangleArea,trianglePerimeter);
        System.out.println("----------------------------------");
        circle c=new circle(1.4,1.2,3.3);
        double circleArea=getArea(c);
        double circlePerimeter=getPerimeter(c);
        getMessage(c,circleArea,circlePerimeter);
        System.out.println("----------------------------------");
        parallelogram p=new parallelogram(1,1,2,1,2,3,3,3);
        double parallelogramArea=getArea(p);
        double parallelogramPerimeter=getPerimeter(p);
        getMessage(p,parallelogramArea,parallelogramPerimeter);
    }
    //下面是利用多态的方式：（根据不同的子类调用不同的方法，形参为父类）
    public static double getArea(Shape s){
        return s.Area();
    }
    public static double getPerimeter(Shape s){
        return s.perimeter();
    }
    public static void getMessage(Shape s,double area,double perimeter){
        System.out.println(s);
        System.out.println("面积为："+area+"  周长为："+perimeter);
    }
}
