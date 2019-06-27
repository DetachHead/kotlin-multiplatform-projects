package com.suparnatural.core.cache

import com.suparnatural.core.fs.PathComponent
import com.suparnatural.core.threading.Future
import com.suparnatural.core.threading.InlineFuture

/**
 * A [CacheStoreWorker] which blocks the calling thread.
 */
class CacheStoreBlockingWorker : CacheStoreWorker {
    /**
     * Adds an object by blocking the calling thread.
     */
    override fun <T: Cacheable> persistObject(
            input: Triple<T, PathComponent, List<CacheStorePreprocessor<Cacheable, Cacheable, Cacheable>>?>,
            task: (it: Triple<T, PathComponent, List<CacheStorePreprocessor<Cacheable, Cacheable, Cacheable>>?>) -> Unit
    ) {
        task(input)
    }

    /**
     * Removes an object by blocking the calling thread.
     */
    override fun unlinkObject(input: PathComponent, task: (it: PathComponent) -> Unit) {
        task(input)
    }

    /**
     * This method is a no-op. It simply returns a resolved future.
     */
    override fun terminate(): Future<Unit> {
        return InlineFuture(Unit)
    }
}