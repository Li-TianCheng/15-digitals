import java.util.Arrays;

/**
 * @Program: 15digitals
 * @ClassName: Node
 * @Description:
 * @Author: Ltc
 * @Create: 2020-10-19 18:12
 **/

public class Node {
    public int[] value;
    public int f;
    public int g;
    public int h;
    public Node father;
    public String nodeString;
    public Node(int[] value, int g, Node father, int[][] cost){
        for (int i = 0; i < 16; i++) {
            h += cost[value[i]][i];
        }
        this.value = value;
        this.g = g;
        this.father = father;
        nodeString = Arrays.toString(value);
        f = g + h;
    }
}
