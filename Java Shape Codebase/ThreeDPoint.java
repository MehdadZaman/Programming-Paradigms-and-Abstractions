package shapes;

/**
 * An unmodifiable point in the three-dimensional space. The coordinates are specified by exactly three doubles (its
 * <code>x</code>, <code>y</code>, and <code>z</code> values).
 */
public class ThreeDPoint implements Point {
	
		private double x;
		private double y;
		private double z;

    public ThreeDPoint(double x, double y, double z) {
        // TODO
    	this.x = x;
    	this.y = y;
    	this.z = z;
    }

    /**
     * @return the (x,y,z) coordinates of this point as a <code>double[]</code>.
     */
    @Override
    public double[] coordinates() { 
    	// TODO
    	return new double[] {x,y,z};
    }
    
    public double getx()
    {
    	return this.x;
    }
    
    public void setx(double x)
    {
    	this.x = x;
    }
    
    public double gety()
    {
    	return this.y;
    }
    
    public void sety(double y)
    {
    	this.y = y;
    }
    
    public double getz()
    {
    	return this.z;
    }
    
    public void setz(double z)
    {
    	this.z = z;
    }
}