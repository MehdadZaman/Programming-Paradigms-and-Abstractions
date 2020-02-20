from unittest import TestCase

from quadrilateral import Quadrilateral
from rectangle import Rectangle
from square import Square


class TestSquare(TestCase):

    def test__is_member(self):
        self.assertRaises(Exception, Square, (3, 2, 3, 2, 3, 2, 3, 2))
        self.assertRaises(Exception, Square, (-3, 2, 3, 2, 3, -2, -3, -1))

    def test_is_Square(self):
        self.assertTrue(Square.isSquare(-2, 2, 2, 2, 2, -2, -2, -2))
        self.assertFalse(Square.isSquare(-3, 2, 3, 2, 3, -2, -3, -2))
        self.assertFalse(Square.isSquare(-3, 2, 3, 2, 3, -2, -3, -1))

    def test__eq__(self):
        self.assertTrue(Square(-2, 2, 2, 2, 2, -2, -2, -2).__eq__(Square(-2, 2, 2, 2, 2, -2, -2, -2)))
        self.assertTrue(Square(-2.2, 2.2, 2.2, 2.2, 2.2, -2.2, -2.2, -2.2).__eq__(
            Square(-2.2, 2.2, 2.2, 2.2, 2.2, -2.2, -2.2, -2.2)))
        self.assertFalse(Square(-2.2, 2.2, 2.2, 2.2, 2.2, -2.2, -2.2, -2.2).__eq__(Square(-2, 2, 2, 2, 2, -2, -2, -2)))
        self.assertFalse(Square(-2, 2, 2, 2, 2, -2, -2, -2).__eq__(Square(-1, 1, 1, 1, 1, -1, -1, -1)))
        self.assertFalse(Square(-2, 2, 2, 2, 2, -2, -2, -2).__eq__(Quadrilateral(-2, 2, 2, 2, 2, -2, -2, -2)))
        self.assertFalse(Square(-1, 1, 1, 1, 1, -1, -1, -1).__eq__(Rectangle(-1, 1, 1, 1, 1, -1, -1, -1)))
        self.assertFalse(Square(-2, 2, 2, 2, 2, -2, -2, -2).__eq__("hello"))

    def test__ne__(self):
        self.assertFalse(Square(-2, 2, 2, 2, 2, -2, -2, -2).__ne__(Square(-2, 2, 2, 2, 2, -2, -2, -2)))
        self.assertFalse(Square(-2.2, 2.2, 2.2, 2.2, 2.2, -2.2, -2.2, -2.2).__ne__(
            Square(-2.2, 2.2, 2.2, 2.2, 2.2, -2.2, -2.2, -2.2)))
        self.assertTrue(Square(-2.2, 2.2, 2.2, 2.2, 2.2, -2.2, -2.2, -2.2).__ne__(Square(-2, 2, 2, 2, 2, -2, -2, -2)))
        self.assertTrue(Square(-2, 2, 2, 2, 2, -2, -2, -2).__ne__(Square(-1, 1, 1, 1, 1, -1, -1, -1)))
        self.assertTrue(Square(-2, 2, 2, 2, 2, -2, -2, -2).__ne__(Quadrilateral(-2, 2, 2, 2, 2, -2, -2, -2)))
        self.assertTrue(Square(-1, 1, 1, 1, 1, -1, -1, -1).__ne__(Rectangle(-1, 1, 1, 1, 1, -1, -1, -1)))
        self.assertTrue(Square(-2, 2, 2, 2, 2, -2, -2, -2).__ne__("hello"))

    def test__str__(self):
        self.assertEqual(Square(-2, 2, 2, 2, 2, -2, -2, -2).__str__(), "type: <class 'square.Square"
                                                                       "'> vertices:  (-2, 2), (2, 2), (2, -2), "
                                                                       "(-2, -2),")
        self.assertNotEqual(Square(-2, 2, 2, 2, 2, -2, -2, -2).__str__(), "type:,")

    def test__snap__(self):
        self.assertEqual(Square(-2.2, 2.2, 2.2, 2.2, 2.2, -2.2, -2.2, -2.2).snap(),
                         Quadrilateral(-2, 2, 2, 2, 2, -2, -2, -2))
        self.assertEqual(Square(-0.2, 0.2, 0.2, 0.2, 0.2, -0.2, -0.2, -0.2).snap(),
                         Quadrilateral(-0.2, 0.2, 0.2, 0.2, 0.2, -0.2, -0.2, -0.2))
