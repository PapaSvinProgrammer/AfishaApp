package com.example.afishaapp.data.http

import com.example.afishaapp.data.module.agent.Agent
import com.example.afishaapp.data.module.agent.AgentResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface AgentService {
    @GET("public-api/v1.4/agents/?text_format=text&fields=id,title,images")
    suspend fun getAgents(): AgentResponse

    @GET("public-api/v1.4/agents/{agentId}/?text_format=text")
    suspend fun getAgentInfo(@Path("agentId") agentId: Int): Agent
}