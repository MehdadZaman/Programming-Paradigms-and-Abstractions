package shapes;

import java.util.List;

public class Sphere implements ThreeDShape, SurfaceAreaShape {
	
	private ThreeDPoint center;
    private double radius;
    
    public Sphere(ThreeDPoint point, double radius) {
        this.center = point;
        this.radius = radius;
    }
	
    public Sphere(double x, double y, double z, double radius) {
    	this.center = new ThreeDPoint(x, y, z);
        this.radius = radius;
    }
    
	@Override
	public int compareTo(ThreeDShape sphere) {
		// TODO Auto-generated method stub
		if(this.volume() > sphere.volume()) return 1;
    	else if(this.volume() < sphere.volume()) return -1;
    	else return 0;
	}

	@Override
	public Point center() {
		// TODO Auto-generated method stub
		return this.center;
	}

	@Override
	public double volume() {
		// TODO Auto-generated method stub
		return (int)((4/3) * Math.PI * Math.pow(radius, 3));
	}
	
	public Sphere random() {
		// TODO Auto-generated method stub
		return new Sphere(Math.random() * 100, Math.random() * 100,
						Math.random() * 100, Math.random() * 100);
	}

	@Override
	public double surfaceArea() {
		// TODO
		return 4 * Math.PI * Math.pow(this.radius, 2);
	}

}
