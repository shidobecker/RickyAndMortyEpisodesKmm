package br.com.shido.rickyandmortyepisodeskmm.apolloclient

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.ApolloExperimental
import com.apollographql.apollo.network.http.ApolloHttpNetworkTransport
import kotlinx.coroutines.ExperimentalCoroutinesApi

class ApolloProvider(private val myLogger: MyLogger) {

    companion object {
        const val API_URL: String = "https://rickandmortyapi.com/graphql"
    }

    @ExperimentalCoroutinesApi
    @ApolloExperimental
    fun createClient(): ApolloClient {
        return ApolloClient(
            interceptors = listOf(LoggingInterceptor(myLogger = myLogger)),
            networkTransport = ApolloHttpNetworkTransport(
                serverUrl = API_URL,
                headers = mapOf(
                    "Accept" to "application/json",
                    "Content-Type" to "application/json",
                )
            ),
        )

    }


}