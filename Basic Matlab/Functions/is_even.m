function y = is_even(x)
    %IS_EVEN Summary of this function goes here
    y = ~mod(x,2);
    plot(x,y,'-',x,y2,'.')
      openfig('test.fig')
      bar(x,y)
      hist(x);
      pie(x)
      scatter(x,y)
end

