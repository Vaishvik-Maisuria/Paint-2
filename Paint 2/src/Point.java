

public class Point {
	int x, y; // Available to our package
	Point(int x, int y){
		this.x=x; this.y=y;
	}
	
	public int getX() {
		return x;
	}
	
	/**
	 * Sets an x value for the point
	 * @param x	The new x value for the point
	 */
	public void setX(int x) {
		this.x = x;
	}
	
	/**
	 * Return the y position of the point
	 * @return	y position
	 */
	public int getY() {
		return y;
	}

	/**
	 * Sets a y value for the point
	 * @param y	The new y value for the point
	 */
	public void setY(int y) {
		this.y = y;
	}
}
