package test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class FarmerProblem {

    /**
     * 判断农夫的位置（1000） <br>
     * 0表示在左岸，1表示在右岸
     *
     * @param location 当前位置状态
     * @return false 左岸，true 右岸
     */
    public static boolean farmerLocation(int location) {
        return (0 != (location & 0x08)); // &是按位与，0x08表示十六进制数8（二进制为00001000）
    }

    /**
     * 判断狼的位置（0100） <br>
     * 0表示在左岸，1表示在右岸
     *
     * @param location 当前位置状态
     * @return false 左岸，true 右岸
     */
    public static boolean wolfLocation(int location) {
        return (0 != (location & 0x04)); // 0x04表示十六进制数4（二进制为00000100）
    }

    /**
     * 判断白菜的位置（0010） <br>
     * 0表示在左岸，1表示在右岸
     *
     * @param location 当前位置状态
     * @return false 左岸，true 右岸
     */
    public static boolean cabbageLocation(int location) {
        return (0 != (location & 0x02)); // 0x02表示十六进制数2（二进制为00000010）
    }

    /**
     * 判断羊的位置（0001） <br>
     * 0表示在左岸，1表示在右岸
     *
     * @param location 当前位置状态
     * @return false 左岸，true 右岸
     */
    public static boolean goatLocation(int location) {
        return (0 != (location & 0x01)); // 0x01表示十六进制数1（二进制为00000001）
    }

    /**
     * 判断安全状态
     * <p>
     * 1、羊与白菜在同岸，且羊与农夫不在同岸，羊会吃白菜，不安全<br>
     * 2、羊与狼在同岸，且羊与农夫不在同岸，狼会吃羊，不安全<br>
     * 3、其他状态安全
     *
     * @param location 当前位置状态
     * @return 返回true为安全，false为不安全
     */
    public boolean isSafe(int location) {
        // 羊与白菜在同岸，且羊与农夫不在同岸，羊会吃白菜，不安全
        if ((goatLocation(location) == cabbageLocation(location))
                && (goatLocation(location) != farmerLocation(location)))
            return false;
        // 羊与狼在同岸，且羊与农夫不在同岸，狼会吃羊，不安全
        if ((goatLocation(location) == wolfLocation(location))
                && (goatLocation(location) != farmerLocation(location)))
            return false;

        // 其他状态均安全
        return true;
    }

    /**
     * 农夫过河问题
     * <p>
     * 问题描述：农夫要把狼、白菜、羊带到对岸，但每次最多只能带一个，当农夫不在时，狼会吃羊，羊会吃白菜。
     *
     * @return
     */
    public List<Integer> farmerProblem() {

        Queue<Integer> moveTO = new LinkedList<Integer>(); // 创建空队列，用于记录可以安全到达的中间状态
        moveTO.add(new Integer(0));// 初始状态进队列

        int[] route = new int[16]; // 用于记录已考虑的状态路径
        // 初始化数组route
        for (int i = 0; i < 16; i++) {
            route[i] = -1;
        }
        route[0] = 0;

        while (!moveTO.isEmpty() && (route[15] == -1)) {
            int location = moveTO.poll(); // 取队顶状态为当前状态，并移除队顶元素

            for (int movers = 1; movers <= 8; movers <<= 1) { // 考虑各种物品移动。
                if ((0 != (location & 0x8)) == (0 != (location & movers))) { // 农夫与移动的物品在同一岸
                    int newlocation = location ^ (0x08 | movers); // 计算新状态，^为按位异或，相同为0，不同为1
                    if (isSafe(newlocation) && (route[newlocation] == -1)) { // 新状态安全且未处理
                        route[newlocation] = location; // 记录新状态的前驱
                        moveTO.add(new Integer(newlocation)); // 新状态入队
                    }
                }
            }
        }
        // 保存状态
        List<Integer> resultList = new ArrayList<Integer>();
        if (route[15] != -1) { // 到达最终状态
            for (int location = 15; location >= 0; location = route[location]) {
                resultList.add(location);
                if (location == 0) {
                    break;
                }
            }
        }
        Collections.reverse(resultList); // 反转顺序
        return resultList;
    }

    /**
     * 记录上次位置
     */
    private int oldLoca = 0;

    /**
     * 中文解释
     *
     * @param location
     */
    private String explainToChinese(int location) {
        String str = "";
        if (farmerLocation(location) != farmerLocation(oldLoca))
            str += "农夫"; // 判断农夫的位置是否改变
        if (wolfLocation(location) != wolfLocation(oldLoca))
            str += "把狼带到了"; // 判断狼的位置是否改变
        else if (cabbageLocation(location) != cabbageLocation(oldLoca))
            str += "把白菜带到了"; // 判断白菜的位置是否改变
        else if (goatLocation(location) != goatLocation(oldLoca))
            str += "把羊带到了"; // 判断羊的位置是否改变
        else
            str += "独自回到了"; // 狼、白菜、羊的位置都未改变
        if (farmerLocation(location))
            str += "右岸"; // 判断下一步农夫是在哪一岸，若农夫当前在左岸，则他下一步必在右岸
        else
            str += "左岸";

        oldLoca = location; // 记录本次位置，为下次作准备

        return str;
    }

    /**
     * 获得整型数转换为四位二进制的字符串<br>
     * 例如：9 = 1001 、 2 = 0010
     *
     * @param n 0到15的十进制整数
     * @return 一个四位的二进制字符串
     */
    private String getIntToFourBinaryStr(int n) {
        char[] bnum = Integer.toBinaryString(n).toCharArray(); // 转为二进制，但是缺少前导0
        char[] bit = {'0', '0', '0', '0'};
        for (int i = bit.length - 1, j = bnum.length - 1; i >= 0 && j >= 0; i--, j--) {
            bit[i] = bnum[j];
        }
        return new String(bit);
    }

    /**
     * 输出结果
     *
     * @param list 一个解集合
     */
    private void outputResult(List<Integer> list) {
        System.out.println("0代表在左岸，1代表在右岸");
        System.out.println("0000 每位数分别代表农夫、狼、白菜、羊");
        System.out.println();

        System.out.println("[位置]\t[二进制]\t[中文解释]");
        for (int i = 0; i < list.size(); i++) {
            int location = list.get(i);
            String str = "位置:" + location + "\t";
            str += getIntToFourBinaryStr(location); // 为了利于观察，转换为二进制输出

            if (location == 0) {
                str += "\t都在左岸";
            } else {
                str += "\t" + explainToChinese(location); // 中文解释
            }
            System.out.println(str);
            if (location == 15) {
                System.out.println("\t\t都在右岸");
            }
        }
    }
    /**
     * 主方法
     */
    public static void main(String[] args) {
        FarmerProblem fp = new FarmerProblem();
        List<Integer> list = fp.farmerProblem();
        fp.outputResult(list);
    }

}