function ex(x)
    xOuter = (x * 2) - 1;
    rightStar = 1;
    leftStar = (x * 2) - 1;
    xInner =  x * 2;
    for ii = 1:xOuter
        for jj = 1:xInner
            if(rightStar == jj || leftStar == jj)
                fprintf("*");
            else
                fprintf(" ");
            end
        end
        rightStar = rightStar + 1;
        leftStar = leftStar - 1;
        fprintf("\n")
    end
end