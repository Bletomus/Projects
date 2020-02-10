#include <iostream>

using namespace std;

int main()
{
    int var = 0;
    int test = 0;
    int msb1 = 1 << (sizeof(int) - 1);
    cout << "Least Significant Bit" << endl;
    cout << "Please enter in the value to be checked!" << endl;
    cin >> var;
    test = var & 1;
    if(test == 1)
    {
        cout << "The least significant bit of " << test << " is " << endl;
        cout << 1 << endl;
    }
    else
    {
        cout << "The least significant bit of " << test << " is " << endl;
        cout << 0 << endl;
    }

    cout << "Most significant Bit" << endl;
    cout << "Please enter in the value to be checked!" << endl;
    cin >> var;
    test = var & msb1;
    if(test == 8)
    {
        cout << "The most significant Bit of " << var << " is " << endl;
        cout << 1 << endl;
    }
    else
    {
        cout << "The most significant Bit of " << var << " is " << endl;
        cout << 0 << endl;
    }
    return 0;
}
