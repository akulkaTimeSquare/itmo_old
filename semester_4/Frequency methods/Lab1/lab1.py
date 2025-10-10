import numpy as np
import matplotlib.pyplot as plt

a = 1
b = 3
dt = 0.01
t0 = 1
t1 = 2
t2 = 4
T = t2-t0
t = np.arange(t0, t2, dt)
y = []
for k in t:
    if k >= t0 and k < t1: y += [a]
    else: y += [b]
y = np.array(y)

k = 100
an = [14/3]
bn = [0]
g = an[0]/2
for i in range(1, k+1):
    an += [np.trapz(2/T*y*np.cos(2*np.pi*i/T*t), t)]
    bn += [np.trapz(2/T*y*np.sin(2*np.pi*i/T*t), t)]
    g += an[-1]*np.cos(2*np.pi*i/3*t) + bn[-1]*np.sin(2*np.pi*i/3*t)

plt.plot(t, y)
plt.plot(t, g)
plt.grid(True)
plt.show()