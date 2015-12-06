package cooAvance;

public class Triangle extends Figure{

	public Triangle(int w, int h){
		super(w,h);
		
	}
	
	public char getChar(int l, int c) {
		int d;
		d=l+c;
		if(d>=getHeight()){
			return ' ';
		}else if(d>=getWidth()){
			return ' ';
		}else{
			return '*';
		}
	}

}
