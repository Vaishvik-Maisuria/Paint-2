

public class Triangle extends Shape{
	private int[] pointsx = {0, 0, 0};
	private int[] pointsy = {0, 0, 0};
	private Point start;
	private Point p2;
	private Point p3;
	
	
	public Triangle() {
		this(new Point(0,0));
	}
	
	public Triangle(Point start) {
		this.start = start;
		this.pointsx[0] = start.getX();
		this.pointsy[0] = start.getY();
	}
	
	
	public Point getStart() {
		return start;
	}
	
	public void SetStart(Point start) {
		this.start  = start;
		this.pointsx[0] = start.getX();
		this.pointsy[0] = start.getY();
	}
	
	public Point getPoint2() {
		return p2;
	}
	
	public void SetPoint2(Point point2) {
		this.p2  = point2;
		this.pointsx[1] = p2.getX();
		this.pointsy[1] = p2.getY();
	}
	
	public Point getPoint3() {
		return p3;	
	}
	
	public void SetPoint3(Point point3) {
		this.p3  = point3;
		this.pointsx[2] = p3.getX();
		this.pointsy[2] = p3.getY();
	}
	
	public int[] getPointsX() {
		return this.pointsx;
	}
	
	public int[] getPointsY() {
		return this.pointsy;
	}
	
}
