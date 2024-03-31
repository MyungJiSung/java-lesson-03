package kr.easw.lesson3;

import java.util.Arrays;

public class SimpleArray {
    private static int[] arrays = new int[10];      // 10칸 배열 선언

    private static int[] answer = new int[]{0, 1, 4, 16, 25, 49, 64, 81, 121, 144};     // 답지 배열

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            fillArray(i);                       // i가 0~9까지 10번 반복
        }
        boolean isMatched = true;
        for (int i = 0; i < 10; i++) {          // i가 0~9까지 10번 반복
            if (arrays[i] != answer[i]) {
                System.out.printf("값이 일치하지 않습니다. (인덱스 %d)\n", i);       // 틀리면 출력
                isMatched = false;
            }
        }
        if (isMatched) {
            System.out.println("정답입니다.");
        } else {
            System.out.println("오답입니다.");
        }
    }

    /**
     * 해당 메서드는 다음과 같은 역할을 가져야 합니다 :
     * 주어진 인덱스를 이용하여 array 변수에 다음 수식을 적용하여 추가해야 합니다.
     * <p>
     * (index x 7 / 5) ^ 2
     * <p>
     * * ^2는 제곱의 의미로 사용되었습니다.
     */
    private static void fillArray(int index) {      // 배열 채우는 역할
        for(index = 0; index < 10; index++){

            arrays[index]=(index*7/5)*(index*7/5);
        }
    }
}