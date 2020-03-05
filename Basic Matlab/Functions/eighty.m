function eighty(x)
    xOuter = (x * 2) - 1;
    for ii=1:xOuter
        for jj=1:x
            if(ii == 1 || ii == x || ii == xOuter)
                if(jj == 1 || jj == x)
                    fprintf(" ");
                else
                    fprintf("*");
                end
            else
                if(jj == 1 || jj == x)
                    fprintf("*");
                else
                    fprintf(" ");
                end
            end
        end
        fprintf("\n")
    end
end

