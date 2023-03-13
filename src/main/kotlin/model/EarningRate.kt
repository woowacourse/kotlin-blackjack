package model

enum class EarningRate(val multiple: Float) {
    BLACKJACK(1.5f), WIN_OR_BLACKJACK_DRAW(1f), DRAW(0f), LOSE(-1f);
}
