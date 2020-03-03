#include <iostream>

using namespace std;

int main()
{
    string sentence;
    cout << "Please enter in a string to see something cool!" << endl;
    getline(cin, sentence);
    double num1 = 0;
    double num2 = 0;
    cout << "Please enter in the one digit:" << endl;
    cin >> num1;
    cout << "Please enter in the second digit" << endl;
    cin >> num2;
    double sum = num1 + num2;
    double sub = num1 - num2;
    double quotient = num1 / num2;
    double product = num1 * num2;
    cout << "I am sorry, I don't care about your string. Here \"" << sentence << "\". Take it back" << endl;
    cout << "Sum:" << sum << endl;
    cout << "Difference:" << sub << endl;
    cout << "Quotient:" << quotient << endl;
    cout << "Product:" << product << endl;
    return 0;
}
