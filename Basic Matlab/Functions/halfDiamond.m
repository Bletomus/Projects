function halfDiamond(x)
    xOuter =  x * 2;
    jj = 1;
    for o = 1:xOuter
        for xInner = 1:jj
            fprintf("%c", '*')
        end
        if(o >= x)
            jj = jj - 1;
        else
            jj = jj + 1;
        end
        
        fprintf("\n")
    end 
end 