package blackjack.model

data class Card(val number: CardNumber, val symbol: CardSymbol) {
    override fun toString(): String {
        val cardNumber =
            when (number) {
                CardNumber.JACK -> "J"
                CardNumber.QUEEN -> "Q"
                CardNumber.KING -> "K"
                CardNumber.ACE -> "A"
                else -> number.value.toString()
            }

        val cardSymbol =
            when (symbol) {
                CardSymbol.DIAMOND -> "다이아몬드"
                CardSymbol.HEART -> "하트"
                CardSymbol.SPADE -> "스페이드"
                CardSymbol.CLOVER -> "클로버"
            }
        return cardNumber + cardSymbol
    }
}
