function hollowRightTriangle(x)
    for o = 1:x
        for xInner = 1:o
            if xInner == 1 || xInner == o || o == x
                fprintf("%c", '*')
            else
                fprintf(" ")
            end 
        end 
        fprintf("\n")
    end 
end 