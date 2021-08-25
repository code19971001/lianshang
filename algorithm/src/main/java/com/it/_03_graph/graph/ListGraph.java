package com.it._03_graph.graph;

import com.it.generic.GenericUnionFind;
import com.it.heap.MinHeap;
import com.it.tools.Prints;

import java.util.*;

/**
 * 有向图的实现：对于无向图也可以使用有向图来表示。
 *
 * @author : code1997
 * @date : 2021/4/8 22:10
 */
public class ListGraph<V, E> extends Graph<V, E> {

    private Map<V, Vertex<V, E>> vertices = new HashMap<>();
    private Set<Edge<V, E>> edges = new HashSet<>();

    private Comparator<Edge<V, E>> edgeComparator = (e1, e2) -> weighManager.compare(e1.weight, e2.weight);

    public ListGraph(WeighManager<E> weighManager) {
        super(weighManager);
    }

    public ListGraph() {

    }

    @Override
    public void print() {
        Prints.printStart();
        vertices.forEach((v, vertex) -> {
                    System.out.println(v);
                    System.out.println(vertex.inEdges);
                    System.out.println(vertex.outEdges);
                }
        );
        edges.forEach(System.out::println);
        Prints.printEnd();
    }

    @Override
    public void bfs(V begin) {
        Vertex<V, E> veVertex = vertices.get(begin);
        if (veVertex == null) {
            return;
        }
        HashSet<Vertex<V, E>> set = new HashSet<>();
        Queue<Vertex<V, E>> queue = new LinkedList<>();
        queue.offer(veVertex);
        set.add(veVertex);
        while (!queue.isEmpty()) {
            Vertex<V, E> poll = queue.poll();
            System.out.println(poll.value);
            poll.outEdges.forEach((veEdge -> {
                Vertex<V, E> to = veEdge.to;
                if (!set.contains(to)) {
                    queue.offer(to);
                    set.add(to);
                }
            }
            ));
        }
    }

    @Override
    public void bfs(V begin, VertexVisitor<V> visitor) {
        if (visitor == null) {
            return;
        }
        Vertex<V, E> veVertex = vertices.get(begin);
        if (veVertex == null) {
            return;
        }
        HashSet<Vertex<V, E>> set = new HashSet<>();
        Queue<Vertex<V, E>> queue = new LinkedList<>();
        queue.offer(veVertex);
        set.add(veVertex);
        while (!queue.isEmpty()) {
            Vertex<V, E> poll = queue.poll();
            if (visitor.visit(poll.value)) {
                return;
            }
            poll.outEdges.forEach((veEdge -> {
                Vertex<V, E> to = veEdge.to;
                if (!set.contains(to)) {
                    queue.offer(to);
                    set.add(to);
                }
            }
            ));
        }

    }


    @Override
    public void dfs(V begin) {
        Vertex<V, E> veVertex = vertices.get(begin);
        if (veVertex == null) {
            return;
        }
        dfs(veVertex, new HashSet<>());
    }

    @Override
    public void dfs(V begin, VertexVisitor<V> visitor) {
        if (visitor == null) {
            return;
        }
        Vertex<V, E> veVertex = vertices.get(begin);
        if (veVertex == null) {
            return;
        }
        Set<Vertex<V, E>> visitedVertices = new HashSet<>();
        Stack<Vertex<V, E>> stack = new Stack<>();
        //先访问起点
        stack.push(veVertex);
        if (visitor.visit(veVertex.value)) {
            return;
        }
        while (!stack.isEmpty()) {
            Vertex<V, E> curVertex = stack.pop();
            for (Edge<V, E> outEdge : curVertex.outEdges) {
                if (visitedVertices.contains(outEdge.to)) {
                    continue;
                }
                stack.push(outEdge.from);
                stack.push(outEdge.to);
                visitedVertices.add(outEdge.from);
                visitedVertices.add(outEdge.to);
                if (visitor.visit(outEdge.to.value)) {
                    return;
                }
                break;
            }
        }
    }

