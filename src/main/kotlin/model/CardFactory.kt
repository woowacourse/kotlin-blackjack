package model

import entity.Card

interface CardFactory {
    fun generate(): Card
}
