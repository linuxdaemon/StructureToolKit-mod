package net.linuxdemon.structuretoolkit.item;

import net.linuxdemon.structuretoolkit.util.NBTHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class ItemStructureTool extends ItemGeneric
{
	public ItemStructureTool ()
	{
		super();
		this.setUnlocalizedName( "structureTool" );
	}

	public ItemStack onItemRightClick ( ItemStack itemStack, World world, EntityPlayer player )
	{
		if ( world.isRemote )
		{
			if ( player.isSneaking() )
			{
				NBTHelper.setIntArray( itemStack, "v1", new int[] { } );
				NBTHelper.setIntArray( itemStack, "v2", new int[] { } );
				NBTHelper.setBoolean( itemStack, "v1Selected", false );
				return itemStack;
			}
			String coordNumberString = "First";
			String keyName = "v1";
			float reach = 5.0F;
			MovingObjectPosition target = player.rayTrace( reach, 1.0F );
			int[] v = new int[] { target.blockX, target.blockY, target.blockZ };
			if ( NBTHelper.getIntArray( itemStack, "v1" ).length == 3 && NBTHelper.getIntArray( itemStack, "v2" ).length == 0 )
			{
				coordNumberString = "Second";
				keyName = "v2";
			}
			else
			{
				NBTHelper.setIntArray( itemStack, "v1", new int[] { } );
				NBTHelper.setIntArray( itemStack, "v2", new int[] { } );
				NBTHelper.setBoolean( itemStack, "v1Selected", false );
			}
			if ( ! world.getBlock( v[ 0 ], v[ 1 ], v[ 2 ] ).equals( Blocks.air ) )
			{
				player.addChatComponentMessage( new ChatComponentText( String.format( "%s coordinate set: %d %d %d", coordNumberString, v[ 0 ], v[ 1 ], v[ 2 ] ) ) );
				NBTHelper.setIntArray( itemStack, keyName, v );
				if (keyName.equals( "v1" ))
				{
					NBTHelper.setBoolean( itemStack, "v1Selected", true );
				}

				if ( NBTHelper.hasTag( itemStack, "v1" ) && NBTHelper.hasTag( itemStack, "v2" ) )
				{
					if ( NBTHelper.getIntArray( itemStack, "v1" ).length == 3 && NBTHelper.getIntArray( itemStack, "v2" ).length == 3 )
					{
						int[] v1 = NBTHelper.getIntArray( itemStack, "v1" );
						int[] v2 = NBTHelper.getIntArray( itemStack, "v2" );

						player.addChatComponentMessage( new ChatComponentText( String.format( "Selecting structure between (%d,%d,%d) and (%d,%d,%d)", v1[ 0 ], v1[ 1 ], v1[ 2 ], v2[ 0 ], v2[ 1 ], v2[ 2 ] ) ) );

						int[] size = new int[] {1, 1, 1};

						for ( int i = 0; i < 3; i++ )
						{
							size[i] += MathHelper.abs_int( v1[i] - v2[i] );
						}

						int volume = 1;
						for (int value : size)
						{
							volume *= value;
						}

						player.addChatComponentMessage( new ChatComponentText( String.format( "Selected area is %d m\u00B3", volume ) ) );

						NBTHelper.setIntArray( itemStack, "v1", new int[] { } );
						NBTHelper.setIntArray( itemStack, "v2", new int[] { } );
						NBTHelper.setBoolean( itemStack, "v1Selected", false );
					}
				}
			}
		}

		return itemStack;
	}
}
