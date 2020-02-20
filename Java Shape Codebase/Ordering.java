package shapes;

import java.util.*;

public class Ordering {

    static class XLocationComparator implements Comparator<TwoDShape> {
        @Override 
        public int compare(TwoDShape o1, TwoDShape o2) { 
        	// TODO
        	double o1x = 0;
        	if(o1 instanceof Circle)
        	{
        		Circle temp = (Circle)o1;
        		o1x = temp.center().coordinates()[0] - temp.getRadius();
        	}
        	else if(o1 instanceof Quadrilateral)
        	{
        		Quadrilateral temp = (Quadrilateral)o1;
        		double min = temp.getVertices()[0].getx();
        		for(int i = 1; i < 4; i++)
        		{
        			if(min > temp.getVertices()[i].getx())
        			{
        				min = temp.getVertices()[i].getx();
        			}
        		}
        		o1x = min;
        	}
        	
        	double o2x = 0;
        	if(o2 instanceof Circle)
        	{
        		Circle temp = (Circle)o2;
        		o2x = temp.center().coordinates()[0] - temp.getRadius();
        	}
        	else if(o2 instanceof Quadrilateral)
        	{
        		Quadrilateral temp = (Quadrilateral)o2;
        		double min = temp.getVertices()[0].getx();
        		for(int i = 1; i < 4; i++)
        		{
        			if(min > temp.getVertices()[i].getx())
        			{
        				min = temp.getVertices()[i].getx();
        			}
        		}
        		o2x = min;
        	}
        	if(o1x > o2x) return 1;
        	else if(o1x < o2x) return -1;
        	else return 0;
        }
    }

    static class AreaComparator implements Comparator<SymmetricTwoDShape> {
        @Override 
        public int compare(SymmetricTwoDShape o1, SymmetricTwoDShape o2) {
        	// TODO
        	if(o1.area() > o2.area()) return 1;
        	else if(o1.area() < o2.area()) return -1;
        	else return 0;
        }
    }

    static class SurfaceAreaComparator implements Comparator<ThreeDShape> {
        @Override public int compare(ThreeDShape o1, ThreeDShape o2) { 
        	// TODO
        		
        	SurfaceAreaShape shape1 = (SurfaceAreaShape)o1;
        	SurfaceAreaShape shape2 = (SurfaceAreaShape)o2;
        	
        	if(shape1.surfaceArea() > shape2.surfaceArea()) return 1;
        	else if(shape1.surfaceArea() < shape2.surfaceArea()) return -1;
        	else return 0;
        }
    }

    // TODO: there's a lot wrong with this method. correct it so that it can work properly with generics.
    static <T> void copy(Collection<? extends T> source, Collection<T> destination) 
    {
        destination.addAll(source);
    }

    public static void main(String[] args) {
        List<TwoDShape>          shapes          = new ArrayList<>();
        List<SymmetricTwoDShape> symmetricshapes = new ArrayList<>();
        List<ThreeDShape>        threedshapes    = new ArrayList<>();

        /*
         * uncomment the following block and fill in the "..." constructors to create actual instances. If your
         * implementations are correct, then the code should compile and yield the expected results of the various
         * shapes being ordered by their smallest x-coordinate, area, volume, surface area, etc. */
        

        List<TwoDPoint> rectCoordinatesTemp = new ArrayList<>();
        rectCoordinatesTemp.add(new TwoDPoint(2, 1));
        rectCoordinatesTemp.add(new TwoDPoint(-2, 1));
        rectCoordinatesTemp.add(new TwoDPoint(-2, -1));
        rectCoordinatesTemp.add(new TwoDPoint(2, -1));
        
        symmetricshapes.add(new Rectangle(rectCoordinatesTemp));
        
        List<TwoDPoint> squareCoordinatesTemp = new ArrayList<>();
        
        
        squareCoordinatesTemp.add(new TwoDPoint(0.5, 0.5));
        squareCoordinatesTemp.add(new TwoDPoint(-0.5, 0.5));
        squareCoordinatesTemp.add(new TwoDPoint(-0.5, -0.5));
        squareCoordinatesTemp.add(new TwoDPoint(0.5, -0.5));
        
        symmetricshapes.add(new Square(squareCoordinatesTemp));
        
        symmetricshapes.add(new Circle(1, 1, 2));

        copy(symmetricshapes, shapes); // note-1 //
        //shapes.add(new Quadrilateral(new ArrayList<>()));
          
        // sorting 2d shapes according to various criteria
        shapes.sort(new XLocationComparator());
        symmetricshapes.sort(new XLocationComparator());
        symmetricshapes.sort(new AreaComparator());
        // sorting 3d shapes according to various criteria
        Collections.sort(threedshapes);
        threedshapes.sort(new SurfaceAreaComparator());
        
        /*
         * if your changes to copy() are correct, uncommenting the following block will also work as expected note that
         * copy() should work for the line commented with 'note-1' while at the same time also working with the lines
         * commented with 'note-2' and 'note-3'. */

        
        List<Number> numbers = new ArrayList<>();
        List<Double> doubles = new ArrayList<>();
        Set<Square>        squares = new HashSet<>();
        Set<Quadrilateral> quads   = new LinkedHashSet<>();
        

        copy(doubles, numbers); // note-2 //
        copy(squares, quads);   // note-3 //
    }
}
