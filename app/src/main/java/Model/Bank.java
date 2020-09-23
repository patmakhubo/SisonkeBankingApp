package Model;

abstract class Bank {

    private double CURRENT_BALANCE;
    private double SAVINGS_BALANCE;

    public Bank() {
        this(100, 100);
    }

    public Bank(double CURRENT_BALANCE, double SAVINGS_BALANCE) {
        this.CURRENT_BALANCE = CURRENT_BALANCE;
        this.SAVINGS_BALANCE = SAVINGS_BALANCE;
    }

    public double getCURRENT_BALANCE() {
        return CURRENT_BALANCE;
    }

    public void setCURRENT_BALANCE(double CURRENT_BALANCE) {
        this.CURRENT_BALANCE = CURRENT_BALANCE;
    }

    public double getSAVINGS_BALANCE() {
        return SAVINGS_BALANCE;
    }

    public void setSAVINGS_BALANCE(double SAVINGS_BALANCE) {
        this.SAVINGS_BALANCE = SAVINGS_BALANCE;
    }
}
