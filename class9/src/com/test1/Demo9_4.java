/**
 * ���ܣ�С���˶����������̣�������¼�������Ƶ����
 */
package com.test1;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class Demo9_4 extends JFrame {
    MyPanel mp=null;
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Demo9_4 demo9_4=new Demo9_4();
	}
	
	//����
	public Demo9_4(){
		mp=new MyPanel();
		
		//mp���뵽JFrame
		this.add(mp);
		
		//ʵ�ּ���
		this.addKeyListener(mp);
		
		this.setSize(400,300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
	}

}

//�����Լ���Panel���
class MyPanel extends JPanel implements KeyListener{
	int x=30;
	int y=30;
	public void paint(Graphics g){
		super.paint(g);
		g.fillOval(x, y, 10, 10);
	}

	@Override
	public void keyPressed(KeyEvent e) {//����ѹ��ȥ
		// TODO Auto-generated method stub
		//System.out.println("��������"+(char)e.getKeyCode());
		if(e.getKeyCode()==KeyEvent.VK_DOWN){
			y+=3;
		}else if(e.getKeyCode()==KeyEvent.VK_UP){
			y-=3;
		}else if(e.getKeyCode()==KeyEvent.VK_LEFT){
			x-=3;
		}else if(e.getKeyCode()==KeyEvent.VK_RIGHT){
			x+=3;
		}
		//����repaint�����������»��ƽ���
		this.repaint();
	}

	@Override
	public void keyReleased(KeyEvent arg0) {//�����ͷ��ˣ��ɿ���
		// TODO Auto-generated method stub
		System.out.println("�����ɿ�");
	}

	@Override
	public void keyTyped(KeyEvent arg0) {//F1 F2��������һ��ֵ�����type���Ǵ�ӡ
		// TODO Auto-generated method stub
		
	}
}
