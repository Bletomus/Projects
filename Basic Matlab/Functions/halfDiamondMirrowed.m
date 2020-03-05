function halfDiamondMirrowed(x)
    xOuter = x;
    kInner =1; 
    jj= x * 2;
    for k = 1:jj
        for ii = 1:xOuter
            fprintf(" ");
        end 
        
        for jj = 1:kInner
            fprintf("*");
        end
        if(k < x)
            xOuter = xOuter - 1;
            kInner = kInner +1;
        else
            xOuter = xOuter + 1;
            kInner = kInner - 1;
        end
        fprintf("\n")
    end
end 