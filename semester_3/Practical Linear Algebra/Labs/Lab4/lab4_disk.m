j = 12;
A = [3 -1; 9 -3];
v0 = [-1; 3];
fin = [];
fin(:,1) = v0;
m = 10;
for k=2:m
    fin(:,k) = A*fin(:,k-1);
end
x = linspace(1, m, m);
scatter(x, fin(1,:));
hold on
scatter(x, fin(2, :));
hold off
grid on
legend('x1', 'x2', "Location", "best")

saveas(gcf, "/home/ventus/Windows/Users/c0ris/Documents/ITMO/3_semester/Practical Linear Algebra/Labs/Lab4/images/disk" + j + ".png")