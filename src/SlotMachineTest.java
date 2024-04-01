import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SlotMachineTest {

    @Test
    public void testRandomElementGeneration() {
        SlotMachine slotMachine = new SlotMachine();
        for (int i = 0; i < 20; i++) {
            String symbol = slotMachine.getRandomElement(slotMachine.getFruits());
            assertTrue(slotMachine.getFruits().contains(symbol), "Generated symbol is not in the FRUITS list");
        }
    }
    @Test
    // Test the payouts for the slot machine

    public void testPayouts() {
        SlotMachine slotMachine = new SlotMachine();
        // Test for 3 of the same fruit
        assertTrue(slotMachine.checkWinner(Arrays.asList("🍎", "🍎", "🍎")) == 50, "Payout for 3 🍎 should be 50");
        assertTrue(slotMachine.checkWinner(Arrays.asList("🍊", "🍊", "🍊")) == 10, "Payout for 3 🍊 should be 10");
        assertTrue(slotMachine.checkWinner(Arrays.asList("🍋", "🍋", "🍋")) == 25, "Payout for 3 🍋 should be 25");
        assertTrue(slotMachine.checkWinner(Arrays.asList("🍉", "🍉", "🍉")) == 10, "Payout for 3 🍉 should be 10");
        assertTrue(slotMachine.checkWinner(Arrays.asList("🍇", "🍇", "🍇")) == 10, "Payout for 3 🍇 should be 10");
        assertTrue(slotMachine.checkWinner(Arrays.asList("💰", "💰", "💰")) == 500, "Payout for 3 💰 should be 500");
        // Test for 2 of the same fruit and 1 different
        assertTrue(slotMachine.checkWinner(Arrays.asList("🍎", "🍎", "🍊")) == 0, "Payout for 2 🍎 should be 0");
        assertTrue(slotMachine.checkWinner(Arrays.asList("🍊", "🍊", "🍋")) == 0, "Payout for 2 🍊 should be 0");
        assertTrue(slotMachine.checkWinner(Arrays.asList("🍋", "🍋", "🍉")) == 0, "Payout for 2 🍋 should be 0");
        assertTrue(slotMachine.checkWinner(Arrays.asList("🍉", "🍉", "🍇")) == 0, "Payout for 2 🍉 should be 0");
        assertTrue(slotMachine.checkWinner(Arrays.asList("🍇", "🍇", "💰")) == 0, "Payout for 2 🍇 should be 0");
        assertTrue(slotMachine.checkWinner(Arrays.asList("💰", "💰", "🍎")) == 0, "Payout for 2 💰 should be 0");
    }
}
