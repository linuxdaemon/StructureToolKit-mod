package net.linuxdemon.structuretoolkit.init;

import cpw.mods.fml.common.registry.GameRegistry;
import net.linuxdemon.structuretoolkit.item.ItemGeneric;
import net.linuxdemon.structuretoolkit.item.ItemStructureTool;

public class ModItems
{
	public static final ItemGeneric structureTool = new ItemStructureTool();

	public static void init ()
	{
		GameRegistry.registerItem( structureTool, "structureTool" );
	}
}
