#include <iostream>
#include <utility>
#include <set>
#include <vector>
#include <string>
#include <fstream>
#include <algorithm>
#include <iostream>

int doOperationFirst(std::string line){
    int count_vowel = 0;
    std::string vowels = "aeiou";
    if (line.find("ab") != std::string::npos || line.find("cd") != std::string::npos || line.find("pq") != std::string::npos || line.find("xy") != std::string::npos)
        return 0;
    for (char c: line){
        if (vowels.find(c) != std::string::npos)
            count_vowel++;
    }
    if (count_vowel < 3)
        return 0;
    for (char c = 'a'; c <= 'z'; c++){
        std::string str(2, c);
        if (line.find(str) != std::string::npos)
            return 1;
    }
    return 0;
}

void doOperationSecond(std::string line){

}

int main(int ac, char** av){
    if (ac != 2)
        return -1;

    int counter_first = 0;
    std::string line;
    std::ifstream inFile(av[1]);

    if (inFile.is_open()){
        while(getline(inFile, line)){
            if (doOperationFirst(line))
                counter_first++;
        }
        inFile.close();
    }
    std::cout << counter_first << std::endl;
    doOperationSecond(line);
    return 0;
}

