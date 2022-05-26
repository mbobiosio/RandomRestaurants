package com.mbobiosio.restaurants.util

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.* // ktlint-disable no-wildcard-imports

/**
 * @Author Mbuodile Obiosio
 * https://linktr.ee/mbobiosio
 */
inline fun <ResultType, RequestType> networkBoundResource(
    crossinline query: () -> Flow<ResultType>,
    crossinline fetch: suspend () -> RequestType,
    crossinline saveFetchResult: suspend (RequestType) -> Unit,
    crossinline shouldFetch: (ResultType?) -> Boolean,
    crossinline onFetchFailed: (Throwable) -> Unit = { },
) = flow {

    val data = query().firstOrNull()
    emit(Resource.Loading(data))

    val flow = when {
        shouldFetch(data) -> {
            emit(Resource.Loading(data))

            try {
                saveFetchResult(fetch())
                query().map {
                    Resource.Success(it)
                }
            } catch (throwable: Throwable) {
                onFetchFailed(throwable)
                query().map {
                    Resource.Error(throwable, it)
                }
            }
        }
        else -> {
            query().map { Resource.Success(it) }
        }
    }
    emitAll(flow)
}.flowOn(Dispatchers.IO)
