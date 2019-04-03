import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Deposit extends Account {

    private double balance;
    private double initialAmount;
    private final double percent;
    private LocalDate dateOfOpening;
    private LocalDate dateOfClosing;

    @Override
    protected void changeBalance(double amount) {
        if (dateOfClosing.isBefore(LocalDate.now()) || amount > 0)
            balance += amount;
        else if (amount < 0) {
            System.out.println("Нельзя снимать с депозита до окончания срока");
        }
    }

    @Override
    public double getAmountOfMoney() {
        LocalDate currentDate = LocalDate.now();
        long months = ChronoUnit.MONTHS.between(this.dateOfOpening, currentDate);
        return initialAmount * (1 + months * percent / (12 * 100)) + balance;
    }

    public Deposit(double initialAmount, Client client, LocalDate dateOfOpening, double percent) {
        this.initialAmount = initialAmount;
        this.client = client;
        this.dateOfOpening = dateOfOpening;
        this.percent = percent;
    }

    public double getPercent() {
        return percent;
    }

    public LocalDate getDateOfOpening() {
        return dateOfOpening;
    }

    public double getCommissionProcent() {
        return commissionProcent;
    }
}
