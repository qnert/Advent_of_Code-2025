#include <iostream>
#include <fstream>

void doOperationFirst(std::string line){
    int open_brackets = 0;
    int closed_brackets = 0;

    for (char c: line){
        if (c == '(')
            open_brackets++;
        else if (c == ')')
            closed_brackets++;
    }

    std::cout << "We are in the " << open_brackets - closed_brackets << " floor!" << std::endl;
    return;
}

void doOperationSecond(std::string line){
    int count = 1;
    int open_brackets = 0;
    int closed_brackets = 0;

    for (char c: line){
        if (c == '(')
            open_brackets++;
        else if (c == ')')
            closed_brackets++;
        
        if (open_brackets - closed_brackets < 0)
            break;
        count++;
    }

    std::cout << "The " << count << " character causes him to enter into the basement!" << std::endl;
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

    doOperationFirst(line);
    doOperationSecond(line);
    return 0;
}

