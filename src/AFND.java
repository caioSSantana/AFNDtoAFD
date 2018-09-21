import java.util.ArrayList;
import java.util.Iterator;

public class AFND {
	ArrayList<Aresta> arestas = new ArrayList<Aresta>();
	ArrayList<Vertice> vertices = new ArrayList<Vertice>();
	
	ArrayList<ArrayList<String>> E_closures = new ArrayList<ArrayList<String>>();
	ArrayList<String> terms = new ArrayList<String>();
	
	ArrayList<Aresta> AFDarestas = new ArrayList<Aresta>();
	ArrayList<Vertice> AFDvertices = new ArrayList<Vertice>();
	
	
	
	public void gerarAFD(){
		ArrayList<String> inicial = E_closure(vertices.get(0).getId());
		if(getVertice(inicial.get(0)).getAdj().size() == 0){
			
		}else{
			String aux = "";
			String t;
			for (int i = 0; i < inicial.size(); i++) {
				aux += inicial.get(i);
			}
			novoVerticeAFD(aux);
			for (int i = 0; i < inicial.size(); i++) {
				for (int j = 0; j < getVertice(inicial.get(i)).getAdj().size(); j++) {
					if(getTermAresta(getVertice(inicial.get(i)).getId(), getVertice(inicial.get(i)).getAdj().get(j).getId()) != "E"){
						t = getTermAresta(getVertice(inicial.get(i)).getId(), getVertice(inicial.get(i)).getAdj().get(j).getId());
						if(!existeVerticeAFD(getVertice(inicial.get(i)).getAdj().get(j).getId())){
							gerarAFD(t ,aux, E_closure(getVertice(inicial.get(i)).getAdj().get(j).getId()));
						}
					}
				}	
			}
		}
	}
	
	public void gerarAFD(String term ,String v ,ArrayList<String> a){
		if(getVertice(a.get(0)).getAdj().size() == 0){
			
		}else{
			String aux = "";
			String t;
			for (int i = 0; i < a.size(); i++) {
				aux += a.get(i);
			}
			if(!existeVerticeAFD(aux)){
				novoVerticeAFD(aux);
				novaArestaAFD(v + aux, v, aux, term);
				for (int i = 0; i < a.size(); i++) {
					for (int j = 0; j < getVertice(a.get(i)).getAdj().size(); j++) {
						if(getTermAresta(getVertice(a.get(i)).getId(), getVertice(a.get(i)).getAdj().get(j).getId()) != "E"){
							t = getTermAresta(getVertice(a.get(i)).getId(), getVertice(a.get(i)).getAdj().get(j).getId());
							if(!existeVerticeAFD(getVertice(a.get(i)).getAdj().get(j).getId())){
								gerarAFD(t ,aux, E_closure(getVertice(a.get(i)).getAdj().get(j).getId()));
							}
						}
					}	
				}
			}else
				fimAFD();
		}
	}
	
	public void fimAFD(){
		String a = AFDvertices.get(AFDvertices.size() - 1).getId();
		ArrayList<String> aux2 = new ArrayList<String>();
		String aux = "";
		
		for (int i = 0; i < a.length(); i++) {
			for (int j = 0; j < getVertice(String.valueOf(a.charAt(i))).getAdj().size(); j++) {
				if(getTermAresta(getVertice(String.valueOf(a.charAt(i))).getId(), getVertice(String.valueOf(a.charAt(i))).getAdj().get(j).getId()) != "E"){
					String t = getTermAresta(getVertice(String.valueOf(a.charAt(i))).getId(), getVertice(String.valueOf(a.charAt(i))).getAdj().get(j).getId());
					aux2 = E_closure(getVertice(String.valueOf(a.charAt(i))).getAdj().get(j).getId());
					for (int k = 0; k < aux2.size(); k++) {
						aux += aux2.get(k);
					}
					novaArestaAFD(a + aux, a, aux, t);
				}
			}
		}
	}
	
