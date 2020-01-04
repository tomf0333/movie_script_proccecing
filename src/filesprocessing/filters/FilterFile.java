package filesprocessing.filters;

import java.io.File;

/**
 * the filter class that filters using the file filter.
 */
class FilterFile extends FilterSubsection {

    /**
     * filters using the file filter, if not is given then does the opposite (name isn't the given name).
     * @param files     -   the files to filter.
     * @param fileName  -   the file name to filter by.
     * @param not       -   true means do opposite, false means do regular.
     * @return  the files after they were filtered.
     */
    public static File[] filter(File[] files, String fileName, boolean not) {
        File[] filteredFiles = new File[files.length];
        int i = 0;
        for (File f : files) {
            if (not) {
                if (!f.getName().equals(fileName)) {
                    filteredFiles[i] = f;
                    i++;
                }
            }
            else {
                if (f.getName().equals(fileName)) {
                    filteredFiles[i] = f;
                    i++;
                }
            }
        }
        return reduceFiles(filteredFiles);
    }
}