public class SchedulerSimulator {
    public static void main(String[] args) {
        // Checar se o argumento foi passado
        if (args.length < 1) {
            System.out.println("Por favor, forneça a carga máxima como argumento.");
            return;
        }

        int maxLoad;
        try {
            maxLoad = Integer.parseInt(args[0]);
            if (maxLoad < 0) {
                System.out.println("A carga máxima não pode ser menor que zero!");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Por favor, forneça um número válido para a carga máxima.");
            return;
        }

        LongTermScheduler longTermScheduler = new LongTermScheduler();
        ShortTermScheduler shortTermScheduler = new ShortTermScheduler();
        // UserInterface userInterface = new UserInterface();

        longTermScheduler.setMaxLoad(maxLoad);
        longTermScheduler.setShortTermScheduler(shortTermScheduler);
        shortTermScheduler.setLongTermScheduler(longTermScheduler);
        // userInterface.setShortTermScheduler(shortTermScheduler);
        // userInterface.setLongTermScheduler(longTermScheduler);

        // Classe UserInterface envia programa para a classe LongTermScheduler
        longTermScheduler.submitJob("program1.txt");
        longTermScheduler.submitJob("program2.txt");
        longTermScheduler.submitJob("program3.txt");
        longTermScheduler.submitJob("program4.txt");

        // Classe UserInterface chama o método de mostrar a lista de submissão do
        // LongTermScheduler
        // userInterface.displaySubmissionQueue();

        // Criar as threads
        Thread threadShortTermScheduler = new Thread(shortTermScheduler);
        Thread threadLongTermScheduler = new Thread(longTermScheduler);
        // Thread threadUserInterface = new Thread(userInterface);

        // Iniciar as threads
        // threadUserInterface.start();
        threadLongTermScheduler.start();
        threadShortTermScheduler.start();

        // Join
        try {
            // threadUserInterface.join();
            threadLongTermScheduler.join();
            threadShortTermScheduler.join();
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }
}
