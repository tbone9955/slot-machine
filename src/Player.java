public class Player {
    private int balance;

    public Player(int balance) {
        this.balance = balance;
    }
    // you spun slot and lost so we STEAL YOUR MONEY
    public void takeLoss(int loss) {
        balance -= loss;
    }
    // you spun the slots and won, add to your funds
    public void takeWinnings(int winnings) {
        balance += winnings;
    }
    // check if player broke
    public boolean hasMoney() {
        return balance > 0;
    }
    // check if player has money
    public int  getBalance() {
        return balance;
    }

}
