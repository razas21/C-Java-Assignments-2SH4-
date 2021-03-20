#include <stdio.h>
#include <stdlib.h>
#include "Questions.h"

//Where val1, pos1 and val2, pos2 represent two sparse vectors of integers, stored efficiently. k1 is the 
//number of non-zero elements of vector 1 and k2 is the number of non-zero elements of vector 2. 
//Function addEff() has to add the two vectors and store the result in efficient representation as well, using 
//val3, pos3

void addEff(int val1[], int val2[], int val3[], int pos1[], int pos2[], int pos3[], int k1, int k2){
	//go through pos[]1 and pos2[] and see if they are equal
	//if they are not, THEN set pos3 and val3 as they are
	//if they are equal then sum val1[i] and val2[i] THEN set pos3 and val3 as they are
	int i, j, sum, index = 0, pass=0;
	for (i=0; i<k1; i++){  //{-11,-44,-33}  {1,2,4}
		for (j=0; j<k2; j++){  //{44,-55,66}   {2,4,6}
			if (pos1[i] == pos2[j]){
				sum = (val1[i] + val2[j]);
				if (sum!=0){
					pos3[index] = pos1[i];
					val3[index] = sum;
				}
				if (sum==0){
					pass = 1;
					index--;
				}
				index++;
				pass = 1;
			}
		}
		if (pass == 0) {
			pos3[index] = pos1[i];
			val3[index] = (val1[i]);
			index++;
		}
		pass = 0;
}
	for (i=0; i<k2; i++){  //         {2,4,6}  {44,-55,66}
			for (j=0; j<k1; j++){ //  {1,2,4}  {-11,-44,-33}
				if (pos2[i] == pos1[j]){
					pass = 1;
				}
			}
			if (pass == 0) {
				pos3[index] = pos2[i];
				val3[index] = (val2[i]);
				index++;
			}
			pass = 0;
	}

	int size = 0, temp, temp2;
	for (i=0; pos3[i]!=0; i++){
		size++;
	}

	for(i=0; i<size; i++)
	    {
	        for(j=i+1; j<size; j++)
	        {
	            if(pos3[j] < pos3[i])
	            {
	                temp = pos3[i];
	                temp2 = val3[i];
	                pos3[i] = pos3[j];
	                val3[i] = val3[j];
	                val3[j] = temp2;
	                pos3[j] = temp;
	            }
	        }
	    }

//	for (i=0; i<size; i++){
//		printf("%d %d", pos3[i],val3[i]);
//		printf("\n");
//	}
}
