package filesprocessing.filters;

import java.io.File;

/**
 * the filter class that filters using the suffix filter.
 */
class FilterSuffix extends FilterSubsection {

    /**
     * filters using the suffix filter, if not is given then does the opposite (does not end with).
     * @param files         -   the files to filter.
     * @param suffString    -   the string to check if the file name ends with.
     * @param not           -   true means do opposite, false means do regular.
     * @return  the files after they were filtered.
     */
    public static File[] filter(File[] files, String suffString, boolean not) {
        File[] filteredFiles = new File[files.length];
        int i = 0;
        for (File f : files) {
            boolean equals = f.getName().substring(f.getName().indexOf(suffString)).equals(suffString);
            if (not){
                if ((!f.getName().contains(suffString)) ||
                        (!equals)) {
                    filteredFiles[i] = f;
                    i++;
                }
            }
            else {
                if ((f.getName().contains(suffString)) &&
                        (equals)) {
                    filteredFiles[i] = f;
                    i++;
                }
            }
        }
        return reduceFiles(filteredFiles);
    }
}