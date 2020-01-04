package filesprocessing;
import filesprocessing.filters.FilterSubsection.*;
import filesprocessing.orders.OrderSubsection.*;

import java.io.File;

import static filesprocessing.filters.FilterSubsection.*;
import static filesprocessing.filters.FilterSubsection.filters.*;
import static filesprocessing.orders.OrderSubsection.order;

/**
 * a class that represents a SECTION as it was defined in the ex description (a collection of lines each
 * helping the ordering and filtering of the required files) and a method that returns the TypeIExceptions
 * that occurred followed by the files requested for each Section.
 */
public class Section {

    private String errorMsg = "Warning in line ";

    private File[] files;

    private String[] filterLine;

    private String[] orderLine;

    private String problems = "";

    private int firstSectionLine;

    Section(File[] files, String[] filterLine, String[] orderLine, int firstSectionLine){
        this.files = files;
        this.filterLine = filterLine;
        this.orderLine = orderLine;
        this.firstSectionLine = firstSectionLine;
        this.filterAndSort();
    }

    /**
     * the main function that filters and orders the files as it should.
     */
    private void filterAndSort() {
        filterFiles();
        sortFiles();
    }

    /**
     * a method that adds warnings to the member that houses the warnings (its easier on the eyes making it
     * into a method in my opinion).
     * @param warning   -   the String with the warning we add to the warnings.
     */
    private void addWarning(String warning){
        if (this.problems.equals(""))
            this.problems += warning;
        else
            this.problems += "\n" + warning;
    }

    /**
     * the method that filters the files according to the filter commands it got from the command file whose
     * parsing is done outside this class.
     * each possible case for filtering is mentioned here so there is no mistake in what the method will do.
     * catches IllegalArgumentException in case it didn't get a filter enum and uses that to filter with all.
     */
    private void filterFiles(){
        try {
            switch (filters.valueOf(this.filterLine[0])) {
                case all:
                    if (this.filterLine.length == 2 && this.filterLine[1].equals("NOT"))
                        this.files = filter(this.files, all, true);
                    else if (this.filterLine.length == 1)
                        this.files = filter(this.files, all, false);
                    else{
                        this.files = filter(this.files, all, false);
                        this.addWarning(this.errorMsg + (this.firstSectionLine + 2));
                    }
                    break;
                case greater_than:
                    if (this.filterLine.length == 3 && Double.valueOf(this.filterLine[1]) >= 0 &&
                            this.filterLine[2].equals("NOT"))
                        this.files = filter(this.files, greater_than, Double.valueOf(this.filterLine[1]),
                                true);
                    else if (this.filterLine.length == 2 && Double.valueOf(this.filterLine[1]) >= 0)
                        this.files = filter(this.files, greater_than, Double.valueOf(this.filterLine[1]),
                                false);
                    else {
                        this.files = filter(this.files, all, false);
                        this.addWarning(this.errorMsg + (this.firstSectionLine + 2));
                    }
                    break;
                case smaller_than:
                    if (this.filterLine.length == 3 && Double.valueOf(this.filterLine[1]) >= 0 &&
                            this.filterLine[2].equals("NOT"))
                        this.files = filter(this.files, smaller_than, Double.valueOf(this.filterLine[1]),
                                true);
                    else if (this.filterLine.length == 2 && Double.valueOf(this.filterLine[1]) >= 0)
                        this.files = filter(this.files, smaller_than, Double.valueOf(this.filterLine[1]),
                                false);
                    else {
                        this.files = filter(this.files, all, false);
                        this.addWarning(this.errorMsg + (this.firstSectionLine + 2));
                    }
                    break;
                case contains:
                    if (this.filterLine.length == 3 && this.filterLine[2].equals("NOT"))
                        this.files = filter(this.files, contains, this.filterLine[1], true);
                    else if (this.filterLine.length == 2)
                        this.files = filter(this.files, contains, this.filterLine[1], false);
                    else {
                        this.files = filter(this.files, all, false);
                        this.addWarning(this.errorMsg + (this.firstSectionLine + 2));
                    }
                    break;
                case between:
                    if (this.filterLine.length == 4 && (Double.valueOf(this.filterLine[1]) <=
                            Double.valueOf(this.filterLine[2])) && Double.valueOf(this.filterLine[1]) >= 0 &&
                            this.filterLine[3].equals("NOT"))
                        this.files = filter(this.files, between, Double.valueOf(this.filterLine[1]),
                                Double.valueOf(this.filterLine[2]), true);
                    else if (this.filterLine.length == 3 && (Double.valueOf(this.filterLine[1]) <=
                            Double.valueOf(this.filterLine[2])) && Double.valueOf(this.filterLine[1]) >= 0)
                        this.files = filter(this.files, between, Double.valueOf(this.filterLine[1]),
                                Double.valueOf(this.filterLine[2]), false);
                    else {
                        this.files = filter(this.files, all, false);
                        this.addWarning(this.errorMsg + (this.firstSectionLine + 2));
                    }
                    break;
                case executable:
                    if (this.filterLine.length == 3 && this.filterLine[1].equals("YES") &&
                            this.filterLine[2].equals("NOT"))
                        this.files = filter(this.files, executable, true, true);
                    else if (this.filterLine.length == 3 && this.filterLine[1].equals("NO") &&
                            this.filterLine[2].equals("NOT"))
                        this.files = filter(this.files, executable, false, true);
                    else if (this.filterLine.length == 2 && this.filterLine[1].equals("YES"))
                        this.files = filter(this.files, executable, true, false);
                    else if (this.filterLine.length == 2 && this.filterLine[1].equals("NO"))
                        this.files = filter(this.files, executable, false, false);
                    else {
                        this.files = filter(this.files, all, false);
                        this.addWarning(this.errorMsg + (this.firstSectionLine + 2));
                    }
                    break;
                case file:
                    if (this.filterLine.length == 3 && this.filterLine[2].equals("NOT"))
                        this.files = filter(this.files, file, this.filterLine[1], true);
                    else if (this.filterLine.length == 2)
                        this.files = filter(this.files, file, this.filterLine[1], false);
                    else {
                        this.files = filter(this.files, all, false);
                        this.addWarning(this.errorMsg + (this.firstSectionLine + 2));
                    }
                    break;
                case hidden:
                    if (this.filterLine.length == 3 && this.filterLine[1].equals("YES") &&
                            this.filterLine[2].equals("NOT"))
                        this.files = filter(this.files, hidden, true, true);
                    else if (this.filterLine.length == 3 && this.filterLine[1].equals("NO") &&
                            this.filterLine[2].equals("NOT"))
                        this.files = filter(this.files, hidden, false, true);
                    else if (this.filterLine.length == 2 && this.filterLine[1].equals("YES"))
                        this.files = filter(this.files, hidden, true, false);
                    else if (this.filterLine.length == 2 && this.filterLine[1].equals("NO"))
                        this.files = filter(this.files, hidden, false, false);
                    else {
                        this.files = filter(this.files, all, false);
                        this.addWarning(this.errorMsg + (this.firstSectionLine + 2));
                    }
                    break;
                case prefix:
                    if (this.filterLine.length == 3 && this.filterLine[2].equals("NOT"))
                        this.files = filter(this.files, prefix, this.filterLine[1], true);
                    else if (this.filterLine.length == 2)
                        this.files = filter(this.files, prefix, this.filterLine[1], false);
                    else {
                        this.files = filter(this.files, all, false);
                        this.addWarning(this.errorMsg + (this.firstSectionLine + 2));
                    }
                    break;
                case suffix:
                    if (this.filterLine.length == 3 && this.filterLine[2].equals("NOT"))
                        this.files = filter(this.files, suffix, this.filterLine[1], true);
                    else if (this.filterLine.length == 2)
                        this.files = filter(this.files, suffix, this.filterLine[1], false);
                    else {
                        this.files = filter(this.files, all, false);
                        this.addWarning(this.errorMsg + (this.firstSectionLine + 2));
                    }
                    break;
                case writable:
                    if (this.filterLine.length == 3 && this.filterLine[1].equals("YES") &&
                            this.filterLine[2].equals("NOT"))
                        this.files = filter(this.files, writable, true, true);
                    else if (this.filterLine.length == 3 && this.filterLine[1].equals("NO") &&
                            this.filterLine[2].equals("NOT"))
                        this.files = filter(this.files, writable, false, true);
                    else if (this.filterLine.length == 2 && this.filterLine[1].equals("YES"))
                        this.files = filter(this.files, writable, true, false);
                    else if (this.filterLine.length == 2 && this.filterLine[1].equals("NO"))
                        this.files = filter(this.files, writable, false, false);
                    else {
                        this.files = filter(this.files, all, false);
                        this.addWarning(this.errorMsg + (this.firstSectionLine + 2));
                    }
                    break;
            }
        }
        catch (IllegalArgumentException e){
            this.files = filter(this.files, all, false);
            this.addWarning(this.errorMsg + (this.firstSectionLine + 2));
        }
    }

