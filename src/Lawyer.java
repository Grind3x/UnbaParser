import java.util.Objects;

public class Lawyer {
    private String fullName;
    private String region;
    private String certificateNumber;
    private String certificateDate;
    private String lawyerForm;
    private String address;
    private String phoneNuberOne;
    private String phoneNuberTwo;
    private String email;

    public Lawyer() {
    }

    public Lawyer(String fullName, String region, String certificateNumber, String certificateDate, String lawyerForm, String address, String phoneNuberOne, String phoneNuberTwo, String email) {
        this.fullName = fullName;
        this.region = region;
        this.certificateNumber = certificateNumber;
        this.certificateDate = certificateDate;
        this.lawyerForm = lawyerForm;
        this.address = address;
        this.phoneNuberOne = phoneNuberOne;
        this.phoneNuberTwo = phoneNuberTwo;
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCertificateNumber() {
        return certificateNumber;
    }

    public void setCertificateNumber(String certificateNumber) {
        this.certificateNumber = certificateNumber;
    }

    public String getCertificateDate() {
        return certificateDate;
    }

    public void setCertificateDate(String certificateDate) {
        this.certificateDate = certificateDate;
    }

    public String getLawyerForm() {
        return lawyerForm;
    }

    public void setLawyerForm(String lawyerForm) {
        this.lawyerForm = lawyerForm;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNuberOne() {
        return phoneNuberOne;
    }

    public void setPhoneNuberOne(String phoneNuberOne) {
        this.phoneNuberOne = phoneNuberOne;
    }

    public String getPhoneNuberTwo() {
        return phoneNuberTwo;
    }

    public void setPhoneNuberTwo(String phoneNuberTwo) {
        this.phoneNuberTwo = phoneNuberTwo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lawyer lawyer = (Lawyer) o;
        return Objects.equals(fullName, lawyer.fullName) &&
                Objects.equals(region, lawyer.region) &&
                Objects.equals(certificateNumber, lawyer.certificateNumber) &&
                Objects.equals(certificateDate, lawyer.certificateDate) &&
                Objects.equals(lawyerForm, lawyer.lawyerForm) &&
                Objects.equals(address, lawyer.address) &&
                Objects.equals(phoneNuberOne, lawyer.phoneNuberOne) &&
                Objects.equals(phoneNuberTwo, lawyer.phoneNuberTwo) &&
                Objects.equals(email, lawyer.email);
    }

    @Override
    public int hashCode() {

        return Objects.hash(fullName, region, certificateNumber, certificateDate, lawyerForm, address, phoneNuberOne, phoneNuberTwo, email);
    }

    @Override
    public String toString() {
        return "Lawyer{" +
                "fullName='" + fullName + '\'' +
                ", region='" + region + '\'' +
                ", certificateNumber='" + certificateNumber + '\'' +
                ", certificateDate='" + certificateDate + '\'' +
                ", lawyerForm='" + lawyerForm + '\'' +
                ", address='" + address + '\'' +
                ", phoneNuberOne='" + phoneNuberOne + '\'' +
                ", phoneNuberTwo='" + phoneNuberTwo + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
