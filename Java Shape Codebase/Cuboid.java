package shapes;

import java.util.List;
import java.util.ArrayList;

// TODO : a missing interface method must be implemented in this class to make it compile. This must be in terms of volume().
public class Cuboid implements ThreeDShape, SurfaceAreaShape {

    private final ThreeDPoint[] vertices = new ThreeDPoint[8];

    /**
     * Creates a cuboid out of the list of vertices. It is expected that the vertices are provided in
     * the order as shown in the figure given in the homework document (from v0 to v7).
     * 
     * @param vertices the specified list of vertices in three-dimensional space.
     */
    public Cuboid(List<ThreeDPoint> vertices) {
        if (vertices.size() != 8)
            throw new IllegalArgumentException(String.format("Invalid set of vertices specified for %s",
                                                             this.getClass().getName()));
        int n = 0;
        for (ThreeDPoint p : vertices) this.vertices[n++] = p;
    }

    @Override
    public double volume() {
        // TODO
    	return sideLength(vertices[0], vertices[1]) * 
    			sideLength(vertices[0], vertices[3]) * sideLength(vertices[0], vertices[5]);
    }

    @Override
    public ThreeDPoint center() { 
        // TODO
    	return new ThreeDPoint(((vertices[0].getx()+vertices[7].getx())/2), ((vertices[0].gety()+vertices[7].gety())/2),
    			((vertices[0].getz()+vertices[7].getz())/2));
    }

	@Override
	public int compareTo(ThreeDShape cuboid) {
		// TODO Auto-generated method stub
		if(this.volume() > cuboid.volume()) return 1;
    	else if(this.volume() < cuboid.volume()) return -1;
    	else return 0;
	}
	
	public double sideLength(ThreeDPoint a, ThreeDPoint b) {
		// TODO
		double len = Math.sqrt((a.gety() - b.gety()) * (a.gety() - b.gety()) + 
					(a.getx() - b.getx()) * (a.getx() - b.getx()) + 
					(a.getz() - b.getz()) * (a.getz() - b.getz()));
        return len;
    }
	
	public Cuboid random() {
		double length = Math.random() * 100;
		double width = Math.random() * 100;
		double height = Math.random() * 100;
		// TODO Auto-generated method stub
		ThreeDPoint point = new ThreeDPoint(Math.random() * 100, Math.random() * 100, Math.random() * 100);
		List<ThreeDPoint> list = new ArrayList<>();
		list.add(point);
		
		list.add(new ThreeDPoint(list.get(0).getx(), list.get(0).gety() - length, list.get(0).getz()));
		
		list.add(new ThreeDPoint(list.get(1).getx(), list.get(1).gety(), list.get(1).getz() - height));
		
		list.add(new ThreeDPoint(list.get(2).getx(), list.get(2).gety() + length, list.get(2).getz()));
		
		list.add(new ThreeDPoint(list.get(3).getx() - width, list.get(3).gety(), list.get(3).getz()));
		
		list.add(new ThreeDPoint(list.get(4).getx(), list.get(4).gety(), list.get(4).getz() + height));
		
		list.add(new ThreeDPoint(list.get(5).getx(), list.get(5).gety() - length, list.get(5).getz()));
		
		list.add(new ThreeDPoint(list.get(6).getx(), list.get(6).gety(), list.get(6).getz() - height));
		
		return new Cuboid(list);
	}

	@Override
	public double surfaceArea() {
		// TODO
		return (2 * sideLength(vertices[0], vertices[1]) * sideLength(vertices[0], vertices[3])) +
				(2 * sideLength(vertices[0], vertices[1]) * sideLength(vertices[0], vertices[5])) + 
    			(2 * sideLength(vertices[0], vertices[3]) * sideLength(vertices[0], vertices[5]));
		
	}
}
