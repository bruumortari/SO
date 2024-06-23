import java.util.ArrayList;

public class Process {
    private int instructionCount = 0;
    private ArrayList<String> instructions;
    private int numberBlocks;
    private String name;

    public Process() {
    
    }

    public int getInstructionCount() {
        return instructionCount;
    }

    public void setInstructionCount(int instructionCount) {
        this.instructionCount = instructionCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setInstructions(ArrayList<String> instructions) {
        this.instructions = instructions;
    }

    public ArrayList<String> getInstructions() {
        return instructions;
    }

    public int calculateNumberOfBlocks() {
        int numberBlocks = 0;
        for(String string : instructions) {
            // Se não for uma instrução execute
            if(!string.equals("execute")) {
                numberBlocks++;
            }
        }
        return numberBlocks;
    }

    public int getNumberBlocks() {
        return numberBlocks;
    }

    public void setNumberBlocks(int numberBlocks) {
        this.numberBlocks = numberBlocks;
    }
}
