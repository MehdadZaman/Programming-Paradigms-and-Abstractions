import math

from quadrilateral import Quadrilateral
from two_d_point import TwoDPoint


class Rectangle(Quadrilateral):

    def __init__(self, *floats) -> None:
        super().__init__(*floats)
        if not self.__is_member():
            raise TypeError("A rectangle cannot be formed by the given coordinates.")

    def __is_member(self) -> bool:
        """Returns True if the given coordinates form a valid rectangle, and False otherwise."""
        return (self.area() != 0) and (Rectangle.isRectangle(self.vertices[0].x, self.vertices[0].y,
                                                             self.vertices[1].x, self.vertices[1].y,
                                                             self.vertices[2].x, self.vertices[2].y,
                                                             self.vertices[3].x, self.vertices[3].y))

    def center(self) -> TwoDPoint:
        """Returns the center of this rectangle, calculated to be the point of intersection of its diagonals."""
        temp = self.vertices
        return TwoDPoint(((temp[0].x + temp[2].x) / 2), ((temp[0].x + temp[2].x) / 2))

    def area(self) -> float:
        """Returns the area of this rectangle. The implementation invokes the side_lengths() method from the superclass,
        and computes the product of this rectangle's length and width."""
        side_lengths = super().side_lengths()
        return side_lengths[0] * side_lengths[1]

    @staticmethod
    def isRectangle(x1, y1, x2, y2, x3, y3, x4, y4) -> bool:
        """Returns True if the given coordinates form a valid rectangle, and False otherwise."""
        d1 = math.sqrt(math.pow(x1 - x2, 2) + math.pow(y1 - y2, 2))
        d2 = math.sqrt(math.pow(x2 - x3, 2) + math.pow(y2 - y3, 2))
        d3 = math.sqrt(math.pow(x3 - x4, 2) + math.pow(y3 - y4, 2))
        d4 = math.sqrt(math.pow(x4 - x1, 2) + math.pow(y4 - y1, 2))

        diag1 = math.sqrt(math.pow(x1 - x3, 2) + math.pow(y1 - y3, 2))
        diag2 = math.sqrt(math.pow(x2 - x4, 2) + math.pow(y2 - y4, 2))

        return (d1 == d3) and (d2 == d4) and (diag1 == diag2)
