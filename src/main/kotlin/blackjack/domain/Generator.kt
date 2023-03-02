package blackjack.domain

interface Generator {

    fun generateCardNumber(): CardNumber
    fun generateCardShape(): CardShape
}
