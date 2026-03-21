#include <iostream>
#include <string>
#include <fstream>
#include <algorithm>
#include <vector>
#include <map>
#include <climits>
#include <utility>

std::string computateString(std::string str){
    int counter = 1;
    char curr = str[0];
    std::vector<std::pair<int, char>> new_digits;

    for (int i = 1; i < str.length(); i++){
        if (str[i] != curr){
            new_digits.push_back({counter, curr});
            curr = str[i];
            counter = 0;
        }
        counter++;
    }
    new_digits.push_back({counter, curr});

    std::string new_str = "";
    for (auto p: new_digits){
        new_str += std::to_string(p.first) + p.second;
    }
    return new_str;
}

std::string doOperationFirst(std::string input, int curr_iteration, int iterations){
    if (curr_iteration >= iterations)
        return input;
    std::string new_str = computateString(input);
    return doOperationFirst(new_str, curr_iteration + 1, iterations);
}

int main() {
    std::cout << doOperationFirst("1113222113", 0 , 40).length() << std::endl;
    std::cout << doOperationFirst("1113222113", 0, 50).length() << std::endl;
    return 0;
}
