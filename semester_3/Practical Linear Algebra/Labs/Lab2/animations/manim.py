from manim import *
import numpy as np

class LinearTransformationSceneExample(LinearTransformationScene):
    def __init__(self):
        LinearTransformationScene.__init__(
            self,
            show_coordinates = True,
            leave_ghost_vectors=True,
            background_plane_kwargs={"x_range":(-20, 20), "y_range":(-20, 20),
                "x_axis_config":{"include_numbers":True}, "y_axis_config":{"include_numbers":True}},
            foreground_plane_kwargs={"x_range":(-60, 60), "y_range":(-60, 60),}
        )

    def construct(self):
        self.camera.frame_height = -40
        self.camera.frame_width = 40
        a = np.array([[2, 1], [3, 4]])
        b = np.array([[3, 1], [3, 5]])
        ab = a.dot(b)
        ba = b.dot(a)

        position_list = [
            [4, 1, 0],  # middle right
            [4, -2.5, 0],  # bottom right
            [0, -2.5, 0],  # bottom left
            [0, 3, 0],  # top left
            [2, 1, 0],  # middle
            [4, 3, 0],  # top right
        ]
        square_and_triangles = Polygon(*position_list, color=PURPLE_B)
        self.add_transformable_mobject(square_and_triangles)
        self.apply_matrix(ab)
        self.wait()
