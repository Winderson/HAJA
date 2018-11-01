package br.com.universidadetuiuti.baja.pibaja.components;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import org.jsoup.nodes.Element;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.model.AxisTitle;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.Cursor;
import com.vaadin.addon.charts.model.DataLabels;
import com.vaadin.addon.charts.model.DataSeries;
import com.vaadin.addon.charts.model.DataSeriesItem;
import com.vaadin.addon.charts.model.HTMLLabelItem;
import com.vaadin.addon.charts.model.HTMLLabels;
import com.vaadin.addon.charts.model.HorizontalAlign;
import com.vaadin.addon.charts.model.Labels;
import com.vaadin.addon.charts.model.LayoutDirection;
import com.vaadin.addon.charts.model.Legend;
import com.vaadin.addon.charts.model.Marker;
import com.vaadin.addon.charts.model.PlotOptionsColumn;
import com.vaadin.addon.charts.model.PlotOptionsPie;
import com.vaadin.addon.charts.model.PlotOptionsSpline;
import com.vaadin.addon.charts.model.Tooltip;
import com.vaadin.addon.charts.model.VerticalAlign;
import com.vaadin.addon.charts.model.XAxis;
import com.vaadin.addon.charts.model.YAxis;
import com.vaadin.addon.charts.model.ZoomType;
import com.vaadin.addon.charts.model.style.Color;
import com.vaadin.addon.charts.model.style.SolidColor;
import com.vaadin.addon.charts.model.style.Style;
import com.vaadin.addon.charts.themes.ValoLightTheme;
import com.vaadin.server.Page.Styles;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

import br.com.universidadetuiuti.baja.pibaja.MyUI;

public class DashboardComponent {

	private Label tituloDashboard;
	private VerticalLayout principalLayout;
	private HorizontalLayout topoLayout;
	private HorizontalLayout centroLayout;   
	private HorizontalLayout rodapeLayout;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DashboardComponent() {
		this.initVariaveis();
		this.initStyles();
		this.initLayouts();
	}

	public void initVariaveis() {
		this.principalLayout = new VerticalLayout();
		this.topoLayout = new HorizontalLayout();
		this.centroLayout = new HorizontalLayout();   
		this.rodapeLayout = new HorizontalLayout();
		this.tituloDashboard = new Label("PI-BAJA");
	}

	public void initStyles() {
		Styles styles = MyUI.getCurrent().getPage().getStyles();
		styles.add(".v-label-titulo{text-align: center;}");
		styles.add(".layoutsBorder{border:solid 2px #000; width: 100%;}");
		principalLayout.addStyleName("layoutsBorder");
		topoLayout.addStyleName("layoutsBorder");
		centroLayout.addStyleName("layoutsBorder");
		rodapeLayout.addStyleName("layoutsBorder");
	}

	public void initLayouts() {
		this.topoLayout.addComponent(this.tituloDashboard);
		this.centroLayout.addComponent(this.getChartPie());
		this.centroLayout.addComponent(this.getDonuts());
		this.rodapeLayout.addComponent(this.getChartColumnPie());
		this.rodapeLayout.addComponent(this.getMultipleAxis());
		this.principalLayout.addComponent(this.topoLayout);
		this.principalLayout.addComponent(this.centroLayout);
		this.principalLayout.addComponent(this.rodapeLayout);
	}
	
	public Component getComponent() {
		return this.principalLayout;
	}

