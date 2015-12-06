package cooAvance;

public abstract class Figure implements Forme{
	
	public Figure (int w, int h){
		this.width=w;
		this.height=h;
	}

	protected int width;
	protected int height;

	public int getWidth(){
		return width;
	}
	public int getHeight(){
		return height;
	}
	
	public abstract char getChar(int l, int c);

}