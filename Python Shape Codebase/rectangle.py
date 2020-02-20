from .quadrilateral import Quadrilateral
from .two_d_point import TwoDPoint


class Rectangle(Quadrilateral):

    def __init__(self, *floats):
        super().__init__(*floats)
        if not self.__is_member():
            raise TypeError("A rectangle cannot be formed by the given coordinates.")

    def __is_member(self):
        """Returns True if the given coordinates form a valid rectangle, and False otherwise."""
        return False  # TODO

    def center(self):
        """Returns the center of this rectangle, calculated to be the point of intersection of its diagonals."""
        return TwoDPoint(0, 0)  # TODO

    def area(self):
        """Returns the area of this rectangle. The implementation invokes the side_lengths() method from the superclass,
        and computes the product of this rectangle's length and width."""
        return 0  # TODO
