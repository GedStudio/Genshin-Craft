package net.deechael.genshin.api.element;

public enum ElementReactionType {

    VAPORIZE(ElementType.PYRO, new ElementType[] {ElementType.HYDRO}, 2.0f),
    VAPORIZE_REVERSE(ElementType.HYDRO, new ElementType[] {ElementType.PYRO}, 1.5f),
    OVERLOADED(ElementType.PYRO, new ElementType[] {ElementType.ELECTRO}, 1.0f),
    MELT(ElementType.CRYO, new ElementType[] {ElementType.PYRO}, 2.0f),
    MELT_REVERSE(ElementType.PYRO, new ElementType[] {ElementType.CRYO}, 1.5f),
    BURNING(ElementType.PYRO, new ElementType[] {ElementType.DENDRO}, 1.0f),
    ELECTRO_CHARGED(ElementType.ELECTRO, new ElementType[] {ElementType.HYDRO}, 1.0f),
    SUPERCONDUCT(ElementType.CRYO, new ElementType[] {ElementType.ELECTRO}, 1.0f),
    QUICKEN(ElementType.DENDRO, new ElementType[] {ElementType.ELECTRO}, 1.0f),
    FROZEN(ElementType.CRYO, new ElementType[] {ElementType.HYDRO}, 1.0f),
    BLOOM(ElementType.DENDRO, new ElementType[] {ElementType.HYDRO}, 1.0f),
    SWIRL(ElementType.ANEMO, new ElementType[] {ElementType.PYRO, ElementType.HYDRO, ElementType.ELECTRO, ElementType.CRYO}, 1.0f),
    CRYSTALLIZE(ElementType.GEO, new ElementType[] {ElementType.PYRO, ElementType.HYDRO, ElementType.ELECTRO, ElementType.CRYO}, 1.0f),
    IMMUNE(null, null, 0.0f);

    private final ElementType first;
    private final ElementType[] second;

    ElementReactionType(ElementType first, ElementType[] second, float damageTimes) {
        this.first = first;
        this.second = second;
    }

}
