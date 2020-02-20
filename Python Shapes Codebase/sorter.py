from quadrilateral import Quadrilateral


class ShapeSorter:

    @staticmethod
    def sort(*args) -> list:
        shapes = list(args)

        try:
            allshapes = True

            for arg in shapes:
                if not isinstance(arg, Quadrilateral):
                    allshapes = False

            if allshapes:
                n = len(shapes)
                for i in range(n):
                    for j in range(0, n - i - 1):
                        if shapes[j].smallest_x() > shapes[j + 1].smallest_x():
                            shapes[j], shapes[j + 1] = shapes[j + 1], shapes[j]

            return shapes

        except:
            return shapes
