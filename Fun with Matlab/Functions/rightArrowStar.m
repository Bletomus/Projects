function rightArrowStar(x)
    xOuter =  x * 2;
    xInner = x;
    spaces = 1;
    for ii = 1:xOuter
        for kk = 1:spaces
            fprintf(" ");
        end
        for jj = 1:xInner
            fprintf("*");
        end
        if(ii < x)
            spaces = spaces + 1;
            xInner = xInner - 1;
        else
            spaces = spaces - 1;
            xInner = xInner + 1;
        end
        fprintf("\n");
    end
end