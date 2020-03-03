#include <iostream>

using namespace std;

int main()
{
    int a = 0;
    int b = 0;
    int c = 0;

    cout << "Please enter in the first value" << endl;
    cin >> a;
    cout << "Please enter in the second value" << endl;
    cin >> b;
    cout << "Please enter in the third value" << endl;
    cin >> c;
    cout << "Largest between the a and b: " << endl;
    cout << (a > b ? a : b) << endl;
    cout << "The largest between a and b and c: " << endl;
    cout << ((a > b ? a : b) > c ? (a > b ? a : b) : c);
    return 0;
}
