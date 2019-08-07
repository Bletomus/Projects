function crosses(x)
    xOuter = x * 2;
    for ii = 1:xOuter
        if(ii == x)
            printline(xOuter)
        else
            for jj = 1:x
                if(jj == x)
                    fprintf("*");
                else
                    fprintf(" ");
                end
            end
        end
        fprintf("\n");
    end 
end 

function printline(x) 
    for ii = 1:x
        fprintf("*");
    end
end