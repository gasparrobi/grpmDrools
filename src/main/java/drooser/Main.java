package drooser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String args[]) {

//        User user = new User("groupama");
//        user.setTestResult(81);
//        user.setLicenseAge(5);
//
//        System.out.println("User's discount before rules: " + user.getDiscount());
//        System.out.println("User's approval status before rules: " + user.isApproved());
//
//        Rule.executeRule(user);
//
//        System.out.println("User's discount after rules: " + user.getDiscount());
//        System.out.println("User's approval status after rules: " + user.isApproved());

        Node node1 = new Node();
        Node node2 = new Node();
        node1.setValue(2);
        node1.setCount(11);
        node2.setValue(1);
        node2.setCount(11);

        List<Node> nodeList = new ArrayList<>(Arrays.asList(node1,node2));

        System.out.println(nodeList.get(0).getValue());
        System.out.println(nodeList.get(0).getCount());
        Collections.sort(nodeList, new RearestComparator());
        System.out.println(nodeList.get(0).getValue());
        System.out.println(nodeList.get(0).getCount());

//        int[][] asd = new int[2][90];
//
//        for (int i = 0; i < asd[0].length; i++) {
//            asd[1][i] = i + 1;
//        }
//
//        for (int i = 0; i < asd[1].length; i++) {
//            System.out.print(asd[1][i]);
//        }
    }

}
