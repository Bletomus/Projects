function invertedMirrowTriangle(x)
    spaces = 1;
    xInner = x;
    for xOuter = 1:x
        for ii = 1:spaces
            fprintf(" ")
        end
        for jj = 1:xInner
            fprintf("*")
        end
        fprintf("\n")
        spaces = spaces + 1;
        xInner = xInner - 1;
    end
end