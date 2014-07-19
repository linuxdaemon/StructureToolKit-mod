package net.linuxdemon.structuretoolkit.structure;

import com.google.common.collect.ContiguousSet;
import com.google.common.collect.DiscreteDomain;
import com.google.common.collect.Range;
import cpw.mods.fml.relauncher.FMLInjectionData;
import net.linuxdemon.structuretoolkit.init.ModBlocks;
import net.linuxdemon.structuretoolkit.util.LogHelper;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

import java.io.File;
import java.sql.Timestamp;
import java.util.Date;

public class Structure
{
	public static boolean writeOut ( World world, int[] v1, int[] v2, boolean storeAir )
	{
		int maxX = v1[ 0 ] > v2[ 0 ] ? v1[ 0 ] : v2[ 0 ];
		int minX = v1[ 0 ] < v2[ 0 ] ? v1[ 0 ] : v2[ 0 ];

		int maxY = v1[ 1 ] > v2[ 1 ] ? v1[ 1 ] : v2[ 1 ];
		int minY = v1[ 1 ] < v2[ 1 ] ? v1[ 1 ] : v2[ 1 ];

		int maxZ = v1[ 2 ] > v2[ 2 ] ? v1[ 2 ] : v2[ 2 ];
		int minZ = v1[ 2 ] < v2[ 2 ] ? v1[ 2 ] : v2[ 2 ];

		String data = "";

		LogHelper.info( "Store Air: " + storeAir );

		String basePath = ( ( File ) ( FMLInjectionData.data()[ 6 ] ) ).getAbsolutePath().replace( File.separatorChar, '/' ).replace( "/.", "" ) + "/structures";
		Date date = new Date();
		String fileName = new Timestamp( date.getTime() ).toString().replace( " ", "_" );
		LogHelper.info( fileName );

		for ( int x : ContiguousSet.create( Range.closed( minX, maxX ), DiscreteDomain.integers() ) )
		{
			for ( int y : ContiguousSet.create( Range.closed( minY, maxY ), DiscreteDomain.integers() ) )
			{
				for ( int z : ContiguousSet.create( Range.closed( minZ, maxZ ), DiscreteDomain.integers() ) )
				{
					if ( ! world.getBlock( x, y, z ).equals( ModBlocks.placeHolder ) )
					{
						if ( (world.getBlock( x, y, z ).getClass() != Blocks.air.getClass()) || storeAir )
						{
							String blockData = String.format("%d %d %d %s", x - minX, y - minY, z - minZ, world.getBlock( x, y, z ).getClass().toString());
							data += String.format("%s%n", blockData);
						}
					}
				}
			}
		}
		LogHelper.info( String.format( "Structure data:%n%s", data.replaceAll( String.format( "%n$" ), "" ) ) );
		return true;
	}

	public static boolean writeOut ( World world, int[] v1, int[] v2 )
	{
		return writeOut( world, v1, v2, false );
	}
}
