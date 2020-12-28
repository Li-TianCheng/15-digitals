import java.util.*;

/**
 * @Program: 15digitals
 * @ClassName: Search
 * @Description:
 * @Author: Ltc
 * @Create: 2020-10-19 18:12
 **/

public class Search {
    private final int[][] adjacent = new int[16][16];
    private final Node S0;
    private final PriorityQueue<Node> Open = new PriorityQueue<>((Node a, Node b) -> a.f == b.f ? a.h-b.h : a.f-b.f);
    private final HashMap<String, Node> OpenMap = new HashMap<>();
    private final HashMap<String, Integer> Closed = new HashMap<>();
    private final int[][] cost;
    public Search(Node s0, int[][] cost){
        this.S0 = s0;
        this.cost = cost;
        for (int i = 0; i < 16; i++){
            int m = i / 4;
            int n = i % 4;
            if (m-1 >= 0) {
                adjacent[i][(m-1)*4+n] = 1;
            }
            if (m+1 < 4) {
                adjacent[i][(m+1)*4+n] = 1;
            }
            if (n-1 >= 0) {
                adjacent[i][m*4+n-1] = 1;
            }
            if (n+1 < 4) {
                adjacent[i][m*4+n+1] = 1;
            }
        }
    }

    public void search(){
        Open.add(S0);
        OpenMap.put(S0.nodeString, S0);
        int step = 0;
        System.out.printf("第%d轮，Closed表长度为%d，Open表长度为%d\n", step, 0, Open.size());
        step++;
        expand();
        while (true){
            if (Open.isEmpty()){
                System.out.println("\n搜索失败");
                break;
            }
            if (Open.peek().h == 0){
                System.out.println("\n搜索成功\n");
                Node curr = Open.peek();
                while(curr != null){
                    System.out.printf("第%d次操作(当前损失为%d):\n", curr.g, curr.f);
                    for (int i = 0; i < 16; i++){
                        System.out.print(curr.value[i]);
                        System.out.print(' ');
                        if (i % 4 == 3) {
                            System.out.print('\n');
                        }
                    }
                    System.out.print('\n');
                    curr = curr.father;
                }
                break;
            }
            System.out.printf("第%d轮，Closed表长度为%d，Open表长度为%d\n", step, Closed.size(), Open.size());
            step++;
            expand();
        }
    }

    private void expand(){
        Node curr = Open.poll();
        assert curr != null;
        OpenMap.remove(curr.nodeString);
        Closed.put(curr.nodeString, curr.f);
        int idx = -1;
        for (int i = 0; i < 16; i++) {
            if (curr.value[i] == 0) {
                idx = i;
            }
        }
        assert idx >= 0;
        for (int i = 0; i < adjacent[idx].length; i++){
            if (adjacent[idx][i] == 1){
                int[] value = curr.value.clone();
                int tem = value[idx];
                value[idx] = value[i];
                value[i] = tem;
                Node temNode = new Node(value, curr.g+1, curr, cost);
                boolean flag1 = OpenMap.containsKey(temNode.nodeString);
                boolean flag2 = Closed.containsKey(temNode.nodeString);
                if (flag1){
                    if (OpenMap.get(temNode.nodeString).f > temNode.f){
                        Open.remove(OpenMap.get(temNode.nodeString));
                        Open.add(temNode);
                        OpenMap.put(temNode.nodeString, temNode);
                    }
                }
                if (flag2){
                    if (Closed.get(temNode.nodeString) > temNode.f){
                        Closed.remove(temNode.nodeString);
                        Open.add(temNode);
                    }
                }
                if (!flag1 && !flag2){
                    Open.add(temNode);
                    OpenMap.put(temNode.nodeString, temNode);
                }
            }
        }
    }
}
