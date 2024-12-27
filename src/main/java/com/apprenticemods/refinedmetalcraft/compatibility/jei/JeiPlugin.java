package com.apprenticemods.refinedmetalcraft.compatibility.jei;

import com.apprenticemods.refinedmetalcraft.RefinedMetalCraft;
import com.apprenticemods.refinedmetalcraft.blocks.JewelingStationScreen;
import com.apprenticemods.refinedmetalcraft.setup.Cache;
import com.apprenticemods.refinedmetalcraft.setup.ModItems;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;

import java.util.stream.Collectors;

@mezz.jei.api.JeiPlugin
public class JeiPlugin implements IModPlugin {
	private static final ResourceLocation PLUGIN_ID = ResourceLocation.fromNamespaceAndPath(RefinedMetalCraft.MODID, "jei_plugin");
	@Override
	public ResourceLocation getPluginUid() {
		return PLUGIN_ID;
	}

	@Override
	public void registerCategories(IRecipeCategoryRegistration registration) {
		final IGuiHelper gui = registration.getJeiHelpers().getGuiHelper();
		registration.addRecipeCategories(new JewelingStationRecipeCategory(gui));
	}

	@Override
	public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
		registration.addRecipeCatalyst(new ItemStack(ModItems.JEWELINGSTATION_BLOCK_ITEM.get()), JewelingStationRecipeCategory.RECIPE_TYPE);
	}

	@Override
	public void registerRecipes(IRecipeRegistration registration) {
		if(Cache.JEWELING_RECIPES == null) {
			return;
		}

		var wrappedJewelingRecipes = Cache.JEWELING_RECIPES.get().stream().map(RecipeHolder::value).map(JewelingStationRecipeWrapper::new).collect(Collectors.toList());
		registration.addRecipes(JewelingStationRecipeCategory.RECIPE_TYPE, wrappedJewelingRecipes);
	}

	@Override
	public void registerGuiHandlers(IGuiHandlerRegistration registration) {
		registration.addRecipeClickArea(JewelingStationScreen.class, 95, 37, 22, 16, JewelingStationRecipeCategory.RECIPE_TYPE);
	}
}