	public void novoVertice(String id){
		Vertice aux = new Vertice(id);
		vertices.add(aux);		
	}
	
	public void novoVerticeAFD(String id){
		Vertice aux = new Vertice(id);
		AFDvertices.add(aux);		
	}

	
	public void novaAresta(String id, String origem, String destino, String term){
		Aresta aux = new Aresta(id);
		boolean a = false;
		for (int i = 0; i < terms.size(); i++) {
			if(term == terms.get(i))
				a = true;
		}
		
		if(a == false)
			terms.add(term);
		
		for (Vertice v : vertices) {
			if(v.getId().equals(origem)){
				Vertice auxV = new Vertice(origem);
				aux.setOrigem(auxV);
			}
		}
		
		for (Vertice v : vertices) {
			if(v.getId().equals(destino)){
				Vertice auxV = new Vertice(destino);
				aux.setDestino(auxV);
			}
			
			if(v.getId().equals(origem)){
				Vertice auxV = new Vertice(destino);
				v.getAdj().add(auxV);
			}
		}
		aux.setTerm(term);
		arestas.add(aux);
	}
	
	public void novaArestaAFD(String id, String origem, String destino, String term){
		Aresta aux = new Aresta(id);
		boolean a = false;
		for (int i = 0; i < terms.size(); i++) {
			if(term == terms.get(i))
				a = true;
		}
		
		if(a == false)
			terms.add(term);
		
		for (Vertice v : AFDvertices) {
			if(v.getId().equals(origem)){
				Vertice auxV = new Vertice(origem);
				aux.setOrigem(auxV);
			}
		}
		
		for (Vertice v : AFDvertices) {
			if(v.getId().equals(destino)){
				Vertice auxV = new Vertice(destino);
				aux.setDestino(auxV);
			}
			
			if(v.getId().equals(origem)){
				Vertice auxV = new Vertice(destino);
				v.getAdj().add(auxV);
			}
		}
		aux.setTerm(term);
		AFDarestas.add(aux);
	}
	
	public String getTermAresta(String origem, String destino){
		for(int i = 0; i < arestas.size();i++){
			if(arestas.get(i).getOrigem().getId().equals(origem )&& arestas.get(i).getDestino().getId().equals(destino)){
				return arestas.get(i).getTerm();
			}
		}
		return null;
	}
	
	public boolean existeAresta(String origem, String destino){
		for(int i = 0; i < arestas.size();i++){
			if(arestas.get(i).getOrigem().getId().equals(origem )&& arestas.get(i).getDestino().getId().equals(destino)){
				return true;
			}
		}
		return false;
	}
	
	public Vertice getVertice(String id){
		for (Vertice v : vertices) {
			if(v.getId().equals(id)){
				return v;
			}
		}
		return null;
	}
	
	public boolean existeVertice(String id){
		for (Vertice v : vertices) {
			if(v.getId().equals(id)){
				return true;
			}
		}
		return false;
	}
	
	public String getTermArestaAFD(String origem, String destino){
		for(int i = 0; i < AFDarestas.size();i++){
			if(AFDarestas.get(i).getOrigem().getId().equals(origem )&& AFDarestas.get(i).getDestino().getId().equals(destino)){
				return AFDarestas.get(i).getTerm();
			}
		}
		return null;
	}
	
	public boolean existeArestaAFD(String origem, String destino){
		for(int i = 0; i < AFDarestas.size();i++){
			if(AFDarestas.get(i).getOrigem().getId().equals(origem )&& AFDarestas.get(i).getDestino().getId().equals(destino)){
				return true;
			}
		}
		return false;
	}
	
	public Vertice getVerticeAFD(String id){
		for (Vertice v : AFDvertices) {
			if(v.getId().equals(id)){
				return v;
			}
		}
		return null;
	}
	
	public boolean existeVerticeAFD(String id){
		for (Vertice v : AFDvertices) {
			if(v.getId().equals(id)){
				return true;
			}
		}
		return false;
	}
	
