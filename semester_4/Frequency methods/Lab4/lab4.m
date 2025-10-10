a = 0.5;
dt = 0.25;
t = -100:dt:100;
y = sin(t);
y = y + a*(rand(size(t))-0.5);

g = zeros(1,length(t));
for k = 1:(length(t)-1)
    g(k) = (y(k+1) - y(k))/dt;
end
g(length(t)) = 0;

vx = -1:dt:1;
furie = zeros(1, length(vx));
for k = 1:length(vx)
    furie(k) = 2*pi*1i*vx(k)*trapz(t, y.*exp(-2*pi*1i*vx(k)*t));
end

der = zeros(1,length(t));
for k = 1:length(t)
    der(k) = trapz(vx, furie.*exp(2*pi*1i*vx*t(k)));
end

subplot(2, 1, 1)
plot(t, der);
hold on
xlim([-30 30])
plot(t, cos(t));
grid on
hold off

subplot(2, 1, 2)
plot(t, g)
hold on
plot(t,cos(t))
grid on
hold off