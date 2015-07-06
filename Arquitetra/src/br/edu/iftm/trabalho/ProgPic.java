package br.edu.iftm.trabalho;

public class ProgPic {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PIC18F4550 pic = new PIC18F4550();
		pic.getMemPrograma().escreverInstrucao(0, 0b0000111000000001);
		pic.getMemPrograma().escreverInstrucao(2, 0b0000111000111111);
		
		// TODO Auto-generated method stub

	}

}
