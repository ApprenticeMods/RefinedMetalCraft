package com.apprenticemods.refinedmetalcraft.compatibility.jei;

import com.apprenticemods.refinedmetalcraft.RefinedMetalCraft;
import com.apprenticemods.refinedmetalcraft.setup.ModItems;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public class JewelingStationRecipeCategory implements IRecipeCategory<JewelingStationRecipeWrapper> {
	public static final RecipeType<JewelingStationRecipeWrapper> RECIPE_TYPE = RecipeType.create(RefinedMetalCraft.MODID, "jeweling_recipes", JewelingStationRecipeWrapper.class);
	private final IGuiHelper guiHelper;
	private final IDrawableStatic background;
	private final IDrawable icon;
	private final Component localizedName;

	public JewelingStationRecipeCategory(IGuiHelper guiHelper) {
		this.guiHelper = guiHelper;
		this.background = guiHelper.createBlankDrawable(155, 40);
		this.icon = guiHelper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModItems.JEWELINGSTATION_BLOCK_ITEM.get()));
		this.localizedName = Component.translatable("container.refinedmetalcraft.jewelingstation");
	}

	@Override
	public RecipeType<JewelingStationRecipeWrapper> getRecipeType() {
		return RECIPE_TYPE;
	}

	@Override
	public Component getTitle() {
		return this.localizedName;
	}

	@Override
	public @Nullable IDrawable getIcon() {
		return this.icon;
	}

	@Override
	public int getWidth() {
		return 155;
	}

	@Override
	public int getHeight() {
		return 40;
	}

	@Override
	public void draw(JewelingStationRecipeWrapper recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
		recipe.draw(recipeSlotsView, guiGraphics, mouseX, mouseY, this.guiHelper);
	}

	@Override
	public void setRecipe(IRecipeLayoutBuilder builder, JewelingStationRecipeWrapper recipe, IFocusGroup focuses) {
		recipe.setRecipe(builder, focuses);
	}
}