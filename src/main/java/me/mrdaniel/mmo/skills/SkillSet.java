package me.mrdaniel.mmo.skills;

import me.mrdaniel.mmo.enums.SkillType;

public class SkillSet {
	
	private Skill mining;
	private Skill woodcutting;
	private Skill excavation;
	private Skill fishing;
	private Skill farming;
	private Skill acrobatics;
	private Skill taming;
	private Skill salvage;
	private Skill repair;
	
	public SkillSet(Skill mining, Skill woodcutting, Skill excavation, Skill fishing, Skill farming, Skill acrobatics, Skill taming, Skill salvage, Skill repair) {
		this.mining = mining;
		this.woodcutting = woodcutting;
		this.excavation = excavation;
		this.fishing = fishing;
		this.farming = farming;
		this.acrobatics = acrobatics;
		this.taming = taming;
		this.salvage = salvage;
		this.repair = repair;
	}
	
	public Skill getSkill(SkillType type) {
		if (type == SkillType.MINING) { return mining; }
		else if (type == SkillType.WOODCUTTING) { return woodcutting; }
		else if (type == SkillType.EXCAVATION) { return excavation; }
		else if (type == SkillType.FISHING) { return fishing; }
		else if (type == SkillType.FARMING) { return farming; }
		else if (type == SkillType.ACROBATICS) { return acrobatics; }
		else if (type == SkillType.TAMING) { return taming; }
		else if (type == SkillType.SALVAGE) { return salvage; }
		else if (type == SkillType.REPAIR) { return repair; }
		else return null;
	}
	
	public void setSkill(SkillType type, Skill skill) {
		if (type == SkillType.MINING) { mining = skill; }
		else if (type == SkillType.WOODCUTTING) { woodcutting = skill; }
		else if (type == SkillType.EXCAVATION) { excavation = skill; }
		else if (type == SkillType.FISHING) { fishing = skill; }
		else if (type == SkillType.FARMING) { farming = skill; }
		else if (type == SkillType.ACROBATICS) { acrobatics = skill; }
		else if (type == SkillType.TAMING) { taming = skill; }
		else if (type == SkillType.SALVAGE) { salvage = skill; }
		else if (type == SkillType.REPAIR) { repair = skill; }
	}
	
	public static SkillSet getEmpty() { return new SkillSet(new Skill(0,0), new Skill(0,0), new Skill(0,0), new Skill(0,0), new Skill(0,0), new Skill(0,0), new Skill(0,0), new Skill(0,0), new Skill(0,0)); }
	
	public boolean addExp(SkillType type, int exp) {
		Skill skill = getSkill(type);
		
		int newExp = exp + skill.exp;
		int nextLvl = expTillNextLevel(skill.level);
		
		if (newExp >= nextLvl) { skill.level = skill.level+1; skill.exp = newExp - nextLvl; return true; }
		else { skill.exp = newExp; return false; }
	}
	public int expTillNextLevel(int level) { return 83 * level + 500; }
	public int[][] serialize() {
		int[][] sRaw = new int[SkillType.MAXNUMBER][2];
		for (SkillType type : SkillType.values()) {
			Skill s = getSkill(type);
			int id = type.id;
			sRaw[id][0] = s.level; sRaw[id][1] = s.exp;
		}
		return sRaw;
	}
	public static SkillSet deserialize(int[][] sRaw) {
		if (sRaw.length != SkillType.MAXNUMBER) { return update(sRaw); }
		
		Skill mining = new Skill(sRaw[SkillType.MINING.id][0], sRaw[SkillType.MINING.id][1]);
		Skill woodcutting = new Skill(sRaw[SkillType.WOODCUTTING.id][0], sRaw[SkillType.WOODCUTTING.id][1]);
		Skill excavation = new Skill(sRaw[SkillType.EXCAVATION.id][0], sRaw[SkillType.EXCAVATION.id][1]);
		Skill fishing = new Skill(sRaw[SkillType.FISHING.id][0], sRaw[SkillType.FISHING.id][1]);
		Skill farming = new Skill(sRaw[SkillType.FARMING.id][0], sRaw[SkillType.FARMING.id][1]);
		Skill acrobatics = new Skill(sRaw[SkillType.ACROBATICS.id][0], sRaw[SkillType.ACROBATICS.id][1]);
		Skill taming = new Skill(sRaw[SkillType.TAMING.id][0], sRaw[SkillType.TAMING.id][1]);
		Skill salvage = new Skill(sRaw[SkillType.SALVAGE.id][0], sRaw[SkillType.SALVAGE.id][1]);
		Skill repair = new Skill(sRaw[SkillType.REPAIR.id][0], sRaw[SkillType.REPAIR.id][1]);
		
		return new SkillSet(mining, woodcutting, excavation, fishing, farming, acrobatics, taming, salvage, repair);
	}
	public static SkillSet update(int[][] sRawOld) {
		int[][] sRaw = new int[SkillType.MAXNUMBER][2];
		
		int lowest = (sRaw.length > sRawOld.length) ? sRawOld.length : sRaw.length;
		
		for (int i = 0; i < lowest; i++) {
			sRaw[i][0] = sRawOld[i][0]; sRaw[i][1] = sRawOld[i][1];
		}
		return deserialize(sRaw);
	}
}