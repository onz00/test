package test.T4Bag;

public class circle extends Shape {
    private double radius;

    public circle(){}

    public circle(double x, double y, double radius) {
        //这里直接用xy作为圆心坐标
        super(x, y);
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    @Override
    public double perimeter() {
        return 2*Math.PI*radius;
    }

    @Override
    public double Area() {
        return Math.PI*Math.pow(radius,2);
    }
    public String point(){
        double left=getX()-radius;
        double right=getX()+radius;
        double up=getY()+radius;
        double down=getY()-radius;

        return "四个极点的坐标为：("+right+","+getY()+"),"+"("+left+","+getY()+"),"+
                "("+getX()+radius+","+up+"),"+"("+getX()+radius+","+down+");";
    }

    @Override
    public String toString() {
        return "圆形：" +
                "圆心为（"+super.getX()+","+super.getY()+
                ");"+point()+
                "半径=" + radius
                ;
    }

}
