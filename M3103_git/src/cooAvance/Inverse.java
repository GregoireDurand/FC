package cooAvance;

public class Inverse extends Figure{
	private Forme f;
	public Inverse(Forme f){
		super(f.getWidth(), f.getHeight());
		this.f=f;
	}

	public char getChar(int w, int h) {
		if(f.getChar(w, h)=='*'){
			return ' ';
		}else{
			return '*';
		}
	}

}
