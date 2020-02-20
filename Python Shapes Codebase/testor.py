from quadrilateral import Quadrilateral
from two_d_point import TwoDPoint
from rectangle import Rectangle
from sorter import ShapeSorter
from square import Square


def change(b, a):
    print(a ** b)


def pt(c, a=1, b=2):
    print(a)
    print(b)
    print(c)


def pt(c=2, a=1, b=2):
    print(a)
    print(b)
    print(c)



def f(*args, **kwargs):
    print(args, kwargs)


def main():
    print("Hello World")


if __name__ == "__main__":
    lst = [1, 2, 3, 4]
    print(lst[-1:-4:-1])