    @Override
    public void dfs2(V begin) {
        Vertex<V, E> veVertex = vertices.get(begin);
        if (veVertex == null) {
            return;
        }
        Set<Vertex<V, E>> visitedVertices = new HashSet<>();
        Stack<Vertex<V, E>> stack = new Stack<>();
        //先访问起点
        stack.push(veVertex);
        System.out.println(veVertex.value);
        while (!stack.isEmpty()) {
            Vertex<V, E> curVertex = stack.pop();
            for (Edge<V, E> outEdge : curVertex.outEdges) {
                if (visitedVertices.contains(outEdge.to)) {
                    continue;
                }
                stack.push(outEdge.from);
                stack.push(outEdge.to);
                visitedVertices.add(outEdge.from);
                visitedVertices.add(outEdge.to);
                System.out.println(outEdge.to.value);
                break;
            }
        }
    }

    private void dfs(Vertex<V, E> vertex, Set<Vertex<V, E>> visitedVertices) {
        System.out.println(vertex.value);
        visitedVertices.add(vertex);
        vertex.outEdges.forEach(veEdge -> {
            //需要判断曾经是否遍历过、
            if (visitedVertices.contains(veEdge.to)) {
                return;
            }
            dfs(veEdge.to, visitedVertices);
        });

    }

    /**
     * 实现拓扑排序:
     * 1)遍历图，维持一张表：顶点，入度。
     * 2）获取入度为0的顶点，将其加入到队列中，维护表
     */
    @Override
    public List<V> topologicalSort() {
        List<V> list = new ArrayList<>();
        Queue<Vertex<V, E>> queue = new LinkedList<>();
        Map<Vertex<V, E>, Integer> record = new HashMap<>();
        vertices.forEach((V v, Vertex<V, E> vertex) -> {
            if (vertex.inEdges.isEmpty()) {
                queue.offer(vertex);
            } else {
                record.put(vertex, vertex.inEdges.size());
            }
        });

        while (!queue.isEmpty()) {
            Vertex<V, E> poll = queue.poll();
            list.add(poll.value);
            //遍历outEdges.to。in
            poll.outEdges.forEach(veEdge -> {
                int size = record.get(veEdge.to) - 1;
                if (size == 0) {
                    queue.offer(veEdge.to);
                } else {
                    record.put(veEdge.to, size);
                }
            });
        }
        return list;
    }

    @Override
    public Set<EdgeInfo<V, E>> mst() {
        return kruskal();
    }

    @Override
    public Map<V, E> shortestPath(V begin) {
        return dijkstra1(begin);
    }

    private Map<V, E> dijkstra1(V begin) {
        Vertex<V, E> vertex = vertices.get(begin);
        if (vertex == null) {
            return null;
        }
        Map<Vertex<V, E>, E> paths = new HashMap<>();
        Map<V, E> selectedPaths = new HashMap<>();

        //初始化paths
        vertex.outEdges.forEach(veEdge -> paths.put(veEdge.to, veEdge.weight));

        while (!paths.isEmpty()) {
            Map.Entry<Vertex<V, E>, E> minPath = getMinPath(paths);

            selectedPaths.put(minPath.getKey().value, minPath.getValue());
            paths.remove(minPath.getKey());
            //更新paths
            minPath.getKey().outEdges.forEach(veEdge -> {
                //如果veEdge.to已经离开桌面，那么就没有松弛的意义
                if (selectedPaths.containsKey(veEdge.to.value) || veEdge.to.value.equals(begin)) {
                    return;
                }
                //松弛操作:relax
                E newWeight = weighManager.add(minPath.getValue(), veEdge.weight);
                E oldWeight = paths.get(veEdge.to);
                //?如果get出来是null如何？？
                if (oldWeight == null || weighManager.compare(newWeight, oldWeight) < 0) {
                    paths.put(veEdge.to, newWeight);
                }
            });

        }
        return selectedPaths;
    }

    private Map.Entry<Vertex<V, E>, E> getMinPath(Map<Vertex<V, E>, E> paths) {
        Iterator<Map.Entry<Vertex<V, E>, E>> iterator = paths.entrySet().iterator();
        Map.Entry<Vertex<V, E>, E> minEntry = iterator.next();
        while (iterator.hasNext()) {
            Map.Entry<Vertex<V, E>, E> next = iterator.next();
            if (weighManager.compare(minEntry.getValue(), next.getValue()) > 0) {
                minEntry = next;
            }
        }
        return minEntry;
    }

