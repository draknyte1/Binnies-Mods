package binnie.extrabees.gui.database;

import binnie.Binnie;
import binnie.core.AbstractMod;
import binnie.craftgui.minecraft.Window;
import binnie.craftgui.mod.database.*;
import binnie.extrabees.ExtraBees;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayer;

public class WindowApiaristDatabase extends WindowAbstractDatabase {
    public WindowApiaristDatabase(final EntityPlayer player, final Side side, final boolean nei) {
        super(player, side, nei, Binnie.Genetics.beeBreedingSystem, 110.0f);
    }

    public static Window create(final EntityPlayer player, final Side side, final boolean nei) {
        return new WindowApiaristDatabase(player, side, nei);
    }

    @Override
    protected void addTabs() {
        new PageSpeciesOverview(this.getInfoPages(Mode.Species), new DatabaseTab(ExtraBees.instance, "species.overview", 0));
        new PageSpeciesClassification(this.getInfoPages(Mode.Species), new DatabaseTab(ExtraBees.instance, "species.classification", 0));
        new PageSpeciesGenome(this.getInfoPages(Mode.Species), new DatabaseTab(ExtraBees.instance, "species.genome", 0));
        new PageSpeciesProducts(this.getInfoPages(Mode.Species), new DatabaseTab(ExtraBees.instance, "species.products", 0));
        new PageSpeciesClimate(this.getInfoPages(Mode.Species), new DatabaseTab(ExtraBees.instance, "species.climate", 0));
        new PageSpeciesResultant(this.getInfoPages(Mode.Species), new DatabaseTab(ExtraBees.instance, "species.resultant", 0));
        new PageSpeciesMutations(this.getInfoPages(Mode.Species), new DatabaseTab(ExtraBees.instance, "species.further", 0));
        new PageBranchOverview(this.getInfoPages(Mode.Branches), new DatabaseTab(ExtraBees.instance, "branches.overview", 0));
        new PageBranchSpecies(this.getInfoPages(Mode.Branches), new DatabaseTab(ExtraBees.instance, "branches.species", 0));
        new PageBreeder(this.getInfoPages(Mode.Breeder), this.getUsername(), new DatabaseTab(ExtraBees.instance, "breeder", 0));
    }

    public AbstractMod getMod() {
        return ExtraBees.instance;
    }

    public String getName() {
        return "Database";
    }

    enum SpeciesTab {
        Overview(255),
        Genome(16776960),
        Productivity(65535),
        Climate(16711680),
        ResultantMutations(16711935),
        FurtherMutations(65280);

        public int colour;

        SpeciesTab(final int colour) {
            this.colour = colour;
        }
    }

    enum BranchesTab {
        Overview(255),
        Species(16711680);

        public int colour;

        BranchesTab(final int colour) {
            this.colour = colour;
        }
    }
}
