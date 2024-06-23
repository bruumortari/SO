import java.util.ArrayList;
import java.util.List;

public class ShortTermScheduler extends Thread implements ControlInterface, InterSchedulerInterface {

    private LongTermScheduler longTermScheduler;

    public void setLongTermScheduler(LongTermScheduler lts) {
        longTermScheduler = lts;
    }

    int count = 0;
    String block;

    // Process currentProcess;

    // Lista de processos bloqueados
    List<Process> blockedQueue = new ArrayList<>();
    // Lista de processos prontos
    List<Process> readyQueue = new ArrayList<>();
    // Lista de processos finalizados
    List<Process> finishedQueue = new ArrayList<>();

    // int quantum = 200;
    boolean isRunning = false;

    // public void organizeReadyQueue(List<Process> readyQueue) {
    // for (int i = 0; i < readyQueue.size(); i++) {
    // for (int j = i + 1; j < readyQueue.size() - 1; j++) {
    // if (readyQueue.get(i).getNumberBlocks() >
    // readyQueue.get(j).getNumberBlocks()) {
    // Process aux = readyQueue.get(i);
    // readyQueue.set(i, readyQueue.get(j));
    // readyQueue.set(j, aux);
    // }
    // }

    // }
    // }

    public void run() {
        // organizeReadyQueue(readyQueue);
        startSimulation();
    }

    public void startSimulation() {
        System.out.println(readyQueue);
        isRunning = true;

        /*
         * Roda a simulação até que a lista de prontos esteja vazia ou que a variável
         * isRunning se torne falsa pela chamada de algum método para
         * suspender/finalizar a simulação
         */
        while (!readyQueue.isEmpty() && isRunning) {
            // Chamada do método para executar os programas simulados
            executeReadyProcesss(readyQueue);
            // Chamada do método para checar se há processos bloqueados
            checkBlockedProcesss();
        }

    }

    /* Executa os processos na lista de prontos */
    private void executeReadyProcesss(List<Process> queue) {
        int instructionCount = 0;
        for (Process process : readyQueue) {
            for (int i = process.getInstructionCount(); i < process.getInstructions().size(); i++) {
                if (process.getInstructions().get(i).equals("execute")) {
                    System.out.println("" + process.getName() + ": execute");
                    instructionCount++;
                    count++;
                } else {
                    System.out.println("" + process.getName() + ": block");
                    instructionCount++;
                    count++;
                    process.setInstructionCount(process.getInstructionCount() + instructionCount);
                    instructionCount = 0;
                    blockedQueue.add(process);
                    break;
                }
            }
        }
        readyQueue.clear();
    }

    /*
     * Coloca os processos bloqueados de volta na lista de prontos para voltarem a
     * ser executados após o block
     */
    private void checkBlockedProcesss() {
        if (!blockedQueue.isEmpty()) {
            for (Process process : blockedQueue) {
                readyQueue.add(process);
            }
        }
        blockedQueue.clear();
    }

    public void suspendSimulation() {
        isRunning = false;
        System.out.println("Simulação suspensa");
    }

    public void resumeSimulation() {
        System.out.println("Simulação retomada");
        startSimulation();
    }

    public void stopSimulation() {
        isRunning = false;
        readyQueue.clear();
        blockedQueue.clear();
        finishedQueue.clear();
        System.out.println("Simulacao finalizada");
    }

    public void displayProcessQueues() {
        System.out.println("\nFila de prontos");
        synchronized (readyQueue) {
            for (Process Process : readyQueue) {
                System.out.println("Process: " + Process);
            }
        }

        System.out.println("\nFila de bloqueados");
        synchronized (blockedQueue) {
            for (Process Process : blockedQueue) {
                System.out.println("Process: " + Process);
            }
        }

        System.out.println("\nFila de terminados");
        synchronized (finishedQueue) {
            for (Process Process : finishedQueue) {
                System.out.println("Process: " + Process);
            }
        }
    }

    public void addProcess(Process bcp) {
        
    }

    public int getProcessLoad() {
        return readyQueue.size() + blockedQueue.size();
    }

}