package Controller;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import Service.Sync;

public class Main {
	
//  ExecutorService == Classe para o gerenciamento de execu��es em paralelo

//  threadpool == � um conjunto de threads (sequ�ncias) pr�-instanciadas prontas para uso.
//	Elas n�o costumam ser liberadas, e ficam l� dispon�veis para reciclagem.

	private static final ExecutorService threadpool = Executors.newFixedThreadPool(3);

	public static void main(String args[]) throws InterruptedException, ExecutionException {
		GerarNumeroAleatorio gerarnum = new GerarNumeroAleatorio();
		System.out.println("Exemplo Async: Gerador de n�mero\n");
		System.out.println("Processando a tarefa ...");
		Future<Integer> future = threadpool.submit(gerarnum);
		while (!future.isDone()) {
			System.out.println("A tarefa ainda n�o foi processada!");
			Thread.sleep(1); // dormir por 1 milissegundo

		}
		System.out.println("Tarefa completa!");
		long num = (long) future.get();
		System.out.println("O n�mero gerado foi: " + num+ "\n\n\n\n\n\n\n");
		threadpool.shutdown();
	
		System.out.println("Exemplo Sync: imprimir Sync\n");
		Sync sync = new Sync();
		sync.foo();
		sync.bar();
		sync.baz();
	
	}

	// Classe que implementa a interface Callable e retorna um numero aleatorio
	private static class GerarNumeroAleatorio implements Callable<Integer> {

		@Override
		public Integer call() {
			Random random = new Random();
			Integer numero = random.nextInt(100);
			return numero;
		}

	}
	
	

}
