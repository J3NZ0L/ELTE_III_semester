/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sort1;

import java.util.*;

/**
 *
 * @author bli
 */
public class Sort1 {

    public static List<Integer> getSortedByNRemainder(List<Integer> list, final int n) {
        return getSortedByNRemainder(list, n, true);
    }

    public static List<Integer> getSortedByNRemainder(List<Integer> list, final int n, final boolean ascending) {
        ArrayList<Integer> listCopy = new ArrayList<>(list);
        Collections.sort(listCopy, new RemainderComparator(n, ascending));
        return listCopy;
    }

    public static List<Integer> sortByComperatorInBetween(List<Integer> list, final int i, final int j,
                                                          Comparator<Integer> comparator) {
        ArrayList<Integer> listCopy = new ArrayList<>(list);
        ArrayList<Integer> listCopy2 = new ArrayList<>(list);
        listCopy.sort(comparator);
        listCopy2.sort(comparator.reversed());
        ArrayList<Integer> returnList = new ArrayList<>();
        //should return the corresponding parts of the list from now on
        return list;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 10, 21, 12, 34, 5));
        System.out.println(getSortedByNRemainder(list, 3));
    }

    
}