	public Chart getDonuts() {
		Random rand = new Random(0);
		Color[] colors = new ValoLightTheme().getColors();
		rand = new Random(0);
		Chart chart = new Chart(ChartType.PIE);
		Configuration conf = chart.getConfiguration();
		conf.setTitle("Amostra modo donuts");
		YAxis yaxis = new YAxis();
		yaxis.setTitle("Total percent market share");
		PlotOptionsPie pie = new PlotOptionsPie();
		pie.setShadow(false);
		conf.setPlotOptions(pie);
		conf.getTooltip().setValueSuffix("%");
		DataSeries innerSeries = new DataSeries();
		innerSeries.setName("Browsers");
		PlotOptionsPie innerPieOptions = new PlotOptionsPie();
		innerSeries.setPlotOptions(innerPieOptions);
		innerPieOptions.setSize("237px");
		innerPieOptions.setDataLabels(new DataLabels());
		innerPieOptions.getDataLabels().setFormatter(
				"this.y > 5 ? this.point.name : null");
		innerPieOptions.getDataLabels().setColor(new SolidColor(255, 255, 255));
		innerPieOptions.getDataLabels().setDistance(-30);

		Color[] innerColors = Arrays.copyOf(colors, 5);
		innerSeries.setData(new String[] { "MSIE", "Firefox", "Chrome",
				"Safari", "Opera" }, new Number[] { 55.11, 21.63, 11.94, 7.15,
						2.14 }, innerColors);
		DataSeries outerSeries = new DataSeries();
		outerSeries.setName("Versions");
		PlotOptionsPie outerSeriesOptions = new PlotOptionsPie();
		outerSeries.setPlotOptions(outerSeriesOptions);
		outerSeriesOptions.setInnerSize("237px");
		outerSeriesOptions.setSize("318px");
		outerSeriesOptions.setDataLabels(new DataLabels());
		outerSeriesOptions
		.getDataLabels()
		.setFormatter(
				"this.y > 1 ? '<b>'+ this.point.name +':</b> '+ this.y +'%' : null");

		DataSeriesItem[] outerItems = new DataSeriesItem[] {
				/* @formatter:off */
				new DataSeriesItem("MSIE 6.0", 10.85, colors[0]),
				new DataSeriesItem("MSIE 7.0", 7.35, colors[0]),
				new DataSeriesItem("MSIE 8.0", 33.06, colors[0]),
				new DataSeriesItem("MSIE 9.0", 2.81, colors[0]),
				new DataSeriesItem("Firefox 2.0", 0.20, colors[1]),
				new DataSeriesItem("Firefox 3.0", 0.83, colors[1]),
				new DataSeriesItem("Firefox 3.5", 1.58, colors[1]),
				new DataSeriesItem("Firefox 3.6", 13.12, colors[1]),
				new DataSeriesItem("Firefox 4.0", 5.43, colors[1]),
				new DataSeriesItem("Chrome 5.0", 0.12, colors[2]),
				new DataSeriesItem("Chrome 6.0", 0.19, colors[2]),
				new DataSeriesItem("Chrome 7.0", 0.12, colors[2]),
				new DataSeriesItem("Chrome 8.0", 0.36, colors[2]),
				new DataSeriesItem("Chrome 9.0", 0.32, colors[2]),
				new DataSeriesItem("Chrome 10.0", 9.91, colors[2]),
				new DataSeriesItem("Chrome 11.0", 0.50, colors[2]),
				new DataSeriesItem("Chrome 12.0", 0.22, colors[2]),
				new DataSeriesItem("Safari 5.0", 4.55, colors[3]),
				new DataSeriesItem("Safari 4.0", 1.42, colors[3]),
				new DataSeriesItem("Safari Win 5.0", 0.23, colors[3]),
				new DataSeriesItem("Safari 4.1", 0.21, colors[3]),
				new DataSeriesItem("Safari/Maxthon", 0.20, colors[3]),
				new DataSeriesItem("Safari 3.1", 0.19, colors[3]),
				new DataSeriesItem("Safari 4.1", 0.14, colors[3]),
				new DataSeriesItem("Opera 9.x", 0.12, colors[4]),
				new DataSeriesItem("Opera 10.x", 0.37, colors[4]),
				new DataSeriesItem("Opera 11.x", 1.65, colors[4])
				/* @formatter:on */
		};

		outerSeries.setData(Arrays.asList(outerItems));
		conf.setSeries(innerSeries, outerSeries);
		chart.drawChart(conf);

		return chart;
	}

