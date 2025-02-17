package br.com.alura.alugames.principal

import br.com.alura.alugames.modelo.Gamer
import br.com.alura.alugames.modelo.Jogo
import br.com.alura.alugames.servicos.ConsumoApi
import transformarEmIdade
import java.util.*


fun main() {
    val leitura = Scanner(System.`in`)
    val gamer = Gamer.criarGamer(leitura)
    println("Cadastro realizado. Dados: $gamer")
    println("Idade: " + gamer.dataNascimento?.transformarEmIdade())
    do {
        println("Digite um código de jogo: ")
        val busca = leitura.nextLine()

    //    try {
    //        val meuInfoJogo = gson.fromJson(json, InfoJogo::class.java)
    //        val meuJogo = Jogo(meuInfoJogo.info.title, meuInfoJogo.info.thumb)
    //        println(meuJogo)
    //    } catch (err: Exception){
    //        println("Jogo inexistente")
    //    }
        val buscaApi = ConsumoApi()
        var meuJogo: Jogo? = null

        runCatching {
            val infoJogo = buscaApi.buscaJogo(busca)
            meuJogo = Jogo(infoJogo.info.title, infoJogo.info.thumb)
        }.onFailure {
            println("Jogo inexistente")
        }.onSuccess {
            println("Quer adicionar uma descrição a esse jogo? S/N")
            val opcao = leitura.nextLine()
            if(opcao.equals("S", true)){
                println("Insira a descrição: ")
                meuJogo?.descricao = leitura.nextLine()
            } else {
                meuJogo?.descricao = meuJogo?.titulo
            }
            gamer.jogosBuscados.add(meuJogo)
        }
        println("Deseja buscar um novo jogo? S/N")
        val resposta = leitura.nextLine()
    } while (resposta.equals("s", true))
    println("Jogos ordenados por titulo: ")
    gamer.jogosBuscados.sortBy {
        it?.titulo
    }
    gamer.jogosBuscados.forEach {
        println("Titulo: " + it?.titulo)
    }

    val jogosFiltrados = gamer.jogosBuscados.filter {
        it?.titulo?.contains("batman", true) ?: false
    }
    println("Jogos filtrados: ")
    println(jogosFiltrados)

    println("Excluir jogo da lista? S/N")
    val excluir = leitura.nextLine()
    if(excluir.equals("s", true)) {
        println("Informe a posição da lista: ")
        val posicao = leitura.nextInt()
        gamer.jogosBuscados.removeAt(posicao)
    }

    println("Lista final: ")
    println(gamer.jogosBuscados)
    println("Busca finalizada")
}