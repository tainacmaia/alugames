import br.com.alura.alugames.modelo.Gamer


fun main(){
    val gamer1 = Gamer("Taina","taina@email.com")
    println(gamer1)

    val gamer2 = Gamer("Rafael","rafael@email.com", "19/04/1994", "supertucano")
    println(gamer2)

    gamer1.let {
    it.dataNascimento = "07/10/1994"
        it.usuario = "aethia"
    }.also {
        println(gamer1.idInterno)
    }
    println(gamer1)

    gamer1.usuario = "aethiaa"
    println(gamer1)
}