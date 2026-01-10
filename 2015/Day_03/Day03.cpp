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

int doOperationSecond(std::string line){
    bool check = true;
    std::pair<int,int> santa = {0,0};
    std::pair<int,int> robo = {0,0};

    std::set<std::pair<int,int>> visited;

    visited.insert(santa);
    visited.insert(robo);

    for (char c : line) {
        if (check == true){
            switch(c) {
                case '^': santa.first++; break;
                case 'v': santa.first--; break;
                case '>': santa.second++; break;
                case '<': santa.second--; break;
            }
            check = false;
            visited.insert(santa);
        }
        else{
            switch(c) {
                case '^': robo.first++; break;
                case 'v': robo.first--; break;
                case '>': robo.second++; break;
                case '<': robo.second--; break;
            }
            check = true;
            visited.insert(robo);
        }
    }

    return visited.size();
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
    std::cout << doOperationFirst(line) << std::endl;
    std::cout << doOperationSecond(line) << std::endl;
    return 0;
}

