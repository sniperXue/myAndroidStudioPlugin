/**
 * ���ܣ������¼��������
 */
package com.test;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
public class Demo9_3 extends JFrame implements ActionListener{
    //����һ��panel
	JPanel mp=null;
	JButton jb1=null;
	JButton jb2=null;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Demo9_3 demo9_3=new Demo9_3();
	}
	public Demo9_3(){
		mp=new JPanel();
		//�¼�Դ
		jb1=new JButton("��ɫ");
		jb2=new JButton("��ɫ");
		Cat myCat1=new Cat();
		this.add(jb1,BorderLayout.NORTH);
		mp.setBackground(Color.BLACK);
		this.add(mp);
		this.add(jb2,BorderLayout.SOUTH);
		
		//ע�����
		jb1.addActionListener(this);//����demo9_3�������
		
		jb1.addActionListener(myCat1);
		//��������仰�Ϳ����Ƴ��¼�Դ������jb1���¼������߶�����myCat1
		//ָ��action����
		jb1.setActionCommand("��ɫ");
		jb2.addActionListener(this);
		jb2.setActionCommand("��ɫ");
		
		this.setSize(200,150);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	//���¼�����ķ���
	@Override
	public void actionPerformed(ActionEvent e) {//�¼�e��һ������
		// TODO Auto-generated method stub
		//�ж����ĸ���ť�����
		if(e.getActionCommand().equals("��ɫ")){
			System.out.println("�������Ǻ�ɫ��ť");
			mp.setBackground(Color.BLACK);
		}else if(e.getActionCommand().equals("��ɫ")){
			System.out.println("�������Ǻ�ɫ��ť");
			mp.setBackground(Color.red);
		}else{
			System.out.println("��֪��");
		}
	}

}
class Cat implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals("��ɫ")){
			System.out.println("èèҲ֪���������Ǻ�ɫ��ť");
			
		}else if(e.getActionCommand().equals("��ɫ")){
			System.out.println("èèҲ֪���������Ǻ�ɫ��ť");
			
		}else{
			System.out.println("��֪��");
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
