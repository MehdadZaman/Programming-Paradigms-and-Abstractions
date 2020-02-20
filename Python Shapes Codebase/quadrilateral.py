import math
from two_d_point import TwoDPoint
from typing import Tuple


class Quadrilateral:

    def __init__(self, *floats) -> None:
        points = TwoDPoint.from_coordinates(list(floats))
        self.__vertices = tuple(points[0:4])

    @property
    def vertices(self) -> Tuple[TwoDPoint]:
        return self.__vertices

    def side_lengths(self) -> Tuple[float]:
        """Returns a tuple of four floats, each denoting the length of a side of this quadrilateral. The value must be
        ordered clockwise, starting from the top left corner."""
        side0 = math.sqrt(math.pow(self.__vertices[0].x - self.vertices[1].x, 2) + math.pow(
            self.__vertices[0].y - self.vertices[1].y, 2))
        side1 = math.sqrt(math.pow(self.__vertices[1].x - self.vertices[2].x, 2) + math.pow(
            self.__vertices[1].y - self.vertices[2].y, 2))
        side2 = math.sqrt(math.pow(self.__vertices[2].x - self.vertices[3].x, 2) + math.pow(
            self.__vertices[2].y - self.vertices[3].y, 2))
        side3 = math.sqrt(math.pow(self.__vertices[3].x - self.vertices[0].x, 2) + math.pow(
            self.__vertices[3].y - self.vertices[0].y, 2))

        return side0, side1, side2, side3

    def smallest_x(self) -> float:
        """Returns the x-coordinate of the vertex with the smallest x-value of the four vertices of this
        quadrilateral."""
        min_coordinate = float("inf")
        for point in self.__vertices:
            if point.x < min_coordinate:
                min_coordinate = point.x

        return min_coordinate

    def __eq__(self, other: object) -> bool:
        try:
            return (type(self) == type(other)) and (self.vertices == other.vertices)
        except:
            return False

    def __ne__(self, other: object) -> bool:
        return not self.__eq__(other)

    def __str__(self) -> str:
        ret = 'type: ' + str(self.__class__) + " vertices: "
        for point in self.__vertices:
            ret = ret + " " + str(point) + ","
        return ret
