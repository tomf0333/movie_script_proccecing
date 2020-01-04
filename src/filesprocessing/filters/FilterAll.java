package filesprocessing.filters;

import java.io.File;

/**
 * the filter class that filters using the all filter.
 */
class FilterAll {

    /**
     * filters using the all filter, if not is given then does the opposite (none).
     *
     * @param files -   the files to filter.
     * @param not   -   true means do opposite, false means do regular.
     * @return  the files after they were filtered.
     */
    public static File[] filter(File[] files, boolean not)
    {
        if (not)
            return new File[0];
        return files;
    }
}
