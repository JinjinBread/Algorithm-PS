import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

class Solution {

    private static int N = 5;
    // 상좌 대각선, 상, 상상, 상우 대각선, 좌, 좌좌, 우, 우우, 하좌 대각선, 하, 하하, 하우 대각선
    private static int[] rw = { -1, -1, -1, -2, 0, 0, 0, 0, 1, 1, 2, 1 };
    private static int[] cw = { -1, 0, 1, 0, -1, -2, 1, 2, -1, 0, 0, 1 };
    private static List<char[][]> placesLists;
    private static String[][] places;

    private static class Student {
        int r;
        int c;
        int roomNum;

        public Student(int r, int c, int roomNum) { this.r = r; this.c = c; this.roomNum = roomNum; }
    }

    public int[] solution(String[][] places) {
        int[] answer = new int[N];
        Arrays.fill(answer, 1);
        this.places = places;
        placesLists = new ArrayList<>();

        initialize();

        Deque<Student> dq = new ArrayDeque<>();

        for (int i = 0; i < N; i++) {
            char[][] room = placesLists.get(i);
            for (int j = 0; j < N; j++) {
                char[] roomLine = room[j];
                for (int k = 0; k < N; k++) {
                    if (roomLine[k] == 'P') {
                        dq.add(new Student(j, k, i));
                    }
                }
            }
        }

        while (!dq.isEmpty()) {
            
            Student student = dq.poll();
            
            // 거리두기를 지키지 않은 학생이 한 명이라도 적발됐다면, 해당 대기실은 다 건너뜀
            if (answer[student.roomNum] == 0) {
                continue;
            }
            
            List<Student> aroundList = isAnyoneAround(student);

            // 맨해튼 거리가 2이하로 될 수 있는 위치에 응시자가 없는 경우
            if (aroundList.isEmpty()) {
                continue;
            }

            for (Student nearStudent : aroundList) {
                
                // 바로 상하좌우에 있는 경우로, 파티션 체크를 안해도 거리두기를 지키지 않은 경우이다.
                if (getDistance(student, nearStudent) == 1) {
                    answer[student.roomNum] = 0;
                    break;
                }

                // 파티션이 없는 경우
                if (!isPartition(student, nearStudent)) {
                    answer[student.roomNum] = 0;
                    break;
                }
            }
        }

        return answer;
    }

    private void initialize() {

        for (int i = 0; i < N; i++) {
            char[][] room = getRoom(i);
            placesLists.add(room);
        }
    }

    private int getDistance(Student st1, Student st2) {
        return Math.abs(st1.r - st2.r) + Math.abs(st1.c - st2.c);
    }

    private boolean isPartition(Student st1, Student st2) {

        char[][] place = placesLists.get(st1.roomNum); // st1.roomNum == st2.roomNum

        // 대각선 상에 존재한다면
        if (isDiagonal(st1, st2)) {
            // 대각선에 파티션이 존재해야 함
            return (place[st1.r][st2.c] == 'X') && (place[st2.r][st1.c] == 'X');
        }

        // 수직, 수평 상에 존재하는 경우 ( + 거리가 2 차이나는 경우 )
        return place[(st1.r + st2.r) / 2][(st1.c + st2.c) / 2] == 'X';
    }

    private boolean isDiagonal(Student st1, Student st2) {

        // 행과 열이 모두 다르면 대각선 위치
        if ((st1.r != st2.r) && (st1.c != st2.c)) {
            return true;
        }
        return false;
    }

    private List<Student> isAnyoneAround(Student student) {
        
        int nowR = student.r;
        int nowC = student.c;
        int roomNum = student.roomNum;

        List<Student> nearBy = new ArrayList<>();

        char[][] room = getRoom(roomNum);
        
        for (int i = 0; i < 12; i++) {
            int r = nowR + rw[i];
            int c = nowC + cw[i];

            // 범위를 벗어나는 경우
            if (r < 0 || c < 0 || r >= N || c >= N) {
                continue;
            }

            if (room[r][c] == 'P') {
                nearBy.add(new Student(r, c, roomNum));
            }
        }

        return nearBy; // 비어있으면 주변에 아무도 없음
    }

    private char[][] getRoom(int roomNum) {
        char[][] room = new char[N][N];
        
        for (int i = 0; i < N; i++) {
            char[] line = places[roomNum][i].toCharArray();
            for (int j = 0; j < N; j++) {
                room[i][j] = line[j];
            }
        }

        return room;
    }
}