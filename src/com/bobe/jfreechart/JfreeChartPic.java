package com.bobe.jfreechart;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.junit.Test;

public class JfreeChartPic {
	
	@Test
	public void testCreatePicDemo(){
		//创建数据填充
		DefaultPieDataset dataset =new DefaultPieDataset();
		dataset.setValue("江西", 3215);
		dataset.setValue("宁夏", 3255);
		dataset.setValue("浙江", 3215);
		dataset.setValue("北京", 4315);
		dataset.setValue("甘肃", 2215);
		dataset.setValue("西藏", 1015);
		//通过ChartFactory创建JfreeChart对象
		JFreeChart chart = ChartFactory.createPieChart("各省质数", dataset,true,true,true);
		//设置字体解决中文方块问题
		
		chart.getLegend().setItemFont(new java.awt.Font("宋体",2,15));
		chart.setBorderVisible(true);
		ChartFrame chartFrame=new ChartFrame("hello world",chart);
		chartFrame.pack();
		chartFrame.setVisible(true);//图形是否可见
        try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
	}
	
	@Test
	public void testPicApplication() throws Exception{
		DefaultPieDataset dataset =new DefaultPieDataset();
		dataset.setValue("江西", 3215);
		dataset.setValue("宁夏", 3255);
		dataset.setValue("浙江", 3215);
		dataset.setValue("北京", 4315);
		dataset.setValue("甘肃", 2215);
		dataset.setValue("西藏", 1015);
		JFreeChart chart =ChartFactory.createPieChart3D("大学之星", dataset);
		
		chart.setBackgroundImageAlpha((float) 1.0);
		chart.setTitle("hello world2");
		ChartFrame frame =new ChartFrame("3D饼图", chart);
		frame.setVisible(true);
		frame.pack();
		Thread.sleep(3000);
	}
	
	//java 柱状图
	@Test
	public void testLinePic(){
		//通过工厂factory创建chart
		
		
		//创建数据集
		DefaultCategoryDataset dataset =new DefaultCategoryDataset();
		//创建数据填充
		
		dataset.addValue(1, new Integer(2), new Integer(2));
		dataset.addValue(1, new Integer(3), new Integer(3));
		dataset.addValue(1, new Integer(2), new Integer(4));
		dataset.addValue(1, new Integer(9), new Integer(2));
		
		
		JFreeChart chart= ChartFactory.createLineChart("linePic", "X", "Y", dataset);
		BufferedImage image = chart.createBufferedImage(500, 500);
		
		File fos_jpg = new File("E://bloodSugarChart.jpg ");
		
		try {
			ChartUtilities.saveChartAsJPEG(fos_jpg, chart, 500, 500);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	

}
