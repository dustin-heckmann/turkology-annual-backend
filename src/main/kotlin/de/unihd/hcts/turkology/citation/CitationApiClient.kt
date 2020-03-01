package de.unihd.hcts.turkology.citation

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.JsonMappingException
import com.fasterxml.jackson.module.kotlin.readValue
import de.unihd.hcts.turkology.citation.domain.Citation
import de.unihd.hcts.turkology.citation.domain.CitationId
import de.unihd.hcts.turkology.citation.search.CitationQuery
import de.unihd.hcts.turkology.citation.search.Limit
import de.unihd.hcts.turkology.citation.search.ListOfCitationHits
import de.unihd.hcts.turkology.citation.search.Skip
import de.unihd.hcts.turkology.config.ElasticSearchConfig
import org.apache.http.HttpHost
import org.elasticsearch.ElasticsearchStatusException
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
import java.io.IOException


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

sealed class TurkologyAnnualException(message: String) : Exception(message)
class CitationNotFound(message: String) : TurkologyAnnualException(message)
class JsonDecodeError(message: String) : TurkologyAnnualException(message)
class JsonMappingError(message: String) : TurkologyAnnualException(message)
class IndexIOError(message: String) : TurkologyAnnualException(message)
class IndexBadRequest(message: String) : TurkologyAnnualException(message)
class IndexNotFound(message: String) : TurkologyAnnualException(message)

@Component
class CitationApiClient(private val config: ElasticSearchConfig) {
    val client = RestHighLevelClient(RestClient.builder(HttpHost(config.host, config.port, "http")))
    val objectMapper = objectMapper()

    fun citation(citationId: CitationId): Either<TurkologyAnnualException, Citation> {
        val request = GetRequest(config.index).run { id(citationId.toString()) }
        val response = try {
            client.get(request, RequestOptions.DEFAULT)
        } catch (e: IOException) {
            return IndexIOError("IO Error from index: " + e.message).left()
        } catch (e: ElasticsearchStatusException) {
            return when {
                e.detailedMessage.contains("index_not_found_exception") -> return IndexNotFound("No such index: ${config.index}").left()
                else -> IndexBadRequest("Bad search request: " + e.message).left()
            }
        }
        if (!response.isExists) return Either.left(CitationNotFound("Citation \"$citationId\" not found"))
        return try {
            objectMapper.readValue<Citation>(response.sourceAsString).right()
        } catch (e: JsonMappingException) {
            e.printStackTrace()
            JsonMappingError("Could not deserialize citation: " + e.message).left()
        } catch (e: JsonProcessingException) {
            e.printStackTrace()
            JsonDecodeError("Could not parse JSON: " + e.message).left()
        }
    }

    fun citations(query: CitationQuery, skip: Skip, limit: Limit): Either<TurkologyAnnualException, ListOfCitationHits> {

        val searchRequest = buildSearchRequest(skip, limit, query)
        val response = try {
            client.search(searchRequest, RequestOptions.DEFAULT)
        } catch (e: IOException) {
            return IndexIOError("IO Error from index: " + e.message).left()
        } catch (e: ElasticsearchStatusException) {
            return when {
                e.detailedMessage.contains("index_not_found_exception") -> return IndexNotFound("No such index: ${config.index}").left()
                else -> IndexBadRequest("Bad search request: " + e.message).left()
            }
        }

        val citations = try {
            response.hits.hits.map {
                CitationHit(objectMapper.readValue<Citation>(it.sourceAsString))
            }
        } catch (e: JsonMappingException) {
            e.printStackTrace()
            return JsonMappingError("Could not deserialize citations: " + e.message).left()
        } catch (e: JsonProcessingException) {
            e.printStackTrace()
            return JsonDecodeError("Could not parse JSON: " + e.message).left()
        }
        return ListOfCitationHits(total = response.hits.totalHits, result = citations).right()

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

