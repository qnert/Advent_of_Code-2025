#include <iostream>
#include <utility>
#include <set>
#include <vector>
#include <fstream>
#include <algorithm>

int doOperationFirst(std::string line){
    std::pair<int,int> coord = {0,0};

    std::set<std::pair<int,int>> visited;     

    visited.insert(coord);

    for (char c : line) {
        switch(c) {
            case '^': coord.first++; break;
            case 'v': coord.first--; break;
            case '>': coord.second++; break;
            case '<': coord.second--; break;
        }
        visited.insert(coord);
    }

    return visited.size();
}

void doOperationSecond(std::string line){
    return;
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
    std::cout << doOperationFirst(line) << std::endl;;
    doOperationSecond(line);
    return 0;
}

