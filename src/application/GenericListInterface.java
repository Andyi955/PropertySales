package application;

public interface GenericListInterface<T> {

    public boolean add(T newEntry);
    public void add(int index, T newEntry);
    public Object remove(int index);
    public void clear();
    public Object set(int index, T anEntry); // like replace
    public Object get(int index); // like getEntry
    public boolean contains(T anEntry);
    public int size(); // like getLength
    public boolean isEmpty();
}
