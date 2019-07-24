package test;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * 窗口类
 *
 * @author wuqianling
 *
 */
public class MyWindow extends JFrame {

    private List<Integer> list = null;
    private DrawPanel drawPanel;
    private TextField textField;

    public MyWindow() {
        this.setTitle("农夫过河");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 设置窗口关闭时退出程序
        this.setSize(660, 420);
        this.setResizable(false); // 固定窗口大小
        this.setLocationRelativeTo(null); // 居中
        // 顶部面板容器
        JPanel top = new JPanel();
        top.setLayout(new BorderLayout()); // 设置边界布局管理器

        textField = new TextField("0000 每位数分别代表农夫、狼、白菜、羊");
        textField.setEditable(false); // 设置不可编辑
        top.add(textField, BorderLayout.CENTER);

        JPanel topRight = new JPanel();
        JButton calbtn = new JButton("计算方案");
        JButton simbtn = new JButton("模拟方案");
        topRight.add(calbtn);
        topRight.add(simbtn);
        top.add(topRight, BorderLayout.EAST);

        this.getContentPane().add(top, BorderLayout.NORTH); // 添加到窗口中

        // 添加模拟动画面板
        drawPanel = new DrawPanel();
        this.getContentPane().add(drawPanel);

        // 添加按钮事件监听
        calbtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calculate();
            }
        });

        simbtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (list == null)
                    calculate();
                drawPanel.startDraw();
            }
        });
    }

    /**
     * 计算方案
     */
    private void calculate() {
        FarmerProblem nf = new FarmerProblem();
        list = nf.farmerProblem();

        String str = "一个解决方案：";
        int i = 0;
        for (; i < list.size() - 1; i++) {
            str += (list.get(i) + ",");
        }
        str += list.get(i);

        textField.setText(str);
        System.out.println("计算得" + str);
    }

    /**
     * 主方法
     *
     * @param args
     */
    public static void main(String[] args) {
        MyWindow win = new MyWindow();
        win.setVisible(true);
    }

    /**
     * 绘制模拟的动画，实现Runnable接口开启线程
     *
     * @author wuqianling
     *
     */
    final class DrawPanel extends JPanel implements Runnable {

        private boolean isRun = false; // 是否正在进行模拟

        private final static int INITVALUE = 4;// 默认初始坐标值
        private int nx = INITVALUE; // 农夫的x轴坐标
        private int lx = INITVALUE; // 狼的x轴坐标
        private int bx = INITVALUE; // 白菜的x轴坐标
        private int yx = INITVALUE; // 羊的x轴坐标

        @Override
        public void paint(Graphics g) {
            // g.clearRect(0, 0, getWidth(), getHeight());
            Color c = g.getColor();
            // 画背景
            g.setColor(Color.lightGray);
            g.fillRect(0, 0, getWidth(), getHeight()); // 画左、右岸
            g.setColor(new Color(208, 238, 239));
            g.fillRect(100, 0, this.getWidth() - 200, this.getHeight()); // 画河道
            // 画河道说明文字
            g.setColor(Color.blue);
            g.drawString("左岸", 60, this.getHeight() / 2 - 6);
            g.drawString("右岸", this.getWidth() - 80, this.getHeight() / 2 - 6);
            g.drawString("河道", this.getWidth() / 2 - 10,
                    this.getHeight() / 2 - 6);

            g.setColor(Color.red);
            g.fillRect(nx, 4, 40, 50); // 画农夫
            g.fillRect(lx, 64, 40, 50); // 画狼
            g.fillRect(bx, 124, 40, 50); // 画白菜
            g.fillRect(yx, 184, 40, 50); // 画羊

            // 画相应文字
            g.setColor(Color.yellow);
            g.drawString("农夫", nx + 8, 30);
            g.drawString("狼", lx + 15, 90);
            g.drawString("白菜", bx + 8, 150);
            g.drawString("羊", yx + 15, 210);

            g.setColor(c); // 还原颜色
        }

        /**
         * 开始模拟绘制动画
         *
         * @return
         */
        public boolean startDraw() {
            if (isRun) // 是否有正在模拟的线程
                return false;
            isRun = true; // 设置模拟开始标识
            // 初始化坐标
            nx = INITVALUE;
            lx = INITVALUE;
            bx = INITVALUE;
            yx = INITVALUE;
            // 开启线程
            Thread th = new Thread(this);
            th.start();

            return true;
        }

        /**
         * 绘制一个过程的模拟动画
         *
         * @param loca1
         *            原状态
         * @param loca2
         *            新状态
         */
        private void drawSim(int loca1, int loca2) {
            int distance = 4; // 每次移动的像素
            int endRight = this.getWidth() - 45; // 右边结束坐标
            // 判断状态是否发生改变
            boolean nxb = FarmerProblem.farmerLocation(loca1) != FarmerProblem
                    .farmerLocation(loca2);
            boolean lxb = FarmerProblem.wolfLocation(loca1) != FarmerProblem.wolfLocation(loca2);
            boolean bxb = FarmerProblem.cabbageLocation(loca1) != FarmerProblem
                    .cabbageLocation(loca2);
            boolean yxb = FarmerProblem.goatLocation(loca1) != FarmerProblem.goatLocation(loca2);

            while (isRun) {
                // 判断农夫在左岸还是右岸，来判断应该加或减坐标
                if (FarmerProblem.farmerLocation(loca2)) {
                    // 农夫在左岸往右岸移动
                    nx += distance;
                    if (lxb)
                        lx += distance;
                    if (bxb)
                        bx += distance;
                    if (yxb)
                        yx += distance;
                    if (nx > endRight) // 右结束边界坐标
                        break;
                } else {
                    // 农夫在右岸往左岸移动
                    nx -= distance;
                    if (lxb)
                        lx -= distance;
                    if (bxb)
                        bx -= distance;
                    if (yxb)
                        yx -= distance;
                    if (nx <= INITVALUE) // 左结束边界坐标
                        break;
                }
                repaint(); // 重绘
                try {
                    Thread.sleep(15);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public void run() {
            System.out.println("模拟开始...");
            // 逐个取出状态进行模拟
            for (int i = 1; isRun && i < list.size(); i++) {
                int loca1 = list.get(i - 1);
                int loca2 = list.get(i);
                System.out.print(loca1 + ",");
                drawSim(loca1, loca2);
            }
            System.out.println(list.get(list.size() - 1));
            System.out.println("模拟结束...");
            isRun = false; // 设置模拟结束标识
        }
    }
}