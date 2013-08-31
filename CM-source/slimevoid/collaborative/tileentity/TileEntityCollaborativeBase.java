package slimevoid.collaborative.tileentity;

import slimevoid.collaborative.core.lib.ConfigurationLib;
import slimevoidlib.tileentity.TileEntityBase;

public class TileEntityCollaborativeBase extends TileEntityBase {

	@Override
	public int getBlockID() {
		return ConfigurationLib.blockCollaborativeBase.blockID;
	}
}
