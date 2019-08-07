function head(x)
    xOuter = x /2;
    rightSide = xOuter/ 2;
    leftSide = xOuter - rightSide;
    z = max(rightSide,leftSide) - 1;
    looper = (x * 2) - 1;
    while z > -1
        for ii = 1:looper
            if(ii < (1 + z) || ii > (looper - z) || (ii > (x - rightSide) && ii < (leftSide + x)))
                fprintf(" ");
            else
                fprintf("*");
            end
        end
        fprintf("\n");
        rightSide = rightSide - 1;
        leftSide = leftSide - 1;
        z = z - 1;
    end       
end


function z = max(x,y)
    if(x > y)
        z = x;
    elseif(x < y)
        z = y;
    else
        z = x;
    end
end