function y = takeInput()
    flag = 0;
    while flag == 0
        y = input('Please enter a value for the shape\n');
        if y > 0
            flag = 1;
        end 
    end 
end 