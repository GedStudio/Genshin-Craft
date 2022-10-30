package net.deechael.genshin.api.character;

import net.deechael.genshin.api.element.ElementType;

import java.util.List;

public interface Character {

    String getName();

    String getLocalizedName(String locale);

    int getStar();

    ElementType getElementType();

    int getLevel();

    int getExp();

    Attribute getAttribute();

    List<Talent> getTalent();

}