	public Component getChartPie() {
		Chart chart = new Chart(ChartType.PIE);
		Configuration conf = chart.getConfiguration();
		conf.setTitle("Amostra modo pizza");
		Tooltip tooltip = new Tooltip();
		tooltip.setValueDecimals(1);
		tooltip.setPointFormat("{series.name}: <b>{point.percentage}%</b>");
		conf.setTooltip(tooltip);
		PlotOptionsPie plotOptions = new PlotOptionsPie();
		plotOptions.setAllowPointSelect(true);
		plotOptions.setCursor(Cursor.POINTER);
		plotOptions.setShowInLegend(true);
		conf.setPlotOptions(plotOptions);
		DataSeries series = new DataSeries();
		series.add(new DataSeriesItem("Firefox", 45.0));
		series.add(new DataSeriesItem("IE", 26.8));
		DataSeriesItem chrome = new DataSeriesItem("Chrome", 12.8);
		chrome.setSliced(true);
		chrome.setSelected(true);
		series.add(chrome);
		series.add(new DataSeriesItem("Safari", 8.5));
		series.add(new DataSeriesItem("Opera", 6.2));
		series.add(new DataSeriesItem("Others", 0.7));
		conf.setSeries(series);

		//        chart.addLegendItemClickListener(new LegendItemClickListener() {
		//            @Override
		//            public void onClick(LegendItemClickEvent event) {
		//                Notification.show("Legend item click"
		//                        + " : "
		//                        + event.getSeriesItemIndex()
		//                        + " : "
		//                        + ((DataSeries) event.getSeries()).get(
		//                                event.getSeriesItemIndex()).getName());
		//            }
		//        });

		chart.drawChart(conf);
		return chart;
	}

	protected Component getMultipleAxis() {
		Chart chart = new Chart();
		Configuration conf = chart.getConfiguration();
		Color[] colors = new ValoLightTheme().getColors();

		conf.getChart().setZoomType(ZoomType.XY);
		conf.setTitle("Average Monthly Weather Data for Tokyo");
		conf.setSubTitle("Source: WorldClimate.com");

		XAxis x = new XAxis();
		x.setCategories("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug",
				"Sep", "Oct", "Nov", "Dec");
		conf.addxAxis(x);

		YAxis y1 = new YAxis();
		Labels labels = new Labels();
		labels.setFormatter("return this.value +'°C'");
		Style style = new Style();
		style.setColor(colors[1]);
		labels.setStyle(style);
		y1.setLabels(labels);
		y1.setOpposite(true);
		AxisTitle title = new AxisTitle("Temperature");
		style = new Style();
		style.setColor(colors[1]);
		y1.setTitle(title);
		conf.addyAxis(y1);

		YAxis y2 = new YAxis();
		y2.setGridLineWidth(0);
		title = new AxisTitle("Rainfall");
		style = new Style();
		style.setColor(colors[0]);
		y2.setTitle(title);
		labels = new Labels();
		labels.setFormatter("this.value +' mm'");
		style = new Style();
		style.setColor(colors[0]);
		labels.setStyle(style);
		y2.setLabels(labels);
		conf.addyAxis(y2);

		YAxis y3 = new YAxis();
		y3.setGridLineWidth(0);
		conf.addyAxis(y3);
		title = new AxisTitle("Sea-Level Pressure");
		style = new Style();
		style.setColor(colors[2]);
		y3.setTitle(title);
		labels = new Labels();
		labels.setFormatter("this.value +' mb'");
		style = new Style();
		style.setColor(colors[2]);
		labels.setStyle(style);
		y3.setLabels(labels);
		y3.setOpposite(true);
		chart.drawChart(conf);

		Tooltip tooltip = new Tooltip();
		tooltip.setFormatter("function() { "
				+ "var unit = { 'Rainfall': 'mm', 'Temperature': '°C', 'Sea-Level Pressure': 'mb' }[this.series.name];"
				+ "return ''+ this.x +': '+ this.y +' '+ unit; }");
		conf.setTooltip(tooltip);

		Legend legend = new Legend();
		legend.setLayout(LayoutDirection.VERTICAL);
		legend.setAlign(HorizontalAlign.LEFT);
		legend.setX(120);
		legend.setVerticalAlign(VerticalAlign.TOP);
		legend.setY(80);
		legend.setFloating(true);
		conf.setLegend(legend);

		DataSeries series = new DataSeries();
		PlotOptionsColumn plotOptionsColumn = new PlotOptionsColumn();
		plotOptionsColumn.setColor(colors[0]);
		series.setPlotOptions(plotOptionsColumn);
		series.setName("Rainfall");
		series.setyAxis(1);
		series.setData(49.9, 71.5, 106.4, 129.2, 144.0, 176.0, 135.6, 148.5,
				216.4, 194.1, 95.6, 54.4);
		conf.addSeries(series);

		series = new DataSeries();
		PlotOptionsSpline plotOptionsSpline = new PlotOptionsSpline();
		plotOptionsSpline.setColor(colors[2]);
		series.setPlotOptions(plotOptionsSpline);
		series.setName("Sea-Level Pressure");
		series.setyAxis(2);
		series.setData(1016, 1016, 1015.9, 1015.5, 1012.3, 1009.5, 1009.6,
				1010.2, 1013.1, 1016.9, 1018.2, 1016.7);
		conf.addSeries(series);

		series = new DataSeries();
		plotOptionsSpline = new PlotOptionsSpline();
		plotOptionsSpline.setColor(colors[1]);
		series.setPlotOptions(plotOptionsSpline);
		series.setName("Temperature");
		series.setData(7.0, 6.9, 9.5, 14.5, 18.2, 21.5, 25.2, 26.5, 23.3, 18.3,
				13.9, 9.6);
		conf.addSeries(series);

		chart.drawChart(conf);

		return chart;
	}

