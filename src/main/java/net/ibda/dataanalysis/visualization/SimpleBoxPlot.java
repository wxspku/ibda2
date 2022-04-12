package net.ibda.dataanalysis.visualization;

import de.erichseifert.gral.data.DataSource;
import de.erichseifert.gral.data.DataTable;
import de.erichseifert.gral.examples.ExamplePanel;
import de.erichseifert.gral.graphics.Insets2D;
import de.erichseifert.gral.plots.BoxPlot;
import de.erichseifert.gral.plots.BoxPlot.BoxWhiskerRenderer;
import de.erichseifert.gral.plots.XYPlot.XYNavigationDirection;
import de.erichseifert.gral.plots.colors.LinearGradient;
import de.erichseifert.gral.plots.colors.ScaledContinuousColorMapper;
import de.erichseifert.gral.ui.InteractivePanel;
import de.erichseifert.gral.util.DataUtils;
import de.erichseifert.gral.util.GraphicsUtils;

import java.awt.*;
import java.util.Random;

public class SimpleBoxPlot extends ExamplePanel {
    /**
     * 序列化版本id
     */
    private static final long serialVersionUID = 5228891435595348789L;
    private static final int SAMPLE_COUNT = 50;
    private static final Random random = new Random();

    @SuppressWarnings("unchecked")
    public SimpleBoxPlot() {
        setPreferredSize(new Dimension(400, 600));
        //创建样本数据
        DataTable data = new DataTable(Integer.class, Integer.class,
                Integer.class);
        for (int i = 0; i < SAMPLE_COUNT; i++) {
            int x = (int) Math.round(5.0 * random.nextGaussian());
            int y = (int) Math.round(5.0 * random.nextGaussian());
            int z = (int) Math.round(5.0 * random.nextGaussian());
            data.add(x, y, z);
        }
        //新建箱线图
        DataSource boxData = BoxPlot.createBoxData(data);
        BoxPlot plot = new BoxPlot(boxData);
        //设置图形格式
        plot.setInsets(new Insets2D.Double(20.0, 50.0, 40.0, 20.0));
        //设置坐标轴格式
        plot.getAxisRenderer(BoxPlot.AXIS_X).setCustomTicks(
                DataUtils.map(
                        new Double[]{1.0, 2.0, 3.0},
                        new String[]{"Column 1", "Column 2", "Column 3"}
                )
        );
        //设置箱盒
        Stroke stroke = new BasicStroke(2f);
        ScaledContinuousColorMapper colors =
                new LinearGradient(GraphicsUtils.deriveBrighter(COLOR1),
                        Color.WHITE);
        colors.setRange(1.0, 3.0);
        BoxWhiskerRenderer pointRenderer =
                (BoxWhiskerRenderer) plot.getPointRenderers(boxData).get(0);
        pointRenderer.setWhiskerStroke(stroke);
        pointRenderer.setBoxBorderStroke(stroke);
        pointRenderer.setBoxBackground(colors);
        pointRenderer.setBoxBorderColor(COLOR1);
        pointRenderer.setWhiskerColor(COLOR1);
        pointRenderer.setCenterBarColor(COLOR1);
        plot.getNavigator().setDirection(XYNavigationDirection.VERTICAL);
        //把图形添加到Swing组件
        InteractivePanel panel = new InteractivePanel(plot);
        add(panel);
    }

    @Override
    public String getTitle() {
        return "Box-and-whisker plot";
    }

    @Override
    public String getDescription() {
        return String.format("Three box-and-whisker plots created from %d random samples", SAMPLE_COUNT);
    }

    public static void main(String[] args) {
        new SimpleBoxPlot().showInFrame();
    }
}
