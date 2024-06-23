public class UserInterface extends Thread implements SubmissionInterface, ControlInterface {

    private LongTermScheduler longTermScheduler;
    private ShortTermScheduler shortTermScheduler;

    public void setLongTermScheduler(LongTermScheduler longTermScheduler) {
        this.longTermScheduler = longTermScheduler;
    }

    public void setShortTermScheduler(ShortTermScheduler shortTermScheduler) {
        this.shortTermScheduler = shortTermScheduler;
    }

    public void run() {
        // Interface gráfica com o usuário
    }

    @Override
    public boolean submitJob(String fileName) {
        longTermScheduler.submitJob(fileName);
        return true;
    }

    @Override
    public void displaySubmissionQueue() {
        longTermScheduler.displaySubmissionQueue();
    }

    @Override
    public void startSimulation() {
        shortTermScheduler.startSimulation();
    }

    @Override
    public void suspendSimulation() {
        shortTermScheduler.suspendSimulation();
    }

    @Override
    public void resumeSimulation() {
        shortTermScheduler.resumeSimulation();
    }

    @Override
    public void stopSimulation() {
        shortTermScheduler.stopSimulation();
    }

    @Override
    public void displayProcessQueues() {
        shortTermScheduler.displayProcessQueues();
    }

}
