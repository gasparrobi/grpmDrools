package drooser;

public class Node implements Comparable<Node>{

    private int value;
    private int count;

    public Node() {};

//    public int compareTo(Node node) {
//        return Integer.compare(this.count, node.count);
//    }

    public int compareTo(Node node) {
       if (this.count < node.getCount()) {
           return -1;
       }
       else if (this.count > node.getCount()) {
           return 1;
       }
       else {
           if (this.value < node.getValue()) {
               return -1;
           }
           else {
               return 1;
           }
       }
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }


}
