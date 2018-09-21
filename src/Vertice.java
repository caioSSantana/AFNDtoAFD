import java.util.ArrayList;

public class Vertice {
	private String id;
	private Vertice pred;
	private ArrayList<Vertice> adj = new ArrayList<Vertice>();
	private boolean end = false;
	
	public boolean getEnd(){
		return end;
	}
	
	public void setEnd(boolean end){
		this.end = end;
	}
	
	public Vertice() {
		
	}
	
	public Vertice(String id) {
		this.id = id;
	}

	public Vertice(Vertice v) {
		this.id = v.id;
		this.pred = v.pred;
		this.adj = v.adj;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public Vertice getPred() {
		return pred;
	}
	
	public void setPred(Vertice pred) {
		this.pred = pred;
	}
	
	public ArrayList<Vertice> getAdj() {
		return adj;
	}
	
	public void setAdj(ArrayList<Vertice> adj) {
		this.adj = adj;
	}
	
	
	
}
