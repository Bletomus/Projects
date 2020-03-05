function image_gray = hdr2gray(image_hdr)
    image_rgb = tonemap(image_hdr);
    image_gray =rgb2gray(image_rgb);
end
