package br.com.shido.rickyandmortyepisodeskmm.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

/**
 * Uses the context of the original flow that has been passed by argument and do the following
 */
class CommonFlow<T>(private val origin: Flow<T>) : Flow<T> by origin {

    /**
     * @param coroutineScope To be used on Android Client, on iOS is nil/null
     * @param callBack To be used on iOS Client since it doesn't know what flow or coroutine is
     */
    fun collectCommon(
        coroutineScope: CoroutineScope? = null,
        callBack: (T) -> Unit
    ) {

        //On Each emission of the flow
        origin.onEach {
            callBack(it)
        }.launchIn(coroutineScope ?: CoroutineScope(Dispatchers.Main))
    }

}

/**
 * Converts a flow to a CommonFlow
 */
fun <T> Flow<T>.asCommonFlow(): CommonFlow<T> = CommonFlow(this)