	public void listaAdjacencia(){
		for (Vertice v : vertices) {
			System.out.print(v.getId());
			for(Vertice auxV : v.getAdj()){
				System.out.print("--> " + auxV.getId());
			}
			System.out.println();
		}
	}
	
	public void tabela(){
		String [][] tabela = new String[vertices.size() + 1][terms.size() + 1];
		String [][] m = matrizAdjacencia();
		
		vertices.get(vertices.size() - 1).setEnd(true);
		
		tabela[0][0] = "\t";
		for(int i = 1; i <= terms.size(); i++){
			tabela[0][i] = terms.get(i - 1);
		}
		
		for(int i = 1; i <= vertices.size(); i++){
			if(vertices.get(i - 1).getEnd()){
				tabela[i][0] = vertices.get(i - 1).getId() + "*";
			}else
				tabela[i][0] = vertices.get(i - 1).getId();
		}
		
		for(int i = 1; i <= vertices.size(); i ++){
			for(int j = 1; j <= terms.size(); j ++){
					tabela[i][j] = "_";
			}
		}
		
		for(int i = 1; i <= vertices.size(); i ++){
			for (int j = 1; j <= vertices.size();j++) {
				if(m[i][j] != "_"){
					
					for(int k = 1; k <= terms.size(); k ++){
						if(tabela[0][k] == m[i][j]){
							if(tabela[i][k] != "_"){
								tabela[i][k] =tabela[i][k] + "," + m[0][j];
							}else
								tabela[i][k] = m[0][j];
						}
					}
				}
			}
		}
		System.out.println();
		
		for(int i = 0; i < tabela.length; i ++){
			for(int j = 0; j < tabela[i].length; j ++){
				System.out.print(tabela[i][j]);
				if(!tabela[i][j].equals("\t"))
					System.out.print("\t");
			}
			
			System.out.println();
		}
	}
	
	public String[][] matrizAdjacencia(){
		String [][] matriz = new String[vertices.size() + 1][vertices.size() + 1];
		
		matriz[0][0] = "\t";
		for(int i = 1; i <= vertices.size(); i++){
			matriz[0][i] = vertices.get(i - 1).getId();
		}
		
		for(int i = 1; i <= vertices.size(); i++){
			matriz[i][0] = vertices.get(i - 1).getId();
		}
		
		for(int i = 1; i <= vertices.size(); i ++){
			for(int j = 1; j <= vertices.size(); j ++){
				if(existeAresta(matriz[i][0], matriz[0][j])){
					matriz[i][j] = getTermAresta(matriz[i][0], matriz[0][j]);
				} else {
					matriz[i][j] = "_";
				}
			}
		}
		
		
		for(int i = 0; i < matriz.length; i ++){
			for(int j = 0; j < matriz.length; j ++){
				System.out.print(matriz[i][j]);
				if(!matriz[i][j].equals("\t"))System.out.print("\t");
			}
			
			System.out.println();
		}
		return matriz;
	}
	
	public ArrayList<String> E_closure(String id){
		ArrayList<String> E_closure = new ArrayList<String>();
		if(existeVertice(id)){
			E_closure.add(id);
		}
		for (int i = 0; i < getVertice(id).getAdj().size(); i++){
			if(getTermAresta(id, getVertice(id).getAdj().get(i).getId()) == "E"){
				E_closure(getVertice(id).getAdj().get(i).getId(), E_closure);
			}
		}
		return E_closure;	
	}
	
	public void E_closure(String id, ArrayList<String> E_closure){
		boolean a = false;
		for (int i = 0; i < E_closure.size(); i++) {
			if(id == E_closure.get(i))
				a = true;
		}
		if(a == false){
			E_closure.add(id);
			for (int i = 0; i < getVertice(id).getAdj().size(); i++){
				if(getTermAresta(id, getVertice(id).getAdj().get(i).getId()) == "E"){
					E_closure(getVertice(id).getAdj().get(i).getId(), E_closure);
				}
			}
		}
	}
	
