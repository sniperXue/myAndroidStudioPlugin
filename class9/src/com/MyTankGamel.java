/**
 * 功能：坦克游戏1.0
 * 1.画坦克
 * 
 * @author Administrator
 *
 */
package com;
import java.awt.*;
import javax.swing.*;


public class MyTankGamel extends JFrame{
    MyPanel mp=null;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyTankGamel mtg=new MyTankGamel();
	}
	
	//构造函数
	public MyTankGamel(){
		mp=new MyPanel();
		this.add(mp);
		this.setSize(400, 300);
		this.setTitle("坦克大战");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

}
//3
//我的面板
class MyPanel extends JPanel{
	//定义一个我的坦克
	Hero hero =null;
	public MyPanel(){
		hero=new Hero(10,10);
	}
	public void paint(Graphics g){
		super.paint(g);
		
		this.drawTank(hero.getX(), hero.getY(), g, 0, 0);
	}
	//画出tank的函数
	public void drawTank(int x,int y,Graphics g,int direct,int type){
		//判断是什么类型的坦克，敌人还是我的
		switch(type){
		case 0:
			g.setColor(Color.cyan);
			break;
		case 1:
			g.setColor(Color.yellow);
			break;
		   
		}
		//判断方向
		switch(direct){
		case 0:
			//画出我的坦克，到时再封装成一个函数
			//三个矩形 （30*5两个  10*20一个）一个圆 一个线
			//1.画出左面的矩形
			g.fill3DRect(x, y, 5, 30,false);
			//2.画出右面的矩形
			g.fill3DRect(x+15, y, 5, 30,false);
			//3.画中间矩形
			g.fill3DRect(x+5, y+5, 10, 20,false);
			//4.画一个圆
		    g.fillOval(x+5, y+10, 10, 10);
		    //5.画一个线
		    g.drawLine(x+10, y+15,x+10, y);
			break;
		}
				
	}

}

//1
class Tank{
	//坦克的横纵坐标
	int x=0;
	int y=0;
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	
	
	public Tank(int x,int y){
		this.x=x;
		this.y=y;
	}
	
}
//2
//我的坦克
class Hero extends Tank{
	public Hero(int x,int y){
		super(x,y);
	}
}