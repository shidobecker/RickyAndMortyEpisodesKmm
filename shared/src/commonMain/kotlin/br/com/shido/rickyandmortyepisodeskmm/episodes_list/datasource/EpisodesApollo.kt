package br.com.shido.rickyandmortyepisodeskmm.episodes_list.datasource

import br.com.shido.rickyandmortyepisodeskmm.FetchEpisodesListQuery
import br.com.shido.rickyandmortyepisodeskmm.apolloclient.GraphQLApolloClient
import com.apollographql.apollo.api.ApolloExperimental
import com.apollographql.apollo.api.Input
import com.apollographql.apollo.api.Response
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.launch

class EpisodesApollo : EpisodesDataSource {

    @ApolloExperimental
    @ExperimentalCoroutinesApi
    override fun fetchEpisodesList(page: Int): Flow<Response<FetchEpisodesListQuery.Data>> {
        val query = FetchEpisodesListQuery(Input.fromNullable(page))
        return GraphQLApolloClient.createClient().query(query).execute()
    }
}