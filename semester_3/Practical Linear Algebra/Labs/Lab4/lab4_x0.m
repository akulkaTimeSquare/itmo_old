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
fplot(xSol, [0, 1.5]);
hold on
fplot(ySol, [0, 1.5]);
grid on
legend('x1','x2','Location','best')

saveas(gcf, "/home/ventus/Windows/Users/c0ris/Documents/ITMO/3_semester/Practical Linear Algebra/Labs/Lab4/images/" + k + "_" + c + ".png")