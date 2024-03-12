package test.T4Bag;

public class triangle extends Shape{
    private double x1,y1;
    private double x2,y2;
    private double x3,y3;

    public triangle(double x1, double y1, double x2, double y2, double x3, double y3) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;
    }
    public triangle(){}

    @Override
    public double perimeter() {
        double a=distance(x1,y1,x2,y2);
       double b= distance(x2,y2,x3,y3);
        double c=distance(x1,y1,x3,y3);
        return a+b+c;
    }
    public static double distance(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    @Override
    public double Area() {
        double a=distance(x1,y1,x2,y2);
        double b= distance(x2,y2,x3,y3);
        double c=distance(x1,y1,x3,y3);
        double s = (a + b + c) / 2;
        //本来打算用点坐标计算面积的，但关键涉及到了向量啥的有点麻烦，这个是利用边长直接求面积的
        return Math.sqrt(s * (s - a) * (s - b) * (s - c));
    }

    @Override
    public String toString() {
        return "三角形：" +
                "(" + x1 +
                ", " + y1 +
                ")( " + x2 +
                ", " + y2 +
                ")( " + x3 +
                ", " + y3 +
                ')';
    }
}
