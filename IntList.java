public class IntList {
	private int item;
	private IntList next;
	
	public IntList(int item) {
         this(item, null);
     }
	 
	public IntList(int item, IntList next) {
        this.item = item;
        this.next = next;
    }
	
	 /** Returns the integer stored by this IntList. */
    public int item() {
        return item;
    }

    /** Returns the next node stored by this IntList. */
    public IntList next() {
        return next;
    }
    public static IntList list(int... items) {
        /** Check for cases when we have no element given. */
        if (items.length == 0) {
            return null;
        }
        /** Create the first element. */
        IntList head = new IntList(items[0]);
        IntList last = head;
        /** Create rest of the list. */
        for (int i = 1; i < items.length; i++) {
            last.next = new IntList(items[i]);
            last = last.next;
        }
        return head;
    }


	public int get (int position) {
		
		if (position < 0){
				throw new IllegalArgumentException("POSITION OUT OF BOUNDS");
		}
		IntList element = this;
		for (int i = 0 ; i < position; i++){
			if (i < 0){
			throw new IllegalArgumentException("POSITION OUT OF BOUNDS");
		}
			element = element.next;
			if(element==null)
				throw new IllegalArgumentException("POSITION OUT OF BOUNDS");
			
		}
		return element.item;
		
	}
	
	public int size() {
		IntList check = this.next;
		int count = 1;
		while (check !=null) {
			check = check.next;
			count++;
		}
		return count;
	}
	public String toString() {
		String rep = "( ";
		for (int i = 0; i < this.size(); i++) {
			rep = rep + this.get(i)+ " ";
			
		}
		
		rep = rep + ")"; 
		return rep;
	}
	
	public boolean equals (Object o) {		
		if (! (o instanceof IntList)) {
			return false;
		}
		else {
			if (((IntList)o).size() != this.size()) 
				return false;
			else{
			
		
				for (int i=0; i<this.size(); i++) {
			
					if(this.get(i)!=((IntList)o).get(i))
						return false;
				}
			return true;
			
			}
		}	
	}
		
	public void add(int item) {
		IntList lastnode = this;
		
		while (lastnode.next!=null)
			lastnode = lastnode.next;
		
		lastnode.next = new IntList (item, null);
	        
	}	
		  
	public int smallest (){
		if (next == null) {
			return item;
		} else { 
			int small = next.smallest ();
			if (item < small) {
				return item; 
			} 	
			return small;
			}
		}

	
	public int squaredSum() {
		int sum = 0; 
		for(int i = 0 ; i < this.size(); i++)
			sum += this.get(i)*this.get(i);
		
		return sum;
			
	}
	public static IntList append (IntList L1, IntList L2) {
		if (L1==null && L2==null) {
			return null;
		} else if (L1 == null) {
			return L2;	
		} else if (L2== null) {
			return L1;
		} else {
			IntList L3 = new IntList(L1.get(0));
			IntList ans = L3;
			for (int i = 1; i < L1.size(); i++) {
				L3.next = new IntList(L1.get(i));
				L3 = L3.next;
				
			}
			for (int i = 0; i < L2.size(); i++) {
				L3.next = new IntList(L2.get(i));
				L3 = L3.next;
			}
			return ans;
		}
	}
