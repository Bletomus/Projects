x = linspace(0,2 * pi,100);
y = sin(x);
plot(x,y);
title('A plot of my lawn');
xlabel('I want you on my lawn');
ylabel('I know you love me!');
z = cos(x);
subplot(2,1,1);
plot(x,y)
title('sin(x)')
xlabel('give the love')
ylabel('Why dont love me!')
subplot(2,1,2);
plot(x,z);
title('cos(x)');
xlabel('when i make');
ylabel('tell baby why dont need');