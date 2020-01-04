package filesprocessing.filters;

import java.io.File;

/**
 * the filter class that filters using the hidden filter.
 */
class FilterHidden extends FilterSubsection {

    /**
     * filters using the hidden filter, if not is given then does the opposite (if "YES" than check "NO"
     * and vise versa).
     * @param files     -   the files to filter.
     * @param hidden    -   "YES" or "NO" if we filter files that *are* hidden or not.
     * @param not       -   true means do opposite, false means do regular.
     * @return  the files after they were filtered.
     */
    public static File[] filter(File[] files, boolean hidden, boolean not) {
        File[] filteredFiles = new File[files.length];
        int i = 0;
        for (File f : files) {
            if (not) {
                if (f.isHidden() != hidden) {
                    filteredFiles[i] = f;
                    i++;
                }
            } else {
                if (f.isHidden() == hidden) {
                    filteredFiles[i] = f;
                    i++;
                }
            }
        }
        return reduceFiles(filteredFiles);
    }
}
