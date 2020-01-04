package filesprocessing;

import filesprocessing.TypeExceptions.TypeIIException;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * the class that actually has the main of the program, uses Section and the filters and orders in their own
 * packages to print to the user the files they wanted with the correct filters and orders they wanted
 * (and any errors along the way).
 */
public class DirectoryProcessor {

    private static File source;

    private static File command;

    private static File[] sourceFiles;

    private static String[] commandLines;

    DirectoryProcessor(String sourceDir, String commandFile) throws IOException {
        DirectoryProcessor.source = new File(sourceDir);
        DirectoryProcessor.command = new File(commandFile);
        this.readArgumentFiles();
    }

    /**
     * reads the arguments and saves the first as the source directory and the second as the command file
     * (as we were told to assume they are as such).
     * also store the command file as String lines in a different member.
     * @throws IOException in case there was some sort of IO Error.
     */
    private void readArgumentFiles() throws IOException {
        sourceFiles = source.listFiles();
        Scanner s = new Scanner(command);
        int size = 0;
        while (s.hasNextLine()){
            s.nextLine();
            size++;
        }
        s = new Scanner(command);
        commandLines = new String[size];
        for (int i = 0; i < size; i++)
            commandLines[i] = s.nextLine();
    }

    /**
     * this method makes all the sections possible from the command file, if there was a TypeIIException then
     * it stops everything, otherwise it will finish with all the sections after their files were filtered
     * and ordered and are ready to print.
     * @return  an Array of the sections created from the command file.
     * @throws TypeIIException  in case there was a FILTER or ORDER line missing or not enough lines.
     */
    public Section[] parseCommand() throws TypeIIException {
        int j = 0;
        int i = 0;
        Section[] sections = new Section[(commandLines.length / 3)];
        try {
            while (j < commandLines.length) {
                if (commandLines[j].equals("FILTER")) {
                    if (commandLines.length < j + 2){
                        throw new TypeIIException("bad format for command file");
                    }
                    else{
                        String[] splittedFilterLine = SplitString.split(commandLines[j + 1], '#');
                        if (commandLines.length >= j + 3 && commandLines[j + 2].equals("ORDER")) {
                            if (commandLines.length < j + 4 || commandLines[j + 3].equals("FILTER")){
                                sections[i] = new Section(sourceFiles, splittedFilterLine,
                                        new String[]{"abs"}, j);
                                i++;
                                j+=3;
                                continue;
                            }
                            else{
                                String[] splittedOrderLine = SplitString.split(commandLines[j + 3], '#');
                                sections[i] = new Section(sourceFiles, splittedFilterLine, splittedOrderLine,
                                        j);
                                i++;
                                j += 4;
                                continue;
                            }
                        }
                        else{
                            throw new TypeIIException("no ORDER sub section found");
                        }
                    }
                }
                else{
                    throw new TypeIIException("no FILTER sub section found");
                }
            }
        }
        catch (IndexOutOfBoundsException e){
            // i make the sections array at least 1 bigger so this is just in case
        }
        return sections;
    }

    /**
     * makes sure there are 2 arguments and only 2 otherwise it is considered a TypeIIException.
     * @param args  -   the arguments to the program.
     * @throws TypeIIException  in case there are more or less than 2.
     */
    public static void checkParams(String[] args) throws TypeIIException {
        if (args.length != 2)
            throw new TypeIIException("not the right amount of arguments");
    }

    public static void main(String[] args) {
        try {
            DirectoryProcessor.checkParams(args);
            DirectoryProcessor dp = new DirectoryProcessor(args[0], args[1]);
            Section[] secs = dp.parseCommand();
            for (Section s : secs){
                if (s != null)
                    s.printSummary();
            }
        }
        catch (IOException io){
            System.err.println("ERROR : an IOException has occurred");
        } catch (TypeIIException t2) {
            System.err.println(t2.getMsg());
        }
    }
}