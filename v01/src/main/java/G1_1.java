/*
* 实现静态排序类，对比三种简单排序与快排和递归排序速度
* */

import java.util.function.IntFunction;

public class G1_1 {
    static class ArraySort {
        public static <T extends Comparable<T>> T[] insertSort(T[] arrayGeneric){
            for (int indexToBeSort = 1; indexToBeSort < arrayGeneric.length; indexToBeSort++) {
                int indexRightReadySortArray = (indexToBeSort - 1);
                for (int index = indexRightReadySortArray; index >= 0; index--){
                    if (arrayGeneric[index].compareTo(arrayGeneric[index+1]) > 0){
                        T tmp = arrayGeneric[index];
                        arrayGeneric[index] = arrayGeneric[index+1];
                        arrayGeneric[index+1] = tmp;
                    }
                    else {
                        break;
                    }
                }
            }
            return arrayGeneric;
        }

        public static <T extends Comparable<T>> T[] selectSort(T[] arrayGeneric){
            for (int indexToBeSort = 0; indexToBeSort < arrayGeneric.length; indexToBeSort += 1){
                T minValue = arrayGeneric[indexToBeSort];
                int minIndex = indexToBeSort;
                for (int index = indexToBeSort + 1; index < arrayGeneric.length; index+=1){
                    if (arrayGeneric[index].compareTo(minValue) < 0){
                        minValue = arrayGeneric[index];
                        minIndex = index;
                    }
                }
                T tmp = arrayGeneric[indexToBeSort];
                arrayGeneric[indexToBeSort] = arrayGeneric[minIndex];
                arrayGeneric[minIndex] = tmp;
            }
            return arrayGeneric;
        }

        public static <T extends Comparable<T>> T[] swapSort(T[] arrayGeneric){
            int indexRight = arrayGeneric.length;
            for (int numReadySort = 1; numReadySort < indexRight; numReadySort += 1){
                for (int index=0; index < indexRight - numReadySort; index+=1) {
                    int nextIndex = index + 1;
                    T nowValue = arrayGeneric[index];
                    T nextValue = arrayGeneric[nextIndex];
                    if (nowValue.compareTo(nextValue) > 0){
                        arrayGeneric[nextIndex] = nowValue;
                        arrayGeneric[index] = nextValue;
                    }
                }
            }
            return arrayGeneric;
        }

        public static <T extends Comparable<T>> T[] fastSort(T[] arrayGeneric,int headIndex, int tailIndex){
            if (headIndex == tailIndex) {
                return arrayGeneric;
            }
            T baseValue = arrayGeneric[headIndex];
            int indexRightMin = headIndex;
            for (int indexRightMax = headIndex + 1; indexRightMax < tailIndex; indexRightMax += 1){
                if (arrayGeneric[indexRightMax].compareTo(baseValue) < 0){
                    indexRightMin += 1;
                    T tmp = arrayGeneric[indexRightMin];
                    arrayGeneric[indexRightMin] = arrayGeneric[indexRightMax];
                    arrayGeneric[indexRightMax] = tmp;
                }
            }
            T tmp = arrayGeneric[indexRightMin];
            arrayGeneric[indexRightMin] = baseValue;
            arrayGeneric[headIndex] = tmp;
            fastSort(arrayGeneric, headIndex, indexRightMin);
            fastSort(arrayGeneric, indexRightMin+1, tailIndex);
            return arrayGeneric;
        }

        public static <T extends Comparable<T>> T[] mergeSort(T[] arrayGeneric, IntFunction<T[]> constructor){
            T[] arrayGenericCollect = constructor.apply(arrayGeneric.length);
            int numElement = 1;
            while (numElement < arrayGeneric.length){
                divide(arrayGeneric, arrayGenericCollect, numElement);
                numElement *= 2;
                divide(arrayGenericCollect, arrayGeneric, numElement);
                numElement *= 2;
            }
            return arrayGeneric;
        }

        public static <T extends Comparable<T>> void divide(T[] arrayGeneric,T[] arrayGenericCollect, int numElement){
            for (int headIndex=0; headIndex < arrayGeneric.length; headIndex += 2*numElement){
                if (arrayGeneric.length - headIndex <= numElement){
                    int tailIndex = arrayGeneric.length;
                    addRemainingToCollect(arrayGenericCollect, headIndex, tailIndex, arrayGeneric, headIndex);
                }
                else if (arrayGeneric.length - headIndex <= 2*numElement){
                    int tailIndex = arrayGeneric.length;
                    merge(arrayGeneric, arrayGenericCollect, headIndex, tailIndex, numElement);
                }
                else {
                    int tailIndex = headIndex + 2*numElement;
                    merge(arrayGeneric, arrayGenericCollect, headIndex, tailIndex, numElement);
                }
            }
        }

        public static <T extends Comparable<T>> void merge(T[] arrayGeneric, T[] arrayGenericCollect,
                                                           int headIndex, int tailIndex, int numElement){
            int firstHeadIndex = headIndex;
            int firstTailIndex = headIndex + numElement;
            int secondHeadIndex = headIndex + numElement;
            int secondTailIndex = tailIndex;
            int index = headIndex;
            while (index < tailIndex){
                if (arrayGeneric[firstHeadIndex].compareTo(arrayGeneric[secondHeadIndex]) < 0){
                    arrayGenericCollect[index] = arrayGeneric[firstHeadIndex];
                    firstHeadIndex += 1;
                }
                else {
                    arrayGenericCollect[index] = arrayGeneric[secondHeadIndex];
                    secondHeadIndex += 1;
                }

                index += 1;

                if (firstHeadIndex == firstTailIndex){
                    addRemainingToCollect(arrayGenericCollect,index,tailIndex,arrayGeneric,secondHeadIndex);
                    break;
                }
                else if (secondHeadIndex == secondTailIndex){
                    addRemainingToCollect(arrayGenericCollect,index,tailIndex,arrayGeneric,firstHeadIndex);
                    break;
                }
            }

        }

        public static <T extends Comparable<T>> void addRemainingToCollect(T[] arrayGenericCollect, int indexCollect, int tailIndexCollect,
                                                                           T[] arrayGeneric, int index){
            while (indexCollect < tailIndexCollect){
                arrayGenericCollect[indexCollect] = arrayGeneric[index];
                indexCollect += 1;
                index += 1;
            }
        }

    }

}



