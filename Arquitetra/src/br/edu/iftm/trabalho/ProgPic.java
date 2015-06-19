package br.edu.iftm.trabalho;

public class ProgPic {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MemoriaDePrograma mem = new MemoriaDePrograma();
		mem.escreverInstrucao (2, 60000);
		
		int inst = mem.lerInstrucao(2);
		System.out.println(inst);
		
		// TODO Auto-generated method stub

	}

}
