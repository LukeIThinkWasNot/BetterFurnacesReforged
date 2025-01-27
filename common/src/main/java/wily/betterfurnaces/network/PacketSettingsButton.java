package wily.betterfurnaces.network;

import dev.architectury.networking.NetworkManager;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import wily.betterfurnaces.blockentity.SmeltingBlockEntity;

import java.util.function.Supplier;

public class PacketSettingsButton {

private BlockPos pos;
	private int[] indexes;
	private int set;

	public PacketSettingsButton(FriendlyByteBuf buf) {
		this(buf.readBlockPos(),buf.readVarIntArray(), buf.readInt());
	}

	public void toBytes(FriendlyByteBuf buf) {
		buf.writeBlockPos(pos);
		buf.writeVarIntArray(indexes);
		buf.writeInt(set);
	}

	public PacketSettingsButton(BlockPos pos, int[] indexes, int set) {
		this.pos = pos;
		this.indexes = indexes;
		this.set = set;
	}
	public PacketSettingsButton(BlockPos pos, int index, int set) {
		this (pos,new int[]{index},set);
	}

	public void handle(Supplier<NetworkManager.PacketContext> ctx) {
		ctx.get().queue(() -> {
			ServerPlayer player = (ServerPlayer) ctx.get().getPlayer();
			SmeltingBlockEntity be = (SmeltingBlockEntity) player.level().getBlockEntity(pos);
			if (player.level().isLoaded(pos)) {
				for (int index : indexes)
					be.furnaceSettings.set(index, set);
				be.getLevel().setBlock(pos, be.getLevel().getBlockState(pos), 2, 3);
				be.setChanged();
			}
		});

	}
}
