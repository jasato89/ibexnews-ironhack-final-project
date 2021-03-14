package com.jasato.loginservice.enums;

public enum Stocks {
    ACCIONA("AJ3.FRK"),
    ACERINOX("ACE1.FRK"),
    ACS("OCI1.FRK"),
    AENA("A44.FRK"),
    ALMIRALL("E2Z.FRK"),
    AMADEUS("AMADF"),
    ARCELORMITTAL("ARRD.FRK"),
    BANKIA("FV02.FRK"),
    BBVA("BBVA.FRK"),
    CAIXABANK("48CA.FRK"),
    CELLNEX_TELECOM("CLNXF"),
    CIE_AUTOMOTIVE("CAD.FRK"),
    ENAGAS("ENGGF"),
    ENDESA("ENA.FRK"),
    FERROVIAL("UFG.FRK"),
    GRIFOLS("G0FB.FRK"),
    IAG("IAG.LON"),
    IBERDROLA("IBE1.FRK"),
    INDITEX("ITXN"),
    INDRA("IDA.FRK"),
    MAPFRE("CMAB.FRK"),
    MELIA_HOTELS("SMIZF"),
    MERLIN_PROPERTIES("MEQA.FRK"),
    NATURGY("GAN.FRK"),
    PHARMA_MAR("PMRA.FRK"),
    RED_ELECTRICA("RDEIF"),
    REPSOL("REPA.FRK"),
    SANTANDER("SAN"),
    SIEMENS_GAMESA("GTQ1.FRK"),
    SOLARIA("SEYMF"),
    TELEFONICA("TEF"),
    VISCOFAN("VIS.FRK");

    private final String symbol;

    private Stocks(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return this.symbol;
    }

}
