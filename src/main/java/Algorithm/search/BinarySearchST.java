package Algorithm.search;

public class BinarySearchST<Key extends Comparable<Key>, Value> {
    private Key[] keys;
    private Value[] vals;
    private int N;
    public BinarySearchST(int capacity){
        keys = (Key[]) new Comparable[capacity];
        vals = (Value[]) new Object[capacity];
    }

    public int size(){
        return N;
    }

    public Value get(Key key){
        if (keys==null || keys.length==0){
            return null;
        }
        int i = rank(key);
        if (i < N && keys[i].compareTo(key)==0){
            return vals[i];
        }else {
            return null;
        }
    }

    public int rank(Key key){
        return rank(key,0, N-1);
    }

    /**
     * 二分查找-递归
     * @param key
     * @param lo
     * @param hi
     * @return
     */
    private int rank(Key key, int lo, int hi){
        if (hi<lo) return lo;
        int mid = lo + (hi - lo)/2;
        int cmp = key.compareTo(keys[mid]);
        if (cmp<0) {
            return rank(key, lo, mid-1);
        }else if(cmp > 0) {
            return rank(key, mid+1, hi);
        }else {
            return mid;
        }
    }

    /**
     * 二分查找-迭代-非递归
     * @param key
     * @return
     */
    private int iterativeRank(Key key){
      int lo = 0, hi = N-1;
      int mid,cmp;
      while ( lo <= hi){
          mid = lo + (hi-lo)/2;
          cmp = key.compareTo(keys[mid]);
          if (cmp<0){
              hi = mid -1;
          }else if (cmp>0){
              lo = mid +1;
          }else {
              return mid;
          }
      }
        return lo;
    }


    public void put(Key key, Value val){
        int i = rank(key);
        if (i < N && keys[i].compareTo(key) == 0){
            vals[i] = val;
            return;
        }
        for (int j = N; j > i; j--){
            keys[j] = keys[j-1];
            vals[j] = vals[j-1];
        }
        keys[i] = key;
        vals[i] = val;
        N++;
    }

}
