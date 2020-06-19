#include "menu.h"
int verify(char * username, char * password);
int withdrawal(int id);
/* Function for taking in the user information
The details to be acquired will be
Name
password
email
Note on creation of each account the balance will be set as 0 each time.*/

int createUser()
{
    system("cls");
    char[100] name;
    char[100] password;
    char[100] email;
    printf("Please enter in your account name!\n");
    gets(name);
    printf("Please enter in your password!\n");
    gets(password);
    printf("Please enter in your email\n");
    gets(email);
    return 200;

}

/*Function that is called when the user wants to withdraw or deposit money
The variable type will determine what the transaction will be
1 is for withdrawal
2 is for deposit
*/
int transaction(int id, int type)
{
    if(type == 1)
    {
        withdrawal(id)
    }
    else if(type == 2)
    {
        deposit(id)
    }
    return 1;
}

/*Function for withdrawing money from the user account*/

int withdrawal(int id)
{
    system("cls");
    int amount = 0;
    printf("Please enter the amount to be withdrawn\n");
    do
    {
        scanf("%d", &amount);
    }
    while(amount < 0);
    return 1;
}
/*Function for depositing money from the user account*/
int deposit(int id)
{
    system("cls");
    int amount = 0;
    printf("Please enter the amount to be deposit\n");
    do
    {
        scanf("%d", &amount);
    }
    while(amount < 0);
    return 1;
}

/* Function for updating user details
To change anything, the user has to reenter the password each time they want to make a modification to any detail*/

int updateDetails(int id)
{
    system("cls");
    int option = 0;
    char[100] name;
    char[100] password;
    char[100] email;
    int exit = 0;
    do
    {
        do
        {
            system("cls");
            printf("1. Change Name\n");
            printf("2. Change Password\n");
            printf("3. Change Email\n");
            printf("4. Back\n")
            scanf("%d", &option);
        }while(option < 1 || option > 4);
        if(option == 4)
        {
            return 1;
        }

        printf("Please enter in password for the registered account name!\n");
        gets(password);
        if(verify(id, password))
        {
            switch(option)
            {
                case 1:
                    printf("Please enter in your account name!\n");
                    gets(name);
                    break;
                case 2:
                    printf("Please enter in your password!\n");
                    gets(password);
                    break;
                case 3:
                    printf("Please enter in your email\n");
                    gets(email);
                    break;
            }

        }
    }while(!exit);

    return 1;
}
//Uses a unique ID and password to verify whether the password entered
//is the same as the one in the database
int verify(int id, char * password)
{
    return 1;
}
