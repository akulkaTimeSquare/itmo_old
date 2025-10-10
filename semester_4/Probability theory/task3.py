import numpy as np
from scipy.stats import rv_continuous, norm
import matplotlib.pyplot as plt
from math import prod


class f_gen(rv_continuous):
    def _pdf(self, x):
        return p(x)


class s_gen(rv_continuous):
    def _pdf(self, x):
        return p(x)
    def _cdf(self, x):
        return norm.cdf(x**3-5)


def p(x):
    return 3*x**2/np.sqrt(2*np.pi)*np.exp(-(5-x**3)**2/2)


def rejection_sampling(size=None):
    if size == None: size = 1
    if isinstance(size, int): length = size
    elif isinstance(size, tuple): length = prod(size)
    else: raise Exception("Incorrect type of size")

    res = []
    while len(res) < length:
        x = np.random.rand()*1.25+1
        y = np.random.rand()*3.6
        if y < p(x): res.append(x)
    res = np.array(res)
    res = res.reshape(size)
    return res


n = 50
gen_1 = f_gen()
gen_2 = s_gen()
samples_1 = gen_1.rvs(size=n)
samples_2 = gen_2.rvs(size=n)
samples_3 = rejection_sampling(size=n)
print(samples_1)
print(samples_2)
print(samples_3)

x = np.arange(0, 2.5, 0.01)
y = p(x)
plt.plot(x, y, label="Плотность распределения")
plt.legend()
plt.grid(True)
plt.show()