    @Override
    public Map<V, PathInfo<V, E>> shortestPath2(V begin) {
        return bellmanFord(begin);
    }

    public Map<V, PathInfo<V, E>> dijkstra2(V begin) {
        Vertex<V, E> vertex = vertices.get(begin);
        if (vertex == null) {
            return null;
        }

        Map<V, PathInfo<V, E>> selectedPaths = new HashMap<>();
        Map<Vertex<V, E>, PathInfo<V, E>> paths = new HashMap<>();

        //init paths:实际上也是做松弛的操作。
        vertex.outEdges.forEach(veEdge -> {
            PathInfo<V, E> pathInfo = new PathInfo<>(veEdge.weight);
            pathInfo.getPathEdgeInfos().add(veEdge.edgeInfo());
            paths.put(veEdge.to, pathInfo);
        });
        while (!paths.isEmpty()) {
            Map.Entry<Vertex<V, E>, PathInfo<V, E>> minPath = getMinPath2(paths);
            selectedPaths.put(minPath.getKey().value, minPath.getValue());
            paths.remove(minPath.getKey());
            //update paths
            minPath.getKey().outEdges.forEach(veEdge -> {
                if (selectedPaths.containsKey(veEdge.to.value) || veEdge.to.value.equals(begin)) {
                    return;
                }
                relax(veEdge, paths, minPath.getValue());

            });
        }
        return selectedPaths;
    }

    /**
     * 松弛操作
     *
     * @param veEdge       需要松弛的边
     * @param paths        最短路径的集合
     * @param minPathValue 最小路径的值
     */
    private void relax(Edge<V, E> veEdge, Map<Vertex<V, E>, PathInfo<V, E>> paths, PathInfo<V, E> minPathValue) {
        E newWeight = weighManager.add(minPathValue.getWeight(), veEdge.weight);
        //control null point exception
        PathInfo<V, E> info = paths.get(veEdge.to);
        if (info != null && weighManager.compare(newWeight, info.getWeight()) >= 0) {
            return;
        }
        if (info == null) {
            info = new PathInfo<>();
            paths.put(veEdge.to, info);
        } else {
            info.getPathEdgeInfos().clear();
        }
        info.setWeight(newWeight);
        List<EdgeInfo<V, E>> pathEdgeInfos = info.getPathEdgeInfos();
        //如果info!=null,则需要将源点到当前节点的最短路径获取到
        pathEdgeInfos.addAll(minPathValue.getPathEdgeInfos());
        pathEdgeInfos.add(veEdge.edgeInfo());
        //paths.put(veEdge.to, info);
    }

    /**
     * TODO: 2021/4/19  : 使用堆来实现集合中求最短路径
     */
    private Map.Entry<Vertex<V, E>, PathInfo<V, E>> getMinPath2(Map<Vertex<V, E>, PathInfo<V, E>> paths) {
        Iterator<Map.Entry<Vertex<V, E>, PathInfo<V, E>>> iterator = paths.entrySet().iterator();
        Map.Entry<Vertex<V, E>, PathInfo<V, E>> minEntry = iterator.next();
        while (iterator.hasNext()) {
            Map.Entry<Vertex<V, E>, PathInfo<V, E>> next = iterator.next();
            if (weighManager.compare(minEntry.getValue().getWeight(), next.getValue().getWeight()) > 0) {
                minEntry = next;
            }
        }
        return minEntry;
    }


