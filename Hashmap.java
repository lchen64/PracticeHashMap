class HashMap {
    class MapNode {
        int key;
        int value;
        MapNode next;
        
        MapNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private ArrayList<MapNode> buckets;
    private int numEntries;
    
    public HashMap() {
        numEntries = 0;
        buckets = new ArrayList<>();
        for(int i = 0; i < 16; i++) {
            buckets.add(null);
        }
    }
    
    public void put(int key, int value) {
        int bucketIndex = getBucketIndex(key);
        MapNode node = new MapNode(key, value);
        
        if(buckets.get(bucketIndex) == null) {
            buckets.set(bucketIndex, node); 
        } else {
            MapNode temp = buckets.get(bucketIndex);
            while(temp != null) {
                if(temp.key == key) {
                    temp.value = value;
                    return;
                }    
                temp = temp.next;
            }
            
            node.next = buckets.get(bucketIndex);
            buckets.set(bucketIndex, node); 
        }
        
        double loadFactor = numEntries / buckets.size();
        if(loadFactor > 0.75) {
            rehash();
        }
    }
    
    private int getBucketIndex(int key) {
        return key % buckets.size();
    }
    
    private void rehash() {
        ArrayList<MapNode> temp = buckets;
        buckets = new ArrayList<>();
        for(int i = 0; i < temp.size() << 1; i++) {
            buckets.add(null);
        }
        
        for(int i = 0; i < temp.size(); i++) {
            MapNode node = temp.get(i);
            while(node != null) {
                put(node.key, node.value);
                node = node.next;
            }
        }
    }
    
    public int get(int key) {
        int bucketIndex = getBucketIndex(key);
        MapNode temp = buckets.get(bucketIndex);
        
        while(temp != null) {
            if(temp.key == key) {
                return temp.value;
            }
            temp = temp.next;
        }
        
        return -1;
    }
    
    /** Removes the mapping of the given key value if Hashmap contains a mapping for the key */
    public void remove(int key) {
        int bucketIndex = getBucketIndex(key);
        MapNode prev = null, temp = buckets.get(bucketIndex);
        
        if(temp == null) {
            return;    
        }
        
        if(temp.key == key) {
            buckets.set(bucketIndex, temp.next);
        } else {
            while(temp != null) {
                if(temp.key == key) {
                    prev.next = temp.next;
                    return;
                } 
                prev = temp;
                temp = temp.next;
            }   
        }
    }
}
