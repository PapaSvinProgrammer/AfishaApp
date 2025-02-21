package com.example.afishaapp.data.repository.http

import com.example.afishaapp.data.http.AgentService
import com.example.afishaapp.data.module.agent.Agent
import com.example.afishaapp.data.module.agent.AgentResponse
import com.example.afishaapp.domain.repository.http.AgentRepository
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Inject

class AgentRepositoryImpl @Inject constructor(
    private val retrofit: Retrofit
): AgentRepository {
    override suspend fun getAgents(): AgentResponse {
        return retrofit.create<AgentService>().getAgents()
    }

    override suspend fun getAgentInfo(agentId: Int): Agent {
        return retrofit.create<AgentService>().getAgentInfo(agentId)
    }
}