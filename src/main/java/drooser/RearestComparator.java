package drooser;

import java.util.Comparator;

public class RearestComparator implements Comparator<Node>{

    public int compare(Node n1, Node n2)
    {
        if (n1.getCount() < n2.getCount()) {
            return -1;
        }
        else if (n1.getCount() > n2.getCount()) {
            return 1;
        }
        else {
            if (n1.getValue() < n2.getValue()) {
                return -1;
            }
            else {
                return 1;
            }
        }
    }

}
