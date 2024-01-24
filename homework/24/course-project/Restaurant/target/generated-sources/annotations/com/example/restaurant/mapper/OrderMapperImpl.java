package com.example.restaurant.mapper;

import com.example.restaurant.controller.resources.OrderResourceRequest;
import com.example.restaurant.controller.resources.OrderResourceResponse;
import com.example.restaurant.entity.Client;
import com.example.restaurant.entity.Order;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.mapstruct.factory.Mappers;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-01-23T00:05:42+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 19.0.1 (Oracle Corporation)"
)
public class OrderMapperImpl implements OrderMapper {

    private final MealMapper mealMapper = Mappers.getMapper( MealMapper.class );
    private final ClientMapper clientMapper = Mappers.getMapper( ClientMapper.class );

    @Override
    public Order fromOrderResource(OrderResourceRequest orderResource) {
        if ( orderResource == null ) {
            return null;
        }

        Order order = new Order();

        order.setClient( orderResourceRequestToClient( orderResource ) );
        order.setMeals( mealMapper.fromStringList( orderResource.getMeals() ) );
        order.setId( orderResource.getId() );

        return order;
    }

    @Override
    public OrderResourceResponse toOrderResource(Order order) {
        if ( order == null ) {
            return null;
        }

        OrderResourceResponse orderResourceResponse = new OrderResourceResponse();

        orderResourceResponse.setDate( order.getDate() );
        orderResourceResponse.setId( order.getId() );
        orderResourceResponse.setClient( clientMapper.toClientResource( order.getClient() ) );
        orderResourceResponse.setTotalPrice( order.getTotalPrice() );
        orderResourceResponse.setMeals( mealMapper.toMealResourceList( order.getMeals() ) );

        return orderResourceResponse;
    }

    @Override
    public List<OrderResourceResponse> toOrderResourceList(List<Order> orders) {
        if ( orders == null ) {
            return null;
        }

        List<OrderResourceResponse> list = new ArrayList<OrderResourceResponse>( orders.size() );
        for ( Order order : orders ) {
            list.add( toOrderResource( order ) );
        }

        return list;
    }

    protected Client orderResourceRequestToClient(OrderResourceRequest orderResourceRequest) {
        if ( orderResourceRequest == null ) {
            return null;
        }

        Client.ClientBuilder client = Client.builder();

        client.name( orderResourceRequest.getClient() );

        return client.build();
    }
}
