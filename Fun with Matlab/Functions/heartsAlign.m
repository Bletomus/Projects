function heartsAlign(x)
    j = strlength(x);
    head(j)
    xInner = j * 2 - 1;
    spaces = 1;
    n = 1;
    half = j / 2;
    while n <= xInner
        if(n <= half || n > half + j)
            fprintf("*")
            n = n + 1;
        else
            fprintf("%s", x)
            n = n + j;
        end
    end
    
    xInner = xInner - 2;
    fprintf("\n")   
    for ii = 2:j
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