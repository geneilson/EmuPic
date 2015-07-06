package br.edu.iftm.trabalho;

import java.util.Stack;


public class PIC18F4550 {
	private byte w, opCode, status, bsr;
	private int pc = 0, insAtual;
	private MemoriaDeDados memDados;
	private MemoriaDePrograma memPrograma;
	Stack<EstadoProcessador> pilha = new Stack<EstadoProcessador>();
	
	
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
		insAtual = memPrograma.lerInstrucao(pc);
	}
	
	private void decodifica() {
		int instCrua = ((insAtual & 0b1111111100000000) >> 8);
		switch (instCrua) {
		case 14 :
			movlw();
			break;
		case  239 :
			goto1();
			break;
		default:
			instCrua = ((insAtual & 0b1111111100000000) >> 1);
			switch (instCrua) {
			case 55:
				movwf();
				break;
			case 118:
				call();
				break;
			default:
				instCrua = ((insAtual & 0b1111111111111110) >> 1);
				if(instCrua == 9){
					preturn();
				}
				break;
			}

			break;
		}
	}

	private void movlw() {
		w = (byte)insAtual;		
	}
	
	private void movwf(){
		int instwf = ((insAtual & 0b0000000100000000) >> 8);
		if (instwf == 0) {
			if(getInsAtual()<96){
				memDados.EscreverNaMemoria(getInsAtual(), getW());
			}else {
				memDados.EscreverNaMemoria((0b111100000000) + getInsAtual(), getW());
			}
		
		}else{
			memDados.EscreverNaMemoria(bsr >> 8 + getInsAtual(), getW());
		
		}
	}
	
	private void goto1() {
		int arggoto1 = (byte) (insAtual & 0b0000000011111111);
		int insgoto2 = memPrograma.lerInstrucao(pc+2);
		int arggoto2 = ((insgoto2 & 0b0000111111111111) << 8);
		int vaifilhao = (arggoto1 & 0b11111111) | (arggoto2 & 0b111111111111);
		pc = vaifilhao;
	}
	
	private void call(){
		EstadoProcessador estado = new EstadoProcessador();
		estado.setPc(pc+2);
		
		if (((insAtual & 0b0000000100000000) >> 8) == 1){
			estado.setStatus(memDados.LerNaMemoria(0xfd8));
			estado.setW(w);
			estado.setBsr(memDados.LerNaMemoria(0xfe0));
		}
		
		pilha.push(estado);		
	}
	
	private void preturn(){
		EstadoProcessador recuperando = pilha.pop();
		
		pc = recuperando.getPc();
		
		if ((insAtual & 0b0000000000000001) == 1){
			memDados.EscreverNaMemoria(0xfd8, recuperando.getStatus());
			w = recuperando.getW();
			memDados.EscreverNaMemoria(0xfe0, recuperando.getBsr());
		}
		
		System.out.println("PC " + recuperando.getPc());
		System.out.println("Status " + recuperando.getStatus());
		System.out.println("W " + recuperando.getW());
		System.out.println("Bsr " + recuperando.getBsr());
	}
	
	private void atualiza(){
		pc+=2;
	}

}
