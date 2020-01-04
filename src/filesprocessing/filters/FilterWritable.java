package filesprocessing.filters;

import java.io.File;

/**
 * the filter class that filters using the writable filter.
 */
class FilterWritable extends FilterSubsection {

    /**
     * filters using the writable filter, if not is given then does the opposite (if "YES" than check "NO"
     * and vise versa).
     * @param files     -   the files to filter.
     * @param writable  -   "YES" or "NO" if we filter files that *are* writable or not.
     * @param not       -   true means do opposite, false means do regular.
     * @return  the files after they were filtered.
     */
    public static File[] filter(File[] files, boolean writable, boolean not) {
        File[] filteredFiles = new File[files.length];
        int i = 0;
        for (File f : files) {
            if (not) {
                if (f.canWrite() != writable) {
                    filteredFiles[i] = f;
                    i++;
                }
            } else {
                if (f.canWrite() == writable) {
                    filteredFiles[i] = f;
                    i++;
                }
            }
        }
        return reduceFiles(filteredFiles);
    }
}
