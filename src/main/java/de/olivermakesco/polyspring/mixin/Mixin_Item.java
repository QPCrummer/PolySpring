package de.olivermakesco.polyspring.mixin;

import de.olivermakesco.polyspring.impl.Duck_ArmorInfo;
import de.olivermakesco.polyspring.impl.Duck_ToolMaterialInterface;
import de.olivermakesco.polyspring.impl.ItemType;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.level.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Item.Properties.class)
public class Mixin_Item implements Duck_ToolMaterialInterface, Duck_ArmorInfo {
    private ToolMaterial material;
    private ArmorMaterial armorMaterial;
    private ArmorType type;
    private ItemType itemType;

    @Inject(method = "tool", at = @At("HEAD"))
    private void polyspring$readToolMaterial(ToolMaterial toolMaterial, TagKey<Block> tagKey, float f, float g, float h, CallbackInfoReturnable<Item.Properties> cir) {
        this.material = toolMaterial;
        if (this.itemType == null) {
            this.itemType = ItemType.ToolOther;
        }
    }

    @Inject(method = "sword", at = @At("HEAD"))
    private void polyspring$readSwordMaterial(ToolMaterial toolMaterial, float f, float g, CallbackInfoReturnable<Item.Properties> cir) {
        this.material = toolMaterial;
        this.itemType = ItemType.Sword;
    }

    @Inject(method = "pickaxe", at = @At("HEAD"))
    private void polyspring$readPickData(ToolMaterial toolMaterial, float f, float g, CallbackInfoReturnable<Item.Properties> cir) {
        this.itemType = ItemType.Pickaxe;
    }

    @Inject(method = "axe", at = @At("HEAD"))
    private void polyspring$readAxeData(ToolMaterial toolMaterial, float f, float g, CallbackInfoReturnable<Item.Properties> cir) {
        this.itemType = ItemType.Axe;
    }

    @Inject(method = "hoe", at = @At("HEAD"))
    private void polyspring$readHoeData(ToolMaterial toolMaterial, float f, float g, CallbackInfoReturnable<Item.Properties> cir) {
        this.itemType = ItemType.Hoe;
    }

    @Inject(method = "shovel", at = @At("HEAD"))
    private void polyspring$readShovelData(ToolMaterial toolMaterial, float f, float g, CallbackInfoReturnable<Item.Properties> cir) {
        this.itemType = ItemType.Shovel;
    }

    @Inject(method = "humanoidArmor", at = @At("HEAD"))
    private void polyspring$readArmorData(ArmorMaterial armorMaterial, ArmorType armorType, CallbackInfoReturnable<Item.Properties> cir) {
        this.armorMaterial = armorMaterial;
        this.type = armorType;
        this.itemType = ItemType.Armor;
    }

    @Inject(method = "wolfArmor", at = @At("HEAD"))
    private void polyspring$readWolfArmorData(ArmorMaterial armorMaterial, CallbackInfoReturnable<Item.Properties> cir) {
        this.armorMaterial = armorMaterial;
        this.itemType = ItemType.WolfArmor;
    }

    @Inject(method = "horseArmor", at = @At("HEAD"))
    private void polyspring$readHorseArmorData(ArmorMaterial armorMaterial, CallbackInfoReturnable<Item.Properties> cir) {
        this.armorMaterial = armorMaterial;
        this.itemType = ItemType.HorseArmor;
    }

    @Override
    public ToolMaterial polyspring$getToolMaterial() {
        return this.material;
    }

    @Override
    public ItemType polyspring$getItemType() {
        return this.itemType;
    }

    @Override
    public ArmorType polyspring$getArmorType() {
        return this.type;
    }

    @Override
    public ArmorMaterial polyspring$getArmorMaterial() {
        return this.armorMaterial;
    }
}
