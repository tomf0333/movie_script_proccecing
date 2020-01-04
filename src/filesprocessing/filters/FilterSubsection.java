package filesprocessing.filters;

import java.io.File;

/**
 * a class that resembles a factory for the different filters in the package,
 * only it doesn't create an object of the filter but only calls the correct filter that was requested.
 * it also houses a filters enum used to make sure only correct filters will be requested.
 */
public class FilterSubsection {
    /**
     * the enum listing the different filters as mentioned in the super class description.
     */
    public enum filters {
        all("all"), greater_than("greater_than"), smaller_than("smaller_than"),
        contains("contains"), between("between"), executable("executable"),
        file("file"), hidden("hidden"), prefix("prefix"), suffix("suffix"),
        writable("writable");

        private final String name;

        filters(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name();
        }
    }

    /**
     * erases null pointers in a File array by making a new smaller one (after files were filtered away).
     * @param files -   the File array with nulls.
     * @return  the File array without nulls (smaller array).
     */
    public static File[] reduceFiles(File[] files) {
        int size = 0;
        for (File f : files) {
            if (f != null)
                size++;
        }
        File[] reducedFiles = new File[size];
        for (int i = 0; i < size; i++) {
            reducedFiles[i] = files[i];
        }
        return reducedFiles;
    }

    // all the different filter methods, each one receives an enum that makes it know which filter to use
    // (to prevent user mistakes) and the right type parameters for given filter.
    public static File[] filter(File[] files, filters filter, boolean not) {
        if (filter == filters.all)
            return FilterAll.filter(files, not);
        return files;
    }

    public static File[] filter(File[] files, filters filter, double comparator, boolean not) {
        switch (filter) {
            case greater_than:
                return FilterGreaterThan.filter(files, comparator, not);
            case smaller_than:
                return FilterSmallerThan.filter(files, comparator, not);
            default:
                return files;
        }
    }

    public static File[] filter(File[] files, filters filter, String comparator, boolean not) {
        switch (filter) {
            case contains:
                return FilterContains.filter(files, comparator, not);
            case prefix:
                return FilterPrefix.filter(files, comparator, not);
            case suffix:
                return FilterSuffix.filter(files, comparator, not);
            case file:
                return FilterFile.filter(files, comparator, not);
            default:
                return files;
        }
    }

    public static File[] filter(File[] files, filters filter, boolean comparator, boolean not) {
        switch (filter) {
            case executable:
                return FilterExecutable.filter(files, comparator, not);
            case hidden:
                return FilterHidden.filter(files, comparator, not);
            case writable:
                return FilterWritable.filter(files, comparator, not);
            default:
                return files;
        }
    }

    public static File[] filter(File[] files, filters filter, double comparator1, double comparator2,
                                boolean not) {
        if (filter == filters.between) {
            return FilterBetween.filter(files, comparator1, comparator2, not);
        }
        return files;
    }
}
