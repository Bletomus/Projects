function mirrowedRightTriangle(x)
    xOuter = x;
    for k = 1:x
        for ii = 1:xOuter
            fprintf(" ");
        end 
        xOuter = xOuter - 1;
        for jj = 1:k
            fprintf("*");
        end
        fprintf("\n")
    end
end 