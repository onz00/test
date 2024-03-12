package test.T4Bag;

public class parallelogram extends Shape {
    private double x1,y1;
    private double x2,y2;
    private double x3,y3;
    private double x4,y4;
    public parallelogram(){}

    public parallelogram(double x1, double y1,
                         double x2, double y2, double x3, double y3, double x4, double y4) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;
        this.x4 = x4;
        this.y4 = y4;
    }

    public double getX1() {
        return x1;
    }

    public void setX1(double x1) {
        this.x1 = x1;
    }

    public double getY1() {
        return y1;
    }

    public void setY1(double y1) {
        this.y1 = y1;
    }

    public double getX2() {
        return x2;
    }

    public void setX2(double x2) {
        this.x2 = x2;
    }

    public double getY2() {
        return y2;
    }

    public void setY2(double y2) {
        this.y2 = y2;
    }

    public double getX3() {
        return x3;
    }

    public void setX3(double x3) {
        this.x3 = x3;
    }

    public double getY3() {
        return y3;
    }

    public void setY3(double y3) {
        this.y3 = y3;
    }

    public double getX4() {
        return x4;
    }

    public void setX4(double x4) {
        this.x4 = x4;
    }

    public double getY4() {
        return y4;
    }

    public void setY4(double y4) {
        this.y4 = y4;
    }

    @Override
    public double perimeter() {
        double a=triangle.distance(x1,y1,x2,y2);
        double b= triangle.distance(x1,y1,x4,y4);
        double c= triangle.distance(x2,y2,x3,y3);
        double d= triangle.distance(x3,y3,x4,y4);
        return a+b+c+d;
    }

    @Override
    public double Area() {
        double a=triangle.distance(x1,y1,x2,y2);
        double b= triangle.distance(x1,y1,x4,y4);
        double c= triangle.distance(x2,y2,x3,y3);
        double s = (a + b + c) / 2;
        //平行四边形的面积其实就是两个三角形的面积
        return Math.sqrt(s * (s - a) * (s - b) * (s - c))*2;
    }

    @Override
    public String toString() {
        return "平行四边形：" +
                "(" + x1 +
                ", " + y1 +
                ")( " + x2 +
                ", " + y2 +
                ")( " + x3 +
                ", " + y3 +
                "),"+"("+x4+
                ","+y4+")";
    }
}
