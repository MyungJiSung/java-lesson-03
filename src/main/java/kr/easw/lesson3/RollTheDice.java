package kr.easw.lesson3;

import java.util.Arrays;
import java.util.Random;

public class RollTheDice {
    private static int[] frequency = new int[6];        // 각 면이 몇 번 나오는지 빈도를 저장할 배열

    private static Random RANDOM = new Random();

    public static void main(String[] args) {
        for (int i = 0; i < 600; i++) {         // 600번 반복
            if (RANDOM.nextDouble() < 0.1) {
                fillArray(RANDOM.nextDouble() * 720);       // 0~1 사이의 난수에 720 곱한 값을 넣어서 실행
            } else {
                fillArray(RANDOM.nextDouble() * 360);       // 0~1 사이의 난수에 360 곱한 값을 넣어서 실행
                    }
        }
        for (int j = 0; j < 6; j++) {
            System.out.println(j+1+" : ");
            System.out.println(frequency[j]+ "번\n");
        }
    }

    /**
     * 해당 메서드는 다음과 같은 역할을 가져야 합니다 :
//   * 주어진 값을 60으로 나눈 후, 나온 값의 수만큼 해당 인덱스에 존재하는 배열 값을 1 증가시켜야 합니다.
     * 0 ~ 1.2 > 1 ~ 2.2,   0.6 ~ 6 > 1.6 ~ 7에서 int로 캐스팅하면 1 ~ 2, 1 ~ 6
     * 또한, 어떠한 경우에서도 주어진 기능을 구현했을 떄, 오류가 발생해서는 안됩니다.
     * <p>
     * 입력값은 일반적으로는 360을 넘지 않으나, 낮은 확률로 360을 넘습니다.
     * 이러한 경우, extendArray 메서드를 구현하여 이를 이용해 배열을 재선언해야 합니다.
     * <p>
     * 또한, 입력값이 double이므로 60으로 나눈 이후 int로 캐스팅이 필요합니다.
     */
    private static void fillArray(double result) {
        if (result <= 360) {
            ++frequency[(int) result / 60];
        }
        else {
            extendArray((int) result / 60);
        }
    }

    /**
     * 해당 메서드는 다음과 같은 역할을 가져야 합니다 :
     * 주어진 값의 크기만큼 배열을 생성한 후, 기존 배열에 있던 데이터를 복사해 반환해야 합니다.
     */
    private static int[] extendArray(int next) {
        int[] newfrequency = Arrays.copyOf(frequency, next);
        return newfrequency;
    }
}