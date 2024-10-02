import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static class Soil {
        int nutrient; // 양분
        List<Tree> trees; // 해당 토양에 있는 나무
        public Soil(int nutrient, List<Tree> trees) {
            this.nutrient = nutrient;
            this.trees = trees;
        }
    }

    static class Tree {
        int r;
        int c;
        int age; // 나이
        boolean isAlive; // 살아있는지 여부
        public Tree(int r, int c, int age, boolean isAlive) {
            this.r = r;
            this.c = c;
            this.age = age;
            this.isAlive = isAlive;
        }
    }

    static int N;
    /*
        7 0 1
        6   2
        5 4 3
    */
    static final int[] rw = {-1, -1, 0, 1, 1, 1, 0, -1};
    static final int[] cw = {0, 1, 1, 1, 0, -1, -1, -1};
    static Soil[][] soil;
    static int[][] S2D2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        soil = new Soil[N+1][N+1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                soil[i][j] = new Soil(5, new ArrayList<>()); // 가장 처음에 양분은 모든 칸에 5만큼 들어있다.
            }
        }

        S2D2 = new int[N+1][N+1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                S2D2[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int age = Integer.parseInt(st.nextToken());
            Tree tree = new Tree(r, c, age, true);
            soil[r][c].trees.add(tree);
        }

        for (int i = 0; i < K; i++) {
            spring();
            summer();
            autumn();
            winter();
        }

        int aliveTreeNum = countAliveTree();
        bw.write(String.valueOf(aliveTreeNum));
        bw.flush();
    }

    static int countAliveTree() {
        int aliveTreeNum = 0;

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                aliveTreeNum += soil[i][j].trees.size();
            }
        }
        return aliveTreeNum;
    }

    static void spring() {

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (soil[i][j].trees.size() > 0) {
                    List<Tree> trees = soil[i][j].trees;

                    // 나이가 어린 나무가 먼저 오게
                    trees.sort(new Comparator<Tree>() {
                        @Override
                        public int compare(Tree t1, Tree t2) {
                            return Integer.compare(t1.age, t2.age);
                        }
                    });
    
                    List<Tree> newTrees = new ArrayList<>();
    
                    for (Tree tree : trees) {
                        // 나무 나이만큼의 양분이 없다면
                        if (soil[i][j].nutrient < tree.age) {
                            tree.isAlive = false; // 죽음
                            newTrees.add(tree);
                        }
                        else {
                            soil[i][j].nutrient -= tree.age; // 자신의 나이만큼 양분을 먹고
                            tree.age += 1; // 나이 1 증가
                            newTrees.add(tree); // 현재 토양에 있는 나무 리스트에 추가
                        }
                    }
                    soil[i][j].trees = newTrees;
                }
            }
        }
    }

    static void summer() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (soil[i][j].trees.size() > 0) {
                    List<Tree> trees = soil[i][j].trees;

                    List<Tree> aliveTrees = new ArrayList<>();
                    // 각각의 죽은 나무마다 나이를 2로 나눈 값이 나무가 있던 칸에 양분으로 추가
                    for (Tree tree : trees) {
                        if (!tree.isAlive) {
                            soil[i][j].nutrient += (tree.age / 2);
                            continue;
                        }
                        aliveTrees.add(tree);
                    }
                    soil[i][j].trees = aliveTrees;
                }
            }
        }
    }

    static void autumn() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (soil[i][j].trees.size() > 0) {
                    List<Tree> trees = soil[i][j].trees;
                    
                    for (Tree tree : trees) {
                        if ((tree.age % 5) == 0) {
                            for (int k = 0; k < 8; k++) {
                                int r = i + rw[k];
                                int c = j + cw[k];

                                if (isValid(r, c)) {
                                    soil[r][c].trees.add(new Tree(r, c, 1, true));
                                }
                            }
                        }
                    }

                }
            }
        }
    }

    static void winter() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                soil[i][j].nutrient += S2D2[i][j];
            }
        }
    }
    
    static boolean isValid(int r, int c) {
        return r > 0 && c > 0 && r <= N && c <= N;
    }
}