	public void E_closures(){
		for (int i = 0; i < vertices.size(); i++) {
			E_closures.add(E_closure(vertices.get(i).getId()));
		}
		for (int i = 0; i < E_closures.size(); i++) {
			System.out.print("E-closure(" + E_closures.get(i).get(0) + "): {");
			for (int j = 0; j < E_closures.get(i).size(); j++) {
				System.out.print(E_closures.get(i).get(j));
			}
			System.out.print("}");
			System.out.println();
		}
	}
	
	public void tabelaAFD(){
		String [][] tabela = new String[AFDvertices.size() + 1][terms.size() + 1];
		String [][] m = matrizAdjacenciaAFD();
		
		tabela[0][0] = "\t";
		for(int i = 1; i <= terms.size(); i++){
			tabela[0][i] = terms.get(i - 1);
		}
		
		for (int j = 0; j < AFDvertices.size(); j++) {
			for (int k = 0; k < AFDvertices.get(j).getId().length(); k++) {
				if(String.valueOf(AFDvertices.get(j).getId().charAt(k)).equals(vertices.get(vertices.size() - 1).getId())){
					AFDvertices.get(j).setEnd(true);
				}
			}
		}
		System.out.println();
		
		for(int i = 0; i < AFDvertices.size(); i++){
			System.out.print(AFDvertices.get(i).getEnd() + "   ");
		}
		
		System.out.println();
		for(int i = 1; i <= AFDvertices.size(); i++){
			if(AFDvertices.get(i - 1).getEnd()){
				tabela[i][0] = AFDvertices.get(i - 1).getId() + "*";
			}else
				tabela[i][0] = AFDvertices.get(i - 1).getId();
		}
		
		for(int i = 1; i <= AFDvertices.size(); i ++){
			for(int j = 1; j <= terms.size(); j ++){
					tabela[i][j] = "_";
			}
		}
		
		for(int i = 1; i <= AFDvertices.size(); i ++){
			for (int j = 1; j <= AFDvertices.size();j++) {
				//System.out.print(m[i][j]);
				if(m[i][j] != "_"){
					
					for(int k = 1; k <= terms.size(); k ++){
						if(tabela[0][k] == m[i][j]){
							if(tabela[i][k] != "_"){
								tabela[i][k] =tabela[i][k] + "," + m[0][j];
							}else
								tabela[i][k] = m[0][j];
						}
					}
				}
			}
		}
		System.out.println();
		
		for(int i = 0; i < tabela.length; i ++){
			for(int j = 0; j < tabela[i].length; j ++){
				System.out.print(tabela[i][j]);
				if(!tabela[i][j].equals("\t"))
					System.out.print("\t");
			}
			
			System.out.println();
		}
	}
	
	public String[][] matrizAdjacenciaAFD(){
		String [][] matriz = new String[AFDvertices.size() + 1][AFDvertices.size() + 1];
		
		matriz[0][0] = "\t";
		for(int i = 1; i <= AFDvertices.size(); i++){
			matriz[0][i] = AFDvertices.get(i - 1).getId();
		}
		
		for(int i = 1; i <= AFDvertices.size(); i++){
			matriz[i][0] = AFDvertices.get(i - 1).getId();
		}
		
		for(int i = 1; i <= AFDvertices.size(); i ++){
			for(int j = 1; j <= AFDvertices.size(); j ++){
				if(existeArestaAFD(matriz[i][0], matriz[0][j])){
					matriz[i][j] = getTermArestaAFD(matriz[i][0], matriz[0][j]);
				} else {
					matriz[i][j] = "_";
				}
			}
		}
		
		
		for(int i = 0; i < matriz.length; i ++){
			for(int j = 0; j < matriz.length; j ++){
				System.out.print(matriz[i][j]);
				if(!matriz[i][j].equals("\t"))System.out.print("\t");
			}
			
			System.out.println();
		}
		return matriz;
	}
}
