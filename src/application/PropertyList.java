package application;

import javafx.collections.ObservableList;

/**
     * Generic version of the MyList class.
     * @param <T> the type of the value being stored on the Linked List
     * Modify the Main class to call this implementation
     */
    public class PropertyList<T> implements GenericListInterface<T>
    {

       // public static Object Node;
        private Node<T> startOfList;
        private int length;

        /**
         *
         */
        public PropertyList() {
            startOfList = null;
            length = 0;
        }


        public boolean add(T newEntry) {
            Node temp = startOfList;
            startOfList = new Node((T)newEntry, temp);
            length++;
            return true;
        }

        public void add(int index, T newEntry) {
            Node previous = startOfList;
            Node current = startOfList;

            if(index < 0 || index > length){   //invalid index
                return;// false;
            }
            if(index == 0){
                add(newEntry);
                return;
            }
            int positionInList = 1; //second node in list
            current=current.next; //current moved on to next item

            while (current != null && positionInList < index) {
                positionInList++;
                current = current.next;
                previous = previous.next;
            }

            if (positionInList == index) {
                Node temp = new Node((T) newEntry, current);
                previous.next = temp;
                length++;
                return;
            }
            return;
        }



        public T remove(int index) {
            Node previous = startOfList;
            Node current = startOfList;

            if(index < 0 || index >= length){   //invalid index
                return null;
            }
            if(index == 0){   //remove startOfList item
                Node temp = startOfList;
                startOfList = startOfList.next;
                length--;
                return (T)(temp.myData);  //Note: need to cast
            }
            int positionInList = 1; //second node in list
            current=current.next; //current moved on to next item

            while (previous != null && positionInList < index) {
                positionInList++;
                current = current.next;
                previous = previous.next;
            }

            if (positionInList == index) {
                T temp =  (T)(current.myData);  //Note: need to cast
                previous.next = current.next;
                length--;
                return temp;
            }
            return null;
        }
        /**

         */

        public void clear() {
            startOfList = null;
            length = 0;
        }

        public T set(int index, Object anEntry) { // like replace
            return null;
        }

        public T get(int index) // like getEntry
        {
            if (index < 0 || index >= length) //invalid index
                return null;
            int positionInList = 0;
            Node temp = startOfList;
            while (positionInList < index) {
                temp = temp.next;
                positionInList++;
            }
            return (T) temp.myData;
        }

        public boolean contains(T anEntry) {
            int positionInList = 0;
            Node temp = startOfList;
            while (positionInList < length) {
                if (temp.myData.equals(anEntry)) {
                    return true;
                }
            }
            return false;
        }

        public int size() // like getLength
        {
            return length;
        }

        public boolean isEmpty() {
            if (length == 0)
                return true;
            return false;
        }

        public String toString() {
            int positionInList = 0;
            Node temp = startOfList;
            String out = "";
            while (positionInList < length) {
                out += ""+positionInList+": "+ temp.myData+"\n";
                positionInList++;
                temp=temp.next;
            }
            return out;
        }




        /*******************************************************************************/
        class Node<T1> {  //inner class
            private T1 myData; // this is a piece of primitive data as a textbook type example
            private Node next;

            private Node(T1 newData, Node newNext) {
                myData = newData;
                next = newNext;
            }
        }
        /*******************************************************************************/

    }