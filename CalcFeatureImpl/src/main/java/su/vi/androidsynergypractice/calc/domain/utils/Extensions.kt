package su.vi.androidsynergypractice.calc.domain.utils

import java.util.LinkedList

fun <E> LinkedList<E>.pollLastKt(): E {
    val last = last()
    removeLast()
    return last
}