    /**
     * bellmanFord:对所有的边进行V-1次松弛操作，那么就可以得到所有的最短路径,因为每一轮的松弛都可以最少得到一个点的最短路径。
     * 特点：
     * > 允许存在负权边。
     * > 可以检测出负权环。
     * 时间复杂度：O(EV)，E为边的数量，V为节点数量。
     * 负权边检测：增加
     */
    private Map<V, PathInfo<V, E>> bellmanFord(V begin) {
        Vertex<V, E> vertex = vertices.get(begin);
        if (vertex == null) {
            return null;
        }
        Map<V, PathInfo<V, E>> selectedPaths = new HashMap<>();
        //init selectedPath:起点的初始化值为zero
        selectedPaths.put(begin, new PathInfo<V, E>(weighManager.zero()));
        int loopCount = vertices.size() - 1;
        for (int i = 0; i < loopCount; i++) {
            for (Edge<V, E> edge : edges) {
                PathInfo<V, E> fromPath = selectedPaths.get(edge.from.value);
                //对每一个边进行松弛操作
                if (fromPath == null) {
                    continue;
                }
                relaxForBellmanFord(edge, selectedPaths, fromPath);
            }
        }
        for (Edge<V, E> edge : edges) {
            PathInfo<V, E> fromPath = selectedPaths.get(edge.from.value);
            //对每一个边进行松弛操作
            if (fromPath == null) {
                continue;
            }
            if (relaxForBellmanFord(edge, selectedPaths, fromPath)) {
                System.out.println("存在负权环");
                return null;
            }
        }
        selectedPaths.remove(begin);
        return selectedPaths;
    }

    /**
     * 松弛操作
     *
     * @param veEdge       需要松弛的边
     * @param paths        最短路径的集合
     * @param minPathValue 最小路径的值
     */
    private boolean relaxForBellmanFord(Edge<V, E> veEdge, Map<V, PathInfo<V, E>> paths, PathInfo<V, E> minPathValue) {
        E newWeight = weighManager.add(minPathValue.getWeight(), veEdge.weight);
        //control null point exception
        PathInfo<V, E> info = paths.get(veEdge.to.value);
        if (info != null && weighManager.compare(newWeight, info.getWeight()) >= 0) {
            return false;
        }
        if (info == null) {
            info = new PathInfo<>();
            paths.put(veEdge.to.value, info);
        } else {
            info.getPathEdgeInfos().clear();
        }
        info.setWeight(newWeight);
        List<EdgeInfo<V, E>> pathEdgeInfos = info.getPathEdgeInfos();
        //如果info!=null,则需要将源点到当前节点的最短路径获取到
        pathEdgeInfos.addAll(minPathValue.getPathEdgeInfos());
        pathEdgeInfos.add(veEdge.edgeInfo());
        paths.put(veEdge.to.value, info);
        return true;
    }

    /**
     * 属于多源最短路径算法，能够求出任意2个顶点之间的最短路径。
     * 时间复杂度为：O(v^3)，效率比执行Dijkstra算法要好,V为顶点的数量。
     * 注：时刻判断是否存在指定的路
     */
    @Override
    public Map<V, Map<V, PathInfo<V, E>>> floyd() {
        Map<V, Map<V, PathInfo<V, E>>> paths = new HashMap<>();
        edges.forEach(veEdge -> {
            Map<V, PathInfo<V, E>> infoMap = paths.get(veEdge.from.value);
            if (infoMap == null) {
                infoMap = new HashMap<>();
                paths.put(veEdge.from.value, infoMap);
            }
            PathInfo<V, E> pathInfo = new PathInfo<>(veEdge.weight);
            pathInfo.getPathEdgeInfos().add(veEdge.edgeInfo());
            infoMap.put(veEdge.to.value, pathInfo);
        });

        vertices.forEach((v2, veVertex2) -> {
            vertices.forEach((v1, veVertex1) -> {
                vertices.forEach((v3, veVertex3) -> {
                    if (v1.equals(v2) || v2.equals(v3) || v1.equals(v3)) {
                        return;
                    }

                    //v1-v2的路径信息
                    PathInfo<V, E> path12 = getPathInfo(v1, v2, paths);
                    if (path12 == null) {
                        return;
                    }
                    PathInfo<V, E> path23 = getPathInfo(v2, v3, paths);
                    if (path23 == null) {
                        return;
                    }
                    PathInfo<V, E> path13 = getPathInfo(v1, v3, paths);
                    E newWeight = weighManager.add(path12.getWeight(), path23.getWeight());
                    if (path13 != null && weighManager.compare(newWeight, path13.getWeight()) >= 0) {
                        return;
                    }
                    if (path13 == null) {
                        PathInfo<V, E> pathInfo = new PathInfo<>(newWeight);
                        paths.get(v1).put(v3, pathInfo);
                    } else {
                        path13.setWeight(newWeight);
                    }
                    //注：：：
                    path13 = getPathInfo(v1, v3, paths);
                    List<EdgeInfo<V, E>> pathEdgeInfos = path13.getPathEdgeInfos();
                    pathEdgeInfos.clear();
                    pathEdgeInfos.addAll(path12.getPathEdgeInfos());
                    pathEdgeInfos.addAll(path23.getPathEdgeInfos());
                });
            });
        });

        return paths;
    }

