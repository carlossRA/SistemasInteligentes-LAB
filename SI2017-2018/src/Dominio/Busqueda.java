package Dominio;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;
	
public class Busqueda {
	
	static boolean optimization = false;

	long tiempoInicialBusqueda;
	long tiempoBusqueda;
	
	EspacioDeEstados espEst = new EspacioDeEstados(null, null);
	Estado e ;
	Hashtable<String, Integer> visited = new Hashtable<String, Integer>();	
	PriorityQueue<Nodo> cola = new PriorityQueue<Nodo>();
	FronteraColaPrioridad f =new FronteraColaPrioridad(cola,visited);
	
	boolean solucion;
	
	// Constructor
	public Busqueda(EspacioDeEstados espEst, Estado e)
	{
		this.espEst = espEst;
		this.e = e;
	}
	
	
	public List<Nodo> Busqueda_Acotada(Problema p, int estrategiaBusqueda, int profMax,Terreno ter,boolean poda) throws IOException{
		
		
		Nodo n = null;
	
		Stack <Sucesor> LS = new Stack<Sucesor>();
		Stack <Nodo> LN = new Stack<Nodo>();
	   Hashtable<String, Integer> visited = new Hashtable<String, Integer>();		
		// Iniciamos la frontera
		PriorityQueue<Nodo> cola = new PriorityQueue();
		FronteraColaPrioridad f = new FronteraColaPrioridad(cola,visited);
		  
		// Le pasamos el nodo raiz e inicializamos tiempo
		tiempoInicialBusqueda = System.currentTimeMillis();
		
		Nodo nodoRaizArbol = new Nodo(e,"nodoRaiz");
		f.insertar(nodoRaizArbol);
		
		solucion = false;
		
		
		// Calculamos la posible solucion
		while(solucion == false && !f.esVacia())
		{

			n = f.Elimina();
			//n.getEstado().getTerreno().imprimirTerreno();
			if(p.EsMeta(n.getEstado(),ter)) {
				solucion = true;
				System.out.println("\nSolucion encontrada\n");
				tiempoBusqueda = System.currentTimeMillis() - tiempoInicialBusqueda;
			}
			else 
			{
				
				LS = e.calculaSucesores(n);
				
				LN = CreaListaNodosArbol(LS, n, profMax, estrategiaBusqueda);	
			   
				  
					   f.insertaLista(LN,estrategiaBusqueda,poda);
				   
				
			}
		}
	
		
		// Si hay solucion la construimos, de lo contrario devolvemos lista vacia
		if(solucion) return CreaSolucion(n);
		return null;
		  
	}
	
	
public List<Nodo> Busqueda(Problema p, int estrategiaBusqueda, int profMax, int incProf,Terreno resuelto,boolean poda) throws IOException{
		
	   List<Nodo> ListaSolucion = new ArrayList<Nodo>();
	
		int profAct = incProf;
		solucion = false;
		
		while(!solucion && profAct <= profMax) {
			
			
			ListaSolucion = Busqueda_Acotada(p,estrategiaBusqueda,profAct,resuelto,poda);
			profAct=profAct +incProf;
		}
		
		return ListaSolucion;
		  
	}
	
	
	
	
	public Stack<Nodo> CreaListaNodosArbol(Stack<Sucesor> LS, Nodo n, int profMax, int estrategiaBusqueda) throws IOException{
		Stack <Nodo> LN = new Stack<Nodo>();
		Sucesor sAux;
		int profundidad, valor = 0, costo=0;
		
			//if(n.getAccion().equals("nodoRaiz"))
				
		
		if(n.getProfundidad()<profMax) {
			profundidad = n.getProfundidad()+1; 
			System.out.println("Profundidad actual: " + profundidad);
			
			
			while(!LS.isEmpty()){
				
				sAux = LS.pop();
				costo = n.GetCosto() + n.getCostoAccion();
												
				switch (estrategiaBusqueda) {
				case 1:					
					valor = profundidad;
					break;
				case 2:
					valor = - profundidad;
					break;
				case 3:
					valor = - profundidad;
					break;
				case 4:
					valor = - profundidad;
					break;
				case 5:
					valor = costo;
					break;
				case 6:
					valor = costo + calculaHeuristica(sAux.getEstado());
					break;
				}
				
				Nodo nAux = new Nodo(n,sAux.getAccion(),costo,sAux.getEstado(), valor);
				LN.add(nAux);
			}
		}
		
		return LN;
	};
	
	
	
	
	public List<Nodo> CreaSolucion(Nodo n) {
		
		List<Nodo> ListaSolucion = new ArrayList<Nodo>();
		
		while(n!=null){
			ListaSolucion.add(n);
			n=n.getNodoPadre();
		}
		
		return ListaSolucion;
	}
	
	public  int calculaHeuristica(Estado e) {
		
		Terreno p = e.getTerreno();
				
		return p.numeroTerrenoSinK(p);
	}
	
}

