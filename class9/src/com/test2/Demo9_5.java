package com.test2;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class Demo9_5 extends JFrame{
    MyPanel mp=null;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Demo9_5 demo9_5=new Demo9_5();
	}
	
	//构造函数
	public Demo9_5(){
		mp=new MyPanel();
		this.add(mp);
		
		//注册监听
		this.addMouseListener(mp);
		this.addKeyListener(mp);
		this.addMouseMotionListener(mp);
		this.addWindowListener(mp);
		
		this.setSize(400, 300);
        this.setVisible(true);		
	}

}
//1.让MyPanel知道鼠标按下的消息，并且知道点击的位置
//2.让MyPanel知道哪个键按下了
//3.让MyPanel知道鼠标移动、拖拽
//4.让MyPanel知道窗口的变化（最小化、最大化、关闭）
class MyPanel extends JPanel implements WindowListener,MouseListener,KeyListener,MouseMotionListener{
	public void paint(Graphics g){
		super.paint(g);
	}
	
	
    //鼠标点击
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("鼠标点击了x="+arg0.getX()+" "+    "  y="+arg0.getY());
	}
	
	
    //鼠标移动到MyPanel
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("鼠标来了");
	}
	
	
    //离开
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("鼠标离开了");
	}
	
	
	//鼠标按下
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
    //鼠标松开
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
	//键按下
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println(arg0.getKeyChar()+"键被按下");
	}
	
	//键松开
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	//键的类型输入F1是不会被触发的
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	//键的拖拽
	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("鼠标当前移动的坐标 x="+arg0.getX()
		                   +"y="+arg0.getY());
	}

	//窗口激活了
	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		System.out.println("窗口又重新打开了");
	}

	//窗口关闭了
	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		System.out.println("窗口关闭了");
	}

	//窗口正在关闭
	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	//窗口最小化
	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		System.out.println("窗口缩小了");
	}


	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	//窗口打开了
	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}