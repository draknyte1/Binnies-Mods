package binnie.extratrees.machines;

import binnie.core.BinnieCore;
import binnie.core.IInitializable;
import binnie.core.Mods;
import binnie.core.machines.MachineGroup;
import binnie.extratrees.ExtraTrees;
import binnie.extratrees.item.ExtraTreeItems;
import cpw.mods.fml.common.registry.GameRegistry;
import forestry.api.core.Tabs;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class ModuleMachine implements IInitializable {
    @Override
    public void preInit() {
        final MachineGroup machineGroup = new MachineGroup(ExtraTrees.instance, "machine", "machine", ExtraTreeMachine.values());
        machineGroup.setCreativeTab(Tabs.tabArboriculture);
        ExtraTrees.blockMachine = machineGroup.getBlock();
        BinnieCore.proxy.registerTileEntity(TileEntityNursery.class, "binnie.tile.nursery", BinnieCore.proxy.createObject("binnie.core.machines.RendererMachine"));
    }

    @Override
    public void init() {
    }

    @Override
    public void postInit() {
        GameRegistry.addRecipe(new ShapedOreRecipe(ExtraTreeMachine.Lumbermill.get(1), "gAg", "GsG", "gPg", 'G', Blocks.glass, 'g', ExtraTreeItems.ProvenGear.get(1), 'A', Items.iron_axe, 's', Mods.Forestry.stack("sturdyMachine"), 'P', "gearBronze"));
        GameRegistry.addRecipe(new ShapedOreRecipe(ExtraTreeMachine.Press.get(1), "iGi", "tSt", "tPt", 'i', "ingotIron", 'G', Blocks.glass, 't', "ingotTin", 'S', Mods.Forestry.stack("sturdyMachine"), 'P', "gearBronze"));
        GameRegistry.addRecipe(new ShapedOreRecipe(ExtraTreeMachine.Brewery.get(1), "bGb", "iSi", "bPb", 'i', "ingotIron", 'G', Blocks.glass, 'b', "gearBronze", 'S', Mods.Forestry.stack("sturdyMachine"), 'P', "gearBronze"));
        GameRegistry.addRecipe(new ShapedOreRecipe(ExtraTreeMachine.Distillery.get(1), "rGr", "iSi", "rPr", 'i', "ingotIron", 'G', Blocks.glass, 'r', "dustRedstone", 'S', Mods.Forestry.stack("sturdyMachine"), 'P', "gearBronze"));
        CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(ExtraTreeMachine.Woodworker.get(1), "wGw", "GsG", "ggg", 'G', Blocks.glass, 'g', ExtraTreeItems.ProvenGear.get(1), 'w', Blocks.planks, 's', Mods.Forestry.stack("impregnatedCasing")));
        CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(ExtraTreeMachine.Panelworker.get(1), "wGw", "GsG", "ggg", 'G', Blocks.glass, 'g', ExtraTreeItems.ProvenGear.get(1), 'w', Blocks.wooden_slab, 's', Mods.Forestry.stack("impregnatedCasing")));
        CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(ExtraTreeMachine.Glassworker.get(1), "wGw", "GsG", "ggg", 'G', Blocks.glass, 'g', ExtraTreeItems.ProvenGear.get(1), 'w', Blocks.glass, 's', Mods.Forestry.stack("impregnatedCasing")));
        CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(ExtraTreeMachine.Tileworker.get(1), "wGw", "GsG", "ggg", 'G', Blocks.glass, 'g', ExtraTreeItems.ProvenGear.get(1), 'w', Items.clay_ball, 's', Mods.Forestry.stack("impregnatedCasing")));
    }
}
