function upsideTriangle(x)
    xInner = x;
    for xOuter = 1:x
        for jj = 1:xInner
            fprintf("*");
        end
        xInner = xInner - 1;
        fprintf("\n");
    end
end