package de.unihd.hcts.turkology.citation

import com.fasterxml.jackson.module.kotlin.readValue
import de.unihd.hcts.turkology.citation.domain.Citation
import de.unihd.hcts.turkology.citation.domain.CitationId
import de.unihd.hcts.turkology.citation.search.CitationQuery
import de.unihd.hcts.turkology.citation.search.Limit
import de.unihd.hcts.turkology.citation.search.ListOfCitationHits
import de.unihd.hcts.turkology.citation.search.Skip
import de.unihd.hcts.turkology.config.ElasticSearchConfig
import org.apache.http.HttpHost
import org.elasticsearch.action.get.GetRequest
import org.elasticsearch.action.search.SearchRequest
import org.elasticsearch.client.RequestOptions
import org.elasticsearch.client.RestClient
import org.elasticsearch.client.RestHighLevelClient
import org.elasticsearch.index.query.BoolQueryBuilder
import org.elasticsearch.index.query.QueryBuilders
import org.elasticsearch.index.query.QueryBuilders.boolQuery
import org.elasticsearch.index.query.QueryBuilders.matchQuery
import org.elasticsearch.search.builder.SearchSourceBuilder
import org.elasticsearch.search.sort.FieldSortBuilder
import org.elasticsearch.search.sort.SortOrder
import org.springframework.stereotype.Component


val SUMMARY_FIELDS = arrayOf(
        "id",
        "volume",
        "number",
        "title",
        "authors",
        "keywords",
        "datePublished",
        "location",
        "rawText"
)

@Component
class CitationApiClient(private val config: ElasticSearchConfig) {
    val client = RestHighLevelClient(RestClient.builder(HttpHost(config.host, config.port, "http")))

    fun citation(citationId: CitationId): Citation {
        val citationAsJson = GetRequest(config.index).run {
            id(citationId.toString())
            client.get(this, RequestOptions.DEFAULT).sourceAsString
        }
        return objectMapper().readValue<Citation>(citationAsJson)
    }

    fun citations(query: CitationQuery, skip: Skip, limit: Limit): ListOfCitationHits {
        val searchRequest = buildSearchRequest(skip, limit, query)
        val response = client.search(searchRequest, RequestOptions.DEFAULT)

        return ListOfCitationHits(total = response.hits.totalHits, result = response.hits.hits.map {
            CitationHit(citation = objectMapper().readValue<Citation>(it.sourceAsString))
        })
    }


    private fun buildSearchRequest(skip: Skip, limit: Limit, query: CitationQuery): SearchRequest {
        val searchSourceBuilder = SearchSourceBuilder().apply {
            fetchSource(
                    SUMMARY_FIELDS,
                    emptyArray<String>()
            )
            from(skip.value)
            size(limit.value)
        }

        boolQuery().apply {
            addContentFilters(query)
            searchSourceBuilder.query(this)
        }

        if (query.queryString == null) {
            searchSourceBuilder.sort(FieldSortBuilder("volume").order(SortOrder.ASC))
            searchSourceBuilder.sort(FieldSortBuilder("number").order(SortOrder.ASC))
        }
        return SearchRequest(config.index).source(searchSourceBuilder)
    }

    private fun BoolQueryBuilder.addContentFilters(query: CitationQuery) {
        query.queryString?.let { queryString ->
            must(QueryBuilders.queryStringQuery(queryString.value))
        }

        query.keyword?.let { keyword ->
            must(boolQuery())
                    .should(matchQuery("keywords.code", keyword.value))
                    .should(matchQuery("keywords.super.code", keyword.value))
                    .should(matchQuery("keywords.super.super.code", keyword.value))
        }

        query.volume?.let { volume ->
            must(matchQuery("volume", volume.value))
        }

        query.number?.let { number ->
            must(matchQuery("number", number.value))
        }
    }
}
