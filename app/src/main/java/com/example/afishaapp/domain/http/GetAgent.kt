package com.example.afishaapp.domain.http

import com.example.afishaapp.data.module.agent.Agent
import com.example.afishaapp.data.module.agent.AgentResponse
import com.example.afishaapp.domain.repository.http.AgentRepository
import javax.inject.Inject

class GetAgent @Inject constructor(
    private val agentRepository: AgentRepository
) {
    suspend fun getAgentInfo(agentId: Int): Agent? {
        if (agentId <= 0) {
            return null
        }

        return agentRepository.getAgentInfo(agentId)
    }
}