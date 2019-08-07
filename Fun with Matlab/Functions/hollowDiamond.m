function hollowDiamond(x)
    xOuter = x * 2 - 1;
    leftEnd = 0;
    rightEnd = 0;
    for ii = 1:xOuter
        for jj = 1:xOuter
            if(jj > x - leftEnd && jj < x + rightEnd)
                fprintf(" ");
            else 
                fprintf("*");
            end
        end
        if(ii < x)
            leftEnd = leftEnd + 1;
            rightEnd =  rightEnd + 1;
        else
            leftEnd = leftEnd - 1;
            rightEnd =  rightEnd - 1;
        end
        fprintf("\n");
    end
end