from unittest import TestCase

from quadrilateral import Quadrilateral
from rectangle import Rectangle
from square import Square
from two_d_point import TwoDPoint


class TestRectangle(TestCase):

    def test__is_member(self):
        self.assertRaises(Exception, Rectangle, (3, 2, 3, 2, 3, 2, 3, 2))
        self.assertRaises(Exception, Rectangle, (-3, 2, 3, 2, 3, -2, -3, -1))


    def test_is_Rectangle(self):
        self.assertTrue(Rectangle.isRectangle(-3, 2, 3, 2, 3, -2, -3, -2))
        self.assertFalse(Rectangle.isRectangle(-3, 2, 3, 2, 3, -2, -3, -1))

    def test_center(self):
        self.assertEqual(Rectangle(-3, 2, 3, 2, 3, -2, -3, -2).center(), TwoDPoint(0, 0))
        self.assertNotEqual(Rectangle(-3, 2, 3, 2, 3, -2, -3, -2).center(), TwoDPoint(0, -1))

    def test_area(self):
        self.assertEqual(Rectangle(-3, 2, 3, 2, 3, -2, -3, -2).area(), 24)
        self.assertNotEqual(Rectangle(-3, 2, 3, 2, 3, -2, -3, -2).area(), -24)
        self.assertNotEqual(Rectangle(-3, 2, 3, 2, 3, -2, -3, -2).area(), 20)

    def test__eq__(self):
        self.assertTrue(Rectangle(-3, 2, 3, 2, 3, -2, -3, -2).__eq__(Rectangle(-3, 2, 3, 2, 3, -2, -3, -2)))
        self.assertFalse(Rectangle(-3, 2, 3, 2, 3, -2, -3, -2).__eq__(Rectangle(-1, 2, 1, 2, 1, -2, -1, -2)))
        self.assertFalse(Rectangle(-3, 2, 3, 2, 3, -2, -3, -2).__eq__(Quadrilateral(-3, 2, 3, 2, 3, -2, -3, -2)))
        self.assertFalse(Rectangle(-1, 1, 1, 1, 1, -1, -1, -1).__eq__(Square(-1, 1, 1, 1, 1, -1, -1, -1)))
        self.assertFalse(Rectangle(-3, 2, 3, 2, 3, -2, -3, -2).__eq__("hello"))

    def test__ne__(self):
        self.assertFalse(Rectangle(-3, 2, 3, 2, 3, -2, -3, -2).__ne__(Rectangle(-3, 2, 3, 2, 3, -2, -3, -2)))
        self.assertTrue(Rectangle(-3, 2, 3, 2, 3, -2, -3, -2).__ne__(Rectangle(-1, 2, 1, 2, 1, -2, -1, -2)))
        self.assertTrue(Rectangle(-3, 2, 3, 2, 3, -2, -3, -2).__ne__(Quadrilateral(-3, 2, 3, 2, 3, -2, -3, -2)))
        self.assertTrue(Rectangle(-1, 1, 1, 1, 1, -1, -1, -1).__ne__(Square(-1, 1, 1, 1, 1, -1, -1, -1)))
        self.assertTrue(Rectangle(-3, 2, 3, 2, 3, -2, -3, -2).__ne__("hello"))

    def test__str__(self):
        self.assertEqual(Rectangle(-3, 2, 3, 2, 3, -2, -3, -2).__str__(), "type: <class 'rectangle.Rectangle"
                                                                          "'> vertices:  (-3, 2), (3, 2), (3, -2), "
                                                                          "(-3, -2),")
        self.assertNotEqual(Rectangle(-3, 2, 3, 2, 3, -2, -3, -2).__str__(), "type:,")