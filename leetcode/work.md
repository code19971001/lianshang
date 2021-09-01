## Week1

### 1 字符串

##### 1 翻转字符串里的单词(151)

描述：给定一个字符串，逐个翻转字符串中的每个单词。

link：https://leetcode-cn.com/problems/reverse-words-in-a-string/

实现步骤：

1. 消除字符串中的多余空格。
2. 将整个字符串进行位置转换
3. 给一个范围，将这个范围内的字符串进行逆序。

```java
public class Test1 {
    public static void main(String[] args) {
        System.out.println("反转之后的字符串："+reverseWords("1234 456789"));
        System.out.println("反转之后的字符串："+reverseWords("  hello world!  "));
        System.out.println("反转之后的字符串："+reverseWords("a good   example"));
        System.out.println("反转之后的字符串："+reverseWords("are you ok"));
        System.out.println();
    }
    public static String reverseWords(String s){
        if (s==null || s==""){
            return "";
        }
        char[] chars = s.toCharArray();
        //存储字符存放的位置
        int cur=0;
        //存放字符串最终的有效长度
        int len=0;
        int count=0;
        //可以规避空格
        boolean spaceFlag=true;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i]!=' '){
                chars[cur++]=chars[i];
                spaceFlag=false;
            }else if (!spaceFlag){
                spaceFlag=true;
                chars[cur++]=chars[i];
            }
        }
        //spaceFlag为true，则代表字符串最后存在空字符
        int len=spaceFlag?cur-1:cur;
        if (len<=0){
            return "";
        }
        reverse(chars,0,len);
        //记录上一个非空格的所以位置（假想的哨兵）
        int spaceIndex=-1;
        for (int i = 0; i < len; i++) {
            if (chars[i]==' '){
                //最后一个单词没有翻转成功
                reverse(chars,spaceIndex+1,i);
                spaceIndex=i;
            }
        }
        //需要单独的反转最后一个单词
        reverse(chars,spaceIndex+1,len);
        return new String(chars,0,len);
    }

    /**
     * 将数组中指定范围的元素进行交换
     * @param chars :待逆序的数组
     * @param begin :开始索引
     * @param end :结束索引
     */
    private static void reverse(char[] chars,int begin,int end){
        char temp;
        for (int i = begin,j=end-1; i<j ; i++,j--) {
            temp=chars[i];
            chars[i]=chars[j];
            chars[j]=temp;
        }
    }
}
```

##### 2 无重复字符的最长字串(4)

给定一个字符串，请找出其中不含重复字符的最长字串。

link：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/

解题思路：

