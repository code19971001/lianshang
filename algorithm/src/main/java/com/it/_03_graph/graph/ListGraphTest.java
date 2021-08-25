package com.it._03_graph.graph;

import com.it._03_graph.tool.GenerateGraph;
import com.it._03_graph.tool.GraphData;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * @author : code1997
 * @date : 2021/4/8 22:20
 */
public class ListGraphTest {

    @Test
    public void init() {
        ListGraph<String, Integer> graph = new ListGraph<>();
        graph.addEdge("v1", "v0", 9);
        graph.addEdge("v0", "v4", 6);
        graph.addEdge("v3", "v4", 1);
        graph.addEdge("v2", "v3", 5);
        graph.addEdge("v2", "v0", 2);
        graph.addEdge("v1", "v2", 3);
        graph.addEdge("v1", "v2", 10);

        graph.print();
        graph.removeEdge("v0", "v4");
        graph.removeVertex("v4");
        graph.print();
    }

    @Test
    public void test() {
        ListGraph<String, Integer> graph = new ListGraph<>();
        graph.test();
    }

    @Test
    public void testBFS() {
        Graph<Object, Double> graph = GenerateGraph.directedGraph(GraphData.BFS_01);
        graph.bfs("A");
    }

    @Test
    public void testBFS2() {
        Graph<Object, Double> graph = GenerateGraph.directedGraph(GraphData.BFS_02);
        graph.bfs(0, (Object value) -> {
            System.out.println(value);
            return false;
        });
    }

    @Test
    public void testDFS() {
        Graph<Object, Double> graph = GenerateGraph.undirectedGraph(GraphData.DFS_02);
        graph.dfs("c");
    }

    @Test
    public void testDFS2() {
        Graph<Object, Double> graph = GenerateGraph.undirectedGraph(GraphData.DFS_02);
        graph.dfs2("c");
    }

    @Test
    public void testTopologicalSort() {
        Graph<Object, Double> dag = GenerateGraph.directedGraph(GraphData.TOPO);
        List<Object> topologicalSort = dag.topologicalSort();
        topologicalSort.forEach(System.out::println);
    }

    static Graph.WeighManager<Double> manager = new Graph.WeighManager<Double>() {
        @Override
        public int compare(Double w1, Double w2) {
            return w1.compareTo(w2);
        }

        @Override
        public Double add(Double w1, Double w2) {
            return w1 + w2;
        }

        @Override
        public Double zero() {
            return 0.0;
        }

    };

    @Test
    public void testMst() {
        Graph<Object, Double> mst = GenerateGraph.undirectedGraph(GraphData.MST_01);
        mst.weighManager = manager;
        Set<Graph.EdgeInfo<Object, Double>> edgeInfos = mst.mst();
        edgeInfos.forEach(System.out::println);
    }

    @Test
    public void testShortestPath() {
        Graph<Object, Double> shortestPath = GenerateGraph.directedGraph(GraphData.SP);
        shortestPath.weighManager = manager;
        Map<Object, Double> paths = shortestPath.shortestPath("A");
        System.out.println(paths);
    }

    /**
     * 对于无向图来说，会将起点也加入进去，所以需要额外的逻辑进行判断
     * 1）返回之前，删除起点。
     * 2）添加的时候，进行判断，如果是起点就不添加。
     */
    @Test
    public void testShortestPath2() {
        Graph<Object, Double> shortestPath = GenerateGraph.undirectedGraph(GraphData.SP);
        shortestPath.weighManager = manager;
        Map<Object, Double> paths = shortestPath.shortestPath("A");
        System.out.println(paths);
    }

    /**
     * 测试路径的详细信息是否正确显示。
     */
    @Test
    public void testShortestPath3() {
        Graph<Object, Double> shortestPath = GenerateGraph.undirectedGraph(GraphData.SP);
        shortestPath.weighManager = manager;
        Map<Object, Graph.PathInfo<Object, Double>> paths = shortestPath.shortestPath2("A");
        System.out.println(paths);
    }

    /**
     * 测试路径的详细信息是否正确显示。
     */
    @Test
    public void testShortestPath4() {
        Graph<Object, Double> shortestPath = GenerateGraph.directedGraph(GraphData.NEGATIVE_WEIGHT1);
        shortestPath.weighManager = manager;
        Map<Object, Graph.PathInfo<Object, Double>> paths = shortestPath.shortestPath2("A");
        System.out.println(paths);
    }

    /**
     * 测试bellmanFord对负权环的检测。
     */
    @Test
    public void testShortestPath5() {
        Graph<Object, Double> shortestPath = GenerateGraph.directedGraph(GraphData.NEGATIVE_WEIGHT2);
        shortestPath.weighManager = manager;
        Map<Object, Graph.PathInfo<Object, Double>> paths = shortestPath.shortestPath2(0);
        System.out.println(paths);
    }

    @Test
    public void testFloyd() {
        testFloyd(GenerateGraph.directedGraph(GraphData.SP));
    }

    @Test
    public void testFloyd2() {
        testFloyd(GenerateGraph.directedGraph(GraphData.NEGATIVE_WEIGHT1));
    }

    @Test
    public void testFloyd3() {
        testFloyd(GenerateGraph.directedGraph(GraphData.NEGATIVE_WEIGHT1));

    }

    private void testFloyd(Graph<Object, Double> shortestPath) {
        shortestPath.weighManager = manager;
        Map<Object, Map<Object, Graph.PathInfo<Object, Double>>> floyd = shortestPath.floyd();
        floyd.forEach((from, objectPathInfoMap) -> {
            System.out.println(from + "---------------------");
            objectPathInfoMap.forEach((to, objectDoublePathInfo) -> {
                System.out.println(to + ",path" + objectDoublePathInfo);
            });
        });
    }


}
