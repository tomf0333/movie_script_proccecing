package filesprocessing.orders;

import java.io.File;

/**
 * the order class that orders using the size order.
 */
class OrderSize extends OrderSubsection {

    /**
     * orders using the size order, if reverse is given then it orders and then reverses.
     * @param files     -   the files to order.
     * @param reverse   -   true means do reverse, false means do regular.
     * @return  the files after they were ordered.
     */
    static File[] order(File[] files, boolean reverse) {
        File[] sortedFiles;
        if (reverse) {
            sortedFiles = Sorter.mergeSortSize(files);
            sortedFiles = reverse(files);
        } else {
            sortedFiles = Sorter.mergeSortSize(files);
        }
        return sortedFiles;
    }
}
