package net.ibda.dataanalysis.visualization;

import de.erichseifert.gral.data.DataTable;
import de.erichseifert.gral.examples.ExamplePanel;
import de.erichseifert.gral.graphics.Insets2D;
import de.erichseifert.gral.plots.PiePlot;
import de.erichseifert.gral.plots.PiePlot.PieSliceRenderer;
import de.erichseifert.gral.plots.colors.LinearGradient;
import de.erichseifert.gral.ui.InteractivePanel;

import java.awt.*;
import java.util.Random;

public class SimplePiePlot extends ExamplePanel {
    /**
     * 序列化版本id
     */
    private static final long serialVersionUID = -3039317265508932299L;
    private static final int SAMPLE_COUNT = 10;
    /**
     * 用于产生随机数据值的实例
     */
    private static Random random = new Random();

    @SuppressWarnings("unchecked")
    public SimplePiePlot() {
        //生成数据
        DataTable data = new DataTable(Integer.class);
        for (int i = 0; i < SAMPLE_COUNT; i++) {
            int val = random.nextInt(8) + 2;
            data.add((random.nextDouble() <= 0.15) ? -val : val);
        }
        //新建饼图
        PiePlot plot = new PiePlot(data);
        //设置图形
        plot.getTitle().setText(getDescription());
        //设置饼块的相对大小
        plot.setRadius(0.9);
        //显示图例
        plot.setLegendVisible(true);
        //向图形区域添加边距
        plot.setInsets(new Insets2D.Double(20.0, 40.0, 40.0, 40.0));
        PieSliceRenderer pointRenderer =
                (PieSliceRenderer) plot.getPointRenderer(data);
        //设置内区域相对大小
        pointRenderer.setInnerRadius(0.4);
        //设置饼块间的间隔大小
        pointRenderer.setGap(0.2);
        //设置颜色
        LinearGradient colors = new LinearGradient(COLOR1, COLOR2);
        pointRenderer.setColor(colors);
        //显示标签
        pointRenderer.setValueVisible(true);
        pointRenderer.setValueColor(Color.WHITE);
        pointRenderer.setValueFont(Font.decode(null).deriveFont(Font.BOLD));
        //把图形添加到Swing组件中
        add(new InteractivePanel(plot), BorderLayout.CENTER);
    }

    @Override
    public String getTitle() {
        return "Donut plot";
    }

    @Override
    public String getDescription() {
        return String.format("Donut plot of %d random data values",
                SAMPLE_COUNT);
    }

    public static void main(String[] args) {
        new SimplePiePlot().showInFrame();
    }
}
