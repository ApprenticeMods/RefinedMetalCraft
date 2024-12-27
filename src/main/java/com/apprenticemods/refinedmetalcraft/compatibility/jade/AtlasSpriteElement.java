package com.apprenticemods.refinedmetalcraft.compatibility.jade;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec2;
import snownee.jade.api.ui.Element;
import snownee.jade.overlay.DisplayHelper;

public class AtlasSpriteElement extends Element {
	private final ResourceLocation sprite;
	private final int width;
	private final int height;
	private final int textureX;
	private final int textureY;
	private final int textureWidth;
	private final int textureHeight;
	private float opacity = 1.0f;

	public AtlasSpriteElement(ResourceLocation sprite, int width, int height, int textureX, int textureY) {
		this.sprite = sprite;
		this.width = width;
		this.height = height;
		this.textureX = textureX;
		this.textureY = textureY;
		this.textureWidth = width;
		this.textureHeight = height;
	}

	public AtlasSpriteElement setOpacity(float opacity) {
		this.opacity = opacity;
		return this;
	}

	public Vec2 getSize() {
		return new Vec2((float)this.width, (float)this.height);
	}

	public void render(GuiGraphics guiGraphics, float x, float y, float maxX, float maxY) {
		//IDisplayHelper.get().blitSprite(guiGraphics, this.sprite, this.width, this.height, 0, 0, Math.round(x), Math.round(y), Math.round(this.getCachedSize().x), Math.round(this.getCachedSize().y));
		RenderSystem.enableBlend();
		guiGraphics.setColor(1.0F, 1.0F, 1.0F, this.opacity);
		guiGraphics.blit(this.sprite, Math.round(x), Math.round(y), textureX, textureY, textureWidth, textureHeight);
		guiGraphics.setColor(1.0F, 1.0F, 1.0F, 1.0F);
	}
}