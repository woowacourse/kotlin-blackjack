package blackjack.model

class Card(val number: CardNumber, val symbol: CardSymbol) {
    override fun toString(): String {
        val cardNumber = convertCardNumber()
        val cardSymbol = convertCardSymbol()
        return cardNumber + cardSymbol
    }

    private fun convertCardSymbol(): String {
        val cardSymbol =
            when (symbol) {
                CardSymbol.DIAMOND -> "다이아몬드"
                CardSymbol.HEART -> "하트"
                CardSymbol.SPADE -> "스페이드"
                CardSymbol.CLOVER -> "클로버"
            }
        return cardSymbol
    }

    private fun convertCardNumber(): String {
        val cardNumber =
            when (number) {
                CardNumber.JACK -> "J"
                CardNumber.QUEEN -> "Q"
                CardNumber.KING -> "K"
                CardNumber.ACE -> "A"
                else -> number.value.toString()
            }
        return cardNumber
    }
}
