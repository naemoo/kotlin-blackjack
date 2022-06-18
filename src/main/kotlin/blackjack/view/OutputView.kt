package blackjack.view

import blackjack.constant.Messages
import blackjack.domain.card.ACE
import blackjack.domain.card.Card
import blackjack.domain.card.CardType
import blackjack.domain.card.JACK
import blackjack.domain.card.KING
import blackjack.domain.card.QUEEN
import blackjack.domain.user.User
import blackjack.domain.user.Users

/**
 * 출력을 담당하는 클래스
 * Created by Jaesungchi on 2022.06.07..
 */
object OutputView : OutputInterface {
    fun printHandOutMessage(users: Users) {
        println()
        println(Messages.HAND_OUT_CARD.format(users.names().joinToString()))
    }

    fun printUsersCard(users: Users) {
        printUserCard(users.dealer)
        users.users.forEach {
            printUserCard(it)
        }
        println()
    }

    override fun printUserCard(user: User) {
        println(
            Messages.PRINT_HAVE_CARDS.format(user.name) + user.cards.hands.joinToString {
                cardToString(it)
            }
        )
    }

    override fun printMoreCard(user: User) {
        println(Messages.WANT_MORE_CARD.format(user.name))
    }

    override fun printDealerHitMessage() {
        println()
        println(Messages.PRINT_DEALER_HIT_MESSAGE)
    }

    fun printResult(users: Users) {
        println()
        printCardAndScore(users.dealer)
        users.users.forEach {
            printCardAndScore(it)
        }
    }

    fun printWinAndLose(users: Users) {
        println()
        println(Messages.FINAL_WIN_AND_LOSE)
        val winAndLose = users.dealer.getWinAndLose(users.users)
        println(Messages.DEALER_WIN_AND_LOSE.format(winAndLose.first, winAndLose.second))
        users.users.forEach {
            val isWin = it.isWin(users.dealer)
            println(Messages.USER_COLON.format(it.name) + if (isWin) Messages.WIN else Messages.LOSE)
        }
    }

    private fun cardToString(card: Card): String {
        val number = when (card) {
            is ACE -> "A"
            is JACK -> "J"
            is QUEEN -> "Q"
            is KING -> "K"
            else -> card.score
        }
        val type = when (card.type) {
            CardType.HEART -> "하트"
            CardType.DIAMOND -> "다이아"
            CardType.SPADE -> "스페이드"
            CardType.CLUB -> "클로버"
        }
        return "$number$type"
    }

    private fun printCardAndScore(user: User) {
        println(
            Messages.PRINT_CARDS_AND_SCORE.format(
                user.name,
                user.cards.hands.joinToString { cardToString(it) },
                user.cards.getScore().value
            )
        )
    }
}
