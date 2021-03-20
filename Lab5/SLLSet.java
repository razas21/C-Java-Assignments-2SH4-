
public class SLLSet{
    private int size;   
    private SLLNode head;

    public SLLSet() {
		this.head = new SLLNode(0,null);;
    }

    public SLLSet(int[] sortedArray) {
        int i;
		SLLNode temp = new SLLNode(sortedArray[sortedArray.length-1],null);	
//		System.out.printf("\nsortedArray[%d]= %d", sortedArray.length, sortedArray[sortedArray.length-1]);
    	
		for (i=sortedArray.length-1; i>0; i--) {
//			System.out.printf("\nsortedArray[%d]= %d", i, sortedArray[i-1]);
			
    		if (i == 1) {
    			this.head = new SLLNode(sortedArray[i-1],temp);
//    			System.out.printf("\nhead= %d", i, sortedArray[i-1]);
    			break;
    		}
    		temp = new SLLNode(sortedArray[i-1],temp);
    	}
    }

    public int getSize() {
    	this.size = 0;
    	SLLNode temp = this.head;

    	while (temp.next != null) {
    		this.size ++;
    		temp = temp.next;
//        	System.out.printf("value: %d \n", temp.value);

    	}
    	if (this.head.next != null){
    		this.size ++;
    	}
//    	System.out.printf("size: %d \n", this.size);

        return this.size;
    }

    public SLLSet copy() {
    	int i;
    	SLLNode copyTemp = new SLLNode(this.head.value,null); //copy head
    	SLLNode temp = this.head;  //original head
        SLLSet Copy = new SLLSet();
        
        Copy.size = getSize();
    	System.out.printf("SIZE: %d \n", this.size);

        Copy.head = copyTemp;
        
//    	System.out.printf("size: %d \n", this.size);

    	for (i=0; i<this.size;i++) {
//        	System.out.printf("og: %d  new: %d ",temp.value, copyTemp.value); 
        	if (temp.next==null) {
//            	System.out.printf("ok");
        		break;
        	}
        	temp = temp.next;
        	copyTemp.next = new SLLNode(temp.value, null);
    		copyTemp = copyTemp.next;
    	}
//    	System.out.printf("size: %d \n", this.size);

        return Copy;
    }

    public boolean isIn(int v) {
    	SLLNode temp = this.head;
    	while (temp.next != null) {
    		if (temp.value == v) {
    			return true;
    		}
    		temp = temp.next;
    	}
    	if (temp.value == v) { //case where number is last
    		return true;
    	}
        return false;
    }

    public void add(int v) {
        if (isIn(v) == false) {
        	SLLNode temp = this.head;
        	SLLNode oldtemp = null;
        	if (temp.value > v) {
        		this.head = new SLLNode(v, temp);
        	}
        	else { 
	        	while (temp.value < v) { //iterate until greater than v found
	        		if (temp.next == null) { //this is the case where v is greatest num
	        			break;
	        		}
	        		oldtemp = temp;
	        		temp = temp.next; //this value is the value before v (after insertion)
	        	}
	        	if (temp.next == null) {
	        		temp.next = new SLLNode(v,null);
	        	}
	        	else {
	        		oldtemp.next = new SLLNode(v,temp);
	        	}
        	}
        }
    }

    public void remove(int v) {
        if (isIn(v)==true) { //checking if v is currently in list
        	SLLNode temp = this.head;
    		SLLNode oldtemp = null;

    		if (temp.value == v) { //first entry case
    			temp = temp.next;
    			this.head = temp;
    		}
    		else { //middle or end cases
	        	while (temp.value!=v) {
	        		oldtemp = temp;
	        		temp = temp.next;
	        	}
	        	if (temp.next == null) { //case where v is at the end
	        		oldtemp.next = null;
	        	}
	        	else {
		        	temp=temp.next;
		        	oldtemp.next = temp;
	    		}
    		}
        }
    }
    
//    public boolean test() {
//    	SLLNode temp = this.head;
//    	while (temp.next!=null) {
//    		temp = temp.next;
//    	}
//    	temp = temp.next;
//    	if (temp == null) {
//    	    	System.out.printf("  ok  ");
//	
//    	}
//    	return true;
//    	
//    }

