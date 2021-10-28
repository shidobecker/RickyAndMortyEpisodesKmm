package br.com.shido.rickyandmortyepisodeskmm.apolloclient

import com.apollographql.apollo.api.ApolloExperimental
import com.apollographql.apollo.api.Operation
import com.apollographql.apollo.interceptor.ApolloInterceptorChain
import com.apollographql.apollo.interceptor.ApolloRequest
import com.apollographql.apollo.interceptor.ApolloRequestInterceptor
import com.apollographql.apollo.interceptor.ApolloResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlin.time.ExperimentalTime
import kotlin.time.measureTimedValue

@ApolloExperimental
internal class LoggingInterceptor(private val myLogger: MyLogger) : ApolloRequestInterceptor {

    @ApolloExperimental
    @ExperimentalTime
    override fun <D : Operation.Data> intercept(
        request: ApolloRequest<D>,
        chain: ApolloInterceptorChain
    ): Flow<ApolloResponse<D>> {

        val uuid = request.requestUuid.toString()
        val requestComposeBody = request.operation.composeRequestBody().toString()
        val operation = request.operation.name().name()
        val variables = request.operation.variables().valueMap().toString()
        val (response, elapsed) = measureTimedValue {
            chain.proceed(request)
        }
        val props = mutableMapOf<String, String>()

        val timeInMillis = elapsed.inWholeMilliseconds.toString()
        props["Request UUID"] = uuid
        props["Request Name"] = operation
        props["Request Variables"] = variables
        props["Response Time"] = timeInMillis
        props["Request Body"] = requestComposeBody
        val propsString = props.toString()
        myLogger.logMessage(propsString)
        return response
    }
}