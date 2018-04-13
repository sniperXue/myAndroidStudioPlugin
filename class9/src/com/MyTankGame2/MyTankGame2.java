/**
 * 功能：坦克游戏的2.0
 * 1.画出坦克
 * 2.坦克可以上下左右移动
 * 3.坦克可以转方向
 */
package com.MyTankGame2;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Vector;

public class MyTankGame2 extends JFrame{
    MyPanel mp=null;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyTankGame2 mtg=new MyTankGame2();
	}
	
	//构造函数
	public MyTankGame2(){
		mp=new MyPanel();
		this.add(mp);
		//注册监听
		this.addKeyListener(mp);
		this.setSize(400, 300);
		this.setTitle("坦克大战");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

}

//3
//我的面板
class MyPanel extends JPanel implements KeyListener{
	//定义一个我的坦克
	Hero hero =null;
	//定义敌人的坦克组
	Vector<EnemyTank> ets=new Vector<EnemyTank>();
	int enSize=3;
	
	public MyPanel(){
		hero=new Hero(10,10);
		//初始化敌人的坦克
		for(int i=0;i<enSize;i++){
			//创建一辆敌人坦克的对象，然后加入
			EnemyTank et=new EnemyTank((i+1)*50,0);
			et.setColor(1);
			et.setDirect(2);
			ets.add(et);
		}
	}
	public void paint(Graphics g){
		super.paint(g);
		
		this.drawTank(hero.getX(), hero.getY(), g, this.hero.getDirect(), 0);
		//画出敌人的坦克
		for(int i=0;i<ets.size();i++){
			
			this.drawTank(ets.get(i).getX(), ets.get(i).getY(), g, ets.get(i).getDirect(), 1);
		}
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
		case 1:
		    //炮筒向右
		    //三个矩形 （30*5两个  10*20一个）一个圆 一个线
			//1.画出上面的矩形
			g.fill3DRect(x, y, 30, 5,false);
			//2.画出下面的矩形
			g.fill3DRect(x, y+15, 30, 5,false);
			//3.画中间矩形
			g.fill3DRect(x+5, y+5, 20, 10,false);
			//4.画一个圆
			g.fillOval(x+10, y+5, 10, 10);
			//5.画一个线
			g.drawLine(x+15, y+10,x+30, y+10);
			break;
		case 2:
			//向下
			//1.画出左面的矩形
			g.fill3DRect(x, y, 5, 30,false);
			//2.画出右面的矩形
			g.fill3DRect(x+15, y, 5, 30,false);
			//3.画中间矩形
			g.fill3DRect(x+5, y+5, 10, 20,false);
			//4.画一个圆
		    g.fillOval(x+5, y+10, 10, 10);
		    //5.画一个线
		    g.drawLine(x+10, y+15,x+10, y+30);
			break;
		case 3:
			//向左
			//1.画出上面的矩形
			g.fill3DRect(x, y, 30, 5,false);
			//2.画出下面的矩形
			g.fill3DRect(x, y+15, 30, 5,false);
			//3.画中间矩形
			g.fill3DRect(x+5, y+5, 20, 10,false);
			//4.画一个圆
			g.fillOval(x+10, y+5, 10, 10);
			//5.画一个线
			g.drawLine(x+15, y+10,x, y+10);
			break;
			
		}
				
	}
	//键按下处理 a表示向左边  s表示向 下
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getKeyCode()==KeyEvent.VK_W){
			//设置我的坦克方向
			this.hero.setDirect(0);
			this.hero.moveUp();
		}else if(arg0.getKeyCode()==KeyEvent.VK_D){
			//向右
			this.hero.moveRight();
			this.hero.setDirect(1);
		}else if(arg0.getKeyCode()==KeyEvent.VK_S){
			//向下
			this.hero.setDirect(2);
			this.hero.moveDown();
	    }else if(arg0.getKeyCode()==KeyEvent.VK_A){
		    //向左
	    	this.hero.setDirect(3);
	    	this.hero.moveLeft();
	    }
		this.repaint();
	}		
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}


