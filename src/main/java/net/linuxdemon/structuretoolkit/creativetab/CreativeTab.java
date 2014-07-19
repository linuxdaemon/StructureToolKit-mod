package net.linuxdemon.structuretoolkit.creativetab;

import net.linuxdemon.structuretoolkit.init.ModItems;
import net.linuxdemon.structuretoolkit.reference.Reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class CreativeTab
{
	public static final CreativeTabs STK_TAB = new CreativeTabs( Reference.MOD_ID.toLowerCase() )
	{
		@Override
		public Item getTabIconItem ()
		{
			return ModItems.structureTool;
		}
	};
}