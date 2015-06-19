package br.edu.iftm.trabalho;

public class MemoriaDeDados {
	private byte[] dadosDaMemoria = new byte[4096];
	
	public MemoriaDeDados(byte[] dadosDaMemoria) {
		this.dadosDaMemoria = dadosDaMemoria;
	}

	public byte[] getDadosDaMemoria() {
		return dadosDaMemoria;
	}

	public void setDadosDaMemoria(byte[] dadosDaMemoria) {
		this.dadosDaMemoria = dadosDaMemoria;
	}

	public void EscreverNaMemoria(int pos, byte dado) {
		if(pos>=2048 && pos<=3935){
			System.out.println("Mem�ria n�o pode ser usada");
		}else if(pos>=3936 && pos<=4095) {
			System.out.println("Registradores Especiaia!!");
			this.dadosDaMemoria[pos] = dado;
			System.out.println("Dado" + dado +"inserido na posi��o de mem�ria: "+pos);
		}else if(pos>=0 && pos<=2047){
			this.dadosDaMemoria[pos] = dado;
			System.out.println("Dado" + dado +"inserido na posi��o de mem�ria: "+pos);
		}else {
			System.out.println("M�moria Inv�lida");
		}
		
	}
	
	public byte LerNaMemoria(int pos) {
		byte dado;
		if(pos>=2048 && pos<=3935){
			return 0;
		}else if(pos>=3096 && pos<=4095) {
			System.out.println("Registradores Especiais!!");
			dado = this.dadosDaMemoria[pos];
			return dado;
		}else if(pos>=0 && pos<=2047) {
			dado = this.dadosDaMemoria[pos];
			return dado;
		}else {
			return 0;
		}

	}

}
