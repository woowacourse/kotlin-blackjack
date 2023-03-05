package domain

interface CardDeck {
    fun draw(): Card

    fun drawInitCards(): Cards
}
