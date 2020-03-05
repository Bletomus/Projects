function hollowInvertedMirrowTriangle(x)
    spaces = 1;
    xInner = x;
    for xOuter = 1:x
        for ii = 1:spaces
            fprintf(" ")
        end
        for jj = 1:xInner
            if jj == 1 || jj == xInner || xOuter == 1
                fprintf("%c", '*')
            else
                fprintf("%c", ' ')
            end
        end
        spaces = spaces + 1;
        fprintf("\n")
        xInner = xInner - 1;
    end
end