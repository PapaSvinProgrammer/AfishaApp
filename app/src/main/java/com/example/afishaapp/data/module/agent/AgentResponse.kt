package com.example.afishaapp.data.module.agent

data class AgentResponse (
    val count: Int,
    val next: String,
    val previous: String,
    val results: List<Agent>
)