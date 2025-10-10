[X,Y] = meshgrid(-6:1:6,-6:1:6);
k = 6;
U = 7*X + 5*Y;
V = -13*X - 7*Y;
x = -6:6;
y2 = -6/5*x;
y1 = x;
%plot(x, y1, color="red")
xlim([-6 6]);
ylim([-6 6]);
hold on
%plot(x, y2, color="blue")
quiver(X,Y,U,V, linewidth=1.25, AutoScaleFactor=1.5, color="k")
hold off
saveas(gcf, "/home/ventus/Windows/Users/c0ris/Documents/ITMO/3_semester/Practical Linear Algebra /Labs/Lab4/images/" + k + ".png")