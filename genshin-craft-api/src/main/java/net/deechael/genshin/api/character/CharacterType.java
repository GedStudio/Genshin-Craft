package net.deechael.genshin.api.character;

import net.deechael.genshin.api.element.ElementType;

public enum CharacterType {
    // Main Character
    TRAVELLER(5, ElementType.NONE),

    // Story Characters
    ALBEDO(5, ElementType.GEO),
    ALOY(5, ElementType.CRYO),
    AMBER(4, ElementType.PYRO),
    ARATAKI_ITTO(5, ElementType.GEO),
    BARBARA(4, ElementType.HYDRO),
    BEIDOU(4, ElementType.ELECTRO),
    BENNETT(4, ElementType.PYRO),
    CANDACE(4, ElementType.HYDRO),
    CHONGYUN(4, ElementType.CRYO),
    COLLEI(4, ElementType.DENDRO),
    CYNO(5, ElementType.ELECTRO),
    DILUC(5, ElementType.PYRO),
    DIONA(4, ElementType.CRYO),
    DORI(4, ElementType.ELECTRO),
    EULA(5, ElementType.CRYO),
    FISCHL(4, ElementType.ELECTRO),
    GANYU(4, ElementType.CRYO),
    GOROU(4, ElementType.GEO),
    HU_TAO(5, ElementType.PYRO),
    JEAN(5, ElementType.ANEMO),
    KAEDEHARA_KAZUHA(5, ElementType.ANEMO),
    KAEYA(4, ElementType.CRYO),
    KAMISATO_AYAKA(5, ElementType.CRYO),
    KAMISATO_AYATO(5, ElementType.HYDRO),
    KEQING(5, ElementType.ELECTRO),
    KLEE(5, ElementType.PYRO),
    KUJOU_SARA(4, ElementType.ELECTRO),
    KUKI_SHINOBU(4, ElementType.ELECTRO),
    LISA(4, ElementType.ELECTRO),
    MONA(5, ElementType.HYDRO),
    NAHIDA(5, ElementType.DENDRO), // 3.2
    NILOU(5, ElementType.HYDRO),
    NINGGUANG(5, ElementType.GEO),
    NOELLE(4, ElementType.GEO),
    QIQI(5, ElementType.CRYO),
    RAIDEN_SHOGUN(5, ElementType.ELECTRO),
    RAZOR(4, ElementType.ELECTRO),
    ROSARIA(4, ElementType.CRYO),
    SANGONOMIYA_KOKOMI(5, ElementType.HYDRO),
    SAYU(4, ElementType.ANEMO),
    SHENHE(5, ElementType.CRYO),
    SHIKANOIN_HEIZOU(4, ElementType.ANEMO),
    SUCROSE(4, ElementType.ANEMO),
    TARTAGLIA(5, ElementType.HYDRO),
    THOMA(4, ElementType.PYRO),
    TIGHNARI(5, ElementType.DENDRO),
    VENTI(5, ElementType.ANEMO),
    XIANGLING(4, ElementType.PYRO),
    XIAO(5, ElementType.ANEMO),
    XINGQIU(4, ElementType.HYDRO),
    XINYAN(4, ElementType.PYRO),
    YAE_MIKO(5, ElementType.ELECTRO),
    YANFEI(4, ElementType.PYRO),
    YELAN(5, ElementType.HYDRO),
    YOIMIYA(5, ElementType.PYRO),
    YUN_JIN(4, ElementType.GEO),
    ZHONGLI(5, ElementType.GEO);

    private final int star;
    private final ElementType elementType;

    CharacterType(int star, ElementType elementType) {
        this.star = star;
        this.elementType = elementType;
    }

    public int getStar() {
        return star;
    }

    public ElementType getElementType() {
        return elementType;
    }

}
