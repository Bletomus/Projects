function hollowInventedPyramid(x)
    xInner = x * 2 - 1;
    spaces = 1;
    for ii = 1:x
        for pp = 1:spaces
            fprintf(" ")
        end
        for jj = 1:xInner
            if(jj == 1 || jj == xInner || ii == 1)
                fprintf("*")
            else 
                fprintf(" ")
            end
        end
        spaces = spaces + 1;
        xInner = xInner - 2;
        fprintf("\n")
    end 
end