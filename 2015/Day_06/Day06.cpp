#include <iostream>
#include <utility>
#include <string>
#include <fstream>
#include <algorithm>
#include <iostream>
#include <bitset>
#include <vector>
#include <algorithm>

void doOperationFirst(std::string line, std::vector<std::bitset<1000>> &rows){
    int x1, y1, x2, y2;
    
    if (line.starts_with("turn off")){
        sscanf(line.c_str(), "turn off %d,%d through %d,%d", &x1, &y1, &x2, &y2);
        for (int i = x1; i <= x2; i++){
            for (int j = y1; j <= y2; j++){
                rows[j].reset(i);
            }
        }
    }
    else if (line.starts_with("turn on")){
        sscanf(line.c_str(), "turn on %d,%d through %d,%d", &x1, &y1, &x2, &y2);
        for (int i = x1; i <= x2; i++){
            for (int j = y1; j <= y2; j++){
                rows[j].set(i);
            }
        }
    }
    else{
        sscanf(line.c_str(), "toggle %d,%d through %d,%d", &x1, &y1, &x2, &y2);
        for (int i = x1; i <= x2; i++){
            for (int j = y1; j <= y2; j++){
                rows[j].flip(i);
            }
        }
    }
    return ;
}

long doOperationSecond(std::string line, std::vector<std::vector<int>> &rows){
    long counter = 0;
    int x1 = 0, y1 = 0, x2 = 0, y2 = 0;
    
    if (line.starts_with("turn off")){
        sscanf(line.c_str(), "turn off %d,%d through %d,%d", &x1, &y1, &x2, &y2);
        for (int i = x1; i <= x2; i++){
            for (int j = y1; j <= y2; j++){
                if (rows[j][i] > 0){
                    rows[j][i]--;
                    counter--;
                }
            }
        }
    }
    else if (line.starts_with("turn on")){
        sscanf(line.c_str(), "turn on %d,%d through %d,%d", &x1, &y1, &x2, &y2);
        for (int i = x1; i <= x2; i++){
            for (int j = y1; j <= y2; j++){
                rows[j][i]++;
                counter++;
            }
        }
    }
    else{
        sscanf(line.c_str(), "toggle %d,%d through %d,%d", &x1, &y1, &x2, &y2);
        for (int i = x1; i <= x2; i++){
            for (int j = y1; j <= y2; j++){
                rows[j][i] += 2;
                counter += 2;
            }
        }
    }
    return counter;
}

int main(int ac, char** av){
    if (ac != 2)
        return -1;

    int counter_first = 0;
    long counter_second = 0;
    std::string line;
    std::ifstream inFile(av[1]);
    std::vector<std::bitset<1000>> rows_first(1000);
    std::vector<std::vector<int>> rows_second(1000, std::vector<int>(1000, 0));

    if (inFile.is_open()){
        while(getline(inFile, line)){
            doOperationFirst(line, rows_first);
            counter_second += doOperationSecond(line, rows_second);
        }
        inFile.close();
    }
    for (auto row: rows_first)
        counter_first += row.count();
    std::cout << counter_first << std::endl;
    std::cout << counter_second << std::endl;
    return 0;
}

