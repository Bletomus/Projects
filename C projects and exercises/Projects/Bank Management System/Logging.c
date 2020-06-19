#include "Logging.h"
#include "menu.h"

int loginScreenNormal()
{
    int option = 0;
    int exit = 0
    do
        system("cls");
        do
        {

            printf("1. Sign in\n");
            printf("2. Sign up\n");
            printf("3. Exit\n");
        }while(option < 1 || option > 3);
        switch()
        {
            case 1:
                login();
                break;
            case 2:
                createUser();
                break;
            default:
                exit = 1;
                break;
        }


    while(!exit);
    return 1;
}

int loginScreenAdmin()
{
        login();
}

int login()
{
    system("cls");

    printf("Please enter in your account name!\n");
    gets(name);
    printf("Please enter in your password!\n");
    gets(password);
    return 1;
}

