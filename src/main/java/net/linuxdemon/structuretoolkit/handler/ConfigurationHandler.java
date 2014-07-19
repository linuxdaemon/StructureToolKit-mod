package net.linuxdemon.structuretoolkit.handler;


import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.linuxdemon.structuretoolkit.reference.Reference;
import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class ConfigurationHandler
{
	public static Configuration configuration;
	public static int maxSizeSelect = 1000;

	public static void init ( File configFile )
	{
		if ( configuration == null )
		{
			configuration = new Configuration( configFile );
			loadConfiguration();
		}
	}

	private static void loadConfiguration ()
	{
		maxSizeSelect = configuration.getInt( "Maximum Select Area", Configuration.CATEGORY_GENERAL, 1000, 100, 100000, "The maximum size area that the structure tool can select" );

		if ( configuration.hasChanged() )
		{
			configuration.save();
		}
	}

	@SubscribeEvent
	public void onConfigurationChangedEvent ( ConfigChangedEvent.OnConfigChangedEvent event )
	{
		if ( event.modID.equalsIgnoreCase( Reference.MOD_ID ) )
		{
			loadConfiguration();
		}
	}
}
