package shapes;

import java.util.List;
import java.util.ArrayList;

public class Rectangle extends Quadrilateral implements SymmetricTwoDShape {
	
	public Rectangle(List<TwoDPoint> vertices) {
        super(vertices);
    }
	
    /**
     * The center of a rectangle is calculated to be the point of intersection of its diagonals.
     *
     * @return the center of this rectangle.
     */
    @Override
    public Point center() {
    	
    	// TODO
    	TwoDPoint[] temp = this.getVertices();
    	TwoDPoint ret = new TwoDPoint(((temp[0].getx()+temp[2].getx())/2), ((temp[0].gety()+temp[2].gety())/2));
    	return ret;
    	
    }

    @Override
    public boolean isMember(List<? extends Point> vertices) { 
    	// TODO
    	if(vertices.size() != 4) return false;
    	List<TwoDPoint> vertices2 = new ArrayList<>();
    	for(int i = 0; i < vertices.size(); i++)
    	{
    		if(vertices.get(i) instanceof TwoDPoint)
    		{
    			vertices2.add((TwoDPoint)vertices.get(i));
    		}
    		else
    		{
    			return false;
    		}
    	}
    	try
    	{
    		Quadrilateral temp = new Quadrilateral(vertices2);
    		
    		TwoDPoint[] vertexes = temp.getVertices();
    		
    		return temp.isRectangle(vertexes[0].getx(), vertexes[0].gety(), 
    								vertexes[1].getx(), vertexes[1].gety(), 
    								vertexes[2].getx(), vertexes[2].gety(), 
    								vertexes[3].getx(), vertexes[3].gety()) && temp.rectArea() > 0;
    	}
    	catch(Exception e)
    	{
    		return false;
    	}
    }

    @Override
    public double area() {
    	 // TODO
    	double[] lengths = this.getSideLengths();
        return lengths[0] * lengths[1];
    }
    
    public boolean isRectangle(double x1, double y1, double x2, double y2,
    		 double x3, double y3, double x4, double y4)
    {

    	double d1 = Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    	double d2 = Math.sqrt(Math.pow(x2 - x3, 2) + Math.pow(y2 - y3, 2));
    	double d3 = Math.sqrt(Math.pow(x3 - x4, 2) + Math.pow(y3 - y4, 2));
    	double d4 = Math.sqrt(Math.pow(x4 - x1, 2) + Math.pow(y4 - y1, 2));
    	
    	double diag1 = Math.sqrt(Math.pow(x1 - x3, 2) + Math.pow(y1 - y3, 2));
    	double diag2 = Math.sqrt(Math.pow(x2 - x4, 2) + Math.pow(y2 - y4, 2));

    	return (d1 == d3) && (d2 == d4) && (diag1 == diag2);
    }
    
}
