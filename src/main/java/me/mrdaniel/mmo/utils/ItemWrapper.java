package me.mrdaniel.mmo.utils;

import org.spongepowered.api.item.ItemType;

public class ItemWrapper {
	
	public ItemType type;
	public int maxDura;
	public int amount;
	public int exp;
	
	public ItemWrapper(ItemType type, int maxDura, int amount, int exp) { this.type = type; this.maxDura = maxDura; this.amount = amount; this.exp = exp; }
}