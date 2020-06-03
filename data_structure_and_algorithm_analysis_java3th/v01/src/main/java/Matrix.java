/*
* 实现二维矩阵常用操作
* */

class Matrix {
    public static <T> T[][] TransposeSquareMatrix(T[][] matrix){
        T[][] result = matrix.clone();
        for (int rowNumber = 0; rowNumber < matrix.length; rowNumber++) {
            for (int columnNumber = rowNumber+1; columnNumber < matrix.length; columnNumber++) {
                result[columnNumber][rowNumber] = matrix[rowNumber][columnNumber];
                result[rowNumber][columnNumber] = matrix[columnNumber][rowNumber];
            }
            result[rowNumber][rowNumber] = matrix[rowNumber][rowNumber];
        }
        return result;
    }

    public static <T> T[][] leftRotate90Degrees(T[][] matrix){
        return reverseOrder(Matrix.TransposeSquareMatrix(matrix));
    }

    public static <T> T[] reverseOrder(T[] array){
        T[] arrayCopy = array.clone();
        for (int i = 0; i < Math.ceil(arrayCopy.length/2); i++){
            T temp = arrayCopy[i];
            arrayCopy[i] = arrayCopy[arrayCopy.length - 1 - i];
            arrayCopy[arrayCopy.length - 1 - i] = temp;
        }
        return arrayCopy;
    }
}
