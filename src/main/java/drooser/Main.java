package drooser;

public class Main {

    public static void main(String args[]) {

        User user = new User("groupama");
        user.setTestResult(81);
        user.setLicenseAge(5);

        System.out.println("User's discount before rules: " + user.getDiscount());
        System.out.println("User's approval status before rules: " + user.isApproved());

        Rule.executeRule(user);

        System.out.println("User's discount after rules: " + user.getDiscount());
        System.out.println("User's approval status after rules: " + user.isApproved());

    }

}
