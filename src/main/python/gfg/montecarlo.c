#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <time.h>
#include <limits.h>

//Monty Carlo method for computing pi
//Inscribe circle (r = 1) in a square and divide the square into 4ths
//Take the top right quadrant and count the number of dots that land within the circle / total number of dots
//pi * r ^ 2 = A
//pi = A
//A = number of dots in quadrant * 4
int main(int argc, char **argv) {
	time_t t;

	srand((unsigned)time(&t));

	int MAX = 1;
	while(1) {
		int n = 0;
		int inside = 0;

		for(int i = 0; i < MAX; ++i) {
			float x = rand() * 1.0/INT_MAX;
			float y = rand() * 1.0/INT_MAX;
			//printf("%f, %f\n", x, y);
			if(sqrt(pow(x, 2) + pow(y, 2)) <= 1) {
				inside++;
			}
		}

		printf("%d) pi ~= %f\n", MAX, inside * 4.0 / MAX);
		MAX++;
	}

	return 0;
}