	public Component getChartColumnPie() {
		Chart chart = new Chart();
		Configuration conf = chart.getConfiguration();
		conf.setTitle("Combinação de coluna e pizza");
		conf.setExporting(true);
		conf.getExporting().setWidth(800);

		XAxis x = new XAxis();
		x.setCategories(new String[] { "Apples", "Oranges", "Pears", "Bananas",
		"Plums" });
		conf.addxAxis(x);

		Style labelStyle = new Style();
		labelStyle.setTop("8px");
		labelStyle.setLeft("40px");
		conf.setLabels(new HTMLLabels(labelStyle, new HTMLLabelItem(
				"Total fruit consumption")));

		DataSeries series = new DataSeries();
		PlotOptionsColumn plotOptions = new PlotOptionsColumn();
		series.setPlotOptions(plotOptions);
		series.setName("Jane");
		series.setData(3, 2, 1, 3, 4);
		conf.addSeries(series);

		series = new DataSeries();
		plotOptions = new PlotOptionsColumn();
		series.setPlotOptions(plotOptions);
		series.setName("John");
		series.setData(2, 3, 5, 7, 6);
		conf.addSeries(series);

		series = new DataSeries();
		plotOptions = new PlotOptionsColumn();
		series.setPlotOptions(plotOptions);
		series.setName("Joe");
		series.setData(4, 3, 3, 9, 0);
		conf.addSeries(series);

		series = new DataSeries();
		PlotOptionsSpline splinePlotOptions = new PlotOptionsSpline();
		Marker marker = new Marker();
		marker.setLineWidth(2);
		marker.setLineColor(new SolidColor("black"));
		marker.setFillColor(new SolidColor("white"));
		splinePlotOptions.setMarker(marker);
		splinePlotOptions.setColor(new SolidColor("black"));
		series.setPlotOptions(splinePlotOptions);
		series.setName("Average");
		series.setData(3, 2.67, 3, 6.33, 3.33);
		conf.addSeries(series);

		series = new DataSeries();
		series.setPlotOptions(new PlotOptionsPie());
		series.setName("Total consumption");
		DataSeriesItem item = new DataSeriesItem("Jane", 13);
		series.add(item);
		item = new DataSeriesItem("John", 23);
		series.add(item);
		item = new DataSeriesItem("Joe", 19);
		series.add(item);

		PlotOptionsPie plotOptionsPie = new PlotOptionsPie();
		plotOptionsPie.setSize("100px");
		plotOptionsPie.setCenter("100px", "80px");
		plotOptionsPie.setShowInLegend(false);
		plotOptionsPie.setShowInLegend(false);
		series.setPlotOptions(plotOptionsPie);
		conf.addSeries(series);

		chart.drawChart(conf);
		return chart;
	}
}
