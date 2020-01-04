package filesprocessing.filters;

import java.io.File;

/**
 * the filter class that filters using the prefix filter.
 */
class FilterPrefix extends FilterSubsection {

    /**
     * filters using the prefix filter, if not is given then does the opposite (does not start with).
     * @param files     -   the files to filter.
     * @param preString -   the string to check if the file name starts with.
     * @param not       -   true means do opposite, false means do regular.
     * @return  the files after they were filtered.
     */
    public static File[] filter(File[] files, String preString, boolean not) {
        File[] filteredFiles = new File[files.length];
        int i = 0;
        for (File f : files) {
            if (not) {
                if (f.getName().indexOf(preString) != 0) {
                    filteredFiles[i] = f;
                    i++;
                }
            } else {
                if (f.getName().indexOf(preString) == 0) {
                    filteredFiles[i] = f;
                    i++;
                }
            }
        }
        return reduceFiles(filteredFiles);
    }
}