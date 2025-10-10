temp = imread("4.png");
img = double(temp)/255;
fourier = fftshift(fft2(img));
ab = abs(fourier); % абсолютные значения
an = angle(fourier); % фазы
l = log(ab+1);
m = max(l(:));
ul = l/m; % нормирование логарифма
imwrite(ul, "2.png");
imshow(ul, []);