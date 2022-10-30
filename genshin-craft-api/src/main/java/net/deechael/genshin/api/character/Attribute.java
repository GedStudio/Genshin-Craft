package net.deechael.genshin.api.character;

public interface Attribute {

    int getMaxHealth();

    int getHealth();

    int getAttack();

    int getDefense();

    int getElementalMastery();

    int getStamina();

    float getCriticalRate();

    float getCriticalDamage();

    float getHealingBonus();

    float getIncomingHealingBonus();

    float getEnergyRecharge();

    float getShieldStrength();

    AttributeDamage getDamageBonus();

    AttributeResistance getResistance();

}
