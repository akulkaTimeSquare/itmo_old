% чтение и приведение матрицы к чб
rgb = imread("image.jpg");
%imshow(rgb);
upd = rgb2gray(rgb);
upd = im2double(upd); 
%imshow(upd);
%imwrite(upd, "updated_image.jpg");

% SVD
k = 2;
[U,S,V] = svd(upd);
V = transpose(V);
nu = U(:,1:k);
nv = V(1:k,:);
ns = S(1:k,1:k);
nimage = nu*ns*nv;
%imshow(nimage);
imwrite(nimage, "nimage" + k + ".jpg")

% степень сжатия
[m,n] = size(upd);
compression = (m*k + k + n*k) / (m*n);
disp(compression);

% 2, 5, 10, 25, 50, 75, 100(d), 150, 250, 500, 700