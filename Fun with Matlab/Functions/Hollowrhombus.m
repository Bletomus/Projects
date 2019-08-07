function Hollowrhombus(x)
    xOuter = x;
    for i = 1:x
        for j = 1:xOuter
            fprintf("%c",' ');
        end
        xOuter = xOuter - 1;
        for l = 1:x
            if (i == 1 || i ==  x || l == 1 || l == x)
                fprintf("%c", '*');
            else
                fprintf("%c", ' ');
            end 
        end 
        fprintf("\n");
    end
end