package chapter04.section01.alg4_2;

import chapter04.section01.Graph;
import edu.princeton.cs.algs4.StdIn;

/**
 * 间隔的度数
 * 正确
 */
public class DegreesOfSeparation {
    public static void main(String[] args) {
        SymbolGraph sg = new SymbolGraph("files/routes", " ");

        Graph G = sg.G();

        String source = "JFK";

        if(!sg.contains(source)) {
            System.out.println(source + "not in database.");
            return;
        }

        int s = sg.index(source);
        BreadthFirstPaths bfs = new BreadthFirstPaths(G, s);

        while(!StdIn.isEmpty()) {
            String sink = StdIn.readLine();
            if(sg.contains(sink)) {
                /**
                 * 返回符号对应的 标识
                 */
                int t = sg.index(sink);
                if(bfs.hasPathTo(t))
                    for(int v: bfs.pathTo(t))
                        System.out.println("  " + sg.name(v));
                else
                    System.out.println("Not connected");
            }else {
                System.out.println("Not in db.");
            }
        }
    }
}
