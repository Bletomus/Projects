function hollowMirrowRhombus(x)
    xOuter = 1;
    for y = 1:x
        for z = 1:xOuter
            fprintf("%c",' ');
        end
        xOuter = xOuter  + 1;
        for l = 1:x
            if y == 1 || y == x || l == 1 || l == x
                fprintf("%c", '*')
            else 
                fprintf("%c", ' ')
            end
        end
        fprintf("\n");
    end
end 