package filesprocessing.orders;

import java.io.File;

/**
 * a class that has the sorting algorithm used by the ordering classes (i chose MergeSort).
 */
public class Sorter {

    // the Sort for abs
    public static File[] mergeSortAbs(File[] inputList) {
        if (inputList.length <= 1) {
            return inputList;
        }
        File[] list1 = new File[inputList.length / 2];
        File[] list2 = new File[inputList.length - list1.length];
        System.arraycopy(inputList, 0, list1, 0, list1.length);
        System.arraycopy(inputList, list1.length, list2, 0, list2.length);
        mergeSortAbs(list1);
        mergeSortAbs(list2);
        mergeAbs(list1, list2, inputList);
        return inputList;
    }

    public static void mergeAbs(File[] list1, File[] list2, File[] resultList) {
        int indexOfList1 = 0;
        int indexOfList2 = 0;
        int indexOfMergedList = 0;
        while (indexOfList1 < list1.length && indexOfList2 < list2.length) {
            if (list1[indexOfList1].getAbsolutePath().compareTo(list2[indexOfList2].getAbsolutePath()) < 0) {
                resultList[indexOfMergedList] = list1[indexOfList1];
                indexOfList1++;
            } else {
                resultList[indexOfMergedList] = list2[indexOfList2];
                indexOfList2++;
            }
            indexOfMergedList++;
        }
        System.arraycopy(list1, indexOfList1, resultList, indexOfMergedList, list1.length -
                indexOfList1);
        System.arraycopy(list2, indexOfList2, resultList, indexOfMergedList, list2.length -
                indexOfList2);
    }

    /**
     * a method to easily get a files type (without its name).
     * @param f -   the file.
     * @return  the files type (empty if hidden).
     */
    public static String getFileType(File f) {
        int i = f.getName().lastIndexOf('.');
        if (i == 0 || i == -1)
            return "";
        else {
            return f.getName().substring(i);
        }
    }

    // the Sort for type
    public static File[] mergeSortType(File[] inputList) {
        if (inputList.length <= 1) {
            return inputList;
        }
        File[] list1 = new File[inputList.length / 2];
        File[] list2 = new File[inputList.length - list1.length];
        System.arraycopy(inputList, 0, list1, 0, list1.length);
        System.arraycopy(inputList, list1.length, list2, 0, list2.length);
        mergeSortType(list1);
        mergeSortType(list2);
        mergeType(list1, list2, inputList);
        return inputList;
    }

    public static void mergeType(File[] list1, File[] list2, File[] resultList) {
        int indexOfList1 = 0;
        int indexOfList2 = 0;
        int indexOfMergedList = 0;
        while (indexOfList1 < list1.length && indexOfList2 < list2.length) {
            if (getFileType(list1[indexOfList1]).compareTo(getFileType(list2[indexOfList2])) < 0) {
                resultList[indexOfMergedList] = list1[indexOfList1];
                indexOfList1++;
            } else if (getFileType(list1[indexOfList1]).compareTo(getFileType(list2[indexOfList2])) == 0) {
                if (list1[indexOfList1].getAbsolutePath().compareTo(list2[indexOfList2].getAbsolutePath())
                        < 0) {
                    resultList[indexOfMergedList] = list1[indexOfList1];
                    indexOfList1++;
                } else {
                    resultList[indexOfMergedList] = list2[indexOfList2];
                    indexOfList2++;
                }
            } else {
                resultList[indexOfMergedList] = list2[indexOfList2];
                indexOfList2++;
            }
            indexOfMergedList++;
        }
        System.arraycopy(list1, indexOfList1, resultList, indexOfMergedList, list1.length -
                indexOfList1);
        System.arraycopy(list2, indexOfList2, resultList, indexOfMergedList, list2.length -
                indexOfList2);
    }

    // the Sort for size
    public static File[] mergeSortSize(File[] inputList) {
        if (inputList.length <= 1) {
            return inputList;
        }
        File[] list1 = new File[inputList.length / 2];
        File[] list2 = new File[inputList.length - list1.length];
        System.arraycopy(inputList, 0, list1, 0, list1.length);
        System.arraycopy(inputList, list1.length, list2, 0, list2.length);
        mergeSortSize(list1);
        mergeSortSize(list2);
        mergeSize(list1, list2, inputList);
        return inputList;
    }

    public static void mergeSize(File[] list1, File[] list2, File[] resultList) {
        int indexOfList1 = 0;
        int indexOfList2 = 0;
        int indexOfMergedList = 0;
        while (indexOfList1 < list1.length && indexOfList2 < list2.length) {
            if (list1[indexOfList1].length() - (list2[indexOfList2].length()) < 0) {
                resultList[indexOfMergedList] = list1[indexOfList1];
                indexOfList1++;
            } else if (list1[indexOfList1].length() - (list2[indexOfList2].length()) == 0) {
                if (list1[indexOfList1].getAbsolutePath().compareTo(list2[indexOfList2].getAbsolutePath())
                        < 0) {
                    resultList[indexOfMergedList] = list1[indexOfList1];
                    indexOfList1++;
                } else {
                    resultList[indexOfMergedList] = list2[indexOfList2];
                    indexOfList2++;
                }
            } else {
                resultList[indexOfMergedList] = list2[indexOfList2];
                indexOfList2++;
            }
            indexOfMergedList++;
        }
        System.arraycopy(list1, indexOfList1, resultList, indexOfMergedList, list1.length -
                indexOfList1);
        System.arraycopy(list2, indexOfList2, resultList, indexOfMergedList, list2.length -
                indexOfList2);
    }
}

