
public class Matrix {
    
	private int[][]  matrixData;
	private int    rowsNum;	
	private int    colsNum;	
	
	public Matrix( int row, int col ) 
	{   
		/*
		* constructs a row-by-col matrix with
		* all elements equal to 0; if row <= 0, the number of rows of the matrix is set to
		* 3; likewise, if col <= 0 the number of columns of the matrix is set to 3.		
		*/
		if (row <= 0 && col <= 0) { //case where both rows and columns are less than or equal zero
			matrixData = new int[3][3]; //allocates matrix
			for (int i=0; i<3; i++) { //loop to initialize all 
				for (int j=0; j<3; j++) {
					matrixData[i][j] = 0; 
				}
			}
		}
		else if (row <= 0) {
			matrixData = new int[3][1]; //allocates matrix
			for (int i=0; i<3; i++) {
				matrixData[i][0] = 0;  //initializes matrix values with zeroes
			}
		}
		else if (col <= 0) {
			matrixData = new int[1][3]; //allocates matrix
			for (int i=0; i<3; i++) {
				matrixData[0][i] = 0;  //initializes matrix values with zeroes
			}
		}
			
		else {
			matrixData = new int[row][col]; //allocates matrix based on inputs
			for (int i=0; i<row; i++) {
				for (int j=0; j<col; j++) {
					matrixData[i][j] = 0; //initializes matrix values with zeroes
				}
			}
		}
	}

	public Matrix( int[][] table) 
	{	

		/*
		* constructs a matrix out of the two dimensional array table, with the same number of rows, columns, and the same
		* element in each position as array table.
		*/ 
		
		matrixData = table;
	}
	
	public int getElement(int i, int j) throws IndexOutOfBoundsException
	{ 	
		/*
		* returns the element on row i and column j of this matrix; 
		* it throws an exception (IndexOutOfBoundsException) if any of indexes i and j is not in the required range 
		* (rows and columns indexing starts with 0)
		*  the detail message of the exception should read: "Invalid indexes".
		*/
		try {
			return this.matrixData[i][j]; 
		}
		catch (IndexOutOfBoundsException e) {
			throw new IndexOutOfBoundsException("Invalid indexes.");
		}
	}
        
    public boolean setElement(int x, int i, int j){ 
        
        /* the detail message of the exception should read: "Invalid indexes" */
    	try {
    		this.matrixData[i][j] = x; //sets element in row i and column j of matrix to x
    		return true;
    	}
    	catch (IndexOutOfBoundsException e) { //exception for invalid index 
    	    return false; 
    	}
    } 

    public Matrix copy(){ 
    	/* fix the code and write your implementation below */
		Matrix copy = new Matrix (this.matrixData.length,this.matrixData[0].length); //allocates new matrix with same number rows and cols as Matrix
		for (int i=0; i<this.matrixData.length; i++) {  //loops through rows
			for (int j=0; j<this.matrixData[0].length; j++) {  //loops through columns
				copy.setElement(getElement(i,j),i,j); //finds value of this element then sets it as the same
			}
		}
        return  copy; //returns copied matrix
    }    
                
	public void addTo( Matrix m ) throws ArithmeticException
	{
		if (m.matrixData.length==matrixData.length && m.matrixData[0].length==matrixData[0].length ) { //makes sure size of matrices are the same
			for (int i=0; i<matrixData.length; i++) { //loops through rows
				for (int j=0; j<matrixData[0].length; j++) { //loops through columns
					setElement((m.getElement(i,j)+getElement(i,j)),i,j);
				}
			}
		}
		else {
			throw new ArithmeticException("Invalid operation");
		}
		/* the detail message of the exception should read: "Invalid operation". */
		
	
	}
	
    public Matrix subMatrix(int i, int j) throws ArithmeticException{ 
        
		/* The exception detail message should read: "Submatrix not defined"*/
    	
    	/* fix the code and write your implementation below */
		Matrix subM = new Matrix (i+1,j+1); //adds one since allocating size

    	if ((i <= matrixData.length && j <= matrixData[0].length) && (i>0||(j>0))) { //makes sure submatrix is within matrix
    		for (int i1=0; i1<=i; i1++) {
    			for (int j1=0; j1<=j; j1++) {
    				subM.setElement(getElement(i1,j1),i1,j1);
    			}
    		}
    	}
    	else {
			throw new ArithmeticException("Submatrix not defined");
    	}
        return  subM; 
        
    }
        
    public int getsizeofrows(){ 
           
		/* update below return */
    	
		return (this.matrixData.length);
    }
        
    public int getsizeofcols(){
		
		/* update below return */
        return (this.matrixData[0].length); 
    }
        
    public boolean isUpperTr(){
        boolean diag = true;
		/* write your implementation here and update return accordingly */
    	for (int i = 0; i < getsizeofrows(); i++) { //loops through rows
    		for (int j = 0; j < i; j++) { //loops through columns stopping before current row (diagonal)
    			if (getElement(i,j) != 0) {
    				return false; 
    			}
    			else {
    				continue; //diag still true
    			}
    		}
    	}
    	if (getsizeofrows() == 0) {
    		return false; //empty matrix case
    	}
        return true;  
	}
        
    public static Matrix sum(Matrix[] matArray) throws ArithmeticException{
            
            
        Matrix superMatrix = new Matrix (matArray[0].getsizeofrows(),matArray[0].getsizeofcols()); //allocates memomry based off of first matrix in array
        for (int i=0; i<matArray.length; i++) { //loops through matrices
        	if ((matArray[0].getsizeofrows() != matArray[i].getsizeofrows()) || matArray[0].getsizeofcols() != matArray[i].getsizeofcols() ) {
    			throw new ArithmeticException("Invalid operation");
        	}
        	else {
        		superMatrix.addTo(matArray[i]); //adds superMatrix with current matrix
        	}
        }
        return superMatrix; 
    }
        
	public String toString(  )
	{
		int rows = getsizeofrows();
		int cols = getsizeofcols();
		String output = new String(); 
		for (int i=0; i<rows; i++) {
			for (int j=0; j<cols; j++) {
				output = output + (Integer.toString(this.matrixData[i][j]) + " ");
			}
			output = output + ("\n"); //adds newline to make it appear as matrix should

		}		return output;
	}
}