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
	
	//���캯��
	public Demo9_5(){
		mp=new MyPanel();
		this.add(mp);
		
		//ע�����
		this.addMouseListener(mp);
		this.addKeyListener(mp);
		this.addMouseMotionListener(mp);
		this.addWindowListener(mp);
		
		this.setSize(400, 300);
        this.setVisible(true);		
	}

}
//1.��MyPanel֪����갴�µ���Ϣ������֪�������λ��
//2.��MyPanel֪���ĸ���������
//3.��MyPanel֪������ƶ�����ק
//4.��MyPanel֪�����ڵı仯����С������󻯡��رգ�
class MyPanel extends JPanel implements WindowListener,MouseListener,KeyListener,MouseMotionListener{
	public void paint(Graphics g){
		super.paint(g);
	}
	
	
    //�����
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("�������x="+arg0.getX()+" "+    "  y="+arg0.getY());
	}
	
	
    //����ƶ���MyPanel
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("�������");
	}
	
	
    //�뿪
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("����뿪��");
	}
	
	
	//��갴��
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
    //����ɿ�
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
	//������
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println(arg0.getKeyChar()+"��������");
	}
	
	//���ɿ�
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	//������������F1�ǲ��ᱻ������
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	//������ק
	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("��굱ǰ�ƶ������� x="+arg0.getX()
		                   +"y="+arg0.getY());
	}

	//���ڼ�����
	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		System.out.println("���������´���");
	}

	//���ڹر���
	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		System.out.println("���ڹر���");
	}

	//�������ڹر�
	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	//������С��
	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		System.out.println("������С��");
	}


	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	//���ڴ���
	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}