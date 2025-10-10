from manim import *
import math
import matplotlib.pyplot as plt


v = [2]
theta = [-math.pi/12]
t = 10**(-6)
x = [0]
z = [1.75]
D = 1.25

for _ in range(5*10**6):
    dx = v[-1] * math.cos(theta[-1])
    dz = v[-1] * math.sin(theta[-1])
    z.append(z[-1] + t*dz)
    x.append(x[-1] + t*dx)
    if z[-1] <= 0: break
    dtheta = (v[-1]**2 - math.cos(theta[-1])) / v[-1]
    dv = -math.sin(theta[-1]) - D*v[-1]**2
    theta.append(theta[-1] + t*dtheta)
    v.append(v[-1] + t*dv)

plt.plot(x, z)
plt.show()