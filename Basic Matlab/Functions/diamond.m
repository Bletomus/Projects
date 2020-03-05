function diamond(x)
    xOuter = x * 2; 
    xInner = 1;
    spaces = x;
    for ii = 1:xOuter
        for pp = 1:spaces
            fprintf(" ")
        end
        for jj = 1:xInner
            fprintf("*")
        end
        if(ii < x)
            xInner = xInner + 2;
            spaces = spaces - 1;
        else
            xInner = xInner - 2;
            spaces = spaces + 1;
        end
        fprintf("\n")
    end 
end