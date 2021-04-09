package com.example.spaceflightnews.util

/**
 * an interface for mapping the entity from the network to the  Domain model
 */
interface EntityMapper<NetworkEntity, DomainModel> {
    fun mapFromNetworkEntity(entity: NetworkEntity): DomainModel
    fun mapToNetworkEntity(domainModel: DomainModel): NetworkEntity
}