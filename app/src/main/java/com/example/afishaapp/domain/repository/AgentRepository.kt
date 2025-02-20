package com.example.afishaapp.domain.repository

import com.example.afishaapp.data.module.agent.Agent
import com.example.afishaapp.data.module.agent.AgentResponse

interface AgentRepository {
    suspend fun getAgents(): AgentResponse
    suspend fun getAgentInfo(agentId: Int): Agent
}