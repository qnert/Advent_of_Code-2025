#include <iostream>
#include <fstream>
#include <cstdlib>
#include <algorithm>
#include <vector>
#include <initializer_list>

int doOperationFirst(std::string line){
    int length;
    int width;
    int height;
    int first_cut = line.find('x');
    int second_cut = line.find('x', first_cut + 1);

    length = std::stoi(line.substr(0, first_cut));
    width = std::stoi(line.substr(first_cut + 1, second_cut - first_cut - 1));
    height = std::stoi(line.substr(second_cut + 1));

    int* surfaces = new int[3];
    surfaces[0] = 2 * length * width;
    surfaces[1] = 2 * width * height;
    surfaces[2] = 2 * height * length;
    return (surfaces[0] + surfaces[1] + surfaces[2] + std::min({surfaces[0] / 2, surfaces[1] / 2, surfaces[2] / 2}));
}

int doOperationSecond(std::string line){
    std::vector<int> edges;
    int first_cut = line.find('x');
    int second_cut = line.find('x', first_cut + 1);

    edges.push_back(std::stoi(line.substr(0, first_cut)));
    edges.push_back(std::stoi(line.substr(first_cut + 1, second_cut - first_cut - 1)));
    edges.push_back(std::stoi(line.substr(second_cut + 1)));

    std::sort(edges.begin(), edges.end());
    return (2 * edges[0] + 2 * edges[1] + edges[0] * edges[1] * edges[2]);
}

int main(int ac, char** av){
    if (ac != 2)
        return -1;
    std::string line;
    int counter_first = 0;
    int counter_second = 0;
    std::ifstream inFile(av[1]);

    if (inFile.is_open()){
        while(getline(inFile, line)){
            counter_first += doOperationFirst(line);
            counter_second += doOperationSecond(line);
        }
        inFile.close();
    }

    std::cout << counter_first << std::endl;
    std::cout << counter_second << std::endl;
    return 0;
}

