function holowupsideTriangle(x)
    xInner = x;
    for xOuter = 1:x
        for ii = 1:xInner
            if ii == 1 || ii == xInner || xOuter == 1
                fprintf("%c", '*')
            else
                fprintf("%c", ' ')
            end
        end
        xInner = xInner - 1;
        fprintf("\n")
    end 
end 