/**
 * @Program: 15digitals
 * @ClassName: Main
 * @Description:
 * @Author: Ltc
 * @Create: 2020-10-19 18:12
 **/

public class Main {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        final int[][] cost = new int[16][16];
        for (int i = 0; i < 15; i++) {
            int[][] token = new int[][]{{0, 1, 2, 3}, {1, 0, 1, 2}, {2, 1, 0, 1}, {3, 2, 1, 0}};
            int[] subToken = token[i%4];
            System.arraycopy(subToken, 0, cost[i+1], i/4*4, 4);
            for (int j = 0; j < 3-i/4; j++){
                int[] temp = subToken.clone();
                for(int l = 0; l < 4; l++) {
                    temp[l] += j + 1;
                }
                System.arraycopy(temp, 0, cost[i+1], (i/4+j+1)*4, 4);
            }
            for (int k = 0; k < i/4; k++){
                int[] temp = subToken.clone();
                for(int l = 0; l < 4; l++) {
                    temp[l] += k + 1;
                }
                System.arraycopy(temp, 0, cost[i+1], (i/4-k-1)*4, 4);
            }
        }
        final int[] s0 = new int[]{11, 9, 4, 15, 1, 3, 0, 12, 7, 5, 8, 6, 13, 2, 10, 14};
        Node node = new Node(s0, 0, null, cost);
        Search solver = new Search(node, cost);
        solver.search();
        long end = System.currentTimeMillis();
        System.out.printf("运行时间%dms\n", end - start);
    }
}
