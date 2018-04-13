/**
 * 功能：小球运动，监听键盘，加深对事件处理机制的理解
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
	
	//构造
	public Demo9_4(){
		mp=new MyPanel();
		
		//mp加入到JFrame
		this.add(mp);
		
		//实现监听
		this.addKeyListener(mp);
		
		this.setSize(400,300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
	}

}

//定义自己的Panel面板
class MyPanel extends JPanel implements KeyListener{
	int x=30;
	int y=30;
	public void paint(Graphics g){
		super.paint(g);
		g.fillOval(x, y, 10, 10);
	}

	@Override
	public void keyPressed(KeyEvent e) {//键被压下去
		// TODO Auto-generated method stub
		//System.out.println("键被按下"+(char)e.getKeyCode());
		if(e.getKeyCode()==KeyEvent.VK_DOWN){
			y+=3;
		}else if(e.getKeyCode()==KeyEvent.VK_UP){
			y-=3;
		}else if(e.getKeyCode()==KeyEvent.VK_LEFT){
			x-=3;
		}else if(e.getKeyCode()==KeyEvent.VK_RIGHT){
			x+=3;
		}
		//调用repaint函数，来重新绘制界面
		this.repaint();
	}

	@Override
	public void keyReleased(KeyEvent arg0) {//键被释放了，松开了
		// TODO Auto-generated method stub
		System.out.println("键被松开");
	}

	@Override
	public void keyTyped(KeyEvent arg0) {//F1 F2键，键的一个值被输出type就是打印
		// TODO Auto-generated method stub
		
	}
}
