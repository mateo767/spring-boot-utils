package io.github.mateo767.springbootutils.integration;

import java.io.Serializable;

public class SomeObject implements Serializable {
    private static final long serialVersionUID = 8545049709284423835L;

    private final String sField;
    private final int iField;

    public SomeObject(String sField, int iField) {
        this.sField = sField;
        this.iField = iField;
    }

    public String getSField() {
        return sField;
    }

    public int getIField() {
        return iField;
    }

    @Override
    public String toString() {
        return "SomeObject(" +
                "sField=" + sField +
                ", iField=" + iField +
                ')';
    }
}