![image-20210816235117786](https://gitee.com/code1997/blog-image/raw/master/lianshang/lianshang03/images/image-20210816235117786.png)

- 求出以所有字符结尾的最长字串的长度（想到hashmap）。
- 求字串的时候我们需要几个变量。pi和li，如图存在pi和li存在三种关系，分别对其进行分析。
  - 如果li>pi，则表明，i结尾的最长字串度为【li+1，i】
  - 如果li=pi，则表明，i结尾的最长字串为【pi+1，i】
  - 如果li<pi，则表明，i结尾最长字串为【pi+1，i】

代码实现：使用hashmap存储某个字符上次出现的index

```java
    /**
     * 求出最长不重复字串的长度
     */
    public static int lengthOfLongestSubstring(String s) {
        if (s == null || "".equalsIgnoreCase(s)) {
            return 0;
        }
        char[] chars = s.toCharArray();
        int maxSubstrLen = 1;
        //当前字符上一次出现的位置
        Map<Character, Integer> preIndex = new HashMap<>();
        preIndex.put(chars[0], 0);
        //i-1位置的字符结尾的最长不重复字串的开始索引，也就是最左索引。
        int li = 0;
        for (int i = 1; i < chars.length; i++) {
            //获取该字符字符上一次出现的位置,如果key不存在就返回-1
            Integer pi = preIndex.getOrDefault(chars[i], -1);
            if (li <= pi) {
                //pi不等与-1并且li<=pi
                li = pi + 1;
            }
            maxSubstrLen = Math.max(i - li + 1, maxSubstrLen);
            //更新当前字符的index
            preIndex.put(chars[i], i);
        }
        return maxSubstrLen;
    }
```

优化1：假设全是小写英文字符

```java
    /**
     * 优化：假设传入的字符串只是小写英文字母，因此我们可以将hashmap转化为26长度的数组
     */
    public static int lengthOfLongestSubstring2(String s) {
        if (s == null || "".equalsIgnoreCase(s)) {
            return 0;
        }
        char[] chars = s.toCharArray();
        int maxSubstrLen = 1;
        //当前字符上一次出现的位置
        int[] preIndex = new int[26];
        preIndex[chars[0] - 'a'] = 0;
        //i-1位置的字符结尾的最长不重复字串的开始索引，也就是最左索引。
        int li = 0;
        for (int i = 1; i < chars.length; i++) {
            //获取该字符字符上一次出现的位置,如果key不存在就返回-1
            int pi = preIndex[chars[i] - 'a'];
            if (li <= pi) {
                //pi不等与-1并且li<=pi
                li = pi + 1;
            }
            maxSubstrLen = Math.max(i - li + 1, maxSubstrLen);
            //更新当前字符的index
            preIndex[chars[i] - 'a'] = i;
        }
        return maxSubstrLen;
    }
```

优化2：假设全是单字节字母

```java
    /**
     * 优化：假设字符串只是单字节字符，因此我们可以使用ascll码值来进行对比
     */
    public static int lengthOfLongestSubstring3(String s) {
        if (s == null || "".equalsIgnoreCase(s)) {
            return 0;
        }
        char[] chars = s.toCharArray();
        int maxSubstrLen = 1;
        //当前字符上一次出现的位置
        int[] preIndex = new int[128];
        Arrays.fill(preIndex, -1);
        preIndex[chars[0]] = 0;
        //i-1位置的字符结尾的最长不重复字串的开始索引，也就是最左索引。
        int li = 0;
        for (int i = 1; i < chars.length; i++) {
            //获取该字符字符上一次出现的位置,如果key不存在就返回-1
            int pi = preIndex[chars[i]];
            if (li <= pi) {
                //pi不等与-1并且li<=pi
                li = pi + 1;
            }
            maxSubstrLen = Math.max(i - li + 1, maxSubstrLen);
            //更新当前字符的index
            preIndex[chars[i]] = i;
        }
        return maxSubstrLen;
    }
```

##### 3 最长公共子序列（1143）



##### 4 最长有效括号（32）



##### 5 最长字符串链（1048 ）



### 2 动态规划

##### 1 礼物的最大价值

描述：在一个 m*n 的棋盘的每一格都放有一个礼物，每个礼物都有一定的价值（价值大于 0）。你可以从棋盘的左上角开始拿格子里的礼物，并每次向右或者向下移动一格、直到到达棋盘的右下角。给定一个棋盘及其上面的礼物的价值，请计算你最多能拿到多少价值的礼物？
link：https://leetcode-cn.com/problems/li-wu-de-zui-da-jie-zhi-lcof

题解：

```java
public static int maxValue(int[][] grid) {
    int[][] dp = new int[grid.length][grid[0].length];
    //初始化dp
    dp[0][0] = grid[0][0];
    for (int i = 1; i < grid[0].length; i++) {
        dp[0][i] = dp[0][i - 1] + grid[0][i];
    }
    for (int i = 1; i < grid.length; i++) {
        dp[i][0] = dp[i - 1][0] + grid[i][0];
    }
    for (int i = 1; i < grid.length; i++) {
        for (int j = 1; j < grid[i].length; j++) {
            dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
        }
    }
    return dp[grid.length - 1][grid[0].length - 1];
}

/**
     * 优化1：使用单行数组来代替dp的二维数组
     */
public static int maxValue2(int[][] grid) {
    int[] dp = new int[grid[0].length];
    //初始化dp
    dp[0] = grid[0][0];
    for (int i = 1; i < grid[0].length; i++) {
        dp[i] = dp[i - 1] + grid[0][i];
    }
    for (int i = 1; i < grid.length; i++) {
        //初始化每行的第一个值
        dp[0] = dp[0] + grid[i][0];
        for (int j = 1; j < grid[i].length; j++) {
            dp[j] = Math.max(dp[j - 1], dp[j]) + grid[i][j];
        }
    }
    return dp[grid[0].length - 1];
}
```

##### 2 买卖股票的最佳时机

描述：假设把某个股票的价格按照时间的先后顺序存储在数组中，请问买卖该股票一次可以获得的最大利润是多少？

题解：

```java

/**
 * 使用两个变量分别用于记录历史股票最低价以及最大利润
 */
public static int maxProfit(int[] prices) {
    if (prices == null || prices.length == 0) {
        return 0;
    }
    int minPrice = prices[0];
    int maxValue = 0;
    for (int i = 1; i < prices.length; i++) {
        if (prices[i] >= minPrice) {
            maxValue = Math.max(maxValue, prices[i] - minPrice);
        } else {
            minPrice = prices[i];
        }
    }
    return maxValue;
}


    /**
     * 使用动态规划的解决：定义一个数组来记录，相邻两天买卖的利润.然后求最大子序列和.
     * 注意：只是一种思路，时间复杂度并不低。O(n)
     */
    public static int maxProfit2(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int[] dp = new int[prices.length];
        dp[0] = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            dp[i] = prices[i] - prices[i - 1];
        }
        System.out.println(Arrays.toString(dp));
        //求最大连续子序列的和
        int initDp = dp[0];
        int maxValue = 0;
        for (int i = 1; i < dp.length; i++) {
            if (initDp <= 0) {
                initDp = dp[i];
            } else {
                initDp += dp[i];
            }
            maxValue = Math.max(initDp, maxValue);
        }
        return maxValue;
    }
```

##### 3 编辑距离

给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数 。

你可以对一个单词进行如下三种操作：

- 插入一个字符
- 删除一个字符
- 替换一个字符

link：https://leetcode-cn.com/problems/edit-distance/

图解：

![image-20210817234028746](https://gitee.com/code1997/blog-image/raw/master/lianshang/lianshang03/images/image-20210817234028746.png)

题解：

```java

    /**
     * dp[i][j]代表从s1[0,i)转化为s2[0,j)的最少转换步骤。
     * todo:将二维数组再转化为一维数组来进行解决
     */
    public static int minDistance(String word1, String word2) {
        if (word1 == null || word2 == null) {
            return 0;
        }
        char[] shortChars;
        char[] longChars;
        shortChars = word1.toCharArray();
        longChars = word2.toCharArray();
        int[][] dp = new int[shortChars.length + 1][longChars.length + 1];
        //w1的空字串转化为w2的空串的最小操作数为0
        for (int i = 0; i < dp[0].length; i++) {
            dp[0][i] = i;
        }
        for (int i = 1; i < dp.length; i++) {
            dp[i][0] = i;
            for (int j = 1; j < dp[i].length; j++) {
                if (shortChars[i - 1] == longChars[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]);
                    dp[i][j]++;
                }
            }
        }
        return dp[shortChars.length][longChars.length];
    }


```

##### 4 最长回文子串

描述：给你一个字符串 `s`，找到 `s` 中最长的回文子串。

link：https://leetcode-cn.com/problems/longest-palindromic-substring/

题解：

方式1：暴力法，迭代每一个字符串，时间复杂度为O(n^2)+判断是否为回文串，因此时间复杂度为O(n^3)。

方式2：使用dp的方式，使用二维数组来保存数据。

![image-20210818001617139](https://gitee.com/code1997/blog-image/raw/master/lianshang/lianshang03/images/image-20210818001617139.png)

题解：

```java
    /**
     * dp的方式：dp[i][j]代表s[i,j]是否为回文串，存储true,false.
     * >如果s[i,j]的长度是(j-i+1)<=2时候，如果s[i]==s[j]的时候那么他们是回文串。
     * >如果s[i,j]的长度是(j-i+1)>2时候，如果s[i+1,j-1]是回文串(代表内层)，并且s[i]==s[j],那么s[i,j]是回文串
     * 时间复杂度为O(n^2)。
     * 空间复杂度也是O(n^2)，我们也可以使用一维数组来对其进行优化。
     */
    public static String longestPalindrome2(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        boolean[][] dp = new boolean[s.length()][s.length()];
        char[] chars = s.toCharArray();
        int beginIndex = 0;
        //用于记录最长回文子串的长度
        int maxLength = 1;
        for (int i = chars.length - 1; i >= 0; i--) {
            for (int j = i; j < chars.length; j++) {
                /*if (j - i + 1 <= 2) {
                    dp[i][j] = chars[i] == chars[j];
                } else {
                    dp[i][j] = chars[i] == chars[j] && dp[i + 1][j - 1];
                }*/
                int curLength = j - i + 1;
                //注意判断条件的顺序
                dp[i][j] = chars[i] == chars[j] && (curLength <= 2 || dp[i + 1][j - 1]);
                //如果长度相同，则选取第一个回文串，不能进行层层覆盖
                if (dp[i][j] && maxLength < curLength) {
                    beginIndex = i;
                    maxLength = curLength;
                }
            }
        }
        return s.substring(beginIndex, beginIndex + maxLength);
    }
```

方式三：扩展中心法以及优化

```java

/**
     * 扩展中心法：将每个字符，每个间隙向两边进行扩展。
     * 扩展中心：2n-1
     * 时间复杂度：O(n*(2n-1))，时间复杂度依旧是O(n^2)，相对与dp的优势是空间复杂度降低，为O(1)
     */
    public static String longestPalindrome3(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }
        //注意：toCharArray也会带来空间复杂度
        char[] chars = s.toCharArray();
        int beginIndex = 0;
        int maxLength = 1;
        //实际上最后一个字符和第一个字符就没有必要进行扩展，
        for (int i = chars.length - 2; i > 0; i--) {
            //求以某个字符或者间隙为中心的最长回文串的长度是多少。
            //以字符为中心向左右进行扩展
            int len1 = longestPalindromeLength(chars, i - 1, i + 1);
            //以间隙开始向左右开始进行扩展。
            int len2 = longestPalindromeLength(chars, i, i + 1);
            len1 = Math.max(len1, len2);
            if (len1 > maxLength) {
                //计算开始索引：注意这里对于len的处理。
                beginIndex = i - ((len1 - 1) >> 1);
                maxLength = len1;
            }

        }
        //处理0号字符右边的间隙，最大长度为2
        if (maxLength < 2 && chars[0] == chars[1]) {
            beginIndex = 0;
            maxLength = 2;
        }
        return new String(chars, beginIndex, maxLength);
    }

    private static int longestPalindromeLength(char[] chars, int leftIndex, int rightIndex) {
        while (leftIndex >= 0 && rightIndex <= chars.length - 1 && chars[leftIndex] == chars[rightIndex]) {
            leftIndex--;
            rightIndex++;
        }
        //跳出循环的时候，长度多了两位。
        return rightIndex - leftIndex - 1;
    }


    /**
     * 扩展中心法优化：算法的核心是将扩展由一个字符，间隙转化为一坨字符为核心。
     * 1）找到右边第一个不等于s[i]的字符，位置记录为r，i左边的位置记录为1
     * 2）由l开始向左，r开始向右扩展，找到最长的回文子串。
     */
    public static String longestPalindrome4(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }
        //注意：toCharArray也会带来空间复杂度
        char[] chars = s.toCharArray();
        int beginIndex = 0;
        int maxLength = 1;
        int i = 0;
        while (i < chars.length) {
            int r = i;
            //找到第一个不等s[i]位置的元素
            while (++r < chars.length && chars[r] == chars[i]) {

            }
            //开始向左向右扩展
            int l = i - 1;
            //r成为新的i
            i = r;
            while (l >= 0 && r <= chars.length - 1 && chars[r] == chars[l]) {
                l--;
                r++;
            }
            //代表l或者r越界或者chars[r] != chars[l],开始获取最长的回文串。注意这里的++l操作
            int curLength = r - ++l;
            if (maxLength < curLength) {
                maxLength = curLength;
                beginIndex = l;
            }

        }
        return new String(chars, beginIndex, maxLength);
    }
```

方法四：马拉车算法

核心概念：

![image-20210819213550308](https://gitee.com/code1997/blog-image/raw/master/lianshang/lianshang03/images/image-20210819213550308.png)

情况1：

![image-20210819223414949](https://gitee.com/code1997/blog-image/raw/master/lianshang/lianshang03/images/image-20210819223414949.png)

情况2：

<img src="https://gitee.com/code1997/blog-image/raw/master/lianshang/lianshang03/images/image-20210819232023870.png" alt="image-20210819232023870"  />

情况3：

![image-20210819232237919](https://gitee.com/code1997/blog-image/raw/master/lianshang/lianshang03/images/image-20210819232237919.png)

情况4：

![image-20210819232357173](https://gitee.com/code1997/blog-image/raw/master/lianshang/lianshang03/images/image-20210819232357173.png)

代码：

```java
   /**
     * 马拉车算法
     * 头部，尾部，字符中间添加特殊字符，必须是原字符串中不包含的字符。
     * m[i]的含义：
     * 1)以cs[i]为扩展中心的最大回文子串的长度(不包含#字符)->最大回文子串在原字串中的开始索引：(i-m[i]) >> 1
     * 2)是以cs[i]为扩展中心的最大回文子串的左半部分或者右半部分的长度(包含#字符)。
     * 实际上就是需要我们求出m[]
     */
    public static String manacher(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }
        //对字符串进行预处理
        char[] oldCs = s.toCharArray();
        char[] cs = preprocess(oldCs);
        int[] m = new int[cs.length];
        //因为头的特殊性(前两个字符的值为^#)，实际上可以去除掉，index = 2的点根据对称性，必然为1.
        int c = 1, r = 1, lastIndex = m.length - 2;
        int maxLength = 0, index = 0;
        for (int i = 2; i < lastIndex; i++) {
            //如果i=r的时候实际上需要以i为中心来进行中心扩展。
            if (r > i) {
                //li和i以c位置中心对称
                int li = (c << 1) - i;
                if (i + m[li] <= r) {
                    //一旦i + m[i] <= r 意味着对称范围在r里面,那么我们可以直接确定m[i]的值
                    m[i] = m[li];
                } else {
                    //当i + m[i] > r 的时候，m[i]的值至少为r-i,相当于对m[i]进行初始化。
                    m[i] = r - i;
                }
            }
            //需要以i为中心进行扩展.
            while (cs[i + m[i] + 1] == cs[i - m[i] - 1]) {
                m[i]++;
            }
            //更新c,r
            if (i + m[i] > r) {
                c = i;
                r = i + m[i];
            }
            //找出最大的回文字串的值.
            if (m[i] > maxLength) {
                maxLength = m[i];
                index = i;
            }
        }
        int beginIndex = (index - maxLength) >> 1;
        return new String(oldCs, beginIndex, maxLength);
    }

    /**
     * 对马拉松算法的字符串进行前置处理
     */
    private static char[] preprocess(char[] chars) {
        //需要注意进位的优先级
        char[] cs = new char[(chars.length << 1) + 3];
        cs[0] = '^';
        cs[1] = '#';
        cs[cs.length - 1] = '$';
        for (int i = 0; i < chars.length; i++) {
            int idx = (i + 1) << 1;
            cs[idx] = chars[i];
            cs[idx + 1] = '#';
        }
        return cs;
    }
```

### 3 树

> 对于二叉树相关的题，递归是非常常见的解题手段

##### 1 二叉树的最近公共祖先

描述：给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。

百度百科中最近公共祖先的定义为：“对于有根树 T 的两个节点 p、q，最近公共祖先表示为一个节点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”

link：https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree

题解：

方法一：

```java
/**
 * p和q的公共祖先存在三种情况
 * 1)p,q在两边，最近公共祖先是根节点。
 * 2)p,q在一边，最近公共祖先必定在一边。
 */
public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
    if (root == null || root == p || root == q) {
        return root;
    }
    //去root的leftChild去找公共祖先
    TreeNode leftParentNode = lowestCommonAncestor2(root.left, p, q);
    //去root的leftChild去找公共祖先
    TreeNode rightParentNode = lowestCommonAncestor2(root.right, p, q);
    if (leftParentNode != null && rightParentNode != null) {
        return root;
    }
    return leftParentNode != null ? leftParentNode : rightParentNode;
}
```

##### 2 恢复二叉搜索树

给你二叉搜索树的根节点 root ，该树中的两个节点被错误地交换。请在不改变其结构的情况下，恢复这棵树。

链接：https://leetcode-cn.com/problems/recover-binary-search-tree

```java
package com.it;

/**
 * 给你二叉搜索树的根节点 root ，该树中的两个节点被错误地交换。请在不改变其结构的情况下，恢复这棵树。
 * 进阶：使用 O(n) 空间复杂度的解法很容易实现。你能想出一个只使用常数空间的解决方案吗？使用morris实现O(1)级别的空间复杂度对二叉树的遍历。
 * link:https://leetcode-cn.com/problems/recover-binary-search-tree
 *
 * @author : code1997
 * @date : 2021/8/23 0:02
 */
public class _99_RecoverBinarySearchTree {

    private TreeNode preNode;

    private TreeNode firstNode;

    private TreeNode secondNode;

    /**
     * 核心：中序是升序的特点，
     * 1）如果被交换的两个节点是挨在一起的，则中序遍历之后只存在一个逆序对。
     * 2）如果被交换的两个节点不是挨在一起的，则中序遍历之后，存在两个逆序对。
     * 3）无论是一个或者两个逆序对，实际上我们我们要找的错误节点就是：第一个逆序对中较大的节点，第二个逆序对中较小二点节点。
     */
    public void recoverTree(TreeNode root) {
        //findWrongNodes(root);
        morris(root);
        //交换两个错误的节点的值
        swap(firstNode, secondNode);
    }

    /**
     * 中序遍历
     */
    private void findWrongNodes(TreeNode root) {
        if (root == null) {
            return;
        }
        findWrongNodes(root.left);
        find(root);
        findWrongNodes(root.right);
    }

    private void find(TreeNode root) {
        if (preNode != null && preNode.val > root.val) {
            //无论是存在几个逆序对都不会出现什么问题：总是保存逆序对中比较小的值。
            secondNode = root;
            //firstNode只能是第一个逆序对中比较大的值，不可以再次被覆盖。
            if (firstNode != null) {
                return;
            }
            firstNode = preNode;
        }
        //需要保留上一次便利的节点
        preNode = root;
        return;
    }

    private void swap(TreeNode firstNode, TreeNode secondNode) {
        int temp = firstNode.val;
        firstNode.val = secondNode.val;
        secondNode.val = temp;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
```

进阶：使用 O(n) 空间复杂度的解法很容易实现。你能想出一个只使用常数空间的解决方案吗？

来源：力扣（LeetCode）

```java
/**
 * 使用morris可以实现二叉树的遍历的时间复杂度为O(n)，空间复杂度为O(1).也被称为线索二叉树，线索指的是只想前驱或者后继。
 * 执行步骤：假设遍历到当前节点是N
 * ①如果N.left!=null,找到N的前驱节点p.
 * 如果p.right == null,那么令P.right=N,N=N.left.回到①
 * 如果P.right == N,那么p.right=null,打印N,N=N.right.回到①
 * 如果N.left==null，打印N,N=N.right.回到①
 */
private void morris(TreeNode root) {
    if (root == null) {
        return;
    }
    while (root != null) {
        if (root.left != null) {
            //找到当前节点的前驱节点
            TreeNode precursor = root.left;
            while (precursor.right != null && precursor.right != root) {
                precursor = precursor.right;
            }
            if (precursor.right == null) {
                precursor.right = root;
                root = root.left;
            } else {
                //precursor.rightChild == node
                precursor.right = null;
                find(root);
                root = root.right;
            }
        } else {
            find(root);
            root = root.right;
        }
    }
}
```

##### 3 最大BST子树

给定一个二叉树，找到其中最大的二叉搜索树(BST)子树，其中最大指的是子树节点数最多的。

思路：以自底向上的方式来实现<--后续遍历，先判断左右节点是否是二叉搜索树，然后再去确定上层是否是二叉搜索树。

定义一个TreeInfo来辅助：

```java
    private static class TreeInfo {
        TreeNode root;
        int size;
        /**
         * max和min主要用来判断其父节点是否是BST
         */
        int max;
        int min;

        public TreeInfo(TreeNode root, int size, int max, int min) {
            this.root = root;
            this.size = size;
            this.max = max;
            this.min = min;
        }
    }
```

- 以root节点为跟根节点的二叉树就是一颗BST子树的条件：
  - 左右子树不为空且分别为最大bst子树，当前节点的值处于两者之间。
  - 左子树为null，右子树不为null且为最大bst子树，当前节点的值小于右子树节点的值。
  - 当前节点的左右子树的值都为null。

题解：

```java
/**
     * 采用自顶向下的方式实现:实际上就是前序遍历的变种。
     * 但是我们发现是可以进行优化的，采用自底向上的方式进行遍历，也就是后序遍历
     */
    public int largestBSTSubtree(TreeNode root) {
        return root == null ? 0 : getInfo(root).size;
    }

    /**
     * 返回以node为根节点的二叉树的最大BST子树信息。
     * 使用后序遍历的方式
     */
    private TreeInfo getInfo(TreeNode root) {
        //如果根节点是null，那么值就是null
        if (root == null) {
            return null;
        }
        TreeInfo li = getInfo(root.left);
        TreeInfo ri = getInfo(root.right);
        int leftBstSize = -1, rightBstSize = -1, max = root.val, min = root.val;
        //li和ri必须符合条件之一才可以算作是BST，因此leftBstSize，rightBstSize的初始值十分重要
        if (li == null) {
            leftBstSize = 0;
        } else if (li.root == root.left && root.val > li.max) {
            leftBstSize = li.size;
            min = li.min;
        }
        if (ri == null) {
            rightBstSize = 0;
        } else if (ri.root == root.left && root.val < li.max) {
            rightBstSize = ri.size;
            max = ri.max;
        }
        if (leftBstSize >= 0 && rightBstSize >= 0) {
            //以root为根节点的二叉树就是BST
            return new TreeInfo(root, leftBstSize + rightBstSize + 1, max, min);
        }
        //以root为根节点的树不是BST
        if (li != null && ri != null) {
            //左右节点都为null
            return li.size > ri.size ? li : ri;
        }
        //左右节点其中一个为空
        return li != null ? li : ri;
    }

    private static class TreeInfo {
        TreeNode root;
        int size;
        /**
         * max和min主要用来判断其父节点是否是BST
         */
        int max;
        int min;

        public TreeInfo(TreeNode root, int size, int max, int min) {
            this.root = root;
            this.size = size;
            this.max = max;
            this.min = min;
        }
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
```

### DFS

> 深度优先搜索：很多排列组合相关的问题，都可以通过DFS实现。
>
> DFS的概念：回溯+剪枝
>
> 关键：
>
> - 找层的概念。
> - 当钻到了不可以再钻的层级的时候，就是保存答案的时候。
> - 每一层可以枚举出可以原则的点。

##### 1 电话号码的字母组合

给定一个仅包含数字 `2-9` 的字符串，返回所有它能表示的字母组合。答案可以按 **任意顺序** 返回，注意1对应任何字母。

成员变量版本：

```java
    char[] chars;
    List<String> list;
    private char[] selectedLetter;

    char[][] lettersArray = {{'a', 'b', 'c'}, {'d', 'e', 'f'}, {'g', 'h', 'i'},
            {'j', 'k', 'l'}, {'m', 'n', 'o'}, {'p', 'q', 'r', 's'},
            {'t', 'u', 'v'}, {'w', 'x', 'y', 'z'}};

    /**
     * 使用递归来解决。
     */
    public List<String> letterCombinations(String digits) {
        list = new ArrayList<>();
        if (digits == null || digits.length() == 0) {
            return list;
        }
        chars = digits.toCharArray();
        selectedLetter = new char[chars.length];
        dfs(0);
        return list;
    }

    /**
     * 搜索第idx层
     * 一般开头写终止条件的判断
     */
    private void dfs(int idx) {
        if (idx == chars.length) {
            //已经进入到了最后一层，得到了一个正确的解
            list.add(new String(selectedLetter));
            return;
        }
        char digit = chars[idx];
        //该层可以做的选择
        char[] letters = lettersArray[digit - '2'];
        //遍历进入下层
        for (char letter : letters) {
            //将进入下一层之前，先将值存储起来
            selectedLetter[idx] = letter;
            dfs(idx + 1);
        }
    }
```

局部遍历版本：

```java
    char[][] lettersArray = {{'a', 'b', 'c'}, {'d', 'e', 'f'}, {'g', 'h', 'i'},
            {'j', 'k', 'l'}, {'m', 'n', 'o'}, {'p', 'q', 'r', 's'},
            {'t', 'u', 'v'}, {'w', 'x', 'y', 'z'}};

    /**
     * 使用递归来解决。
     */
    public List<String> letterCombinations(String digits) {
        List<String> list = new ArrayList<>();
        if (digits == null || digits.length() == 0) {
            return list;
        }
        char[] chars = digits.toCharArray();
        char[] selectedLetter = new char[chars.length];
        dfs(0, chars, selectedLetter, list);
        return list;
    }

    /**
     * 搜索第idx层
     * 一般开头写终止条件的判断
     */
    private void dfs(int idx, char[] chars, char[] selectedLetter, List<String> list) {
        if (idx == chars.length) {
            //已经进入到了最后一层，得到了一个正确的解
            list.add(new String(selectedLetter));
            return;
        }
        char digit = chars[idx];
        //该层可以做的选择
        char[] letters = lettersArray[digit - '2'];
        //遍历进入下层
        for (char letter : letters) {
            //将进入下一层之前，先将值存储起来
            selectedLetter[idx] = letter;
            dfs(idx + 1, chars, selectedLetter, list);
        }
    }
```

##### 2 全排列

##### 3 全排列Ⅱ

##### 4 括号生成

描述：数字 `n` 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 **有效的** 括号组合。

有效括号组合需满足：左括号必须以正确的顺序闭合。

题解：

```java
    public List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList<>();
        if (n < 0) {
            return list;
        }
        if (n == 0) {
            list.add("");
            return list;
        }
        dfs(0, n, n, new char[n << 1], list);
        return list;
    }

    /**
     * 当左右括号数量相等的时候，我们只可以选择左括号.
     * 1）什么时候可以选择左括号？只要左括号的数量大于0.
     * 2）什么时候可以选择右括号？右括号的数量大于0并且左右括号的数量不一样.
     * 3）每进入一层都需要知道左右括号剩下多少
     */
    private void dfs(int idx, int leftRemain, int rightRemain, char[] string, List<String> list) {
        if (string.length == idx) {
            //判断是否合法，如何合法就添加到list中去.
            list.add(new String(string));
            return;
        }
        //枚举这一层所有可能的选择(常规写法是直接for循环即可，但是这里选择性太小，没有必要进行for循环)，然后选择一个值，进入下一层.
        if (leftRemain > 0) {
            string[idx] = '(';
            dfs(idx + 1, leftRemain - 1, rightRemain, string, list);
        }
        if (rightRemain > 0 && leftRemain != rightRemain) {
            string[idx] = ')';
            dfs(idx + 1, leftRemain, rightRemain - 1, string, list);
        }
    }
```

##### 习题



- 51.N皇后问题

- 52.N皇后问题Ⅱ

- 112.路径总和

- 113.路径综合Ⅱ

  ```java
     public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
          List<List<Integer>> list = new ArrayList<>();
          if (root == null) {
              return list;
          }
          dfs(root, list, targetSum, new ArrayList<>());
          return list;
      }
  
      private void dfs(TreeNode treeNode, List<List<Integer>> list, int targetSum, List<Integer> nums) {
          if (treeNode == null) {
              return;
          }
          targetSum -= treeNode.val;
          nums.add(treeNode.val);
          if (treeNode.left == null && treeNode.right == null) {
              //说明到了叶子节点,判断值的和
              if (targetSum == 0) {
                  list.add(new ArrayList<>(nums));
              }
          } else {
              dfs(treeNode.left, list, targetSum, nums);
              dfs(treeNode.right, list, targetSum, nums);
          }
          //恢复现场
          nums.remove(nums.size() - 1);
      }
  
      public class TreeNode {
          int val;
          TreeNode left;
          TreeNode right;
  
          TreeNode() {
          }
  
          TreeNode(int val) {
              this.val = val;
          }
  
          TreeNode(int val, TreeNode left, TreeNode right) {
              this.val = val;
              this.left = left;
              this.right = right;
          }
      }
  ```

- 39.组合总和

  ```java
  ```

  





### 习题列表：

##### 动态规划

- 1143

- 53
- 322
- 300
- 70
- 198
- 674
- 63
- 122
- 123
- 188
- 714
- 673
- 1235
- 943
- 516
- 376

##### 树

- 94
- 98
- 230
- 101
- 108
- 102
- 104
- 105
- 106
- 297
- 449



### 小总结：

##### 数组

- 88：合并两个有序数组
- 75：颜色分类
- 16：部分排序

##### 链表

- 203：移除链表元素
- 2：两数相加
- 160：相交链表
- 86：分隔链表
- 234：回文链表

##### 栈+队列

- 155：最小栈
- 239：滑动窗口最大值
- 654：最大二叉树
- 739：每日温度

##### 字符串

- 9：字符串轮转
- 572：另一个树的子树
- 242：有效的字母异位词
- 151：翻转字符串里的单词
- 3：无重复字符的最长字串

##### 动态规划

- 47：全排列Ⅱ
- 121：买卖的最佳时机
- 72：编辑距离
- 5：最长回文字串

##### 二叉树

- 236：二叉树的最近公共祖先
- 99：恢复二叉搜索树
- 333：最大BST子树

##### DFS

- 17：电话号码的字母组合
- 46：全排列
- 47：全排列Ⅱ
- 22：括号生成

##### 高频

- 283：移动零
- 1：两数之和
- 15：三数之和
- 50：Pow(x,n)
- 62：圆圈中最后剩下的数字
- 54：螺旋矩阵
- 146：LRU缓存机制
- 7：整数反转
- 252：会议室
- 253：会议室Ⅱ
- 11：盛最多水的容器
- 42：接雨水







