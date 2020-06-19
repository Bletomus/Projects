#include <stdio.h>
#include <stdlib.h>

void menu();
void advanced_menu();
int main()
{
    int option = 0;
    do
    {
        system("cls");
        printf("\tImagination Bank\n");
        printf("Please select an Account type:\n");
        printf("1. Administrator Account \n");
        printf("2. Customer Account\n");
        printf("3. Exit\n");
        scanf("%d",&option);
    }while(option != 1 && option != 2 && option != 3);

    getchar();
    switch(option)
    {
        case 1:
            advanced_menu();
            break;
        case 2:
            menu();
            break;
        default:
            printf("Thank you for visiting Imagination Bank!");
            getchar();
            break;
    }

    return 0;
}

void menu()
{
    system("cls");
    int option = 0;
    do
    {
        printf("\tImagination Bank\n");
        printf("\t\tMain Menu\n");
        printf("1. Create a new account\n");
        printf("2. Update account information\n");
        printf("3. Withdraw an amount of money\n");
        printf("4. Deposit an amount of money in the account\n");
        printf("5. View account details\n");
        printf("6. Exit\n");
        scanf("%d", &option);
    }while(option > 6 || option < 1);
    switch(option)
    {
        case 1:

            break;
        case 2:
            break;
        default:
            break;
    }

}
void advanced_menu()
{
    system("cls");
    int option = 0;
    printf("\tImagination Bank(Administrator)\n");
    printf("\t\tMain Menu\n");
    printf("1. Create a new account\n");
    printf("2. Update account information\n");
    printf("3. Withdraw an amount of money\n");
    printf("4. Deposit an amount of money in the account\n");
    printf("5. View account details\n");
    printf("6. Delete an account\n");
    printf("7. Freeze an account\n");
    printf("8. View list of members\n");
    printf("9. Exit\n");
    scanf("%d", &option);
}
