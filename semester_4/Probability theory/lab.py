import random

n = 100
s = 0
k = 0
for i1 in range(n):
    for j1 in range(n):
        for i2 in range(n):
            for j2 in range(n):
                x1 = random.random()
                x2 = random.random()
                y1 = random.random()
                y2 = random.random()
                if ((4*x1*x2) > (y1-y2)**2) and ((4*y1*y2) > (x1-x2)**2) and ((1-(y1+y2)+y1*y2) > ((x1-x2)**2)/4) and ((1-(x1+x2)+x1*x2) > ((y1-y2)**2)/4):
                    s += 1
                k += 1
print(s/k)