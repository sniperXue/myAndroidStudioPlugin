/**
 * ���ܣ�̹����Ϸ��2.0
 * 1.����̹��
 * 2.̹�˿������������ƶ�
 * 3.̹�˿���ת����
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
	
	//���캯��
	public MyTankGame2(){
		mp=new MyPanel();
		this.add(mp);
		//ע�����
		this.addKeyListener(mp);
		this.setSize(400, 300);
		this.setTitle("̹�˴�ս");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

}

//3
//�ҵ����
class MyPanel extends JPanel implements KeyListener{
	//����һ���ҵ�̹��
	Hero hero =null;
	//������˵�̹����
	Vector<EnemyTank> ets=new Vector<EnemyTank>();
	int enSize=3;
	
	public MyPanel(){
		hero=new Hero(10,10);
		//��ʼ�����˵�̹��
		for(int i=0;i<enSize;i++){
			//����һ������̹�˵Ķ���Ȼ�����
			EnemyTank et=new EnemyTank((i+1)*50,0);
			et.setColor(1);
			et.setDirect(2);
			ets.add(et);
		}
	}
	public void paint(Graphics g){
		super.paint(g);
		
		this.drawTank(hero.getX(), hero.getY(), g, this.hero.getDirect(), 0);
		//�������˵�̹��
		for(int i=0;i<ets.size();i++){
			
			this.drawTank(ets.get(i).getX(), ets.get(i).getY(), g, ets.get(i).getDirect(), 1);
		}
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
		case 1:
		    //��Ͳ����
		    //�������� ��30*5����  10*20һ����һ��Բ һ����
			//1.��������ľ���
			g.fill3DRect(x, y, 30, 5,false);
			//2.��������ľ���
			g.fill3DRect(x, y+15, 30, 5,false);
			//3.���м����
			g.fill3DRect(x+5, y+5, 20, 10,false);
			//4.��һ��Բ
			g.fillOval(x+10, y+5, 10, 10);
			//5.��һ����
			g.drawLine(x+15, y+10,x+30, y+10);
			break;
		case 2:
			//����
			//1.��������ľ���
			g.fill3DRect(x, y, 5, 30,false);
			//2.��������ľ���
			g.fill3DRect(x+15, y, 5, 30,false);
			//3.���м����
			g.fill3DRect(x+5, y+5, 10, 20,false);
			//4.��һ��Բ
		    g.fillOval(x+5, y+10, 10, 10);
		    //5.��һ����
		    g.drawLine(x+10, y+15,x+10, y+30);
			break;
		case 3:
			//����
			//1.��������ľ���
			g.fill3DRect(x, y, 30, 5,false);
			//2.��������ľ���
			g.fill3DRect(x, y+15, 30, 5,false);
			//3.���м����
			g.fill3DRect(x+5, y+5, 20, 10,false);
			//4.��һ��Բ
			g.fillOval(x+10, y+5, 10, 10);
			//5.��һ����
			g.drawLine(x+15, y+10,x, y+10);
			break;
			
		}
				
	}
	//�����´��� a��ʾ�����  s��ʾ�� ��
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getKeyCode()==KeyEvent.VK_W){
			//�����ҵ�̹�˷���
			this.hero.setDirect(0);
			this.hero.moveUp();
		}else if(arg0.getKeyCode()==KeyEvent.VK_D){
			//����
			this.hero.moveRight();
			this.hero.setDirect(1);
		}else if(arg0.getKeyCode()==KeyEvent.VK_S){
			//����
			this.hero.setDirect(2);
			this.hero.moveDown();
	    }else if(arg0.getKeyCode()==KeyEvent.VK_A){
		    //����
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


