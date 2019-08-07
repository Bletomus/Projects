function hollowMirrowedRightTraingle(x)
    xOuter = x;
    for k = 1:x
        for ii = 1:xOuter
            fprintf(" ");
        end 
        xOuter = xOuter - 1;
        for jj = 1:k
            if jj == 1 || jj == k || k == x
                fprintf("%c", '*')
            else
                fprintf(" ")
            end 
        end
        fprintf("\n")
    end
end