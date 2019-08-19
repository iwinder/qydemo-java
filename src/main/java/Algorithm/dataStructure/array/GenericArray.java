package Algorithm.dataStructure.array;

public class GenericArray<T> {

    private final int DEFAUL_CAPACIPY = 10;
    private T[] data;
    private int size;

    public GenericArray(int capacipy) {
        this.data = (T[]) new Object[capacipy];
        this.size = 0;
    }

    public GenericArray() {
        this.data = (T[]) new Object[DEFAUL_CAPACIPY];
        this.size = 0;
    }

    public boolean add(int index, T o){
        checkIndexForAdd(index);
        if (size==data.length){
            resize(2*data.length);
        }
        for (int i=size;i>index;i--){
            data[i+1] = data[i];
        }
        data[index] = o;
        size++;
        return true;
    }

    public T remove(int index){
        checkIndexForRemove(index);
        T ret = data[index];
        for (int i = index+1;i<size;i++ ){
            data[i-1] = data[i];
        }
        size--;
        data[size] = null;

        if (size==data.length/4 && data.length/2!=0){
            resize(data.length/2);
        }
        return ret;
    }

    public boolean contains(T o){
        return indexOf(o) >= 0;
    }

    public int indexOf(T o){
        if (o == null){
            for (int i=0;i<size;i++){
                if (data[i] == null)
                    return i;
            }
        } else {
            for (int i=0;i<size;i++){
                if (o.equals(data[i]))
                    return i;
            }
        }
        return -1;
    }

    private void resize(int capacipy){
        T[] newData = (T[]) new Object[capacipy];
        for (int i=0;i<data.length;i++){
            newData[i] = data[i];
        }
        data = newData;
    }
    private void checkIndexForAdd(int index){
        if(index < 0 || index>size){
            throw new IllegalArgumentException("Add failed! Require index >=0 and index <= size.");
        }
    }

    private void checkIndexForRemove(int index){
        if(index < 0 || index>size){
            throw new IllegalArgumentException("Remove failed! Require index >=0 and index <= size.");
        }
    }
}
