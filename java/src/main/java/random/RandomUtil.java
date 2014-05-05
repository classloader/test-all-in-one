package random;

import java.util.Map;

/**
 * 随机工具类
 *
 * 使用权重的集合Map构建随机元数据对象
 *
 * 比如：
 * 我们有3个url地址，他们的权重分别为1,2,3现在我们利用RandomUtil来根据权重随机获取url：
 *
 * <p><blockquote><pre>
 *
 * map.put(url1, 1);
 * map.put(url2, 2);
 * map.put(url3, 3);
 * RandomMeta<String, Integer> md = RandomUtil.buildWeightMeta(map);
 * String weightRandomUrl = md.random();
 *
 * </pre></blockquote><p>
 *
 * @author wxf on 14-5-5.
 */
public class RandomUtil {
    public static <T> WeightMeta<T> buildWeightMeta(final Map<T, Integer> weightMap) {
        final int size = weightMap.size();
        Object[] nodes = new Object[size];
        int[] weights = new int[size];
        int index = 0;
        int weightAdder = 0;
        for (Map.Entry<T, Integer> each : weightMap.entrySet()) {
            nodes[index] = each.getKey();
            weights[index++] = (weightAdder = weightAdder + each.getValue());
        }
        return new WeightMeta<T>((T[]) nodes, weights);
    }
}
