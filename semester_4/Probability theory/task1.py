import numpy as np

lam = 1
mu = 1/lam
delta = 0.05
epsilon = 0.01
num_samples = 100
n_delta_epsilon = int(1/(delta*epsilon**2))
samples = np.random.default_rng().exponential(scale=1/lam, size=(num_samples, n_delta_epsilon))

means = np.mean(samples, axis=1)
count = np.sum(np.abs(means - mu) <= epsilon)
print("Доля:", count/num_samples)