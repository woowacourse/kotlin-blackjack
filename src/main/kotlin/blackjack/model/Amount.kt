package blackjack.model

class Amount(val value: Int){
    fun toMinus():Amount = Amount(-value)
}
