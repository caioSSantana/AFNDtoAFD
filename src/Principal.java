import java.util.ArrayList;

//Vazio é representado por "E" 

public class Principal {

	public static void main(String[] args) {
		// (10)*
		AFND afn = new AFND();
		afn.novoVertice("A");
		afn.novoVertice("B");
		afn.novoVertice("C");
		afn.novoVertice("D");
		afn.novoVertice("E");
		afn.novoVertice("F");
		afn.novaAresta("AB", "A", "B", "E");
		afn.novaAresta("AF", "A", "F", "E");
		afn.novaAresta("BC", "B", "C", "1");
		afn.novaAresta("CD", "C", "D", "E");
		afn.novaAresta("DE", "D", "E", "0");
		afn.novaAresta("EB", "E", "B", "E");
		afn.novaAresta("EF", "E", "F", "E");
		
		afn.tabela();
		afn.E_closures();
		afn.gerarAFD();
		afn.tabelaAFD();
	}

}
