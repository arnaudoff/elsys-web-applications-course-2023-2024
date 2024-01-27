package com.example.restaurant.mapper;

import com.example.restaurant.controller.resources.ClientResource;
import com.example.restaurant.entity.Client;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-01-23T00:00:24+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 19.0.1 (Oracle Corporation)"
)
public class ClientMapperImpl implements ClientMapper {

    @Override
    public Client fromClientResource(ClientResource clientResource) {
        if ( clientResource == null ) {
            return null;
        }

        Client.ClientBuilder client = Client.builder();

        client.id( clientResource.getId() );
        client.name( clientResource.getName() );
        client.email( clientResource.getEmail() );
        client.phone( clientResource.getPhone() );
        client.address( clientResource.getAddress() );

        return client.build();
    }

    @Override
    public ClientResource toClientResource(Client client) {
        if ( client == null ) {
            return null;
        }

        ClientResource clientResource = new ClientResource();

        clientResource.setId( client.getId() );
        clientResource.setName( client.getName() );
        clientResource.setEmail( client.getEmail() );
        clientResource.setPhone( client.getPhone() );
        clientResource.setAddress( client.getAddress() );

        return clientResource;
    }

    @Override
    public List<ClientResource> toClientResourceList(List<Client> clients) {
        if ( clients == null ) {
            return null;
        }

        List<ClientResource> list = new ArrayList<ClientResource>( clients.size() );
        for ( Client client : clients ) {
            list.add( toClientResource( client ) );
        }

        return list;
    }
}
