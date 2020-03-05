function hollowPyramid(x)
    xInner = 1;
    spaces = x;
    for ii = 1:x
        for pp = 1:spaces
            fprintf(" ")
        end
        for jj = 1:xInner
            if(jj == 1 || jj == xInner || ii == x)
                fprintf("*")
            else
                fprintf(" ")
            end
        end
        xInner = xInner + 2;
        spaces = spaces - 1;
        fprintf("\n")
    end 
end 