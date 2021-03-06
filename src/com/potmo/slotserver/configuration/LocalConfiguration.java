package com.potmo.slotserver.configuration;

import org.jvnet.hk2.annotations.Service;

import com.potmo.slotserver.campaignserver.CampaignServer;
import com.potmo.slotserver.gameserver.GameServer;
import com.potmo.slotserver.persistenceserver.PersistenceServer;
import com.potmo.slotserver.transactionserver.TransactionServer;
import com.potmo.slotserver.wagerserver.WagerServer;

@Service
public class LocalConfiguration implements ServerConfiguration
{
	@Override
	public String getGameServerBaseUri()
	{
		return GameServer.BASE_URI;
	}

	@Override
	public String getTransportServerBaseUri()
	{
		return WagerServer.BASE_URI;
	}

	@Override
	public String getPersistanceServerBaseUri()
	{
		return PersistenceServer.BASE_URI;
	}

	@Override
	public String getTransactionServerBaseUri()
	{
		return TransactionServer.BASE_URI;
	}

	@Override
	public String getCampaignServerBaseUri()
	{
		return CampaignServer.BASE_URI;
	}

	@Override
	public String getRiakServerUri()
	{
		return "http://127.0.0.1:8098/riak";
	}

}
