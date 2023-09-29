package aplicacao;

import java.util.List;

import com.db4o.ObjectContainer;
import com.db4o.query.Query;

import modelo.Apresentacao;
import modelo.Artista;


public class Deletar {
	protected ObjectContainer manager;

	public Deletar(){
		manager = Util.conectarBanco();
		apagarApresentacao();
        apagarArtista();
		Util.desconectar();
		
		System.out.println("\n\n aviso: feche sempre o plugin OME antes de executar aplicação");
	}

	public void apagarApresentacao(){
		//localizar a apresentação de id 2
		Query q = manager.query();
		q.constrain(Apresentacao.class);
		q.descend("id").constrain(2);
		List<Apresentacao> resultados = q.execute();
	
		if(resultados.size()>0) {
			//apagar a apresentação
			Apresentacao a =  resultados.get(0);
			manager.delete(a);
			manager.commit();
			System.out.println("apagou a Apresentação 2 mas não apagou sua cidade nem seu artista");
		}
		else
			System.out.println("Apresentação inexistente");
	}

	 public void apagarArtista() {
	        // Localize o artista pelo nome (substitua "Nome do Artista" pelo nome real)
	        Query q = manager.query();
	        q.constrain(Artista.class);
	        q.descend("nome").constrain("Nome do Artista");
	        List<Artista> resultados = q.execute();

	        if (resultados.size() > 0) {
	            // Apague o artista
	            Artista artista = resultados.get(0);
	            manager.delete(artista);
	            manager.commit();
	            System.out.println("Apagou o Artista com o nome 'Nome do Artista'.");
	        } else {
	            System.out.println("Artista inexistente.");
	        }
	    }


	//=================================================
	public static void main(String[] args) {
		new Deletar();
	}
}

