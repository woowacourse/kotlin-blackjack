package blackjack.model

interface CardDrawer {
    fun drawCard(generateCard: () -> Card): PickingState
}
