from unittest import TestCase

from quadrilateral import Quadrilateral
from rectangle import Rectangle
from square import Square


class TestQuadrilateral(TestCase):

    def test_side_lengths(self):
        self.assertEqual(Quadrilateral(-3, 2, 3, 2, 3, -2, -3, -2).side_lengths(), (6, 4, 6, 4))
        self.assertNotEqual(Quadrilateral(-3, 2, 3, 2, 3, -2, -3, -2).side_lengths(), (6, 1, 6, 4))

    def test_smallest_x(self):
        self.assertEqual(Quadrilateral(1, 2, 3, 4, -6, 5, 6, 7).smallest_x(), -6)
        self.assertNotEqual(Quadrilateral(1, 1, 1, 1, -1, 0, 4, 1).smallest_x(), 0)

    def test__eq__(self):
        self.assertTrue(Quadrilateral(1, 2, 3, 4, -6, 5, 6, 7).__eq__(Quadrilateral(1, 2, 3, 4, -6, 5, 6, 7)))
        self.assertFalse(Quadrilateral(1, 2, 3, 4, -6, 5, 6, 7).__eq__(Quadrilateral(1, 2, 0, 4, -6, 5, 6, 7)))
        self.assertFalse(Quadrilateral(-3, 2, 3, 2, 3, -2, -3, -2).__eq__(Rectangle(-3, 2, 3, 2, 3, -2, -3, -2)))
        self.assertFalse(Quadrilateral(-3, 2, 3, 2, 3, -2, -3, -2).__eq__(Square(-2, 2, 2, 2, 2, -2, -2, -2)))
        self.assertFalse(Quadrilateral(1, 2, 3, 4, -6, 5, 6, 7).__eq__("hello"))

    def test__ne__(self):
        self.assertFalse(Quadrilateral(1, 2, 3, 4, -6, 5, 6, 7).__ne__(Quadrilateral(1, 2, 3, 4, -6, 5, 6, 7)))
        self.assertTrue(Quadrilateral(1, 2, 3, 4, -6, 5, 6, 7).__ne__(Quadrilateral(1, 2, 0, 4, -6, 5, 6, 7)))
        self.assertTrue(Quadrilateral(-3, 2, 3, 2, 3, -2, -3, -2).__ne__(Rectangle(-3, 2, 3, 2, 3, -2, -3, -2)))
        self.assertTrue(Quadrilateral(-3, 2, 3, 2, 3, -2, -3, -2).__ne__(Square(-2, 2, 2, 2, 2, -2, -2, -2)))
        self.assertTrue(Quadrilateral(1, 2, 3, 4, -6, 5, 6, 7).__ne__("hello"))

    def test__str__(self):
        self.assertEqual(Quadrilateral(1, 2, 3, 4, -6, 5, 6, 7).__str__(), "type: <class 'quadrilateral.Quadrilateral"
                                                                           "'> vertices:  (1, 2), (3, 4), (-6, 5), "
                                                                           "(6, 7),")
        self.assertNotEqual(Quadrilateral(1, 2, 3, 4, -6, 5, 6, 7).__str__(), "type:,")
