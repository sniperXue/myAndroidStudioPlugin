package com.MyTankGame2;

//1
class Tank{
	//坦克的横纵坐标
	int x=0;
	int y=0;
	
	//坦克方向
	//0表示上  1表示右  2表示下  3表示左
	int direct=0;
	
	//坦克的速度
	int speed=5;
	//坦克颜色
	int color;
	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getDirect() {
		return direct;
	}

	public void setDirect(int direct) {
		this.direct = direct;
	}

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
	
	//坦克向上移动
	public void moveUp(){
		this.y = y-speed;
	}
	//坦克向右移动
	public void moveRight(){
		this.x= x+speed;
	}
	//向下移动
	public void moveDown(){
		this.y=y+speed;
	}
	//向左移动
	public void moveLeft(){
		this.x=x-speed;
	}
}

//敌人的坦克
class EnemyTank extends Tank{
	public EnemyTank(int x,int y){
		super(x,y);
	}
}