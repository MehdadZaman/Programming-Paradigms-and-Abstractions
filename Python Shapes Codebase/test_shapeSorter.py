from unittest import TestCase

from quadrilateral import Quadrilateral
from rectangle import Rectangle
from sorter import ShapeSorter
from square import Square


class TestShapeSorter(TestCase):

    def test_shape_sorter(self):
        "Regular sorting test of unsorted list"
        self.assertEqual(ShapeSorter.sort(
            Rectangle(-3, 2, 3, 2, 3, -2, -3, -2),
            Quadrilateral(-3, 2, 3, 2, -4, -2, -3, -2),
            Square(-2, 2, 2, 2, 2, -2, -2, -2)
        ),
            [Quadrilateral(-3, 2, 3, 2, -4, -2, -3, -2),
             Rectangle(-3, 2, 3, 2, 3, -2, -3, -2),
             Square(-2, 2, 2, 2, 2, -2, -2, -2)]
        )

        "Regular sorting test of sorted list"
        self.assertEqual(ShapeSorter.sort(
            Quadrilateral(-3, 2, 3, 2, -4, -2, -3, -2),
            Rectangle(-3, 2, 3, 2, 3, -2, -3, -2),
            Square(-2, 2, 2, 2, 2, -2, -2, -2)
        ),
            [Quadrilateral(-3, 2, 3, 2, -4, -2, -3, -2),
             Rectangle(-3, 2, 3, 2, 3, -2, -3, -2),
             Square(-2, 2, 2, 2, 2, -2, -2, -2)]
        )

        "Same shapes sorting - Squares"
        self.assertEqual(ShapeSorter.sort(
            Square(-2, 2, 2, 2, 2, -2, -2, -2),
            Square(-2, 2, 2, 2, 2, -2, -2, -2),
            Square(-2, 2, 2, 2, 2, -2, -2, -2)
        ),
            [Square(-2, 2, 2, 2, 2, -2, -2, -2),
             Square(-2, 2, 2, 2, 2, -2, -2, -2),
             Square(-2, 2, 2, 2, 2, -2, -2, -2)]
        )

        "Same lowest x, but different Classes"
        self.assertEqual(ShapeSorter.sort(
            Square(-2, 2, 2, 2, 2, -2, -2, -2),
            Quadrilateral(-2, 2, 2, 2, 2, -2, -2, -2),
            Rectangle(-2, 2, 2, 2, 2, -2, -2, -2)
        ),
            [Square(-2, 2, 2, 2, 2, -2, -2, -2),
             Quadrilateral(-2, 2, 2, 2, 2, -2, -2, -2),
             Rectangle(-2, 2, 2, 2, 2, -2, -2, -2)]
        )

        "Sorting of same type"
        self.assertEqual(ShapeSorter.sort(
            Rectangle(-3, 2, 3, 2, 3, -2, -3, -2),
            Rectangle(0, 8, 4, 8, 4, 0, 0, 0),
            Rectangle(-2, 2, 2, 2, 2, -2, -2, -2)
        ),
            [Rectangle(-3, 2, 3, 2, 3, -2, -3, -2),
             Rectangle(-2, 2, 2, 2, 2, -2, -2, -2),
             Rectangle(0, 8, 4, 8, 4, 0, 0, 0)]
        )

        "Empty sorted list"
        self.assertEqual(ShapeSorter.sort(),
                         []
                         )

        "One element sorted list"
        self.assertEqual(ShapeSorter.sort(
            Quadrilateral(-2, 2, 2, 2, 2, -2, -2, -2)
        ),
            [Quadrilateral(-2, 2, 2, 2, 2, -2, -2, -2)]
        )

        "False element sort"
        self.assertEqual(ShapeSorter.sort(
            Rectangle(-3, 2, 3, 2, 3, -2, -3, -2),
            Quadrilateral(-3, 2, 3, 2, -4, -2, -3, -2),
            Square(-2, 2, 2, 2, 2, -2, -2, -2),
            "Hello"
        ),
            [Rectangle(-3, 2, 3, 2, 3, -2, -3, -2),
             Quadrilateral(-3, 2, 3, 2, -4, -2, -3, -2),
             Square(-2, 2, 2, 2, 2, -2, -2, -2),
             "Hello"]
        )

        "One None element sorted list"
        self.assertEqual(ShapeSorter.sort(
            None
        ),
            [None]
        )