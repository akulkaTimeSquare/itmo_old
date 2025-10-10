import numpy as np


def calc_angle(a, b):
    pr = a.dot(b)
    n = np.linalg.norm(a) * np.linalg.norm(b)
    return np.arccos(pr/n)

def vector_abs3d(a):
    np.cbrt(a)

def vector_subtraction3d(a, b):
    return (a[0]-b[0], a[1]-b[1], a[2]-b[2])


def normalize(v, a, m, w):
    return (v, vector_subtraction3d(a, v), vector_subtraction3d(m, v), vector_subtraction3d(w, v))


def program():
    with open("input.txt") as file:
        lines = [list(map(float, line.split(" "))) for line in file.readlines()]
    v, a, m, w = np.array([lines[0][0], lines[0][1], 0]), np.array([lines[1][0], lines[1][1], 0]), np.array([lines[2][0], lines[2][1], 1]), np.array([lines[3][0], lines[3][1], 0])
    a, m, w = a - v, m - v, w - v
    angle_mast = np.rad2deg(calc_angle(m, np.array([0, 0, 1])))

    if a[0] != 0 or a[1] < 0:
        a_angle = calc_angle(a, np.array([0, 1, 0]))
        if a[0] < 0: a_angle = -a_angle
        matrix = np.array([np.array([np.cos(a_angle), -np.sin(a_angle)]), np.array([np.sin(a_angle), np.cos(a_angle)])])
        a = np.append(matrix.dot(np.array([a[0], a[1]])), np.array([0]))
        m = np.append(matrix.dot(np.array([m[0], m[1]])), np.array([1]))
        w = np.append(matrix.dot(np.array([w[0], w[1]])), np.array([0]))

    w = w - (w.dot(m)/m.dot(m)) * m
    #print(a, m, w)

    angle_gun = np.rad2deg(calc_angle(w, np.array([-1, 0, 0])))
    #print(angle_gun, angle_mast)
    with open("output.txt", "w") as file:
        if angle_gun < 30 or angle_gun > 150: 
            file.write("0" + "\n")
            file.write("0" + "\n")
            file.write("Нет пробития")
        else:
            #print(a, m, w)
            file.write(("-1" if w[0] > 0 else "1") + "\n")
            file.write(str(round(90 - angle_gun, 2)) + "\n")
            file.write(str(round(angle_mast)) + "\n")
            file.write("Есть пробитие")

if __name__ == "__main__":
    program()
    """
    a = np.array([[-2], [2]])
    a_angle = -(np.pi)/4
    print(a)
    matrix = np.array([[np.cos(a_angle), -np.sin(a_angle)], [np.sin(a_angle), np.cos(a_angle)]])
    print(matrix)
    print(matrix.dot(a))
    """