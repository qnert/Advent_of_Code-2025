#include <iostream>
#include <string>
#include <fstream>
#include <algorithm>
#include <vector>
#include <map>
#include <climits>
#include <utility>

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

std::string trim(std::string s) { return ltrim(rtrim(s)); }

std::vector<std::string> splitString(const std::string& str, const std::string& delim) {
    std::vector<std::string> tokens;
    size_t start = 0, pos;
    while ((pos = str.find(delim, start)) != std::string::npos) {
        tokens.push_back(trim(str.substr(start, pos - start)));
        start = pos + delim.length();
    }
    tokens.push_back(trim(str.substr(start)));
    return tokens;
}

int main(int ac, char** av) {
    if (ac != 2)
        return -1;

    std::string line;
    std::ifstream inFile(av[1]);
    if (inFile.is_open()) {
        while (getline(inFile, line)) {
            auto parts = splitString(line, " to ");
            if (parts.size() < 2) continue;
            std::string cityA = trim(parts[0]);
            auto rightParts = splitString(parts[1], " = ");
            if (rightParts.size() < 2) continue;
            std::string cityB = trim(rightParts[0]);
            int w = std::stoi(trim(rightParts[1]));

            int a = getOrAdd(cityA);
            int b = getOrAdd(cityB);
            dist[a][b] = w;
            dist[b][a] = w;
        }
        inFile.close();
    }
    return 0;
}
