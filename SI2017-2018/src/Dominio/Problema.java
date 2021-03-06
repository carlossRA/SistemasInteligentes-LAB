package Dominio;

import java.io.IOException;
import java.util.Stack;

public class Problema {
	
	private EspacioDeEstados espEst;
	private Estado estInicial;
	
	public Problema(EspacioDeEstados espEst, Estado estInicial) {
		this.espEst = espEst;
		this.estInicial = estInicial;
	}

	public EspacioDeEstados getEspEst() {
		return espEst;
	}

	public void setEspEst(EspacioDeEstados espEst) {
		this.espEst = espEst;
	}

	public Estado getEstInicial() {
		return estInicial;
	}

	public void setEstInicial(Estado estInicial) {
		this.estInicial = estInicial;
	}
	

	public boolean EsMeta(Estado e,Terreno terreno){
	 	Terreno ter =e.getTerreno();
		Casilla [][] terrenoResuelto = terreno.getTerrenoResuelto();
		
			Casilla[][] cas = ter.getTerreno();
			
			
		for(int i=0;i<terreno.getFilas();i++)
			for(int j=0;j<terreno.getColumnas();j++)
			{
				if(cas[i][j].getCantidad() != terrenoResuelto[i][j].getCantidad()) return false;
			}
			
		
		return true;
	
	}
	
	/*public Stack<Sucesor> calculaSucesores(Estado e) throws IOException{
		Terreno padre=e.getTerreno();
		Pieza [][]a=padre.getTerrenoResueltoMatriz();
		Terreno sucesor=new Terreno (padre.getTerrenoMatriz(),padre.getImagenNegroRedim());
		Stack <Sucesor>sucesores =new Stack();
		
		
		int [] movimientos=padre.MovimientosValidos(); //obtengo los movimientos validos del padre
	
		// para evitar la misma referencia en memoria creo un nuevo Terreno hijo.
		// si no lo creara de nuevo cambiaria el Terreno del padre.
		
	
		for(int i=0;i<movimientos.length;i++)
		{	
			if(movimientos[i]==1){
			
				//movemos el Terreno para cambiar el estado del sucesor
				sucesor.Movimiento(i);
				Estado estadoSucc=new Estado(sucesor);
				//creamos el sucesor 
				sucesores.push(new Sucesor(sucesor.MovimientoRealizado(i),estadoSucc, 1));
				sucesor=new Terreno (padre.getTerrenoMatriz(),padre.getImagenNegroRedim());
			}
		}	
		return sucesores;
	}*/
}
