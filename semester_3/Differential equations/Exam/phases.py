from manim import *

class ContinuousMotion(Scene):
    def construct(self):
        func = lambda pos: -3*pos[0]*UP + 17*pos[1]*LEFT
        stream_lines = StreamLines(func, stroke_width=3,
                    max_anchors_per_line=30, color=[GREEN, BLUE, YELLOW])
        self.add(stream_lines)
        stream_lines.start_animation(warm_up=False, flow_speed=1.5)
        self.wait(stream_lines.virtual_time / stream_lines.flow_speed)