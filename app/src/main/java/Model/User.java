package Model;

public class User extends Bank{
    private int ID ;
    private String NAME;
    private String SURNAME;
    private String EMAIL;
    private String PASSWORD;
    private String MOBILE;
    private String GENDER;
//
public User() {
    this(-1,"","","","","","");
}
    public User(String NAME, String SURNAME,String EMAIL, String PASSWORD) {
        this(-1,NAME,SURNAME,EMAIL,PASSWORD,"","");
    }

    public User(String NAME, String SURNAME, String EMAIL, String PASSWORD, String MOBILE, String GENDER) {
        this(-1,NAME,SURNAME,EMAIL,PASSWORD,MOBILE,GENDER);
    }

    public User(int ID, String NAME, String SURNAME, String EMAIL, String PASSWORD, String MOBILE, String GENDER) {
        super();
        this.ID = ID;
        this.NAME = NAME;
        this.SURNAME = SURNAME;
        this.EMAIL = EMAIL;
        this.PASSWORD = PASSWORD;
        this.MOBILE = MOBILE;
        this.GENDER = GENDER;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }
    public String getSURNAME() {
        return SURNAME;
    }

    public void setSURNAME(String SURNAME) {
        this.SURNAME = SURNAME;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }

    public String getMOBILE() {
        return MOBILE;
    }

    public void setMOBILE(String MOBILE) {
        this.MOBILE = MOBILE;
    }

    public String getGENDER() {
        return GENDER;
    }

    public void setGENDER(String GENDER) {
        this.GENDER = GENDER;
    }

    public void setCURRENT_BALANCE(double CURRENT_BALANCE) {
        super.setCURRENT_BALANCE(CURRENT_BALANCE);
    }

    public double getCURRENT_BALANCE() {
        return super.getCURRENT_BALANCE();
    }

    public double getSAVINGS_BALANCE() {
        return super.getSAVINGS_BALANCE();
    }

    public void setSAVINGS_BALANCE(double SAVINGS_BALANCE) {
        super.setSAVINGS_BALANCE(SAVINGS_BALANCE);
    }
}
