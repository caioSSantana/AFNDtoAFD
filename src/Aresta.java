
public class Aresta {
	private Vertice origem;
	private Vertice destino;
	private String id;
	private String term;
	
	
	
	public Aresta(String id) {
		super();
		this.id = id;
	}
	public Aresta() {
		super();
	}
	public Vertice getOrigem() {
		return origem;
	}
	public void setOrigem(Vertice origem) {
		this.origem = origem;
	}
	public Vertice getDestino() {
		return destino;
	}
	public void setDestino(Vertice destino) {
		this.destino = destino;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTerm() {
		return term;
	}
	public void setTerm(String term) {
		this.term = term;
	}
	
	
}
