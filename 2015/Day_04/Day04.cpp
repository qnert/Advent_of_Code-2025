#include <iostream>
#include <utility>
#include <set>
#include <vector>
#include <fstream>
#include <algorithm>
#include <iostream>
#include <iomanip>
#include <sstream>
#include "md5.h"

void doOperationFirst(std::string line){
    std::cout << line << std::endl;
}

void doOperationSecond(std::string line){
}

int main(int ac, char** av){
    if (ac != 2)
        return -1;
    std::string line;
    std::ifstream inFile(av[1]);

    if (inFile.is_open()){
        while(getline(inFile, line)){}
        inFile.close();
    }
    doOperationFirst(line);
    return 0;
}

