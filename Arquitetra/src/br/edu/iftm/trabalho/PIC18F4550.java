package br.edu.iftm.trabalho;

public class PIC18F4550 {
	private byte w, opCode;
	private int pc = 0, insAtual;
	private MemoriaDeDados memDados;
	private MemoriaDePrograma memPrograma;
	
	
	
	public PIC18F4550()  {
		this((byte)0,(byte)0,0,0,new MemoriaDeDados(), new MemoriaDePrograma());
		// TODO Auto-generated constructor stub
	}

	public PIC18F4550(byte w, byte opCode, int pc, int insAtual,
			MemoriaDeDados memDados, MemoriaDePrograma memPrograma) {
		super();
		this.w = w;
		this.opCode = opCode;
		this.pc = pc;
		this.insAtual = insAtual;
		this.memDados = memDados;
		this.memPrograma = memPrograma;
	}

	public byte getW() {
		return w;
	}

	public void setW(byte w) {
		this.w = w;
	}

	public byte getOpCode() {
		return opCode;
	}

	public void setOpCode(byte opCode) {
		this.opCode = opCode;
	}

	public int getPc() {
		return pc;
	}

	public void setPc(int pc) {
		this.pc = pc;
	}

	public int getInsAtual() {
		return insAtual;
	}

	public void setInsAtual(int insAtual) {
		this.insAtual = insAtual;
	}

	public MemoriaDeDados getMemDados() {
		return memDados;
	}

	public void setMemDados(MemoriaDeDados memDados) {
		this.memDados = memDados;
	}

	public MemoriaDePrograma getMemPrograma() {
		return memPrograma;
	}

	public void setMemPrograma(MemoriaDePrograma memPrograma) {
		this.memPrograma = memPrograma;
	}

	public void executaCicloDeMaquina () {
		buscaInstrucao();
		decodifica();
		atualiza();
	}
	
	private void buscaInstrucao() {
		insAtual = memPrograma.lerInstrucao( pc);
		
		
	}
	
	private void decodifica() {
		int instCrua = ((insAtual & 0b1111111100000000) >> 8);
		switch (instCrua) {
		case 14 :
			executaMovlw();
			break;

		default:
			break;
		}
	}
	
	private void executaMovlw() {
		w = (byte)insAtual;		
	}
	
	private void atualiza(){
		pc+=2;
	}

}
