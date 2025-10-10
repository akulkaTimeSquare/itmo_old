syms x(t) y(t)

k = 6;
c = 3;
ode1 = diff(x) == 7*x +5*y;
ode2 = diff(y) == -13*x -7*y;
odes = [ode1; ode2];
cond1 = x(0) == -10;
cond2 = y(0) == -5;
conds = [cond1; cond2];
[xSol(t),ySol(t)] = dsolve(odes,conds);
l = linspace(0, 1.5, 250);
x1 = xSol(l);
x2 = ySol(l);
comet(x1, x2);
grid on

saveas(gcf, "/home/ventus/Windows/Users/c0ris/Documents/ITMO/3_semester/Practical Linear Algebra/Labs/Lab4/images/" + k + "_comet_" + c + ".png")