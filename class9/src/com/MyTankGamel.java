/**
 * ���ܣ�̹����Ϸ1.0
 * 1.��̹��
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
	
	//���캯��
	public MyTankGamel(){
		mp=new MyPanel();
		this.add(mp);
		this.setSize(400, 300);
		this.setTitle("̹�˴�ս");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

}
//3
//�ҵ����
class MyPanel extends JPanel{
	//����һ���ҵ�̹��
	Hero hero =null;
	public MyPanel(){
		hero=new Hero(10,10);
	}
	public void paint(Graphics g){
		super.paint(g);
		
		this.drawTank(hero.getX(), hero.getY(), g, 0, 0);
	}
	//����tank�ĺ���
	public void drawTank(int x,int y,Graphics g,int direct,int type){
		//�ж���ʲô���͵�̹�ˣ����˻����ҵ�
		switch(type){
		case 0:
			g.setColor(Color.cyan);
			break;
		case 1:
			g.setColor(Color.yellow);
			break;
		   
		}
		//�жϷ���
		switch(direct){
		case 0:
			//�����ҵ�̹�ˣ���ʱ�ٷ�װ��һ������
			//�������� ��30*5����  10*20һ����һ��Բ һ����
			//1.��������ľ���
			g.fill3DRect(x, y, 5, 30,false);
			//2.��������ľ���
			g.fill3DRect(x+15, y, 5, 30,false);
			//3.���м����
			g.fill3DRect(x+5, y+5, 10, 20,false);
			//4.��һ��Բ
		    g.fillOval(x+5, y+10, 10, 10);
		    //5.��һ����
		    g.drawLine(x+10, y+15,x+10, y);
			break;
		}
				
	}

}

//1
class Tank{
	//̹�˵ĺ�������
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
//�ҵ�̹��
class Hero extends Tank{
	public Hero(int x,int y){
		super(x,y);
	}
}