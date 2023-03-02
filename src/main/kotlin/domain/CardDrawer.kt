package domain

interface CardDrawer {
    fun draw(): Card

    fun drawInitCards(): Cards
}
