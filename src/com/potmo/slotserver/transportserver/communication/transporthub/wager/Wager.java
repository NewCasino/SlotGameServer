package com.potmo.slotserver.transportserver.communication.transporthub.wager;

import java.io.IOException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.potmo.slotserver.gameserver.GameServer;
import com.potmo.slotserver.transportserver.communication.transporthub.TransportRequest;
import com.potmo.slotserver.transportserver.communication.transporthub.TransportResponse;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("")
public class Wager
{

	@Path("wager")
	@POST
	public TransportResponse transportMessage( @Context ContainerRequestContext context, TransportRequest request ) throws JsonParseException, JsonMappingException, IOException
	{

		// set up a client to be able to relay the message
		Client gameClient = ClientBuilder.newBuilder().build();
		WebTarget gameTarget = gameClient.target( GameServer.BASE_URI );

		// pass the wager request to the actual game
		String response = gameTarget.path( "wager" ).path( request.game ).request().accept( MediaType.APPLICATION_JSON ).post( Entity.json( request.payload ), String.class );

		System.out.println( "Holla: " + response );

		// read the cost and winnings
		// this is actually a FreeSpinWagerResponse but we don't care since we only use some of the fields
		ObjectMapper objectMapper = new ObjectMapper();
		WagerResponse wagerResponse = objectMapper.readValue( response, WagerResponse.class );

		System.out.println( "I will take: " + wagerResponse.totalCost + " and return " + wagerResponse.totalWin );

		//TODO: Talk to CasinArena about this
		//TODO: Persist the wager in DB 

		return new TransportResponse( response );
	}
}
