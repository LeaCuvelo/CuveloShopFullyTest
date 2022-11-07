package com.cuvelo.shopfully.test.analytics

interface StreamFullyEvent {
    val eventType: String
    val attributes: Map<String, Any>
}