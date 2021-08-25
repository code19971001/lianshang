package com.it._05_greedy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 0-1背包问题
 *
 * @author : code1997
 * @date : 2021/4/22 22:34
 */
public class _0_1_package {
    public static void main(String[] args) {
        //下标为物品编号-1，第一个参数是重量，第二个参数价值。
        Article[] articles = {
                new Article(1, 35, 10),
                new Article(2, 30, 40),
                new Article(3, 60, 30),
                new Article(4, 50, 50),
                new Article(5, 40, 35),
                new Article(6, 10, 40),
                new Article(7, 25, 30)
        };
        Arrays.sort(articles, (article1, article2) -> Double.compare(article2.valueDensity, article1.valueDensity));
        System.out.println("价值密度主导");
        articlePackage(articles);
        Arrays.sort(articles, Comparator.comparingInt(article -> article.weight));
        System.out.println("重量主导");
        articlePackage(articles);
        Arrays.sort(articles, Comparator.comparingInt(article -> -article.value));
        System.out.println("重量主导");
        articlePackage(articles);

    }

    private static void articlePackage(Article[] articles) {
        Arrays.stream(articles).forEach(System.out::println);

        int load = 150;
        int index = 0;
        int totalValue = 0;
        while (load >= 0 && index < articles.length) {
            if (load < articles[index].weight) {
                index++;
                continue;
            }
            load -= articles[index].weight;
            System.out.println("将物品：" + articles[index].id + "装入到背包中去");
            totalValue += articles[index].value;
            index++;

        }
        System.out.println("总共装了：" + index + "件物品到背包中去,总价值为：" + totalValue);
    }

    static class Article {
        int id;
        int weight;
        int value;
        double valueDensity;

        public Article(int id, int weight, int value) {
            this.id = id;
            this.weight = weight;
            this.value = value;
            this.valueDensity = value * 1.0 / weight;
        }

        @Override
        public String toString() {
            return "Article{" +
                    "id=" + id +
                    ", weight=" + weight +
                    ", value=" + value +
                    ", valueDensity=" + valueDensity +
                    '}';
        }
    }
}
