package domain.card

interface CardPicker {
    fun draw(): Card

    fun drawInitCards(): Cards
}
