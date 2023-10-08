package wily.betterfurnaces.util;

import net.minecraft.core.NonNullList;
import net.minecraft.world.Container;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class RecipeUtil {
    public static <T extends Recipe<Container>> List<T> getRecipes(RecipeManager manager, RecipeType<?> type) {
        Collection<RecipeHolder<?>> recipes = manager.getRecipes();
        return (List)recipes.stream().filter((iRecipe) -> {
            return iRecipe.value().getType() == type;
        }).map(RecipeHolder::value).collect(Collectors.toList());
    }

}