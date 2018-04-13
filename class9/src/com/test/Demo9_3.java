/**
 * 功能：讲解事件处理机制
 */
package com.test;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
public class Demo9_3 extends JFrame implements ActionListener{
    //定义一个panel
	JPanel mp=null;
	JButton jb1=null;
	JButton jb2=null;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Demo9_3 demo9_3=new Demo9_3();
	}
	public Demo9_3(){
		mp=new JPanel();
		//事件源
		jb1=new JButton("黑色");
		jb2=new JButton("红色");
		Cat myCat1=new Cat();
		this.add(jb1,BorderLayout.NORTH);
		mp.setBackground(Color.BLACK);
		this.add(mp);
		this.add(jb2,BorderLayout.SOUTH);
		
		//注册监听
		jb1.addActionListener(this);//想让demo9_3对象监听
		
		jb1.addActionListener(myCat1);
		//从上面这句话就可以推出事件源对象是jb1，事件监听者对象是myCat1
		//指定action命令
		jb1.setActionCommand("黑色");
		jb2.addActionListener(this);
		jb2.setActionCommand("红色");
		
		this.setSize(200,150);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	//对事件处理的方法
	@Override
	public void actionPerformed(ActionEvent e) {//事件e是一个对象
		// TODO Auto-generated method stub
		//判断是哪个按钮被点击
		if(e.getActionCommand().equals("黑色")){
			System.out.println("你点击的是黑色按钮");
			mp.setBackground(Color.BLACK);
		}else if(e.getActionCommand().equals("红色")){
			System.out.println("你点击的是红色按钮");
			mp.setBackground(Color.red);
		}else{
			System.out.println("不知道");
		}
	}

}
class Cat implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals("黑色")){
			System.out.println("猫猫也知道你点击的是黑色按钮");
			
		}else if(e.getActionCommand().equals("红色")){
			System.out.println("猫猫也知道你点击的是红色按钮");
			
		}else{
			System.out.println("不知道");
		}
	}
	
}
/*
class MyPanel extends JPanel{
	public void paint(Graphics g){
		super.paint(g);
		g.fillRect(0, 0, width, height);
		
	}
	
}
*/
