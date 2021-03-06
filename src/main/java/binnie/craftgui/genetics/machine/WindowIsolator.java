package binnie.craftgui.genetics.machine;

import binnie.core.AbstractMod;
import binnie.craftgui.core.geometry.Position;
import binnie.craftgui.minecraft.GUIIcon;
import binnie.craftgui.minecraft.Window;
import binnie.craftgui.minecraft.control.*;
import binnie.craftgui.resource.Texture;
import binnie.craftgui.resource.minecraft.StandardTexture;
import binnie.extrabees.core.ExtraBeeTexture;
import binnie.genetics.Genetics;
import binnie.genetics.machine.Isolator;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;

public class WindowIsolator extends WindowMachine {
    static Texture ProgressBase;
    static Texture Progress;

    static {
        WindowIsolator.ProgressBase = new StandardTexture(0, 218, 142, 17, ExtraBeeTexture.GUIProgress.getTexture());
        WindowIsolator.Progress = new StandardTexture(0, 201, 142, 17, ExtraBeeTexture.GUIProgress.getTexture());
    }

    public WindowIsolator(final EntityPlayer player, final IInventory inventory, final Side side) {
        super(330, 208, player, inventory, side);
    }

    public static Window create(final EntityPlayer player, final IInventory inventory, final Side side) {
        return new WindowIsolator(player, inventory, side);
    }

    @Override
    public void initialiseClient() {
        this.setTitle("Isolator");
        int x = 16;
        final int y = 32;
        new ControlLiquidTank(this, x, y).setTankID(0);
        x += 26;
        new ControlSlotArray(this, x, y + 3, 1, 3).create(Isolator.slotReserve);
        x += 20;
        new ControlIconDisplay(this, x, y + 3 + 1, GUIIcon.ArrowRight.getIcon());
        x += 18;
        new ControlSlot(this, x, y + 3).assign(5);
        new ControlSlot(this, x, y + 36 + 3).assign(0);
        new ControlSlotCharge(this, x + 18 + 2, y + 36 + 3, 0).setColour(15722671);
        x += 18;
        new ControlProgress(this, x, y + 3, WindowIsolator.ProgressBase, WindowIsolator.Progress, Position.Left);
        x += 142;
        new ControlSlot(this, x, y + 3).assign(6);
        new ControlSlot(this, x, y + 3 + 36).assign(1);
        new ControlIconDisplay(this, x + 1, y + 3 + 19, GUIIcon.ArrowUp.getIcon());
        x += 20;
        new ControlIconDisplay(this, x, y + 3 + 1, GUIIcon.ArrowRight.getIcon());
        x += 18;
        new ControlSlotArray(this, x, y + 3, 2, 3).create(Isolator.slotFinished);
        new ControlEnergyBar(this, 260, 130, 16, 60, Position.Bottom);
        new ControlErrorState(this, 153.0f, 81.0f);
        new ControlPlayerInventory(this);
    }

    @Override
    public String getTitle() {
        return "Incubator";
    }

    @Override
    protected AbstractMod getMod() {
        return Genetics.instance;
    }

    @Override
    protected String getName() {
        return "Isolator";
    }
}
