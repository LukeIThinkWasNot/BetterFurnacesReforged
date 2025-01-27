package wily.betterfurnaces.inventory;

import net.minecraft.world.item.ItemStack;
import wily.betterfurnaces.blockentity.SmeltingBlockEntity;
import wily.betterfurnaces.blockentity.InventoryBlockEntity;
import wily.betterfurnaces.init.ModObjects;
import wily.betterfurnaces.items.LiquidFuelUpgradeItem;
import wily.betterfurnaces.items.UpgradeItem;
import wily.factoryapi.ItemContainerUtil;
import wily.factoryapi.base.FactoryItemSlot;

import java.util.function.Predicate;

public class SlotFuel extends HideableSlot {

    public SlotFuel(InventoryBlockEntity be, int index, int x, int y) {
        super(be, index, x, y);

    }
    public SlotFuel(InventoryBlockEntity be, int index, int x, int y, Predicate<FactoryItemSlot> isActive) {
        super(be, index, x, y,isActive);
    }

    public boolean mayPlace(ItemStack stack) {
        return super.mayPlace(stack) && (!(stack.getItem() instanceof UpgradeItem) && (SmeltingBlockEntity.isItemFuel(stack) || ( be instanceof SmeltingBlockEntity smeltBe && ItemContainerUtil.isEnergyContainer(stack) && smeltBe.hasUpgrade(ModObjects.ENERGY.get())) ||  ItemContainerUtil.isFluidContainer(stack) || LiquidFuelUpgradeItem.supportsItemFluidHandler(stack)));
    }

    @Override
    public int getMaxStackSize(ItemStack stack) {
        return  ItemContainerUtil.isFluidContainer(stack) ? 1 : super.getMaxStackSize(stack);
    }


}
