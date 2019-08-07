function rhombus(x)
    xOuter = x;
    for i = 1:x
        for j = 1:xOuter
            fprintf("%c",' ');
        end
        xOuter = xOuter - 1;
        for l = 1:x
            fprintf("%c", '*')
        end 
        fprintf("\n");
    end
end