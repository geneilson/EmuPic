package br.edu.iftm.trabalho;

public class MemoriaDePrograma {
	private byte[] dadosDePrograma = new byte[2097152];
	
	public byte[] getDadosDePrograma() {
		return dadosDePrograma;
	}

	public void setDadosDePrograma(byte[] dadosDePrograma) {
		this.dadosDePrograma = dadosDePrograma;
	}

	public MemoriaDePrograma() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MemoriaDePrograma(byte[] dadosDePrograma) {
		this.dadosDePrograma = dadosDePrograma;
	}
	
	public void EscreverNaMemoria(int pos, byte dado) {
		if(pos>=32768 && pos<=2097151){
			System.out.println("Memória não pode ser usada");
		}else if(pos>=0 && pos<=32767){
			this.dadosDePrograma[pos] = dado;
			System.out.println("Dado " + dado +" inserido na posição de memória: "+pos);
		}else {
			System.out.println("Mémoria Inválida");
		}
		
	}
	
	public byte LerNaMemoria(int pos) {
		byte dado;
		if(pos>=32768 && pos<=2097151){
			return 0;
		}else if(pos>=0 && pos<=32767) {
			dado = this.dadosDePrograma[pos];
			return dado;
		}else {
			return 0;
		}

	}
	
	public void escreverInstrucao(int pos, int valor) {
		byte MSB =(byte) ((valor & 0b1111111100000000) >> 8);
		byte LSB =(byte) (valor & 0b0000000011111111);
		EscreverNaMemoria(pos, MSB);
		EscreverNaMemoria( pos+1,  LSB);
	}

	public int lerInstrucao(int pos) {
		int MSB = LerNaMemoria( pos);
		int LSB = LerNaMemoria( pos+1);
		int inst = ((MSB & 0b11111111) << 8) | (LSB & 0b11111111);
		return inst;
	}

}
