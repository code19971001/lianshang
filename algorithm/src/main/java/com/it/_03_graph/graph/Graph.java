package com.it._03_graph.graph;


import java.util.*;

/**
 * @author : code1997
 * @date : 2021/4/8 21:58
 */
public abstract class Graph<V, E> {

    protected WeighManager<E> weighManager;

    public Graph(WeighManager weighManager) {
        this.weighManager = weighManager;
    }

    public Graph() {

    }

    public interface WeighManager<E> {
        int compare(E w1, E w2);

        E add(E w1, E w2);

        E zero();

    }

    /**
     * 获取边的数量
     *
     * @return : 边的数量
     */
    public abstract int edgesSize();

    /**
     * 获取顶点的数量
     *
     * @return : 顶点数量
     */
    public abstract int verticesSize();

    /**
     * 添加一个顶点
     *
     * @param v :顶点
     */
    public abstract void addVertex(V v);

    /**
     * 添加一条边。
     *
     * @param from 起始点。
     * @param to   终点
     */
    public abstract void addEdge(V from, V to);

    /**
     * 增加一条边。
     *
     * @param from   起始点
     * @param to     终止点
     * @param weight 权重
     */
    public abstract void addEdge(V from, V to, E weight);

    /**
     * 删除顶点
     *
     * @param v 顶点
     */
    public abstract void removeVertex(V v);

    /**
     * 移除边
     *
     * @param from 起始点
     * @param to   终点
     */
    public abstract void removeEdge(V from, V to);

    /**
     * 展示图的结构
     */
    public abstract void print();

    /**
     * 广度优先搜索
     * eg:二叉树的层序遍历。
     */
    public abstract void bfs(V begin);

    /**
     * @param begin   ：图的起点
     * @param visitor ：实际上我们需要进行操作。
     */
    public abstract void bfs(V begin, VertexVisitor<V> visitor);

    /**
     * 深度优先搜索：顺着一条路，一直往下走。
     * eg:二叉树的前序遍历。
     */
    public abstract void dfs(V begin);

    /**
     * @param begin   ：图的起点
     * @param visitor ：实际上我们需要进行操作。
     */
    public abstract void dfs(V begin, VertexVisitor<V> visitor);

    /**
     * 深度优先搜索：使用迭代的方式实现。
     */
    public abstract void dfs2(V begin);

    /**
     * 必须是有向无环图。
     *
     * @return
     */
    public abstract List<V> topologicalSort();

    interface VertexVisitor<V> {
        boolean visit(V v);
    }

    public abstract Set<EdgeInfo<V, E>> mst();

    public static class EdgeInfo<V, E> {
        private V from;
        private V to;
        private E weigtht;

        public EdgeInfo(V from, V to, E weigtht) {
            this.from = from;
            this.to = to;
            this.weigtht = weigtht;
        }

        public V getFrom() {
            return from;
        }

        public void setFrom(V from) {
            this.from = from;
        }

        public V getTo() {
            return to;
        }

        public void setTo(V to) {
            this.to = to;
        }

        public E getWeigtht() {
            return weigtht;
        }

        public void setWeigtht(E weigtht) {
            this.weigtht = weigtht;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof EdgeInfo)) {
                return false;
            }
            EdgeInfo<?, ?> edgeInfo = (EdgeInfo<?, ?>) o;
            return Objects.equals(getFrom(), edgeInfo.getFrom()) && Objects.equals(getTo(), edgeInfo.getTo());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getFrom(), getTo());
        }

        @Override
        public String toString() {
            return "EdgeInfo{" +
                    "from=" + from +
                    ", to=" + to +
                    ", weigtht=" + weigtht +
                    '}';
        }
    }

    /**
     * 给定一个起点，返回源点到其他顶点之间的最短路径值。
     *
     * @param begin : 最短路径的源点。
     * @return : 源点到其他顶点的最短路径
     */
    protected abstract Map<V, E> shortestPath(V begin);

    protected abstract Map<V, PathInfo<V, E>> shortestPath2(V begin);

    protected abstract Map<V, Map<V, PathInfo<V, E>>> floyd();


    public static class PathInfo<V, E> {

        private List<EdgeInfo<V, E>> pathEdgeInfos = new LinkedList<>();
        private E weight;

        public PathInfo() {
        }

        public PathInfo(E weight) {
            this.weight = weight;
        }

        public List<EdgeInfo<V, E>> getPathEdgeInfos() {
            return pathEdgeInfos;
        }

        public void setPathEdgeInfos(List<EdgeInfo<V, E>> pathEdgeInfos) {
            this.pathEdgeInfos = pathEdgeInfos;
        }

        public E getWeight() {
            return weight;
        }

        public void setWeight(E weight) {
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "PathInfo{" +
                    "weight=" + weight +
                    ", pathEdgeInfos=" + pathEdgeInfos +
                    '}';
        }
    }


}
