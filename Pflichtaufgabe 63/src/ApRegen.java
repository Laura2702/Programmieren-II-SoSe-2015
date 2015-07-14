public class ApRegen extends Thread {
    public void run(Player player) {
        try {
            this.sleep(5000);
        } catch (InterruptedException e) {
        }
        player.regenerateAp();
    }

}