    private PathInfo<V, E> getPathInfo(V from, V to, Map<V, Map<V, PathInfo<V, E>>> paths) {
        Map<V, PathInfo<V, E>> map = paths.get(from);
        return map == null ? null : map.get(to);
    }


    /**
     * 基于prim算法实现的最小生成树，
     * 1）拿到一个顶点，将outEdges加入到最小堆。
     * 2）将边放到set数据集中(最小生成树)。
     *
     * @return ：最小生成树集合。
     */
    private Set<EdgeInfo<V, E>> prim() {
        Iterator<Vertex<V, E>> iterator = vertices.values().iterator();
        if (!iterator.hasNext()) {
            return new HashSet<>();
        }
        Set<EdgeInfo<V, E>> edgeInfos = new HashSet<>();
        Set<Vertex<V, E>> addedVertices = new HashSet<>();
        Vertex<V, E> begin = iterator.next();
        addedVertices.add(begin);
        //使用堆来进行操作，因为java官方的PriorityQueue无法在传入比较器的同时传入建堆的元素，因此这里可以自己手动实现一个堆。
        MinHeap<Edge<V, E>> minHeap = new MinHeap<>(begin.outEdges, edgeComparator);
        int verticesSize = vertices.size();
        while (!minHeap.isEmpty() && addedVertices.size() < verticesSize) {
            Edge<V, E> edge = minHeap.remove();
            if (addedVertices.contains(edge.to)) {
                continue;
            }
            edgeInfos.add(edge.edgeInfo());
            //注意要避免重复添加
            addedVertices.add(edge.to);
            minHeap.addAll(edge.to.outEdges);
        }

        return edgeInfos;
    }

    /**
     * 按照边的权重顺序(从小到大)将边加入到生成树中，直到生成树中含有V-1条边位置(V是顶点数量)。
     * 使用并查集来判断是否有环：如果两个顶点本来就在一个集合，那么如果再次添加就会构成环。
     * 因此时间复杂度为：O(ElogE).
     *
     * @return ： 最小生成树集合。
     */
    private Set<EdgeInfo<V, E>> kruskal() {
        int edgeSize = vertices.size() - 1;
        if (edgeSize == -1) {
            return null;
        }
        HashSet<EdgeInfo<V, E>> edgeInfos = new HashSet<>();
        //O(E)
        MinHeap<Edge<V, E>> heap = new MinHeap<>(edges, edgeComparator);
        GenericUnionFind<Vertex<V, E>> uf = new GenericUnionFind<>();
        //O(V)
        vertices.forEach((v, veVertex) ->
                uf.makeSet(veVertex)
        );
        //最坏的情况下，堆中元素空了。所以是O(E)级别
        while (!heap.isEmpty() && edgeInfos.size() < edgeSize) { //E
            //O(logE)
            Edge<V, E> curEdge = heap.remove();
            //如果构成环，则跳过添加
            //O(α(n)),基本是常数级别的
            if (uf.isSame(curEdge.from, curEdge.to)) {
                continue;
            }
            edgeInfos.add(curEdge.edgeInfo());
            uf.union(curEdge.from, curEdge.to);
        }
        return edgeInfos;
    }


    public void test() {
        Edge<String, Integer> veEdge1 = new Edge<>(new Vertex<>("v1"), new Vertex<>("v2"));
        Edge<String, Integer> veEdge2 = new Edge<>(new Vertex<>("v1"), new Vertex<>("v2"));
        veEdge2.weight = 100;
        System.out.println("---->" + veEdge1.equals(veEdge2));
        HashSet<Edge<String, Integer>> edgeSet = new HashSet<>();
        edgeSet.add(veEdge1);
        edgeSet.add(veEdge2);
        edgeSet.forEach(System.out::println);

    }

