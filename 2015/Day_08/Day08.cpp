#include <iostream>
#include <utility>
#include <string>
#include <fstream>
#include <algorithm>
#include <iostream>
#include <algorithm>

//New length for the part b encoding
int reEncodedLength(std::string line){
    int extra = 2;

    for (char c : line) {
        if (c == '"' || c == '\\') {
            extra++;
        }
    }

    return line.length() + extra;
}

//Check for valid hexa chars after an initial \x
bool isHexaChar(char c){
    return (c >= '0' && c <= '9') ||
           (c >= 'A' && c <= 'F') ||
           (c >= 'a' && c <= 'f');
}

int doOperationFirst(std::string line){
    if (!line.empty() && line.back() == '\r')
        line.pop_back();
    int real_chars = 0;
    for (int i = 0; i < line.length(); i++){
        real_chars++;
        if (line[i] == '\\'){
            if (i + 3 < line.length() && line[i + 1] == 'x' && isHexaChar(line[i + 2]) && isHexaChar(line[i + 3])){
                i += 3;
            }
            else if (i + 1 < line.length() && line[i + 1] == '\\')
                i += 1;
            else if (i + 1 < line.length() && line[i + 1] == '"'){
                i += 1;
            }
        }
        else if (line[i] == '"')
            real_chars--;
    }
    return (line.length() - real_chars);
}

int doOperationSecond(std::string line){
    if (!line.empty() && line.back() == '\r')
        line.pop_back();
    return (reEncodedLength(line) - line.length());
}

int main(int ac, char** av){
    if (ac != 2)
        return -1;

    int result_first = 0;
    int result_second = 0;
    std::string line;
    std::ifstream inFile(av[1]);

    if (inFile.is_open()){
        while(getline(inFile, line)){
            result_first += doOperationFirst(line);
            result_second += doOperationSecond(line);
        }
        std::cout << result_first << std::endl;
        std::cout << result_second << std::endl;
        inFile.close();
    }
    return 0;
}

