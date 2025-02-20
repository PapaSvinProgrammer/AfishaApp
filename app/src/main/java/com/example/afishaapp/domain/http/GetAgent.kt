package com.example.afishaapp.domain.http

import com.example.afishaapp.data.module.agent.Agent
import com.example.afishaapp.data.module.agent.AgentResponse
import com.example.afishaapp.domain.repository.AgentRepository
import javax.inject.Inject

class GetAgent @Inject constructor(
    private val agentRepository: AgentRepository
) {
    suspend fun getAgents(): AgentResponse {
        return agentRepository.getAgents()
    }

    suspend fun getAgentInfo(agentId: Int): Agent {
        return agentRepository.getAgentInfo(agentId)
    }
}