    @Override
    public int edgesSize() {
        return edges.size();
    }

    @Override
    public int verticesSize() {
        return vertices.size();
    }

    @Override
    public void addVertex(V v) {

        if (vertices.containsKey(v)) {
            return;
        }
        vertices.put(v, new Vertex<>(v));

    }

    @Override
    public void addEdge(V from, V to) {
        addEdge(from, to, null);
    }

    @Override
    public void addEdge(V from, V to, E weight) {
        //无论该结点是否存在都直接添加
        addVertex(from);
        addVertex(to);
        //来到这里意味着from和to一定存在,那么我们需要添加边信息。
        Vertex<V, E> fromVertex = vertices.get(from);
        Vertex<V, E> toVertex = vertices.get(to);
        Edge<V, E> veEdge = new Edge<>(fromVertex, toVertex);
        veEdge.weight = weight;
        //直接覆盖即可
        if (fromVertex.outEdges.contains(veEdge)) {
            fromVertex.outEdges.remove(veEdge);
            edges.remove(veEdge);
        }
        if (toVertex.inEdges.contains(veEdge)) {
            toVertex.inEdges.remove(veEdge);
            edges.remove(veEdge);
        }
        fromVertex.outEdges.add(veEdge);
        toVertex.inEdges.add(veEdge);
        //添加到edges中
        edges.add(veEdge);

    }

    /**
     * 删除顶点需要删除所有相关的边
     */
    @Override
    public void removeVertex(V v) {
        Vertex<V, E> removeVertex = vertices.remove(v);
        //删除包含vertex的边
        if (removeVertex == null) {
            return;
        }
        //遍历入度和出度。使用迭代器对象来进行删除
        Iterator<Edge<V, E>> outIterator = removeVertex.outEdges.iterator();
        while (outIterator.hasNext()) {
            Edge<V, E> next = outIterator.next();
            next.to.inEdges.remove(next);
            outIterator.remove();
            edges.remove(next);
        }
        Iterator<Edge<V, E>> inIterator = removeVertex.inEdges.iterator();
        while (inIterator.hasNext()) {
            Edge<V, E> next = inIterator.next();
            next.from.outEdges.remove(next);
            inIterator.remove();
            edges.remove(next);
        }
    }

    @Override
    public void removeEdge(V from, V to) {
        Vertex<V, E> fromVertex = vertices.get(from);
        if (fromVertex == null) {
            return;
        }
        Vertex<V, E> toVertex = vertices.get(to);
        if (toVertex == null) {
            return;
        }
        Edge<V, E> veEdge = new Edge<>(fromVertex, toVertex);
        if (fromVertex.outEdges.remove(veEdge)) {
            toVertex.inEdges.remove(veEdge);
            edges.remove(veEdge);
        }

    }

    static class Vertex<V, E> {
        V value;
        Set<Edge<V, E>> inEdges = new HashSet<>();
        Set<Edge<V, E>> outEdges = new HashSet<>();

        public Vertex(V v) {
            this.value = v;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof Vertex)) {
                return false;
            }
            Vertex<V, E> vertex = (Vertex<V, E>) o;
            return Objects.equals(value, vertex.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(value);
        }

        @Override
        public String toString() {
            return value == null ? "null" : value.toString();
        }
    }

    static class Edge<V, E> {
        Vertex<V, E> from;
        Vertex<V, E> to;
        E weight;

        EdgeInfo<V, E> edgeInfo() {
            return new EdgeInfo<>(from.value, to.value, weight);
        }

        public Edge(Vertex<V, E> from, Vertex<V, E> to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof Edge)) {
                return false;
            }
            Edge<V, E> edge = (Edge<V, E>) o;
            return Objects.equals(from, edge.from) && Objects.equals(to, edge.to);
        }

        @Override
        public int hashCode() {
            return Objects.hash(from, to);
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "from=" + from +
                    ", to=" + to +
                    ", weight=" + weight +
                    '}';
        }

    }
}
