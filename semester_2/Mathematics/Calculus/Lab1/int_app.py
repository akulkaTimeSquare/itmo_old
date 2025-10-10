# Integral f(x) = e^(3x) from a = 0 by b = 0.5 (by default)
import sys
import numpy as np
import matplotlib.pyplot as plt
from matplotlib import patches
from fractions import Fraction


def main(save = False):
    # User data
    n = input("Введите число точек разбиения: ")
    while not n.isdigit() or float(n) < 1 or int(n) != float(n):
        print("Число точек разбиения должно быть целым положительным числом!")
        n = input("Введите число точек разбиения: ")
    n = int(n)

    t = float(input("Введите способ разбиения (0 - левые, 1 - средние, 2 - правые): "))
    while t not in (0, 1, 2):
        print("Введите либо 0, либо 1, либо 2!")
        t = float(input("Введите способ разбиения (0 - левые, 1 - средние, 2 - правые): "))
    t = int(t)

    # Integral data
    a = Fraction()
    b = Fraction(1, 2)
    d = (b - a) / n
    integral = 0
    x = np.linspace(a - 0.01, b + 0.01) # axis
    y = np.exp(3 * x) # function

    # Plot data
    fig, ax = plt.subplots()
    lw = 2/(n//10 + 1) # linewidth of integral rectangles
    fc = "cornflowerblue" # facecolor
    ec = "silver" # edgecolor
    
    # Calculation
    while a < b:
        u = float(a)
        if t == 0:
            val = np.exp(3 * u)
        elif t == 1:
            val = np.exp(3 * ( u + d / 2))
        else:
            val = np.exp(3 * (u + d))
        rect = patches.Rectangle((u, 0), d, val, linewidth=lw, facecolor=fc, edgecolor=ec)
        ax.add_patch(rect)
        integral += val * d
        a += d
    
    # Results
    print(f"Integral value: {integral}")
    plt.plot(x, y, color = "tomato", label = "e^(3x)")
    plt.xlabel("x")
    plt.ylabel("y")
    plt.title(f"Integral approximation (n = {n}), value = {round(integral, 8)}")
    plt.legend()
    plt.grid(True, linestyle="dashed")

    # Plot saving
    if save == True: plt.savefig(f"exp_{n}_{t}.png")

    plt.show()


if __name__ == "__main__":
    if (len(sys.argv) == 2 and ("s" in sys.argv[1] or "save" in sys.argv[1])):
        main(save = True)
    else:
        main()

