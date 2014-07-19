package net.linuxdemon.structuretoolkit.init;

import cpw.mods.fml.common.registry.GameRegistry;
import net.linuxdemon.structuretoolkit.block.BlockGeneric;
import net.linuxdemon.structuretoolkit.block.BlockPlaceHolder;

public class ModBlocks
{
	public static final BlockGeneric placeHolder = new BlockPlaceHolder();

	public static void init ()
	{
		GameRegistry.registerBlock( placeHolder, "placeHolder" );
	}
}
