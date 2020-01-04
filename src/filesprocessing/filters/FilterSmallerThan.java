package filesprocessing.filters;

import java.io.File;

/**
 * the filter class that filters using the smaller_than filter.
 */
class FilterSmallerThan extends FilterSubsection {

    /**
     * filters using the smaller_than filter, if not is given then does the opposite (greater or equal to).
     * @param files -   the files to filter.
     * @param stNum -   the size we check the files are smaller than.
     * @param not   -   true means do opposite, false means do regular.
     * @return  the files after they were filtered.
     */
    public static File[] filter(File[] files, double stNum, boolean not) {
        File[] filteredFiles = new File[files.length];
        int i = 0;
        for (File f : files) {
            if (not) {
                if ((double) f.length() / 1024 >= stNum) {
                    filteredFiles[i] = f;
                    i++;
                }
            }
            else {
                if ((double) f.length() / 1024 < stNum) {
                    filteredFiles[i] = f;
                    i++;
                }
            }
        }
        return reduceFiles(filteredFiles);
    }
}