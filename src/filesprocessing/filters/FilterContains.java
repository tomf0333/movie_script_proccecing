package filesprocessing.filters;

import java.io.File;

/**
 * the filter class that filters using the contains filter.
 */
class FilterContains extends FilterSubsection {

    /**
     * filters using the contains filter, if not is given then does the opposite (does not contain).
     * @param files         -   the files to filter.
     * @param containString -   the string to check if the file name contains.
     * @param not           -   true means do opposite, false means do regular.
     * @return  the files after they were filtered.
     */
    public static File[] filter(File[] files, String containString, boolean not) {
        File[] filteredFiles = new File[files.length];
        int i = 0;
        for (File f : files) {
            if (not){
                if (!f.getName().contains(containString)){
                    filteredFiles[i] = f;
                    i++;
                }
            }
            else {
                if (f.getName().contains(containString)) {
                    filteredFiles[i] = f;
                    i++;
                }
            }
        }
        return reduceFiles(filteredFiles);
    }
}