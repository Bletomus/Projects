function invertedPyramid(x)
    xInner = x * 2 - 1;
    spaces = 0;
    for ii = 1:x
        for pp = 1:spaces
            fprintf(" ")
        end
        for jj = 1:xInner
            fprintf("*")
        end
        spaces = spaces + 1;
        xInner = xInner - 2;
        fprintf("\n")
    end 
end