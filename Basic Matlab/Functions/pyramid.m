function pyramid(x)
    xInner = 1;
    spaces = x;
    for ii = 1:x
        for pp = 1:spaces
            fprintf(" ")
        end
        for jj = 1:xInner
            fprintf("*")
        end
        xInner = xInner + 2;
        spaces = spaces - 1;
        fprintf("\n")
    end 
end 