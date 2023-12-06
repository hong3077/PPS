package com.example.planepowerselecter

class Sorter {

    /**
     * Performs bubble sort on the 2D array based on the motor power (ascending order).
     */
    fun bottomToTopPOWER(arr: Array<Array<String>>) {
        val n = arr.size

        for (i in 0 until n - 1) {
            for (j in 0 until n - i - 1) {
                if (arr[j][2].toInt() > arr[j + 1][2].toInt()) { //모터의 출력을 오름차순으로 정렬
                    // 스와핑
                    val temp = arr[j]
                    arr[j] = arr[j + 1]
                    arr[j + 1] = temp
                }
            }
        }
    }

    fun topToBottomPOWER(arr: Array<Array<String>>) {
        val n = arr.size

        for (i in 0 until n - 1) {
            for (j in 0 until n - i - 1) {
                if (arr[j][2].toInt() < arr[j + 1][2].toInt()) { //모터의 출력을 내림차순으로 정렬
                    // 스와핑
                    val temp = arr[j]
                    arr[j] = arr[j + 1]
                    arr[j + 1] = temp
                }
            }
        }
    }

    fun bottomToTopCOST(arr: Array<Array<String>>) {
        val n = arr.size

        for (i in 0 until n - 1) {
            for (j in 0 until n - i - 1) {
                if (arr[j][4].toInt() > arr[j + 1][4].toInt()) { //모터의 가격을 오름차순으로 정렬
                    // 스와핑
                    val temp = arr[j]
                    arr[j] = arr[j + 1]
                    arr[j + 1] = temp
                }
            }
        }
    }

    fun topToBottomCOST(arr: Array<Array<String>>) {
        val n = arr.size

        for (i in 0 until n - 1) {
            for (j in 0 until n - i - 1) {
                if (arr[j][4].toInt() < arr[j + 1][4].toInt()) { //모터의 가격을 내림차순으로 정렬
                    // 스와핑
                    val temp = arr[j]
                    arr[j] = arr[j + 1]
                    arr[j + 1] = temp
                }
            }
        }
    }
}