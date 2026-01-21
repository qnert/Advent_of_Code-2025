#include <iostream>
#include <utility>
#include <set>
#include <vector>
#include <string>
#include <fstream>
#include <algorithm>
#include <iostream>
#include <unordered_map>

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

bool secondHelper(std::string line){
    std::unordered_map<std::string, int> lastIndex;

    for (int i = 0; i < line.size() - 1; i++){
        std::string sub = line.substr(i, 2);

        if (lastIndex.count(sub) != 0 && i - lastIndex[sub] >= 2)
            return true;

        lastIndex[sub] = i;
    }
    return false;
}

bool doOperationSecond(std::string line){
    if (secondHelper(line)){
        for (int i = 0; i < line.size() - 3; i++){
            if (line[i] == line[i + 2])
                return true;
        }
    }

    return false;
}

int main(int ac, char** av){
    if (ac != 2)
        return -1;

    int counter_first = 0;
    int counter_second = 0;
    std::string line;
    std::ifstream inFile(av[1]);

    if (inFile.is_open()){
        while(getline(inFile, line)){
            if (doOperationFirst(line))
                counter_first++;
            if (doOperationSecond(line))
                counter_second++;
        }
        inFile.close();
    }
    std::cout << counter_first << std::endl;
    std::cout << counter_second << std::endl;
    return 0;
}

