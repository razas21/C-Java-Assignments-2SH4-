#include <stdio.h>
#include <stdlib.h>
#include "Questions.h"

//Classlist Program
student **create_class_list(char *filename, int *sizePtr){
	
	student **class_list; //initializes list of pointers to student pointers (point to student vars)
	int i;

	/*write your implementation here*/
	FILE *input_file=fopen(filename,"r");

	fscanf(input_file,"%d", sizePtr);
	class_list = (student**)calloc((*sizePtr), sizeof(student *));


	for (i=0; i<*sizePtr; i++){
		class_list[i] = (student*)calloc(*sizePtr, sizeof(student));
		fscanf(input_file, "%d", &class_list[i] -> student_id);
		fscanf(input_file, "%s", class_list[i] -> first_name);
		fscanf(input_file, "%s", class_list[i] -> last_name);
	}

	/* finally return the created class list*/
	fclose(input_file);
	return class_list;
}

int find(int idNo, student **list, int size)
{
	int i;
	for (i=0; i<size; i++){
		if (list[i]-> student_id == idNo) {
			return i;
		}
	}
	/*write your implementation here and update the return accordingly*/
	return -1;
}

void input_grades(char *filename, student **list, int size)
{

	/*write your implementation here*/
	FILE *input_file=fopen(filename,"r");
	int i, student_id;
	for (i=0; i<size; i++){
		fscanf(input_file,"%d", &student_id);
		fscanf(input_file,"%d %d", (&list[find(student_id, list, size)] -> project1_grade), (&list[find(student_id, list, size)] -> project2_grade));
	}
	fclose(input_file);

}

void compute_final_course_grades(student **list, int size)
{
	
	/*write your implementation here*/
	int i;
	double avg;
	for (i=0; i<size; i++){
		avg = ((list[i]->project1_grade) + (list[i]->project2_grade))/2.0;
		list[i]->final_grade = avg;
	}
	}

void output_final_course_grades(char *filename, student **list, int size)
{
	
	/*write your implementation here*/
	int i;
	FILE *output_file=fopen(filename,"w");
	fprintf(output_file,"%d\n", size);
	for (i=0; i<size; i++){
		fprintf(output_file, "%d %f\n", list[i]->student_id, list[i]->final_grade);
	}
	fclose(output_file);

}


void withdraw(int idNo, student **list, int* sizePtr)
{
	/*write your implementation here*/
	int i, size;
	size = *sizePtr;
//	printf("%d ", list[idNo]);
//	printf("%d ", list[(size-1)]);

	if (find(idNo, list, *sizePtr) == -1){
		printf("That ID number is not in the classlist.");
		return;
	}
	else {
		for (i=find(idNo, list, *sizePtr); i<*sizePtr-1; i++){
			list[i] = list[i+1];
		}
		free(list[*sizePtr]);
	}
}

void destroy_list(student **list, int *sizePtr)
{
	/*write your implementation here*/
	int i;
	for (i=0; i<*sizePtr; i++){
		free(list[i]);
	}
	free(list);
	*sizePtr = 0;
}