    /**
     * the method that orders the files according to the order commands it got from the command file whose
     * parsing is done outside this class.
     * each possible case for ordering is mentioned here so there is no mistake in what the method will do.
     * catches IllegalArgumentException in case it didn't get an order enum and uses that to order with abs.
     */
    private void sortFiles(){
        try{
            switch (orders.valueOf(this.orderLine[0])){
                case abs:
                    if (this.orderLine.length == 2 && this.orderLine[1].equals("REVERSE"))
                        this.files = order(this.files, orders.abs, true);
                    else if (this.orderLine.length == 1)
                        this.files = order(this.files, orders.abs, false);
                    else{
                        this.files = order(this.files, orders.abs, false);
                        this.addWarning(this.errorMsg + (this.firstSectionLine + 4));
                    }
                    break;
                case type:
                    if (this.orderLine.length == 2 && this.orderLine[1].equals("REVERSE"))
                        this.files = order(this.files, orders.type, true);
                    else if (this.orderLine.length == 1)
                        this.files = order(this.files, orders.type, false);
                    else{
                        this.files = order(this.files, orders.abs, false);
                        this.addWarning(this.errorMsg + (this.firstSectionLine + 4));
                    }
                    break;
                case size:
                    if (this.orderLine.length == 2 && this.orderLine[1].equals("REVERSE"))
                        this.files = order(this.files, orders.size, true);
                    else if (this.orderLine.length == 1)
                        this.files = order(this.files, orders.size, false);
                    else{
                        this.files = order(this.files, orders.abs, false);
                        this.addWarning(this.errorMsg + (this.firstSectionLine + 4));
                    }
                    break;
            }
        }
        catch (IllegalArgumentException e){
            this.files = order(this.files, orders.abs, false);
            this.addWarning(this.errorMsg + (this.firstSectionLine + 4));
        }

    }

    private File[] getFiles(){
        return this.files;
    }

    /**
     * a method that prints the errors and files of this section, in this order as needed.
     */
    public void printSummary(){
        if (this.problems.equals("")){
            for (File f : this.getFiles())
                System.out.println(f.getName());
        }
        else {
            System.err.println(this.problems);
            for (File f : this.getFiles())
                System.out.println(f.getName());
        }
    }
}