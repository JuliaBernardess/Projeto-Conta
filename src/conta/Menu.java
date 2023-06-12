package conta;

import java.util.Scanner;



import java.io.IOException;
import java.util.InputMismatchException;

import conta.controller.ContaController;

import conta.model.ContaCorrente;
import conta.model.ContaPoupanca;
import conta.util.cores;

public class Menu {

	public static void main(String[] args) {
		
		Scanner leia = new Scanner(System.in);	
	
		ContaController contas = new ContaController();
		
		int   opcao, numero, agencia, tipo, aniversario, numeroDestino;
		String titular;
		float saldo, limite,valor;
		
		System.out.println("\ncriar contas \n");
		

		ContaCorrente cc1 = new ContaCorrente(contas.gerarNumero(), 123, 1, "João da Silva", 1000f, 100.0f);
		contas.cadastrar(cc1);

		ContaCorrente cc2 = new ContaCorrente(contas.gerarNumero(), 124, 1, "Maria da Silva", 2000f, 100.0f);
		contas.cadastrar(cc2);

		ContaPoupanca cp1 = new ContaPoupanca(contas.gerarNumero(), 125, 2, "Mariana do Santos", 4000f, 12);
		contas.cadastrar(cp1);

		ContaPoupanca cp2 = new ContaPoupanca(contas.gerarNumero(), 126, 2, "Juliana Ramos", 8000f, 15);
		contas.cadastrar(cp2);

		contas.listarTodas();

		while (true) {
			System.out.println( "*******************");
			System.out.println("                                                     ");
			System.out.println("                BANCO DO BRAZIL COM Z                ");
			System.out.println("                                                     ");
			System.out.println("*******************");
			System.out.println("                                                     ");
			System.out.println("            1 - Criar Conta                          ");
			System.out.println("            2 - Listar todas as Contas               ");
			System.out.println("            3 - Buscar Conta por Numero              ");
			System.out.println("            4 - Atualizar Dados da Conta             ");
			System.out.println("            5 - Apagar Conta                         ");
			System.out.println("            6 - Sacar                                ");
			System.out.println("            7 - Depositar                            ");
			System.out.println("            8 - Transferir valores entre Contas      ");
			System.out.println("            9 - Sair                                 ");
			System.out.println("                                                     ");
			System.out.println("*******************");
			System.out.println("Entre com a opção desejada:                          ");
			System.out.println("                                                     ");
			System.out.println();

			try {
				opcao = leia.nextInt();

			} catch (InputMismatchException e) {
				System.out.println("\nDigite valores inteiros!");
				leia.nextLine();
				opcao = 0;
			}

			if (opcao == 9) {
				System.out.println( "\nBanco do Brazil com Z - O seu Futuro começa aqui!");
				leia.close();
				System.exit(0);
			}
			switch (opcao) {
			case 1: {
				System.out.println( " Criar Conta\n\n");

				System.out.println( "Digite o Numero da Agencia : ");
				agencia = leia.nextInt();
				System.out.println( "Digite o Titular da Conta : ");
				leia.skip("\\R?");
				titular = leia.nextLine();

				do {
					System.out.println( "Digite o Tipo de Conta (1 - cc ou 2 - cp): ");
					tipo = leia.nextInt();
				} while (tipo < 1 && tipo > 2);

				System.out.println( "Digite o Saldo da Conta (R$): ");
				saldo = leia.nextFloat();

				switch (tipo) {
				case 1 -> {
					System.out.println( "Digite o Limite de Credito (R$): ");
					limite = leia.nextFloat();
					contas.cadastrar( new ContaCorrente(contas.gerarNumero(), agencia, tipo, titular, saldo, limite));
				}

				case 2 -> {
					System.out.println( "Digite o Dia do Aniversario da Conta: ");
					aniversario = leia.nextInt();
					contas.cadastrar(
							new ContaPoupanca(contas.gerarNumero(), agencia, tipo, titular, saldo, aniversario));
				}

				}

				keypress();
				break;
			}
			case 2: {
				System.out.println( " Listar todas as Contas\n ");

				contas.listarTodas();

				keypress();
				break;
			}
			case 3: {
				System.out.println( " Buscar Conta por Numero\n ");
				System.out.println( "Digite Número da Conta: ");
				numero = leia.nextInt();

				contas.procurarPorNumero(numero);

				keypress();
				break;
			}
			case 4: {
				System.out.println( " Atualizar Dados da Conta\n ");
				
				System.out.println( " Digite o numero da Conta\n ");
				numero = leia.nextInt();
				
				if(contas.buscarNaCollection(numero) != null) {
					 
						System.out.println( " Atalizar Dados da Conta\n\n");

						System.out.println( "Digite o Numero da Agencia : ");
						agencia = leia.nextInt();
						System.out.println( "\nDigite o Titular da Conta : ");
						leia.skip("\\R?");
						titular = leia.nextLine();
						System.out.println( "\nDigite o Saldo da Conta (R$): ");
						saldo = leia.nextFloat();
						
						tipo = contas.retornaTipo(numero);

						switch (tipo) {
						case 1 -> {
							System.out.println( "\nDigite o Limite de Credito (R$): ");
							limite = leia.nextFloat();
							contas.cadastrar(new ContaCorrente(contas.gerarNumero(), agencia, tipo, titular, saldo, limite));
						}

						case 2 -> {
							System.out.println( "\nDigite o Dia do Aniversario da Conta: ");
							aniversario = leia.nextInt();
							contas.cadastrar(
									new ContaPoupanca(contas.gerarNumero(), agencia, tipo, titular, saldo, aniversario));
						}
						default -> {	System.out.println( "\nTipo de Conta Inválido! ");
	}					
	}         
}               else System.out.println("\nConta Não Encontrada! ");
	
				keypress();
				break;
				}
			case 5: {
				System.out.println( " Apagar Conta\n ");
				System.out.println(" Digite o numero da Conta\n ");
				numero = leia.nextInt();
				
				contas.deletar(numero);
				
				keypress();
				break;
			}
			case 6: {
				System.out.println( "  Sacar\n  ");
				System.out.println("\nDigite numero da Conta: ");
				numero = leia.nextInt();
				
				do {
					System.out.println( "\n Digite o valor do Saque: ");
					valor = leia.nextFloat();
				} while(valor <= 0);
				
				contas.sacar(numero, valor);
				
				keypress();
				break;
			}
			case 7: {
				System.out.println( "  Depositar\n  ");
				System.out.println("\nDigite numero da Conta: ");
				numero = leia.nextInt();
				
				do {
					System.out.println( "\n Digite o valor do Deposito(R$): ");
					valor = leia.nextFloat();
				} while(valor <= 0);
				
				contas.depositar(numero, valor);
				
				keypress();
				break;
			}
			case 8: {
				System.out.println(" Transferencia entre Contas\n ");
				System.out.println(" Digite o número da conta de origem: ");
				numero = leia.nextInt();
				System.out.println("Digite o número da conta destino: ");
				numeroDestino = leia.nextInt();
				
				do {
					System.out.println(" Digite o valor da transferencia (R$): ");
					valor = leia.nextFloat();
					
				}while(valor <= 0);
				
				contas.transferir(numero, numeroDestino, valor);
				
				keypress();
				break;
			}

			default:
				System.out.println(  "\nOpção Inválida!\n" );
				keypress();
				break;
			}
		}

	}

	public static void keypress() {
		try {
			System.out.println( "\n\n Pressione Enter para continuar... ");
			System.in.read();
		} catch (IOException e) {
			System.out.println( "\nVocê pressionou uma tecla diferente de Enter");
		}

	




		
			
					
	        
	}
}
