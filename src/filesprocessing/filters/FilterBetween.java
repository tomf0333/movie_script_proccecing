package filesprocessing.filters;

import java.io.File;

/**
 * the filter class that filters using the between filter.
 */
class FilterBetween extends FilterSubsection {

    /**
     * filters using the between filter, if not is given then does the opposite (greater than the bigger one
     * or smaller than the smaller one).
     * @param files -   the files to filter.
     * @param gtNum -   the smaller number for the between.
     * @param stNum -   the bigger number for the between.
     * @param not   -   true means do opposite, false means do regular.
     * @return  the files after they were filtered.
     */
    public static File[] filter(File[] files, double gtNum, double stNum, boolean not){
        File[] filteredFiles = new File[files.length];
        int i = 0;
        for (File f : files) {
            if (not) {
                if (((double) f.length() / 1024 >= stNum) || ((double) f.length() / 1024 <= gtNum)) {
                    filteredFiles[i] = f;
                    i++;
                }
            }
            else {
                if (((double) f.length() / 1024 < stNum) && ((double) f.length() / 1024 > gtNum)) {
                    filteredFiles[i] = f;
                    i++;
                }
            }
        }
        return reduceFiles(filteredFiles);
    }
}