    public SLLSet union(SLLSet s) {
        SLLSet newSet = new SLLSet();
    	SLLNode set1 = this.head; //this is used to track current node
    	SLLNode set2 = s.head; //this is used to track current node
    	SLLNode currNode = null; //this is used for the new set
//    	System.out.printf("%d", s.head.value);
    	
    	//initializing head
    	if (this.head == null || s.head == null) return newSet;
    	else if (s.head == null) return this; //returning this list if s is empty
    	else if (this.head == null) return s; //if object list empty, returning s array
    	else {
	    	if (this.head.value < s.head.value) { //makes new head equivalent to the lower of the two heads
	    		newSet.head = new SLLNode(this.head.value,null);
	    		currNode = newSet.head;
	    		set1 = set1.next;
	    	}
	    	else if (this.head.value > s.head.value){
	    		newSet.head = new SLLNode(s.head.value,null);
	    		currNode = newSet.head;
	    		set2 = set2.next;
	    	}
	    	else { //case heads are equal
	    		newSet.head = new SLLNode(this.head.value,null);
	    		currNode = newSet.head;
	    		set1 = set1.next;
	    		set2 = set2.next;
	    	}
	    	
	    	//creating rest of the set
	    	while (set1 != null && set2 != null) { //running until we reach the end of either set
	    		if (set1.value < set2.value) { 
	    			currNode.next = new SLLNode(set1.value,null);
	    			currNode = currNode.next; //goes onto next value of new set
	    			set1 = set1.next;
	    		}
	    		else if (set1.value > set2.value) {
	    			currNode.next = new SLLNode(set2.value,null);
	    			currNode = currNode.next; //goes onto next value of new set
	    			set2 = set2.next;
	    		}
	    		else { //both set values are equivalent
	    			currNode.next = new SLLNode(set1.value,null);
	    			currNode = currNode.next;
	    			set1 = set1.next;
	    			set2 = set2.next;
	    		}
	    	}
	    	
	    	if (set1 == null) { //this is to continue after set1 is run through
	    		while (set2 != null) {
	        		currNode.next = new SLLNode(set2.value,null);
	        		currNode = currNode.next;
	        		set2 = set2.next;
	    		}
	    	}
	    	if (set2 == null) { //this is to continue after set2 is run through
	    		while (set1 != null) {
	        		currNode.next = new SLLNode(set1.value,null);
	        		currNode = currNode.next;
	        		set1 = set1.next;
	    		}
	    	}
	    	
	        return newSet;
    	}
    }

    public SLLSet intersection(SLLSet s) {

		SLLSet newSet = new SLLSet();
		
		if (s.head == null || this.head == null) return newSet; //same logic as union 
		
		else {
			//iterating variables
			SLLNode temp1 = this.head;
			SLLNode temp2 = s.head;
			
			//loop runs through list and if values are equivalent, sets head to the value and exits loop. if one value is smaller than other, it moves on to next element
			while (temp1 != null && temp2!= null) {
				if (temp1.value == temp2.value) {
					newSet.head = new SLLNode(temp1.value,null);
					temp1 = temp1.next;
					temp2= temp2.next;
					newSet.size+=1;
					break;
				}
				
				else if(temp1.value < temp2.value) temp1 = temp1.next;
				
				else if(temp1.value > temp2.value) temp2 = temp2.next;
			} 		
		
			SLLNode tracker = newSet.head;
			
			//loop adds remaining elements to newSet, if values are equivalent, it puts value in newlist and moves on, 
			//if one is smaller than other it pushes the list to next element for the smaller one
	
			while (temp1!=null && temp2!=null) {
				if (temp1.value == temp2.value) {
					SLLNode nodeNew = new SLLNode (temp1.value, tracker.next);
					tracker.next = nodeNew;
					temp1 = temp1.next;
					temp2 = temp2.next;
					tracker = tracker.next;
					newSet.size+=1;
				}	
				else if (temp1.value < temp2.value) temp1 = temp1.next;
				else if (temp1.value > temp2.value) temp2 = temp2.next;
			}
		}
		return newSet;
	}


    public SLLSet difference(SLLSet s) {
    	// when lists are both null, return this
		if (s.head == null || this.head == null) return this;
		
		else {
			
			// Make list copy if above if statement does not work
			SLLSet copy = this.copy();
			
			// iterating through lists
			SLLNode temp = s.head;

			// While temp is not null, it removes the element and moves on only if criteria for difference is met
			while (temp != null) {
				if (copy.isIn(temp.value) == true) {
					copy.remove(temp.value);
				}
				temp = temp.next;
			}
			return copy;
		}
    }

    public static SLLSet union(SLLSet[] sArray) {
    	SLLSet ss = new SLLSet();

		//Iterates through array and uses union on every list in array
		
		for (int a = 0; a < sArray.length; a++) {
			ss = ss.union(sArray[a]);
		}

        return ss;
    }

    @Override
    public String toString() {
    	int i;
//		System.out.printf("Str-ok ");
		String output = new String();  //initialzing string
		SLLNode temp = this.head;
    	for (i=0; i<this.size; i++) {
    		output = output + temp.value;
    		temp = temp.next;
    		if (temp != null) {
    			output = output + ", ";
    		}
    	}
    	return output;
    }
}
