package model

enum class Result(val multiple: Float) {
    BLACKJACK(1.5f), WIN(1f), DRAW(0f), LOSE(-1f)
}
