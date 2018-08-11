package Others.base.Collections.MapDemo;

import Utills.PrintUtill;

/** Map的简单实现。有助于观察关联数组是如何创建的。
 * @param <K>
 * @param <V>
 */
public class AssociativeArray<K ,V> {
    private Object[][] pairs;
    private int index;
    public AssociativeArray(int length){
        pairs = new Object[length][2];
    }
    public void put(K key, V valuel){
        if (index > pairs.length){
            throw new ArrayIndexOutOfBoundsException();
        }
        pairs[index++] = new Object[]{key,valuel};
    }

    @SuppressWarnings("unchecked")
    public V get(K key){
        for (int i=0; i<index; i++){
            if (key.equals(pairs[i][0])){
                return (V) pairs[i][1];
            }
        }
        return null;
    }


    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i=0; i <index; i++){
            result.append(pairs[i][0].toString());
            result.append(" : ");
            result.append(pairs[i][1].toString());
            if (i < index -1){
                result.append("\n");
            }
        }
        return result.toString();
    }

    public static void main(String[] args) {
        AssociativeArray<String, String> map = new AssociativeArray<String, String>(6);
        map.put("sky","blue");
        map.put("grass", "green");
        map.put("ocean", "dancing");
        map.put("tree", "tall");
        map.put("earth", "brown");
        map.put("sun", "warm");

        try {
            map.put("extra", "Object");
        } catch (ArrayIndexOutOfBoundsException e){
            PrintUtill.println("Too many objects!");
        }

        PrintUtill.print(map);

        PrintUtill.printlnRule();

        PrintUtill.print(map.get("ocean"));
    }
}
