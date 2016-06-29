package miscutil.gregtech.api.interfaces.internal;

import miscutil.gregtech.api.enums.GregtechOrePrefixes;
import miscutil.gregtech.api.enums.GregtechOrePrefixes.GT_Materials;
import net.minecraft.item.ItemStack;

public interface Interface_OreRecipeRegistrator {
    /**
     * Contains a Code Fragment, used in the OrePrefix to register Recipes. Better than using a switch/case, like I did before.
     *
     * @param aPrefix   always != null
     * @param aMaterial always != null, and can be == _NULL if the Prefix is Self Referencing or not Material based!
     * @param aStack    always != null
     */
    public void registerOre(GregtechOrePrefixes aPrefix, GT_Materials aMaterial, String aOreDictName, String aModName, ItemStack aStack);
}