package domain.card

interface CardDeck {
    fun draw(): Card

    fun drawInitCards(): Cards
}
