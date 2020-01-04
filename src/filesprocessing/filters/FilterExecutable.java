package filesprocessing.filters;

import java.io.File;

/**
 * the filter class that filters using the executable filter.
 */
class FilterExecutable extends FilterSubsection {

    /**
     * filters using the executable filter, if not is given then does the opposite (if "YES" than check "NO"
     * and vise versa).
     * @param files         -   the files to filter.
     * @param executable    -   "YES" or "NO" if we filter files that *are* executable or not.
     * @param not           -   true means do opposite, false means do regular.
     * @return  the files after they were filtered.
     */
    public static File[] filter(File[] files, boolean executable, boolean not) {
        File[] filteredFiles = new File[files.length];
        int i = 0;
        for (File f : files) {
            if (not) {
                if (f.canExecute() != executable) {
                    filteredFiles[i] = f;
                    i++;
                }
            }
            else {
                if (f.canExecute() == executable) {
                    filteredFiles[i] = f;
                    i++;
                }
            }
        }
        return reduceFiles(filteredFiles);
    }
}