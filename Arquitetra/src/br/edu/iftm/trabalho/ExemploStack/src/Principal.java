import java.util.*;

public class Principal {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Stack<EstadoProcessador> pilha = new Stack<EstadoProcessador>();
		
		//Call
		EstadoProcessador estado = new EstadoProcessador();
		estado.setPc(1500);
		estado.setStatus((byte)35);
		estado.setW((byte)37);
		estado.setBsr((byte)87);
		
		//Push
		pilha.push(estado);
		
		
		//Return
		//Pop
		EstadoProcessador recuperando = pilha.pop();
		
		System.out.println(recuperando.getPc());
		System.out.println(recuperando.getBsr());
		System.out.println(recuperando.getStatus());
		System.out.println(recuperando.getW());
		
		
	}

}
