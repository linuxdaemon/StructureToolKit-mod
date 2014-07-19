package net.linuxdemon.structuretoolkit.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.linuxdemon.structuretoolkit.reference.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;

public class BlockGeneric extends Block
{

	public BlockGeneric ( Material material )
	{
		super( material );
		this.setCreativeTab( Reference.CREATIVE_TAB );
	}

	public BlockGeneric ()
	{
		this( Material.rock );
	}

	@Override
	public String getUnlocalizedName ()
	{
		return String.format( "tile.%s%s", Reference.MOD_ID.toLowerCase() + ":", getUnwrappedUnlocalizedName( super.getUnlocalizedName() ) );
	}

	@Override
	@SideOnly( Side.CLIENT )
	public void registerBlockIcons ( IIconRegister iconRegister )
	{
		blockIcon = iconRegister.registerIcon( String.format( "%s", getUnwrappedUnlocalizedName( this.getUnlocalizedName() ) ) );
	}

	protected String getUnwrappedUnlocalizedName ( String unlocalizedName )
	{
		return unlocalizedName.substring( unlocalizedName.indexOf( "." ) + 1 );
	}
}
