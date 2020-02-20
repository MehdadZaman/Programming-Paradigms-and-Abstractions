from typing import List


class TwoDPoint:

    def __init__(self, x, y) -> None:
        self.__x = x
        self.__y = y

    @property
    def x(self) -> float:
        return self.__x

    @property
    def y(self) -> float:
        return self.__y

    def __eq__(self, other: object) -> bool:
        try:
            return (self.__x, self.__y) == (other.x, other.y)
        except:
            return False

    def __ne__(self, other: object) -> bool:
        return not self.__eq__(other)

    def __str__(self) -> str:
        return '(%g, %g)' % (self.__x, self.__y)

    # TODO: add magic methods such that two TwoDPoint objects can be added and subtracted coordinate-wise just by using
    #  syntax of the form p + q or p - q

    def __add__(self, other: object) -> 'TwoDPoint':
        try:
            return TwoDPoint(self.__x + other.x, self.__y + other.y)
        except:
            return None

    def __sub__(self, other: object) -> 'TwoDPoint':
        try:
            return TwoDPoint(self.__x - other.x, self.__y - other.y)
        except:
            return None

    @staticmethod
    def from_coordinates(coordinates: List[float]) -> List['TwoDPoint']:
        if len(coordinates) % 2 != 0:
            raise Exception("Odd number of floats given to build a list of 2-d points")
        points = []
        it = iter(coordinates)
        for x in it:
            points.append(TwoDPoint(x, next(it)))
        return points
