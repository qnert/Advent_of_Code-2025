#include <iostream>
#include <utility>
#include <string>
#include <fstream>
#include <algorithm>
#include <iostream>
#include <algorithm>
#include <vector>
#include <map>
#include <sstream>
#include <cstdint>

//Own isNumber function
bool isNumber(const std::string& s) {
    std::istringstream iss(s);
    double d;
    char c;
    return (iss >> d) && !(iss >> c);
}

//Own whitespace trimming function
std::string ltrim(std::string s) {
    s.erase(s.begin(), std::find_if(s.begin(), s.end(), [](unsigned char c) {
        return !std::isspace(c);
    }));
    return s;
}

std::string rtrim(std::string s) {
    s.erase(std::find_if(s.rbegin(), s.rend(), [](unsigned char c) {
        return !std::isspace(c);
    }).base(), s.end());
    return s;
}

std::string trim(std::string s) {
    return ltrim(rtrim(s));
}

//own string split function with given delimiter
std::vector<std::string> splitString(const std::string& str, const std::string& delimiter) {
    std::vector<std::string> tokens;
    size_t start = 0, pos;

    while ((pos = str.find(delimiter, start)) != std::string::npos) {
        tokens.push_back(trim(str.substr(start, pos - start)));
        start = pos + delimiter.length();
    }
    tokens.push_back(trim(str.substr(start)));

    return tokens;
}

//recursive solving function for the problem
uint16_t doOperationFirst(std::map<std::string, std::string>& operations, std::map<std::string, uint16_t>& cache, std::string wire){
    if (cache.contains(wire))
        return cache[wire];
    uint16_t ret_val = 0;
    std::string op = operations[wire];
    std::vector<std::string> splitted = splitString(op, " ");
    if (splitted[1] == "->"){
        ret_val = isNumber(splitted[0].c_str()) ? std::stoi(splitted[0]) : doOperationFirst(operations, cache, splitted[0]);
    }
    else if (splitted[0] == "NOT"){
        ret_val = ~(doOperationFirst(operations, cache, splitted[1]));
    }
    else if (splitted[1] == "LSHIFT"){
        ret_val = doOperationFirst(operations, cache, splitted[0]) << std::stoi(splitted[2]);
    }
    else if (splitted[1] == "RSHIFT"){
        ret_val = doOperationFirst(operations, cache, splitted[0]) >> std::stoi(splitted[2]);
    }
    else if (splitted[1] == "AND"){
        std::string tmp = splitted[0];
        ret_val = isNumber(tmp.c_str()) ? std::stoi(tmp) & doOperationFirst(operations, cache, splitted[2]) : doOperationFirst(operations, cache, splitted[0]) & doOperationFirst(operations, cache, splitted[2]);
    }
    else if (splitted[1] == "OR"){
        ret_val = doOperationFirst(operations, cache, splitted[0]) | doOperationFirst(operations, cache, splitted[2]);
    }
    cache[wire] = ret_val;
    return ret_val;
}

int main(int ac, char** av){
    if (ac != 2)
        return -1;

    int counter_first = 0;
    long counter_second = 0;
    std::string line;
    std::ifstream inFile(av[1]);
    std::map<std::string, uint16_t> cache;
    std::map<std::string, std::string> operations;


    if (inFile.is_open()){
        while(getline(inFile, line)){
                int value = 0;
                std::string split;
                std::istringstream ss(line);
                std::vector<std::string> splitted = splitString(line, " ");
                operations[splitted.back()] = line;
        }
        inFile.close();
        
        //First Operation
        uint16_t res_1 = doOperationFirst(operations, cache, "a");
        std::cout << res_1 << std::endl;

        //Clear the Map and set b in the cache to the value from part 1 and use the same function with the new cache
        cache.clear();
        cache["b"] = res_1;
        uint16_t res_2 = doOperationFirst(operations, cache, "a");
        std::cout << res_2 << std::endl;

    }
    return 0;
}

