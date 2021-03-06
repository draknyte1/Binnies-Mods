package binnie.craftgui.extratrees.dictionary;

import binnie.core.machines.Machine;
import binnie.craftgui.core.CraftGUI;
import binnie.craftgui.core.IWidget;
import binnie.craftgui.core.geometry.IArea;
import binnie.craftgui.core.geometry.IPoint;
import binnie.craftgui.core.geometry.Position;
import binnie.craftgui.events.EventMouse;
import binnie.craftgui.minecraft.Window;
import binnie.craftgui.minecraft.control.ControlProgressBase;
import binnie.craftgui.resource.Texture;
import binnie.craftgui.resource.minecraft.StandardTexture;
import binnie.extratrees.core.ExtraTreeTexture;
import binnie.extratrees.machines.Distillery;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraftforge.fluids.FluidStack;
import org.lwjgl.opengl.GL11;

public class ControlDistilleryProgress extends ControlProgressBase {
    static Texture DistilleryBase;
    static Texture DistilleryOverlay;
    static Texture LiquidFlow;
    static Texture Output;

    static {
        ControlDistilleryProgress.DistilleryBase = new StandardTexture(43, 0, 58, 66, ExtraTreeTexture.Gui);
        ControlDistilleryProgress.DistilleryOverlay = new StandardTexture(139, 0, 18, 66, ExtraTreeTexture.Gui);
        ControlDistilleryProgress.LiquidFlow = new StandardTexture(101, 0, 38, 66, ExtraTreeTexture.Gui);
        ControlDistilleryProgress.Output = new StandardTexture(68, 66, 17, 7, ExtraTreeTexture.Gui);
    }

    protected ControlDistilleryProgress(final IWidget parent, final float x, final float y) {
        super(parent, x, y, 58.0f, 66.0f);
        this.addSelfEventHandler(new EventMouse.Down.Handler() {
            @Override
            public void onEvent(final EventMouse.Down event) {
                int distillationLevel = -1;
                if (new IArea(45.0f, 8.0f, 19.0f, 11.0f).contains(ControlDistilleryProgress.this.getRelativeMousePosition())) {
                    distillationLevel = 0;
                } else if (new IArea(45.0f, 23.0f, 19.0f, 11.0f).contains(ControlDistilleryProgress.this.getRelativeMousePosition())) {
                    distillationLevel = 1;
                } else if (new IArea(45.0f, 38.0f, 19.0f, 11.0f).contains(ControlDistilleryProgress.this.getRelativeMousePosition())) {
                    distillationLevel = 2;
                }
                if (distillationLevel >= 0) {
                    final NBTTagCompound nbt = new NBTTagCompound();
                    nbt.setByte("i", (byte) distillationLevel);
                    Window.get(ControlDistilleryProgress.this.getWidget()).sendClientAction("still-level", nbt);
                }
            }
        });
    }

    @Override
    public void onRenderBackground() {
        CraftGUI.Render.texture(ControlDistilleryProgress.DistilleryBase, new IPoint(0.0f, 0.0f));
        CraftGUI.Render.texturePercentage(ControlDistilleryProgress.LiquidFlow, new IArea(18.0f, 0.0f, 38.0f, 66.0f), Position.Left, this.progress);
        final Distillery.ComponentDistilleryLogic component = Machine.getInterface(Distillery.ComponentDistilleryLogic.class, Window.get(this).getInventory());
        FluidStack stack = null;
        if (component != null) {
            stack = component.currentFluid;
        }
        if (stack != null) {
            for (int y = 0; y < 4; ++y) {
                this.renderFluid(stack, new IPoint(1.0f, 1 + y * 16));
            }
        }
    }

    @Override
    public void onRenderForeground() {
        final int level = Machine.getInterface(Distillery.ComponentDistilleryLogic.class, Window.get(this).getInventory()).level;
        CraftGUI.Render.texture(ControlDistilleryProgress.Output, new IPoint(47.0f, 14 + level * 15));
        CraftGUI.Render.texture(ControlDistilleryProgress.DistilleryOverlay, new IPoint(0.0f, 0.0f));
    }

    public void renderFluid(final FluidStack fluid, final IPoint pos) {
        final int hex = fluid.getFluid().getColor(fluid);
        final int r = (hex & 0xFF0000) >> 16;
        final int g = (hex & 0xFF00) >> 8;
        final int b = hex & 0xFF;
        final IIcon icon = fluid.getFluid().getIcon();
        GL11.glColor4f(r / 255.0f, g / 255.0f, b / 255.0f, 1.0f);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        CraftGUI.Render.iconBlock(pos, fluid.getFluid().getIcon());
        GL11.glDisable(3042);
    }
}
