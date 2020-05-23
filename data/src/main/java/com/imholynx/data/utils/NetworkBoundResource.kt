package com.imholynx.data.utils

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

abstract class NetworkBoundResource<ResultType, RequestType> {

    private var result: Observable<ResultType>

    init {

        val diskObservable = Observable.defer {
            loadFromDb()
        }

        val networkObservable = Observable.defer {
            createCall()
                .flatMapCompletable(this::saveCallResult)
                .andThen(loadFromDb())
        }

        result = shouldFetch().flatMapObservable { shouldFetch: Boolean ->
            if (shouldFetch) {
                networkObservable
            } else {
                diskObservable
            }
        }
    }

    fun asObservable(): Observable<ResultType> {
        return result
    }

    protected abstract fun saveCallResult(request: RequestType): Completable

    protected abstract fun loadFromDb(): Observable<ResultType>

    protected abstract fun shouldFetch(): Single<Boolean>

    protected abstract fun createCall(): Observable<RequestType>
}