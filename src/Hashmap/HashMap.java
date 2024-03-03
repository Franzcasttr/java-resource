package Hashmap;

public class HashMap<K,V> {
    private List<Pair<K,V>>[] values;
    private int firstFreeIndex;
    public HashMap(){
        this.values = new List[32];
        this.firstFreeIndex = 0;
    }

    public V get(K key){
        int hashValue = Math.abs(key.hashCode() % this.values.length); // calculating hashvalue for the key
        if (this.values[hashValue] == null) {
            return null;
        }


        List<Pair<K,V>> valuesAtIndex = this.values[hashValue];

        for (int i = 0; i < valuesAtIndex.size(); i++) {
            if (valuesAtIndex.value(i).getKey().equals(key)) {
                return valuesAtIndex.value(i).getValue();
            }
        }

        return null;
    }

    public void add(K key, V value){

        List<Pair<K,V>> valuesAtIndex = getListBasedOnKey(key);


        int index = getIndexOfKey(valuesAtIndex, key);


        if (index <= 0) {
            valuesAtIndex.add(new Pair<K,V>(key, value));
            this.firstFreeIndex++;
        }else{
            valuesAtIndex.value(index).setValue(value);
        }

        if (1.0 * this.firstFreeIndex / this.values.length > 0.75) { //grow the size of the hash map if the number of key-value pairs in it is greater than 75% of the size of the internal array
            grow();
        }

    }

    private List<Pair<K,V>> getListBasedOnKey(K key){
        int hashValue = Math.abs(key.hashCode() % this.values.length);  //calculates the hash value for the key, and uses it to determine the suitable index in the internal array

        if (values[hashValue] == null) {
            return values[hashValue] = new List<>(); // If there is no value in that index, we create a list into that index
        }

        return values[hashValue];
    }


    private int getIndexOfKey(List<Pair<K, V>>myList, K key){

        for (int i = 0; i < myList.size(); i++) {
            if (myList.value(i).getKey().equals(key)) {
                return i;
            }
        }

        return -1;
    }

    private void grow(){
        List<Pair<K,V>>[] newArray = new List[this.values.length * 2];

        for (int i = 0; i < newArray.length; i++) {
            copy(newArray, i);
        }

        this.values = newArray;
    }


    private void copy(List<Pair<K,V>>[] newArray, int fromIdx){

        for (int i = 0; i < this.values[fromIdx].size(); i++) {
            Pair<K, V> value = this.values[fromIdx].value(i); // key value pairs from old

            int hashValue = Math.abs(value.hashCode() % newArray.length);

            if (newArray[hashValue] == null) {
                newArray[hashValue] = new List<>();
            }

            newArray[hashValue].add(value);
        }
    }

    public V remove(K key){

        List<Pair<K,V>> valuesAtIndex = getListBasedOnKey(key);
        if (valuesAtIndex.size() == 0) {
            return null;
        }

        int index = getIndexOfKey(valuesAtIndex, key);
        if (index <= 0) {
            return null;
        }

        Pair<K, V> pair = valuesAtIndex.value(index);

        valuesAtIndex.remove(pair);
        return pair.getValue();
    }




}

