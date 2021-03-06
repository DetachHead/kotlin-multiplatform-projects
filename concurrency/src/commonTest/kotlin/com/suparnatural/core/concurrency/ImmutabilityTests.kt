package com.suparnatural.core.concurrency

import kotlin.test.Test

class ImmutabilityTests {

    class FD {
        var x by Immutability<FD?>(null)
    }

    @Test
    fun testFrozenDelegate() {
        val f = FD().toImmutable()

        (0..10).map { WorkerFactory.newBackgroundWorker() }.map {
            it.execute(f) { ff ->
                repeat(20000) {
                    ff.x = FD()
                }
            }
        }.forEach {
            it.await()
        }
    }
}
