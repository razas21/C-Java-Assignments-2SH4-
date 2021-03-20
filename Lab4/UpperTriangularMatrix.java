/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jianfeng
 */
//n*(n+1)/2
public class UpperTriangularMatrix {
	private int n;
	private int[] array;

	public UpperTriangularMatrix(int n) {
		if (n <= 0) { 
			this.n = 1; //sets size of matrix to 1 
			array = new int[this.n]; //allocates array

		} else {
			this.n = n;
		}
		this.array = new int[this.n * (this.n + 1) / 2]; //allocates array size based off formula given
	}

	public UpperTriangularMatrix(Matrix upTriM) throws IllegalArgumentException {
		if (upTriM.isUpperTr() == true && upTriM.getsizeofrows() == upTriM.getsizeofcols()) {  //makes sure its an upper trianular square matrix
			this.n = upTriM.getsizeofrows(); //gets size of matrix
			this.array = new int[this.n * (this.n + 1) / 2];  //allocates array
			int z = 0;
			for (int i = 0; i < this.n; i++) {
				for (int j = i; j < this.n; j++) { //only goes through non-zero elements of triangular matrix
					this.array[z] = upTriM.getElement(i, j);
					z++; //goes to next element of array
				}
			}
		} else {
			throw new IllegalArgumentException("Not an upper triangular Matrix");
		}
	}

	public int getDim() {

		/* write your implementation here and update return accordingly */
		return this.n;
	}

	public int getElement(int i, int j) throws IndexOutOfBoundsException {

		/* write your implementation here and update return accordingly */
		try {
			return this.array[i * (2 * this.n - i + 1) / 2 + j - i];
		} catch (IndexOutOfBoundsException e) {
			throw new IndexOutOfBoundsException("Invalid indexes");
		}
	}

	public void setElement(int x, int i, int j) throws IndexOutOfBoundsException, IllegalArgumentException {
		int val = getElement(i, j);
		try {
			if (val == 0 && x != 0) { //ensures it's within upper triangle
				throw new IllegalArgumentException("Incorrect argument");
			} else {
				this.array[i * (2 * this.n - i + 1) / 2 + j - i] = x;
			}

		} catch (IndexOutOfBoundsException e) {
			throw new IllegalArgumentException("Invalid index");
		}
	}

	public Matrix fullMatrix() {
		Matrix full = new Matrix(n, n); //allocates new nxn matrix
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (j < i) { //skips lower triangular elements
					continue;
				}
				full.setElement(this.getElement(i, j), i, j);
			}
		}
		return full;
	}

	public String toString() {
		return this.fullMatrix().toString();
	}

	public int getDet() {
		int det = 1;
		for (int i = 0; i < this.getDim(); i++) {
			det *= this.getElement(i, i);
		}
		return det;
	}

	public double[] effSolve(double[] b) throws IllegalArgumentException {

		/* fix the following and write your implementation */
		
		//this.array --> A matrix (efficient)
		double[] sol = new double[b.length]; // --> x vector
		//b --> b vector
		double sum; 
		
		for (int i=0; i<this.n; i++) {  //loop to check if determinant is zero
			if (this.array[i * (2 * this.n - i + 1) / 2] == 0) {
				throw new IllegalArgumentException("The determinant of the matrix is 0");
			}
		}
		
		if (b.length != this.n) {   //check to if dimension of b is correct
			throw new IllegalArgumentException("The dimension of the matrix is not defined for operation");
		}
		
		for (int i = this.n - 1; i > -1; i--) {   //this loop solves for sol[]
			sum = 0.0; //used in calculations
			for (int j = i + 1; j < this.n; j++) { 
				sum += this.array[i * (2 * this.n - i + 1) / 2 + j - i] * sol[j];  //based off textbook formula
			}
			sol[i] = (b[i] - sum) / this.array[i*(2 * this.n-i + 1)/2]; //based off textbook formula
		}
		return sol;
	}
}