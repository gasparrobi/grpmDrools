package drooser;

public class User {

    private String type;
    private int discount;
    private int testResult;
    private boolean approved;
    private int licenseAge;

    public User(String type) {
        this.type = type;
        this.discount = 0;
        this.approved = false;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public Integer getTestResult() {
        return testResult;
    }

    public void setTestResult(Integer testResult) {
        this.testResult = testResult;
    }

    public Boolean isApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    public Integer getLicenseAge() {
        return licenseAge;
    }

    public void setLicenseAge(Integer licenseAge) {
        this.licenseAge = licenseAge;
    }

    public Boolean getApproved() {
        return approved;
    }
}
