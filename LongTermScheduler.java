import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class LongTermScheduler extends Thread implements SubmissionInterface, InterSchedulerInterface {

    private int maxLoad;

    // Lista de processos submetidos
    private final ArrayList<Process> submissionQueue = new ArrayList<>();

    private ShortTermScheduler shortTermScheduler;

    public void setShortTermScheduler(ShortTermScheduler shortTermScheduler) {
        this.shortTermScheduler = shortTermScheduler;
    }

    public void setMaxLoad(int maxLoad) {
        this.maxLoad = maxLoad;
    }

    @Override
    public void run() {

    }

    /*
     * Lê cada linha do programa simulado e armazena em um objeto Process, depois
     * adiciona o processo criado na lista de submissão e na ista de prontos
     */
    @Override
    public boolean submitJob(String fileName) {
        // Ler e interpretar o programa simulado
        try {
            String line;
            int blockCounter = 0;
            ArrayList<String> instructions = new ArrayList<>();
            File file = new File(fileName);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            Process process = new Process();

            while ((line = bufferedReader.readLine()) != null) {
                if (line.startsWith("program")) {
                    process.setName(line.substring(8).trim());
                } else if (line.equals("begin")) {
                    continue;
                } else if (line.startsWith("block")) {
                    blockCounter++;
                    instructions.add(line);
                } else if (line.equals("execute")) {
                    instructions.add(line);
                } else {
                    break;
                }
            }
            process.setNumberBlocks(blockCounter);
            process.setInstructions(instructions);

            bufferedReader.close();

            // Checa se a lista de submissão já não está com a carga máxima permitida
            if (submissionQueue.size() < maxLoad) {
                // Adicona processo na lista de submissão
                submissionQueue.add(process);
                // Adiciona processo na lista de prontos do ShortTermScheduler
                shortTermScheduler.readyQueue.add(process);
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public void displaySubmissionQueue() {
        System.out.println("Lista de processos na lista de submissão: ");
        synchronized (submissionQueue) {
            for (Process process : submissionQueue) {
                System.out.println("Processo " + process.getName());
            }
        }
    }

    @Override
    public void addProcess(Process bcp) {
       
    }

    @Override
    public int getProcessLoad() {
        return shortTermScheduler.getProcessLoad();
    }
}
