package kr.easw.lesson3;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class SnakeGameWithoutTails {
    private static final int BOARD_SIZE = 10;
    // 0 - 빈 타일
    // 1 - 스네이크 블럭
    // 2 - 아이템
    private static final int[][] board = new int[BOARD_SIZE][BOARD_SIZE];       // 10x10 2차원 배열
    private static final Random RANDOM = new Random();      // 변수에 난수 저장
    private static int score = 0;       // 스코어 변수 초기화
    private static SnakeLocation location = new SnakeLocation(0, 0);        // x, y 저장하는 구조체
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);       // 변수에 입력 받기
        while (true) {              // true면 계속 반복
            printBoard();           // 보드
            System.out.print("[우측 (r) | 좌측 (l) | 위 (u) | 아래 (d) | 종료 (0) ] : ");
            if (!nextDirection(scanner.next())) {       // 리턴이 false이면
                System.out.println("게임 오버!");
                System.out.printf("점수: %d\n", score);       // 점수 출력 후 종료
                break;
            }
            if (!hasItemOnBoard())      // 판에 아이템이 없는 경우
                placeRandomItem();      // 아이템 배치
        }
    }
    /**
     * 해당 메서드는 다음과 같은 역할을 가져야 합니다 :
     * 사용자의 입력을 받고, 다음 위치로 옮기거나 게임을 종료해야 합니다.
     * 허용되는 입력은 다음과 같습니다 :
     * - 우측(r) | 좌측 (l) | 위 (u) | 아래 (d) | 종료 (0)
     * 다음 좌표는 location 변수에 계속해서 업데이트되어야 합니다.
     * 다음 좌표에 아이템이 존재한다면, 점수를 1 증가하고 다음 좌표의 값을 0으로 되돌려야 합니다.
     * (BOARD_SIZE)이상이 되거나 최소 값(0) 아래로 내려간다면 같은 좌표로 설정하여 이동하지 않도록
     * 사용자의 입력이 종료(0)였다면, false값을 반환하여 게임을 종료
     */
    private static boolean nextDirection(String keyword) {

        switch (keyword) {
            case "r":     // 우로 한칸
               if(location.y < BOARD_SIZE && location.y >= 0){
                location = location.adjust(0, 1);
                if (hasItemOnBoard())
                    score++;
                }
                   break;
            case "l":   // 좌로 한칸
                if(location.y < BOARD_SIZE && location.y >= 0) {
                   location =  location.adjust(0, -1);
                    if (hasItemOnBoard())
                        score++;
                }break;
            case "u":   // 위로 한칸
                if(location.x < BOARD_SIZE && location.x >= 0) {
                    location =  location.adjust(-1, 0);
                     if (hasItemOnBoard())
                         score++;
                 }
                break;
            case "d":   // 아래로 한칸
                if(location.x < BOARD_SIZE && location.x >= 0) {
                   location =  location.adjust(1, 0);
                    if (hasItemOnBoard())
                        score++;
                }
                break;
            case "0":
                return false;

            default:
                System.out.println("다시 입력하십시오.");
                break;
        }
        return true;
    }

    private static void printBoard() {      // 보드 말판을 그려주는 역할
        for (int i = 0; i < 25; i++) {
            System.out.println();
        }
        for (int x = 0; x < BOARD_SIZE; x++) {  // 0~9 가로세로 10칸
            for (int y = 0; y < BOARD_SIZE; y++) {
                 if (location.getX() == x && location.getY() == y) {
                   System.out.print("◼ ");
                    continue;
                }
                switch (board[x][y]) {      // 해당 배열에 저장된 값에 따라
                    case 0:                 // 0 - 빈 타일
                        System.out.print("・ ");
                        break;
                    case 1:                 // 1 - 스네이크 블럭
                        System.out.print("◼");
                        break;
                    case 2:                 // 2 - 아이템
                        System.out.print("* ");
                        break;
                }
            }
            System.out.println();
        }
    }
    private static void placeRandomItem() {     // 아이템 배치
        // for문의 조건으로 랜덤을 넣을 경우, 계속 비교하여 최종 결과값이 바뀌므로 변수로 선언합니다.
        int toPlace = (int) (RANDOM.nextDouble() * 10);     // 변수에 1~9까지의 정수 캐스팅
        for (int i = 0; i < toPlace; i++) {
            int retry = 0;
            while (retry < 5) {
                SnakeLocation locate = new SnakeLocation((int)(RANDOM.nextDouble() * (BOARD_SIZE-1)), (int)(RANDOM.nextDouble() * (BOARD_SIZE-1)));
                if (board[locate.getX()][locate.getY()] != 0) {
                    retry++;
                    continue;
                }
                board[locate.getX()][locate.getY()] = 2;
                break;
            }
        }
    }
    private static boolean hasItemOnBoard() {   // 아이템 줍기
        for (int x = 0; x < BOARD_SIZE; x++) {
            for (int y = 0; y < BOARD_SIZE; y++) {
                if (board[x][y] == 2) {
                    return true;
                }
            }
        }
        return false;
    }
    private static class SnakeLocation {        // 위치 기록
        private final int x;
        private final int y;
        public SnakeLocation(int x, int y) {
            this.x = x;
            this.y = y;
        }
        public int getX() {
            return x;
        }
        public int getY() {
            return y;
        }
        public SnakeLocation adjust(int x, int y) {
            return new SnakeLocation(this.x + x, this.y + y);
        }
    }
}