package com.apprenticemods.refinedmetalcraft.compatibility.jei;

import com.apprenticemods.refinedmetalcraft.base.gui.tooltip.ItemStackTooltipComponent;
import com.apprenticemods.refinedmetalcraft.recipes.JewelingStationRecipe;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JewelingStationRecipeWrapper {
	JewelingStationRecipe recipe;

	public JewelingStationRecipeWrapper(JewelingStationRecipe recipe) {
		this.recipe = recipe;
	}

	public void setRecipe(IRecipeLayoutBuilder builder, IFocusGroup focuses) {
		builder.addInputSlot(19, 1).setSlotName("back").addItemStacks(Arrays.asList(recipe.getBack().getItems()));
		builder.addInputSlot(1, 19).setSlotName("left").addItemStacks(Arrays.asList(recipe.getLeft().getItems()));
		builder.addInputSlot(19, 19).setSlotName("front").addItemStacks(Arrays.asList(recipe.getFront().getItems()));
		builder.addInputSlot(37, 19).setSlotName("right").addItemStacks(Arrays.asList(recipe.getRight().getItems()));

		builder.addOutputSlot(133, 10).setSlotName("output").addItemStack(recipe.getResultItem());

		List<ItemStack> allTools = new ArrayList<>();
		for(Ingredient step : recipe.getToolSteps()) {
			var toolsForStep = step.getItems();
			allTools.addAll(Arrays.asList(toolsForStep));
		}

		int stepX = 78;
		builder.addInputSlot(stepX, 10).setSlotName("tools").addRichTooltipCallback((recipeSlotView, tooltip) -> {
			tooltip.clear();
			tooltip.add(Component.translatable("container.refinedmetalcraft.jeweling_station.required_tools"));
			for(Ingredient step : recipe.getToolSteps()) {
				if(step.hasNoItems()) {
					continue;
				}
				tooltip.add(new ItemStackTooltipComponent(step.getItems()[0]));
			}

		}).addItemStacks(allTools);
	}

	public void draw(IRecipeSlotsView view, GuiGraphics stack, double mouseX, double mouseY, IGuiHelper guiHelper) {
		final IDrawableStatic slot = guiHelper.getSlotDrawable();
		slot.draw(stack, 18, 0);
		slot.draw(stack, 0, 18);
		slot.draw(stack, 18, 18);
		slot.draw(stack, 36, 18);

		guiHelper.getRecipePlusSign().draw(stack, 60, 12);
		guiHelper.createAnimatedRecipeArrow(20).draw(stack, 100, 10);

		guiHelper.getOutputSlot().draw(stack, 128, 5);
	}
}