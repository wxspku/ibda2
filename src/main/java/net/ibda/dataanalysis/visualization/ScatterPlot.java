package net.ibda.dataanalysis.visualization;

import de.erichseifert.gral.data.DataTable;
import de.erichseifert.gral.examples.ExamplePanel;
import de.erichseifert.gral.graphics.Insets2D;
import de.erichseifert.gral.plots.XYPlot;
import de.erichseifert.gral.ui.InteractivePanel;

import java.awt.*;
import java.util.Random;

public class ScatterPlot extends ExamplePanel {
    /**
     * 序列化版本id
     */
    private static final long serialVersionUID = -412699430625953887L;
    private static final int SAMPLE_COUNT = 100000;
    /**
     * 用于产生随机数据值的实例
     */
    private static final Random random = new Random();

    @SuppressWarnings("unchecked")
    public ScatterPlot() {
        //生成100000个数据点
        DataTable data = new DataTable(Double.class, Double.class);
        for (int i = 0; i <= SAMPLE_COUNT; i++) {
            data.add(random.nextGaussian() * 2.0, random.nextGaussian() * 2.0);
        }
        //新建XYPlot对象
        XYPlot plot = new XYPlot(data);
        //设置图形
        plot.setInsets(new Insets2D.Double(20.0, 40.0, 40.0, 40.0));
        plot.getTitle().setText(getDescription());
        //设置数据点
        plot.getPointRenderers(data).get(0).setColor(COLOR1);
        //把图形添加到Swing组件中
        add(new InteractivePanel(plot), BorderLayout.CENTER);
    }

    @Override
    public String getTitle() {
        return "Scatter plot";
    }

    @Override
    public String getDescription() {
        return String.format("Scatter plot with %d data points",
                SAMPLE_COUNT);
    }

    public static void main(String[] args) {
        new ScatterPlot().showInFrame();
    }
}
