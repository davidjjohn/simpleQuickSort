// Simple implementation of quicksort on an array of int.
// Taken from Roberts' textbook.
//
// Modified by:
//     David John
//     March, 2021

import java.util.Random;

public class Main {

    private static Random rand;
    private static int[] myData;
    private static long count;

    public static void main(String[] args) {

        rand = new Random();
        myData = populate(32
                ,10000);
        count = 0;
        sort(myData);
        System.out.println("Number of steps: "+count);


    }

    /**
     * Populate the array with integers I,  -bound < I < bound
     *
     * @param N
     * @param bound
     * @return
     */
    public static int[] populate(int N, int bound){
        assert(N>0): "Illegal array size";
        assert(bound>0 && bound <10000000): "Illegal bound";

        int[] temp = new int[N];
        for (int i=0; i<N;i++){
            temp[i] = rand.nextInt(bound);
        }
        return temp;
    }

    /**
     *
     * Taken from Roberts, Figure 11-9
     *
     * sorts an array of integers into ascending order.
     *
     * @param array
     */

    public static void sort(int[] array){

        quicksort(array,0,array.length);

        //for(int i=0; i<16; i++){
        //    System.out.println(array[i]);
        //}
    }


    /**
     *
     * Taken from Roberts, Figure 11-9
     *
     * Applies the Quicksort algorithm to the elements in the
     * subarray starting at p1 (inclusive) and ending at p2 (exclusive).
     *
     * @param array
     * @param p1
     * @param p2
     */
    private static void quicksort(int[] array, int p1, int p2){

        // if length of array <= 1 then there is nothing to do
        if (p2-1 <= p1) return;

        // partition array[p1,...p2)  {array[p1,...,p2-1]}
        int boundary = partition(array,p1,p2);

        // keeping in mind that array[boundary] is in the right place,
        // quicksort the two pieces, array[p1,boundary) & array(boundary, p2)
        //     array[p1,...,boundary-1] & array[boundary+1,...,p2-1]
        quicksort(array,p1,boundary);
        quicksort(array,boundary+1,p2);
    }

    /**
     *
     * Taken from Roberts, Figure 11-9
     *
     * Rearranges the elements of the subarray delimited by the indices
     * pStart and pEnd so that "small" elements are grouped at the left end
     * of the array and "large' element are grouped at the right end.
     * The distinction between "small" and "large" is relative to the
     * "pivot" value, which initially appears in array[start].
     * When the partitioning is done, the function returns a boundary
     * index what that array[i]<pivot for all i< boundary and array[i]==pivot
     * for i==boundary and array[i]>=pivot for all i>boundary.
     *
     * @param array
     * @param pStart
     * @param pEnd
     * @return
     *
     */
    private static int partition(int[] array, int pStart, int pEnd){

        // establish pivot element and positions of left finger and right finger
        int pivot = array[pStart];
        int leftfinger = pStart+1;
        int rightfinger = pEnd-1;

        // this loop concentrates on moving the left and right fingers
        while(true){

            // move left and then right fingers as long as the ordering, relative to
            // the pivot element is ok
            while(leftfinger < rightfinger && array[rightfinger] >= pivot) {count++;rightfinger--;}
            while (leftfinger < rightfinger && array[leftfinger] < pivot) {count++;leftfinger++;}

            // if the two fingers end up at the same point leave loop
            if (leftfinger ==rightfinger) break;

            // swap the values associated with the left and right fingers and
            // continue through loop
            count++;
            int temp = array[leftfinger];
            array[leftfinger] = array[rightfinger];
            array[rightfinger] = temp;
        }

        // in this case the pivot final position remains
        // at pStart
        if (array[leftfinger] >= pivot) return pStart;

        // exchange pivot and array[leftfinger] values and return
        // leftfinger position, the final position of pivot
        count++;
        array[pStart] = array[leftfinger];
        array[leftfinger] = pivot;
        return leftfinger;
    }
}
