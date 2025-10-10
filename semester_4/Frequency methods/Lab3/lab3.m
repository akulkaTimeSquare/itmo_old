a = 1;
t1 = -10;
t2 = 5;
T = 50;
dt = 0.1;
t = -T/2:dt:T/2;
N = length(t);
g = zeros(1,N);
for i = 1:N
    if t(i) < t1 || t(i) > t2
        g(i) = 0;
    else
        g(i) = a;
    end
end
%plot(t, g);
%ylim([-1 a+1]);

c = 0;
b = 5;
u = g + b*(rand(size(t))-0.5) + c*sin(dt);
U = fftshift(fft(u));
n0 = 5;
for i = 1:N
    if t(i) < -n0 || t(i) > n0
        U(i) = 0;
    end
end
plot(t, u)
hold on
    plot(t, ifft(ifftshift(U)))
    grid on
hold off
xlim([t1-1 t2+1]);