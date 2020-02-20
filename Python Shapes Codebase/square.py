import math

from rectangle import Rectangle
from quadrilateral import Quadrilateral


class Square(Rectangle):

    def __init__(self, *floats) -> None:
        super().__init__(*floats)
        if not self.__is_member():
            raise TypeError("A square cannot be formed by the given coordinates.")

    def __is_member(self) -> bool:
        """Returns True if the given coordinates form a valid rectangle, and False otherwise."""
        return (self.area() != 0) and (Square.isSquare(self.vertices[0].x, self.vertices[0].y,
                                                       self.vertices[1].x, self.vertices[1].y,
                                                       self.vertices[2].x, self.vertices[2].y,
                                                       self.vertices[3].x, self.vertices[3].y))

    @staticmethod
    def isSquare(x1, y1, x2, y2, x3, y3, x4, y4) -> bool:
        """Returns True if the given coordinates form a valid rectangle, and False otherwise."""
        d1 = math.sqrt(math.pow(x1 - x2, 2) + math.pow(y1 - y2, 2))
        d2 = math.sqrt(math.pow(x2 - x3, 2) + math.pow(y2 - y3, 2))
        d3 = math.sqrt(math.pow(x3 - x4, 2) + math.pow(y3 - y4, 2))
        d4 = math.sqrt(math.pow(x4 - x1, 2) + math.pow(y4 - y1, 2))

        return (d1 == d2) and (d2 == d3) and (d3 == d4) and (d4 == d1)

    def snap(self) -> Quadrilateral:
        """Snaps the sides of the square such that each corner (x,y) is modified to be a corner (x',y') where x' is the
        integer value closest to x and y' is the integer value closest to y. This, of course, may change the shape to a
        general quadrilateral, hence the return type. The only exception is when the square is positioned in a way where
        this approximation will lead it to vanish into a single point. In that case, a call to snap() will not modify
        this square in any way."""

        tempVertices = self.vertices

        vertex1 = (math.floor(tempVertices[0].x + 0.5), math.floor(tempVertices[0].y + 0.5))
        vertex2 = (math.floor(tempVertices[1].x + 0.5), math.floor(tempVertices[1].y + 0.5))
        vertex3 = (math.floor(tempVertices[2].x + 0.5), math.floor(tempVertices[2].y + 0.5))
        vertex4 = (math.floor(tempVertices[3].x + 0.5), math.floor(tempVertices[3].y + 0.5))

        if (vertex1 == vertex2) and (vertex2 == vertex3) and (vertex3 == vertex4) and (vertex4 == vertex1):
            return Quadrilateral(tempVertices[0].x, tempVertices[0].y,
                                 tempVertices[1].x, tempVertices[1].y,
                                 tempVertices[2].x, tempVertices[2].y,
                                 tempVertices[3].x, tempVertices[3].y)
        else:
            return Quadrilateral(math.floor(tempVertices[0].x + 0.5), math.floor(tempVertices[0].y + 0.5),
                                 math.floor(tempVertices[1].x + 0.5), math.floor(tempVertices[1].y + 0.5),
                                 math.floor(tempVertices[2].x + 0.5), math.floor(tempVertices[2].y + 0.5),
                                 math.floor(tempVertices[3].x + 0.5), math.floor(tempVertices[3].y + 0.5))
