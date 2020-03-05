memorial_hdr = hdrread('.\Test Images\hw1_memorial.hdr');
atrium_hdr = hdrread('.\Test Images\hw1_atrium.hdr');
memorial_gray = hdr2gray(memorial_hdr);
atrium_gray = hdr2gray(atrium_hdr);
figure, imshow(memorial_gray);
figure, imshow(atrium_gray);