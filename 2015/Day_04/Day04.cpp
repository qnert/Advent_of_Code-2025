#include <iostream>
#include <utility>
#include <set>
#include <vector>
#include <fstream>
#include <algorithm>
#include <iostream>
#include <iomanip>
#include <sstream>
#include <openssl/md5.h>

std::string md5(const std::string& input) {
    unsigned char digest[MD5_DIGEST_LENGTH];
    MD5((unsigned char*)input.c_str(), input.size(), digest);

    std::ostringstream oss;
    for (int i = 0; i < MD5_DIGEST_LENGTH; ++i) {
        oss << std::hex << std::setw(2) << std::setfill('0')
            << (int)digest[i];
    }
    return oss.str();
}

void doOperationFirst(std::string line){
    int counter = 0;

    while(!md5(line + std::to_string(counter)).starts_with("00000")){
        counter++;
    }
    std::cout << md5(line + std::to_string(counter)) << " " << "Solution: " << counter << std::endl;
}

void doOperationSecond(std::string line){
    int counter = 0;

    while(!md5(line + std::to_string(counter)).starts_with("000000")){
        counter++;
    }
    std::cout << md5(line + std::to_string(counter)) << " " << "Solution: " << counter << std::endl;
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
    doOperationSecond(line);
    return 0;
}

