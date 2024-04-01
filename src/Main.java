import java.util.*;

class SlotMachine {
    //fruit symbols
    private List<String> FRUITS  = Arrays.asList("🍎", "🍊", "🍋", "🍉", "🍇", "💰");
    public List<String> getFruits() {
        return FRUITS;
    }
    private ArrayList<List> reels;
    //private int starting player balance = 10
    private final int  COST_PER_SPIN = 1;  // $1  each spin
    private final int NUM_REELS = 3;

    private Random random;

    private Player player;
    //private int currentPlayerBalance = STARTING_PLAYER_BALANCE;

    SlotMachine() {
        random = new Random();
        reels =  new ArrayList<List>();

        // Create a Player instance with a starting balance of $10
        player = new Player(10);
        initializeReels();
    }

    public static void main(String[] args) {

        //slot machine instance
        SlotMachine slotMachine = new SlotMachine();

        Scanner scanner = new Scanner(System.in);

        while (slotMachine.player.hasMoney()) {
            // Spin the slot machine

            System.out.print("\nDo you want to spin for $1?: (Y or N) ");
            char decision = scanner.next().charAt(0);   //either 'Y' or 'N'
            if (decision == 'Y' || decision == 'y')
                slotMachine.spin();
            else {
                System.out.println("booo! Dont be a wimp");
                break; //exit the loop
            }
        }
    }

    // Initialize the symbols displayed on each reel
    private void initializeReels() {
        for (int i = 0; i < NUM_REELS; i++) {
            reels.add(FRUITS);
        }

    }


    public String getRandomElement(List<String> list)
    {
        Random rand = new Random();
        return list.get(rand.nextInt(list.size()));
    }


    // for a more realistic simulation where the user sees the changes
    public void spin() {

        player.takeLoss(COST_PER_SPIN);

        long updateInterval = 100; // Update every 100 milliseconds
        int numIterations = 3;


        // Spinning animation
        for (int i = 0; i < numIterations; i++) {
            updateDisplay();
        }

        // Display the final result after spinning
        displayFinalResult();
    }

    // Method to update the display during spinning animation
    private void updateDisplay() {

        System.out.println();
        System.out.println("-----Reel is Spinning-----");

        // Loop through each reel and randomly select a fruit for each
        for (int i = 0; i < NUM_REELS; i++)  {
            System.out.print(" [ ");
            System.out.print (getRandomElement(reels.get(i)));  //print a random fruit from each wheel
            System.out.print(" ] ");
        }

    }

    // Method to display the result after spinning
    public void displayFinalResult() {
        ArrayList<String> results = new ArrayList<String>();;  // store the final 3 fruits
        String fruit = "";

        System.out.print("\n\n");
        System.out.println(" ------ RESULTS ------");
        System.out.print("\n");
        for (int i = 0; i < NUM_REELS; i++) {
            System.out.print("  [ ");
            fruit = getRandomElement(reels.get(i));
            results.add(fruit);
            System.out.print(fruit);
            System.out.print(" ]  ");
        }

        int winnings = checkWinner(results);
        if(winnings>0) {
            player.takeWinnings(winnings);
            System.out.print("\n");
            System.out.println("\n        Won! $" + winnings + "!!!");
        }
        else {
            System.out.print("\n");
            System.out.println(" -- Sorry, no winner. --");

        }

        System.out.print("\n\n\n");
        System.out.println("=========================");
        System.out.println("    Your Current Balance:  $" + player.getBalance());
        System.out.println("🟩🟩🟩🟩🟩🟩🟩🟩🟩🟩🟩");
        System.out.println("      SLOTS    $1 per Spin            ");
        System.out.println("🟩🟩🟩🟩🟩🟩🟩🟩🟩🟩🟩");
        System.out.println();

        System.out.print("\n");
            if (player.getBalance() == 0) {
                System.out.println("Game Over! You are out of money. Better luck next time ya bum!");
            }
    }


    // Check winner method
    public int checkWinner(List results) {
        int JACKPOT_PAYOUT = 500;
        int NORMAL_PAYOUT = 10;
        int LEMON_PAYOUT = 25;
        int APPLE_PAYOUT = 50;

        String JACKPOT = "💰";
        String APPLE = "🍎";
        String LEMON = "🍋";

        String reel1 = results.get(0).toString();
        String reel2 = results.get(1).toString();
        String reel3 = results.get(2).toString();

        // Check if all reels match
        if (reel1.equals(reel2) && reel2.equals(reel3)) {
            if (reel1.equals(JACKPOT)) { // Check if all reels have "MONEY BAGS" for jackpot
                return JACKPOT_PAYOUT;
            } else if (reel1.equals(LEMON)) {
                return LEMON_PAYOUT;
            } else if (reel1.equals(APPLE)) {
                return APPLE_PAYOUT;
            } else {

                return NORMAL_PAYOUT;
            }
        }
        // No winner
        return 0;
    }
}