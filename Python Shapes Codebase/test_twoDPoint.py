from unittest import TestCase

from two_d_point import TwoDPoint


class TestTwoDPoint(TestCase):

    def test_from_coordinates(self):
        self.assertEqual(TwoDPoint.from_coordinates([1, 2]), [TwoDPoint(1, 2)])
        self.assertEqual(TwoDPoint.from_coordinates([1, 2, 3, 4]), [TwoDPoint(1, 2), TwoDPoint(3, 4)])
        self.assertEqual(TwoDPoint.from_coordinates([]), [])
        self.assertRaises(Exception, TwoDPoint.from_coordinates, [1, 2, 3])

    def test__eq__(self):
        self.assertFalse(TwoDPoint(2, 3).__eq__('Hello World'))
        self.assertFalse(TwoDPoint(2, 3).__eq__(None))
        self.assertTrue(TwoDPoint(2, 3).__eq__(TwoDPoint(2, 3)))
        self.assertFalse(TwoDPoint(2, 3).__eq__(TwoDPoint(1, 3)))

    def test__add__(self):
        self.assertEqual(TwoDPoint(2, 3).__add__('Hello World'), None)
        self.assertEqual(TwoDPoint(2, 3).__add__(None), None)
        self.assertEqual(TwoDPoint(4, 5).__add__(TwoDPoint(6, 7)), TwoDPoint(10, 12))
        self.assertNotEqual(TwoDPoint(8, 3).__add__(TwoDPoint(2, 9)), TwoDPoint(6, 7))

    def test__sub__(self):
        self.assertEqual(TwoDPoint(2, 3).__sub__('Hello World'), None)
        self.assertEqual(TwoDPoint(2, 3).__sub__(None), None)
        self.assertEqual(TwoDPoint(2, 3).__sub__(TwoDPoint(2, 3)), TwoDPoint(0, 0))
        self.assertNotEqual(TwoDPoint(2, 3).__sub__(TwoDPoint(1, 3)), TwoDPoint(0, 0))