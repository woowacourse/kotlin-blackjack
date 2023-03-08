package domain

interface CardPicker {
    fun draw(): Card

    fun drawInitCards(): Cards
}
